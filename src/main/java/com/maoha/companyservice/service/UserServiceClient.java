package com.maoha.companyservice.service;

import com.maoha.companyservice.dto.CompIdWithEmployeesDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "user-service", path = "/users")
public interface UserServiceClient {

    @PostMapping("by-company-ids")
    List<CompIdWithEmployeesDto> getUsersByCompanyIds(@RequestBody List<Integer> companyIds);
}
