package com.maoha.companyservice.service;

import com.maoha.companyservice.dto.CompIdWithEmployeesDto;
import feign.FeignException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ResilientUserServiceClient {
    private final UserServiceClient userServiceClient;

    @CircuitBreaker(name = "userServiceClient", fallbackMethod = "getFallbackData")
    @Retry(name = "userServiceClient")
    public List<CompIdWithEmployeesDto> getUsersByCompanyIds(@RequestBody List<Integer> companyIds) {
        return userServiceClient.getUsersByCompanyIds(companyIds);
    }

    public List<CompIdWithEmployeesDto> getFallbackData(FeignException.NotFound e) {
        return new ArrayList<>();
    }

    public List<CompIdWithEmployeesDto> getFallbackData(Exception ex) {
        return new ArrayList<>();
    }
}
