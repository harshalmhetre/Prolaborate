package com.prolaborate.prolaborate_backend.dto;
import lombok.Data;

@Data
public class ProjectDTO {
    private Long id;
    private String title;
    private String studentName;
    private String department;
    private String domain;
    private String description;
}