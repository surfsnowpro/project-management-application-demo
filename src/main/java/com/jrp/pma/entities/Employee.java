package com.jrp.pma.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long employeeId;
    private String firstName;
    private String lastName;
    private String email;
    // Many employees to one project
    // Lazy => when loading a project or multiple projects, the associated employees are NOT loaded
    // Eager => when loading a project or multiple projects, the associated employees ARE loaded
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id") // will create new column in employees table called "project_id" -> foreign key
    private Project project;

    public Employee() {
    }

    public Employee(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}
