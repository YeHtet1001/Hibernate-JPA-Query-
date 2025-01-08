package com.jdc.query.test;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import com.jdc.query.entity.Department;

public class DepartmentTest extends JpaEmfFactory{
	
	@Test
	@Order(1)
	void findAllWithCriteriaQueryForDepartment() {
		
		var cb = em.getCriteriaBuilder();
		
		var cq = cb.createQuery(Department.class);
		
		var root = cq.from(Department.class);
		
		cq.select(root);
		
		var query = em.createQuery(cq);
		
		System.out.println("Createria Result :"+ query.getResultList());
		
	}
	

}
