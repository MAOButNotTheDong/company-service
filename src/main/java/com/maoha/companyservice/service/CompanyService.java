package com.maoha.companyservice.service;

import com.maoha.companyservice.dao.CompanyRepository;
import com.maoha.companyservice.dto.CompIdWithEmployeesDto;
import com.maoha.companyservice.dto.CompanyDto;
import com.maoha.companyservice.dto.CompanyWithEmployeesDto;
import com.maoha.companyservice.dto.EmployeeDto;
import com.maoha.companyservice.exception.CompanyNotFoundException;
import com.maoha.companyservice.exception.CompanyValidationException;
import com.maoha.companyservice.mapper.CompanyMapper;
import com.maoha.companyservice.model.Company;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;
    private final ResilientUserServiceClient resilientUserServiceClient;

    public CompanyWithEmployeesDto getCompany(Integer id) {
        Optional<Company> companyOpt = companyRepository.findById(id);
        if (companyOpt.isEmpty()) {
            throw new CompanyNotFoundException(id);
        }
        List<CompIdWithEmployeesDto> companiesWithEmployees = resilientUserServiceClient.getUsersByCompanyIds(List.of(companyOpt.get().getId()));
        return companyMapper.companyToCompanyWithEmployeesResponseDto(companyOpt.get(), companiesWithEmployees.getFirst().getEmployees());
    }

    public Integer createCompany(CompanyDto companyDto) {
        if (companyDto.getId() != null) {
            throw new CompanyValidationException("Id needs to be empty");
        }
        Company company = companyMapper.companyDtoToCompany(companyDto);
        return companyRepository.save(company).getId();
    }

    public Integer updateCompany(CompanyDto companyDto) {
        if (companyDto.getId() == null) {
            throw new CompanyValidationException("Id can't be null");
        }
        Company company = companyMapper.companyDtoToCompany(companyDto);
        return companyRepository.save(company).getId();
    }

    public void deleteCompany(Integer id) {
        companyRepository.deleteById(id);
    }

    public List<CompanyWithEmployeesDto> getAllCompanies() {
        List<Company> companies = companyRepository.findAll(Sort.by("id").ascending());
        if (companies.isEmpty()) {
            return new ArrayList<>();
        }

        List<Integer> companyIds = companies.stream().map(Company::getId).toList();

        List<CompIdWithEmployeesDto> companiesAndEmployees = resilientUserServiceClient.getUsersByCompanyIds(companyIds);
        Map<Integer, List<EmployeeDto>> employeesMap = companiesAndEmployees.stream().collect(Collectors.toMap(CompIdWithEmployeesDto::getId, CompIdWithEmployeesDto::getEmployees));

        return companies.stream()
                .map(company -> companyMapper.companyToCompanyWithEmployeesResponseDto(
                        company,
                        employeesMap.getOrDefault(company.getId(), List.of())))
                .toList();
    }

    public List<CompanyDto> getCompaniesById(List<Integer> ids) {
        List<Company> companies = companyRepository.findAllById(ids);
        if (companies.isEmpty()) {
            throw new CompanyNotFoundException(ids);
        }
        return companies.stream().map(companyMapper::companyToCompanyDto).toList();
    }

}

