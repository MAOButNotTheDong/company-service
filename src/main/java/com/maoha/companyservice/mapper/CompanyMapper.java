package com.maoha.companyservice.mapper;

import com.maoha.companyservice.dto.CompanyDto;
import com.maoha.companyservice.dto.CompanyWithEmployeesDto;
import com.maoha.companyservice.dto.EmployeeDto;
import com.maoha.companyservice.model.Company;

import java.util.List;


//@Mapper(componentModel = "spring")
public interface CompanyMapper {
    Company companyDtoToCompany(CompanyDto companyDto);
    CompanyDto companyToCompanyDto(Company company);
    CompanyWithEmployeesDto companyToCompanyWithEmployeesResponseDto(Company company, List<EmployeeDto> employees);
}
