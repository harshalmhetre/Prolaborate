package com.prolaborate.prolaborate_backend.controller;

import com.prolaborate.prolaborate_backend.dto.ProjectCreateUpdateDTO;
import com.prolaborate.prolaborate_backend.dto.ProjectDTO;
import com.prolaborate.prolaborate_backend.entity.Student;
import com.prolaborate.prolaborate_backend.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<ProjectDTO> createProject(
            @RequestPart("dto") ProjectCreateUpdateDTO dto,
            @RequestPart(value = "images", required = false) MultipartFile[] images,
            @RequestPart(value = "videos", required = false) MultipartFile[] videos,
            @AuthenticationPrincipal Student currentStudent) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(projectService.createProject(dto, images, videos, currentStudent));
    }

    @PutMapping(value = "/{id}", consumes = {"multipart/form-data"})
    public ResponseEntity<ProjectDTO> updateProject(
            @PathVariable Long id,
            @RequestPart("dto") ProjectCreateUpdateDTO dto,
            @RequestPart(value = "images", required = false) MultipartFile[] images,
            @RequestPart(value = "videos", required = false) MultipartFile[] videos,
            @AuthenticationPrincipal Student currentStudent) {
        return ResponseEntity.ok(projectService.updateProject(id, dto, images, videos, currentStudent));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id, @AuthenticationPrincipal Student currentStudent) {
        projectService.deleteProject(id, currentStudent);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDTO> getProject(@PathVariable Long id) {
        return ResponseEntity.ok(projectService.getProject(id));
    }

    @GetMapping
    public ResponseEntity<List<ProjectDTO>> getAllProjects() {
        return ResponseEntity.ok(projectService.getAllProjects());
    }
}