package com.iteureka.backeurekafour.controller;

import java.util.Optional;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iteureka.backeurekafour.model.ProjectEntity;
import com.iteureka.backeurekafour.service.ProjectService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/project")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping
    public Iterable<ProjectEntity> getAllProject() {
        return projectService.findAll();
    }

    @GetMapping ("/{id}") 
    public Optional<ProjectEntity> getProjectById(@PathVariable Long id) {
        return projectService.findById(id);
    }

    @PostMapping
    public ProjectEntity saveProject(@RequestBody ProjectEntity projectEntity) {
        return projectService.save(projectEntity);
    }

    @PutMapping("/{id}")
    public ProjectEntity updateProject(
        @PathVariable Long id,
        @RequestBody ProjectEntity projectEntity) {
            return projectService.put(id, projectEntity);
    }

    @DeleteMapping("/{id}")
    public void deleteProjectById(@PathVariable Long id) {
        projectService.deleteById(id);
    }

}