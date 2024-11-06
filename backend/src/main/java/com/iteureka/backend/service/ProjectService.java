package com.iteureka.backend.service;

import org.springframework.stereotype.Service;

import com.iteureka.backend.model.ProjectEntity;

@Service
public interface ProjectService {

    Iterable <ProjectEntity> findAll();

}
