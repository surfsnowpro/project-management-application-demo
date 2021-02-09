package com.jrp.pma.controllers;

import com.jrp.pma.dao.EmployeeRepository;
import com.jrp.pma.dao.ProjectRepository;
import com.jrp.pma.entities.Employee;
import com.jrp.pma.entities.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    ProjectRepository projectRepo;

    @Autowired
    EmployeeRepository employeeRepo;

    @GetMapping("/new")
    public String displayProjectForm(Model model) {
        Project project = new Project();
        model.addAttribute("project", project);

        List<Employee> employees = employeeRepo.findAll();
        model.addAttribute("allEmployees", employees);

        return "projects/new-project";
    }

    @GetMapping
    public String displayProjects(Model model) {
        List<Project> projects = projectRepo.findAll();
        model.addAttribute("projects", projects);

        return "projects/list-projects.html";
    }

    @PostMapping(value = "/save")
    public String createProject(Project project, @RequestParam List<Long> employees, Model model) {
        projectRepo.save(project);

        // Use a redirect to prevent duplicate submissions
        return "redirect:/projects";
    }
}
