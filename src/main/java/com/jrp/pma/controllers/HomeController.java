package com.jrp.pma.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jrp.pma.dao.EmployeeRepository;
import com.jrp.pma.dao.ProjectRepository;
import com.jrp.pma.dto.EmployeeProjectDto;
import com.jrp.pma.dto.ProjectChartDataDto;
import com.jrp.pma.entities.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    @Autowired
    ProjectRepository projectRepo;
    @Autowired
    EmployeeRepository employeeRepo;

    @GetMapping("/")
    public String displayHome(Model model) throws JsonProcessingException {
        Map<String, Object> map = new HashMap<>();

        List<Project> projects = projectRepo.findAll();
        model.addAttribute("projects", projects);

        List<EmployeeProjectDto> employeeProjects = employeeRepo.getEmployeeProjects();
        model.addAttribute("employees", employeeProjects);

        List<ProjectChartDataDto> chartData = projectRepo.getProjectStatus();
        // Convert chartData object into JSON structure for use in Javascript
        ObjectMapper mapper = new ObjectMapper();
        // [["NOTSTARTED", 1], ["INPROGRESS", 2], ["COMPLETED", 1]]
        String json = mapper.writeValueAsString(chartData);
        model.addAttribute("projectStatusCnt", json);

        return "main/home";
    }
}
