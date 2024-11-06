package com.iteureka.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iteureka.backend.model.ProjectEntity;
import com.iteureka.backend.service.ProjectService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/project")
@AllArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping
    public Iterable<ProjectEntity> getAllProject() {
        return projectService.findAll();
    }
}
