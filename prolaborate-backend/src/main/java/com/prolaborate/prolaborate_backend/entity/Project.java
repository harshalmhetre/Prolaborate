package com.prolaborate.prolaborate_backend.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String department;
    private String domain;
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    @ElementCollection
    @CollectionTable(name = "project_images", joinColumns = @JoinColumn(name = "project_id"))
    @Column(name = "image", columnDefinition = "bytea")
    @Lob
    private List<byte[]> images = new ArrayList<>(); // Max 5

    @ElementCollection
    @CollectionTable(name = "project_videos", joinColumns = @JoinColumn(name = "project_id"))
    @Column(name = "video", columnDefinition = "bytea")
    @Lob
    private List<byte[]> videos = new ArrayList<>(); // Max 2

    public String getStudentName() {
        return (student != null) ? student.getName() : null;
    }

    public void setStudentName(String name) {
        if (this.student != null) {
            this.student.setName(name);
        }
    }
}