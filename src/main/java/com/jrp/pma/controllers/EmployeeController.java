package com.jrp.pma.controllers;

import com.jrp.pma.dao.EmployeeRepository;
import com.jrp.pma.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    public EmployeeRepository repository;

    @GetMapping("/new")
    public String displayEmployeeForm(Model model) {
        Employee employee = new Employee();
        model.addAttribute("employee", employee);

        return "employees/new-employee";
    }

    @GetMapping
    public String displayEmployees(Model model) {
        List<Employee> employees = repository.findAll();
        model.addAttribute("employees", employees);

        return "employees/list-employees.html";
    }

    @PostMapping("/save")
    public String saveEmployee(Employee employee) {
        repository.save(employee);
        return "redirect:/employees/new";
    }
}
