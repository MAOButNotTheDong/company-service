package com.maoha.companyservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmployeeDto {
    private Integer Id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
}
