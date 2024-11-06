package com.iteureka.backend.controller;

import com.iteureka.backend.model.ProjectEntity;
import com.iteureka.backend.service.ProjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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

}
