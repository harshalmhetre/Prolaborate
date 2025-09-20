package com.prolaborate.prolaborate_backend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class StudentDTO {
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String college;
    @NotBlank
    private String department;
    private String interests;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}