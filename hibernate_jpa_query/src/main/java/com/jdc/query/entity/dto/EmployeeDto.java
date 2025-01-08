package com.jdc.query.entity.dto;

import java.time.LocalDate;

public record EmployeeDto(
		
		String employee,
		LocalDate dob,
		String department
		
		){

}
