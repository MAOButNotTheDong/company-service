package com.maoha.companyservice.mapper;

import com.maoha.companyservice.dto.CompanyDto;
import com.maoha.companyservice.dto.CompanyWithEmployeesDto;
import com.maoha.companyservice.dto.EmployeeDto;
import com.maoha.companyservice.model.Company;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CompanyMapperManual implements CompanyMapper {
    @Override
    public Company companyDtoToCompany(CompanyDto companyDto) {
        if ( companyDto == null ) {
            return null;
        }

        Company company = new Company();

        company.setId( companyDto.getId() );
        company.setName( companyDto.getName() );
        company.setBudget( companyDto.getBudget() );

        return company;
    }

    @Override
    public CompanyDto companyToCompanyDto(Company company) {
        if ( company == null ) {
            return null;
        }

        CompanyDto companyDto = new CompanyDto();

        companyDto.setId( company.getId() );
        companyDto.setName( company.getName() );
        companyDto.setBudget( company.getBudget() );

        return companyDto;
    }

    @Override
    public CompanyWithEmployeesDto companyToCompanyWithEmployeesResponseDto(Company company, List<EmployeeDto> employees) {
        if ( company == null && employees == null ) {
            return null;
        }

        CompanyWithEmployeesDto companyWithEmployeesDto = new CompanyWithEmployeesDto();

        if ( company != null ) {
            companyWithEmployeesDto.setId( company.getId() );
            companyWithEmployeesDto.setName( company.getName() );
            companyWithEmployeesDto.setBudget( company.getBudget() );
        }
        List<EmployeeDto> list = employees;
        if ( list != null ) {
            companyWithEmployeesDto.setEmployees( new ArrayList<EmployeeDto>( list ) );
        }

        return companyWithEmployeesDto;
    }
}
