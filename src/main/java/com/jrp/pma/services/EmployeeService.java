package com.jrp.pma.services;

import com.jrp.pma.dao.EmployeeRepository;
import com.jrp.pma.dto.EmployeeProjectDto;
import com.jrp.pma.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository empRepo;

    @Autowired
    public EmployeeService(EmployeeRepository repository) {
        empRepo = repository;
    }

    public List<Employee> getAll() {
        return empRepo.findAll();
    }

    public List<EmployeeProjectDto> employeeProjects() {
        return empRepo.getEmployeeProjects();
    }
}
