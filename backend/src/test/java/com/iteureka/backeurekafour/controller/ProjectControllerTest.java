package com.iteureka.backeurekafour.controller;

import com.iteureka.backeurekafour.model.ProjectEntity;
import com.iteureka.backeurekafour.service.ProjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


public class ProjectControllerTest {

    @InjectMocks
    private ProjectController projectController;

    @Mock
    private ProjectService projectService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Prueba unitaria para el método que obtiene todos los proyectos
    @Test
    void testGetAllProject() {
        when(projectService.findAll()).thenReturn(Collections.emptyList());
        Iterable<ProjectEntity> projects = projectController.getAllProject();
        assertNotNull(projects);
        verify(projectService, times(1)).findAll();
    }

    @Test
    void testGetProjectById() {
        Long id = 1L;
        ProjectEntity project = new ProjectEntity();
        project.setId(id);

        when(projectService.findById(id)).thenReturn(Optional.of(project));
        Optional<ProjectEntity> foundProject = projectController.getProjectById(id);
        assertTrue(foundProject.isPresent());
        assertEquals(id, foundProject.get().getId());
        verify(projectService, times(1)).findById(id);
    }

    // Prueba para el método que guarda un cliente
    @Test
    void testSaveProject() {
        ProjectEntity project = new ProjectEntity();
        when(projectService.save(any(ProjectEntity.class))).thenReturn(project);
        ProjectEntity savedProject = projectController.saveProject(project);
        assertNotNull(savedProject);
        verify(projectService, times(1)).save(project);
    }

    // Prueba para el método que elimina un cliente por su ID
    @Test
    void testDeleteProjectById() {
        Long id = 1L;
        projectController.deleteProjectById(id);
        verify(projectService, times(1)).deleteById(id);
    }

}
