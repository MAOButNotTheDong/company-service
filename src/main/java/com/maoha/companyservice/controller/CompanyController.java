package com.maoha.companyservice.controller;

import com.maoha.companyservice.dto.CompanyDto;
import com.maoha.companyservice.dto.CompanyWithEmployeesDto;
import com.maoha.companyservice.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("companies")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;

    @GetMapping("/{id}")
    public CompanyWithEmployeesDto getCompany(@PathVariable Integer id) {
        return companyService.getCompany(id);
    }

    @PostMapping("/by-ids")
    public List<CompanyDto> getCompaniesById(@RequestBody List<Integer> ids) {
        return companyService.getCompaniesById(ids);
    }

    @PostMapping
    public Integer createCompany(@RequestBody CompanyDto company) {
        return companyService.createCompany(company);
    }

    @PutMapping
    public Integer updateCompany(@RequestBody CompanyDto company) {
        return companyService.updateCompany(company);
    }

    @DeleteMapping("/{id}")
    public void deleteCompany(@PathVariable Integer id) {
        companyService.deleteCompany(id);
    }

    @GetMapping
    public List<CompanyWithEmployeesDto> getCompanies() {
        return companyService.getAllCompanies();
    }

}
