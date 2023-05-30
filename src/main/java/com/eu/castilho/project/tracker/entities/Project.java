package com.eu.castilho.project.tracker.entities;

import jakarta.persistence.*;

import java.time.DayOfWeek;
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
    private Long id;
    @JoinColumn(name = "Name")
    private String projectName;
    @JoinColumn(name = "InitialHour")
    private String startHour;
    @JoinColumn(name = "EndHour")
    private String endHour;
    @JoinColumn(name = "ValuePerHour")
    private Double valuePerHour;
    @JoinColumn(name = "Duration")
    private Long duration;
    @JoinColumn(name = "TotalValue")
    private Double totalValue;

    public Project() {
    }

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
        LocalDateTime start = LocalDateTime.parse(getStartHour(), fmt1);
        LocalDateTime end = LocalDateTime.parse(getEndHour(), fmt1);

        long durationInDays = Duration.between(start, end).toDays();
        long durationInHours = Duration.between(start, end).toHours();

        LocalDateTime currentDay = start;
        long workingDays = 0;
        long totalWorked;

        while (currentDay.isBefore(end)){
            if (currentDay.getDayOfWeek() != DayOfWeek.SATURDAY && currentDay.getDayOfWeek() != DayOfWeek.SUNDAY){
                workingDays++;
            }
            currentDay = currentDay.plusDays(1);
        }

        if(durationInDays == 0){
            totalWorked = durationInHours;
        } else {
            totalWorked = workingDays * 8;
        }
        return totalWorked;
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
