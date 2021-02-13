package com.jrp.pma.controllers;

import com.jrp.pma.entities.Employee;
import com.jrp.pma.entities.Project;
import com.jrp.pma.services.EmployeeService;
import com.jrp.pma.services.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;
    private final EmployeeService employeeService;

    public ProjectController(
            ProjectService projectService,
            EmployeeService employeeService
    ) {
        this.projectService = projectService;
        this.employeeService = employeeService;
    }

    @GetMapping("/new")
    public String displayProjectForm(Model model) {
        Project project = new Project();
        model.addAttribute("project", project);

        List<Employee> employees = employeeService.getAll();
        model.addAttribute("allEmployees", employees);

        return "projects/new-project";
    }

    @GetMapping
    public String displayProjects(Model model) {
        List<Project> projects = projectService.getAll();
        model.addAttribute("projects", projects);

        return "projects/list-projects.html";
    }

    @PostMapping(value = "/save")
    public String createProject(Project project, @RequestParam List<Long> employees, Model model) {
        projectService.save(project);

        // Use a redirect to prevent duplicate submissions
        return "redirect:/projects";
    }
}
