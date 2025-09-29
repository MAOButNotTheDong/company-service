package com.maoha.companyservice.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CompIdWithEmployeesDto {
    @JsonAlias("companyId")
    private Integer id;
    @JsonAlias("users")
    private List<EmployeeDto> employees;
}
