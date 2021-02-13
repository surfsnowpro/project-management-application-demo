package com.jrp.pma.services;

import com.jrp.pma.dao.ProjectRepository;
import com.jrp.pma.entities.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository projRepo;

    @Autowired
    public ProjectService(ProjectRepository repository) {
        projRepo = repository;
    }

    public List<Project> getAll() {
        return projRepo.findAll();
    }

    public Project save(Project project) {
        return projRepo.save(project);
    }
}
