package com.eu.castilho.project.tracker.services;

import com.eu.castilho.project.tracker.entities.Project;
import com.eu.castilho.project.tracker.repositories.ProjectRepository;
import com.eu.castilho.project.tracker.services.exceptions.DatabaseException;
import com.eu.castilho.project.tracker.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
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

    public Project insert(Project project){
        return projectRepository.save(project);
    }

    public void delete (Long id){
        try{
            projectRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException(id);
        }
        catch (DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        }
    }

    public Project update(Long id, Project obj){
        try{
            Project entity = projectRepository.getReferenceById(id);
            updateData(entity, obj);
            return projectRepository.save(entity);
        }
        catch(EntityNotFoundException e){
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(Project entity, Project obj){
        entity.setProjectName(obj.getProjectName());
        entity.setStartHour(obj.getStartHour());
        entity.setEndHour(obj.getEndHour());
        entity.setValuePerHour(obj.getValuePerHour());
        entity.setDuration(obj.getDuration());
        entity.setTotalValue(obj.getTotalValue());
    }

}
