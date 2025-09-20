package com.prolaborate.prolaborate_backend.service;

import com.prolaborate.prolaborate_backend.dto.StudentDTO;
import com.prolaborate.prolaborate_backend.entity.Student;
import com.prolaborate.prolaborate_backend.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService implements UserDetailsService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public StudentDTO registerStudent(StudentDTO dto) {
        if (studentRepository.findByUsername(dto.getUsername()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already exists");
        }
        Student student = new Student();
        student.setName(dto.getName());
        student.setCollege(dto.getCollege());
        student.setDepartment(dto.getDepartment());
        student.setInterests(dto.getInterests());
        student.setUsername(dto.getUsername());
        student.setPassword(passwordEncoder.encode(dto.getPassword()));
        student.getRoles().add("ROLE_STUDENT");
        Student saved = studentRepository.save(student);
        return toDTO(saved);
    }

    public List<StudentDTO> getAllStudents() {
        return studentRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    private StudentDTO toDTO(Student student) {
        StudentDTO dto = new StudentDTO();
        dto.setId(student.getId());
        dto.setName(student.getName());
        dto.setCollege(student.getCollege());
        dto.setDepartment(student.getDepartment());
        dto.setInterests(student.getInterests());
        dto.setUsername(student.getUsername());
        return dto;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return studentRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }
}