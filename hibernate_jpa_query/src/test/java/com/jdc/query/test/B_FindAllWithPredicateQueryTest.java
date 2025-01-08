package com.jdc.query.test;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.jdc.query.entity.Employee;
import com.jdc.query.entity.Employee_;

public class B_FindAllWithPredicateQueryTest extends JpaEmfFactory {

	
	@ParameterizedTest
	@CsvSource("j")
	@Order(1)
	void findWithNativeQueryTest( String name ) {
		
		
		var query = em.createNativeQuery( "select * from employee_tbl where lower(name) like ?" , Employee.class);
		query.setParameter(1,name.toLowerCase().concat("%"));
		
		System.out.println( "Predicated Result:" + query.getResultList() );
		
	}
	
	@ParameterizedTest
	@CsvSource("m")
	@Order(2)
	void findWidthJpqlQueryTest( String name ) {
		
		var query = em.createQuery("select e from Employee e where lower(name) like :n",Employee.class);
		query.setParameter("n", name.toLowerCase().concat("%"));
		
		System.out.println( "Predicated Result:" + query.getResultList() );
		
		
	}
	
	@ParameterizedTest
	@CsvSource("a")
	@Order(3)
	void findWithCriterQueryTest( String name ) {
		
		var cb = em.getCriteriaBuilder();
		
		var cq = cb.createQuery(Employee.class);
		
		//from Employee e
		var root = cq.from(Employee.class);
		
		//select * from Employee e
		cq.select(root);
		
		//lower(name) like :name
		var predicate = cb.like( cb.lower( root.get(Employee_.name) ), name.toLowerCase().concat("%") );//get("parameter")==String
		
		// where lower(name) like :name;
		cq.where(predicate);
		
		//select * from Employee e where lower(name) like :name;
		var query = em.createQuery(cq);
		
		System.out.println( "Predicated Result:" + query.getResultList() );
		
		
	}
	
}
