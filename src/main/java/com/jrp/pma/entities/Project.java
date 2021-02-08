package com.jrp.pma.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long projectId;
    private String name;
    private String stage; // NOTSTARTED, COMPLETED, INPROGRESS
    private String description;
    // One project can have many employees
    @OneToMany(mappedBy = "project") // mappedBy => have to define a property in employee called "project" w/ type Project
    private List<Employee> employees;

    public Project() {}

    public Project(String name, String stage, String description, List<Employee> employees) {
        this.name = name;
        this.stage = stage;
        this.description = description;
    }
}
