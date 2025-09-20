package com.prolaborate.prolaborate_backend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProjectCreateUpdateDTO {
    @NotBlank
    private String title;
    @NotBlank
    private String department;
    @NotBlank
    private String domain;
    @NotBlank
    private String description;
}