package com.eu.castilho.project.tracker.entities;

import jakarta.persistence.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Entity
@Table(name = "tb_projects")
public class Project {

    @Transient
    DateTimeFormatter fmt1 = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;
    @JoinColumn(name = "Name")
    private String projectName;
    @JoinColumn(name = "Start_Hour")
    private String startHour;
    @JoinColumn(name = "End_Hour")
    private String endHour;
    @JoinColumn(name = "Value_Per_Hour")
    private Double valuePerHour;
    @JoinColumn(name = "Duration_Project")
    private Long duration;
    @JoinColumn(name = "Total_Value")
    private Double totalValue;

    public Project(Long id, String projectName, String startHour, String endHour, Double valuePerHour, Project duration, Project totalValue) {
        this.id = id;
        this.projectName = projectName;
        this.startHour = startHour;
        this.endHour = endHour;
        this.valuePerHour = valuePerHour;
        setDuration(getDuration());
        setTotalValue(getTotalValue());
    }

    public Long getId() {
        return id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getStartHour() {
        return startHour;
    }

    public void setStartHour(String startHour) {
        this.startHour = startHour;
    }

    public String getEndHour() {
        return endHour;
    }

    public void setEndHour(String endHour) {
        this.endHour = endHour;
    }

    public Double getValuePerHour() {
        return valuePerHour;
    }

    public void setValuePerHour(Double valuePerHour) {
        this.valuePerHour = valuePerHour;
    }

    public Long getDuration() {
        LocalDateTime start = LocalDateTime.parse(this.startHour, fmt1);
        LocalDateTime end = LocalDateTime.parse(this.endHour, fmt1);
        return Duration.between(start, end).toHours();
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public Double getTotalValue() {
        return getDuration() * getValuePerHour();
    }

    public void setTotalValue(Double totalValue) {
        this.totalValue = totalValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return id.equals(project.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", projectName='" + projectName + '\'' +
                ", startHour='" + startHour + '\'' +
                ", endHour='" + endHour + '\'' +
                ", valuePerHour=" + valuePerHour +
                ", duration=" + getDuration() +
                ", totalValue=" + getTotalValue() +
                '}';
    }
}
