package com.maoha.companyservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CompIdWithEmployeesDto {
    private Integer id;
    private List<EmployeeDto> employees;
}
