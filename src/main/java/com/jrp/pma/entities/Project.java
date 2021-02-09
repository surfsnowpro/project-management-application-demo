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
    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST},
            fetch = FetchType.LAZY)
    @JoinTable(name = "project_employee",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id"))
    private List<Employee> employees;

    public Project() {}

    public Project(String name, String stage, String description, List<Employee> employees) {
        this.name = name;
        this.stage = stage;
        this.description = description;
    }
}
