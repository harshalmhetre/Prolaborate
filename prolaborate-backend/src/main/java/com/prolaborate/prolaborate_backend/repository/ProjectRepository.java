package com.prolaborate.prolaborate_backend.repository;

import com.prolaborate.prolaborate_backend.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}