package com.jdc.query.test;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.jdc.query.entity.Department;
import com.jdc.query.entity.Department_;
import com.jdc.query.entity.Employee;
import com.jdc.query.entity.Employee_;

public class D_FindCountWithPredicateAndJoinQuery extends JpaEmfFactory{
	
	@ParameterizedTest
	@CsvSource("IT")
	@Order(1)
	void findEmployeeCountByDepartmentNameTest( String name ) {
		
		var cb = em.getCriteriaBuilder();
		
		var cq = cb.createQuery(Long.class);
		
		var root = cq.from(Employee.class);
		
		cq.select( cb.count( root.get( Employee_.id ) ) );
		
		var predicate = cb.equal(root.get(Employee_.department).get(Department_.name), name);
		
		cq.where(predicate);
		
		var query = em.createQuery(cq);
		
		System.out.println( "count : " + query.getSingleResult() );
		
	}
	

	@ParameterizedTest
	@CsvSource("j")
	@Order(2)
	void findDepartmentByEmployeeNameTest( String name ) {
		
		var cb = em.getCriteriaBuilder();
		
		var cq = cb.createQuery(Department.class);
		
		var root = cq.from(Department.class);
		
		cq.select(root);
		
		var join = root.join(Department_.employees);
		
		var predicate = cb.like( cb.lower( join.get( Employee_.name ) ), name.toLowerCase().concat( "%" ) );
		
		cq.where(predicate);
		
		
		var query = em.createQuery(cq);
		
		System.out.println( "Department List : " + query.getResultList() );
		
	}
	
	
	

}
