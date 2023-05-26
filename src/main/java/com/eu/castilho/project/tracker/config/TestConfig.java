package com.eu.castilho.project.tracker.config;

import com.eu.castilho.project.tracker.entities.Project;
import com.eu.castilho.project.tracker.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.ArrayList;
import java.util.List;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public void run(String... args) throws Exception {

        List<Project> projectList = new ArrayList<>();

        projectList.add(new Project(null, "blue", "02/05/2023 10:00", "07/05/2023 10:00", 50.0, null, null));
        projectList.add(new Project(null, "red", "06/07/2022 11:00", "18/09/2022 10:30", 60.0, null, null));
        projectList.add(new Project(null, "black", "06/02/2019 09:00", "08/02/2019 11:00", 25.0, null, null));
        projectList.add(new Project(null, "white", "04/02/2023 10:00", "07/03/2023 18:00", 45.0, null, null));
        projectList.add(new Project(null, "green", "02/05/2018 14:00", "07/08/2018 16:00", 35.0, null, null));
        projectList.add(new Project(null, "yellow", "02/05/2018 14:00", "02/05/2018 16:00", 30.0, null, null));

        for (Project project: projectList) {
            projectRepository.save(project);
        }

    }
}
