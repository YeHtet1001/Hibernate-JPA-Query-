package com.jdc.query.test;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.jdc.query.entity.Department_;
import com.jdc.query.entity.Employee;
import com.jdc.query.entity.Employee_;
import com.jdc.query.entity.dto.EmployeeDto;

public class E_ProjectionWithPredicateAndJoinQueryTest extends JpaEmfFactory {
	
	@ParameterizedTest
	@CsvSource("Sale")
	@Order(1)
	void JpqlQueryTest(String name) {
		
		var query = em.createQuery( """
			select new com.jdc.query.entity.dto.EmployeeDto( e.name , e.dob , e.department.name )
			from Employee e
			where e.department.name = :name""",EmployeeDto.class);
		query.setParameter("name", name);
		
		System.out.println("Employee List : " + query.getResultList() );
		
		 
	}
	
	
	@ParameterizedTest
	@CsvSource("Sale")
	@Order(2)
	void findByDepNameWithCriteria( String name ) {
		
		
		var cb = em.getCriteriaBuilder();
		
		var cq = cb.createQuery(EmployeeDto.class);
		
		var root = cq.from (Employee.class);
		
		var predicate = cb.equal(root.get(Employee_.department).get(Department_.name),name);
		
		cq.multiselect(
				root.get(Employee_.name),
				root.get(Employee_.dob),
				root.get(Employee_.department).get(Department_.name)
				);
		
		cq.where(predicate);
		
		var query = em.createQuery(cq);
		
		System.out.println("Employee List : " + query.getResultList() );
		
	}

}
