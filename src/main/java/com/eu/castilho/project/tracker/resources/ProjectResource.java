package com.eu.castilho.project.tracker.resources;

import com.eu.castilho.project.tracker.entities.Project;
import com.eu.castilho.project.tracker.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/projects")
public class ProjectResource {

    @Autowired
    private ProjectService projectService;

    @GetMapping
    public ResponseEntity<List<Project>> findAll(){
        List<Project> list = projectService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Project> findById(@PathVariable Long id){
        Project obj = projectService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

}
