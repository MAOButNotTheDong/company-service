package com.maoha.companyservice.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CompanyWithEmployeesDto {
    private Integer id;
    private String name;
    private String budget;
    private List<EmployeeDto> employees;
}
