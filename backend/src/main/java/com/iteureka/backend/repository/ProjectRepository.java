package com.iteureka.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iteureka.backend.model.ProjectEntity;

public interface ProjectRepository extends JpaRepository<ProjectEntity, Long> {

}
