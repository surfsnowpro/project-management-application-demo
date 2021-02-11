package com.jrp.pma.dao;

import com.jrp.pma.dto.EmployeeProjectDto;
import com.jrp.pma.entities.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    @Override
    List<Employee> findAll();

    @Query(nativeQuery = true, value = "SELECT e.first_name AS firstName, e.last_name AS lastName, COUNT(pe.employee_id) AS projectCount " +
            "FROM employees e LEFT JOIN project_employee pe ON pe.employee_id = e.employee_id " +
            "GROUP BY e.first_name, e.last_name ORDER BY 3 DESC")
    List<EmployeeProjectDto> getEmployeeProjects();
}
