package com.prolaborate.prolaborate_backend.service;

import com.prolaborate.prolaborate_backend.dto.ProjectCreateUpdateDTO;
import com.prolaborate.prolaborate_backend.dto.ProjectDTO;
import com.prolaborate.prolaborate_backend.entity.Project;
import com.prolaborate.prolaborate_backend.entity.Student;
import com.prolaborate.prolaborate_backend.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public ProjectDTO createProject(ProjectCreateUpdateDTO dto, MultipartFile[] images, MultipartFile[] videos, Student currentStudent) {
        validateMedia(images, videos);

        Project project = new Project();
        project.setTitle(dto.getTitle());
        project.setStudentName(currentStudent.getName());
        project.setDepartment(dto.getDepartment());
        project.setDomain(dto.getDomain());
        project.setDescription(dto.getDescription());
        project.setStudent(currentStudent);
        project.setImages(convertToByteList(images));
        project.setVideos(convertToByteList(videos));

        Project saved = projectRepository.save(project);
        return toDTO(saved);
    }

    public ProjectDTO updateProject(Long id, ProjectCreateUpdateDTO dto, MultipartFile[] images, MultipartFile[] videos, Student currentStudent) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Project not found"));

        if (!project.getStudent().getId().equals(currentStudent.getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Not authorized to update this project");
        }

        validateMedia(images, videos);

        project.setTitle(dto.getTitle());
        project.setStudentName(currentStudent.getName());
        project.setDepartment(dto.getDepartment());
        project.setDomain(dto.getDomain());
        project.setDescription(dto.getDescription());
        if (images != null && images.length > 0) {
            project.setImages(convertToByteList(images));
        }
        if (videos != null && videos.length > 0) {
            project.setVideos(convertToByteList(videos));
        }

        Project updated = projectRepository.save(project);
        return toDTO(updated);
    }

    public void deleteProject(Long id, Student currentStudent) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Project not found"));
        if (!project.getStudent().getId().equals(currentStudent.getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Not authorized to delete this project");
        }
        projectRepository.deleteById(id);
    }

    public ProjectDTO getProject(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Project not found"));
        return toDTO(project);
    }

    public List<ProjectDTO> getAllProjects() {
        return projectRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    private void validateMedia(MultipartFile[] images, MultipartFile[] videos) {
        if (images != null && images.length > 5) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Maximum 5 images allowed");
        }
        if (videos != null && videos.length > 2) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Maximum 2 videos allowed");
        }
    }

    private List<byte[]> convertToByteList(MultipartFile[] files) {
        List<byte[]> bytesList = new ArrayList<>();
        if (files != null) {
            for (MultipartFile file : files) {
                try {
                    if (!file.isEmpty()) {
                        bytesList.add(file.getBytes());
                    }
                } catch (IOException e) {
                    throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to process file");
                }
            }
        }
        return bytesList;
    }

    private ProjectDTO toDTO(Project project) {
        ProjectDTO dto = new ProjectDTO();
        dto.setId(project.getId());
        dto.setTitle(project.getTitle());
        dto.setStudentName(project.getStudent().getName());
        dto.setDepartment(project.getDepartment());
        dto.setDomain(project.getDomain());
        dto.setDescription(project.getDescription());
        return dto;
    }
}