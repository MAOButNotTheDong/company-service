package com.maoha.companyservice.exception;


import com.maoha.companyservice.exception.dto.ExceptionResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {

    public ResponseEntity<ExceptionResponseDto> defaultExceptionResponse(Throwable e, HttpStatus status, String message) {
        ExceptionResponseDto responseDto = ExceptionResponseDto.builder()
                .status(status.getReasonPhrase())
                .exceptionType(e.getClass().toString())
                .message(message)
                .build();
        return ResponseEntity.status(status).body(responseDto);
    }

    @ExceptionHandler(CompanyNotFoundException.class)
    public ResponseEntity<ExceptionResponseDto> tickerNotFoundException(CompanyNotFoundException e) {
        return defaultExceptionResponse(e,HttpStatus.NOT_FOUND,  e.getMessage());
    }

    @ExceptionHandler(CompanyValidationException.class)
    public ResponseEntity<ExceptionResponseDto>  userWithEmailAlreadyExists(CompanyValidationException e) {
        return defaultExceptionResponse(e, HttpStatus.BAD_REQUEST, e.getMessage());
    }
}
