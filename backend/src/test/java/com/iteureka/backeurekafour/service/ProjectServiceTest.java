package com.iteureka.backeurekafour.service;

import com.iteureka.backeurekafour.model.ProjectEntity;
import com.iteureka.backeurekafour.repository.ProjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Optional;

class ProjectServiceTest {

    @InjectMocks
    private ProjectService projectService;

    @Mock
    private ProjectRepository projectRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        projectService.findAll();
        verify(projectRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        Long id = 1L;
        when(projectRepository.findById(id)).thenReturn(Optional.of(new ProjectEntity()));
        Optional<ProjectEntity> foundProject = projectService.findById(id);
        assertTrue(foundProject.isPresent());
        verify(projectRepository, times(1)).findById(id);
    }

    @Test
    void testSave() {
        ProjectEntity project = new ProjectEntity();
        when(projectRepository.save(any(ProjectEntity.class))).thenReturn(project);

        ProjectEntity savedProject = projectService.save(project);
        assertNotNull(savedProject);
        verify(projectRepository, times(1)).save(project);
    }

    @Test
    void testDeleteById() {
        Long id = 1L;
        projectService.deleteById(id);
        verify(projectRepository, times(1)).deleteById(id);
    }
}