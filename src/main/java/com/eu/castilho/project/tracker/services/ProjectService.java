package com.eu.castilho.project.tracker.services;

import com.eu.castilho.project.tracker.entities.Project;
import com.eu.castilho.project.tracker.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public List<Project> findAll(){
        return projectRepository.findAll();
    }

    public Project findById(Long id){
        Optional<Project> obj = projectRepository.findById(id);
        return obj.get();
    }

    public Project saveProject (Project project){
        return projectRepository.save(project);
    }

    public void deleteProject (Project project){
        projectRepository.delete(project);
    }

}
