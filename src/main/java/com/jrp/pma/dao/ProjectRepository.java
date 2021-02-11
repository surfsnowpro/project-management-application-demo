package com.jrp.pma.dao;

import com.jrp.pma.dto.ProjectChartDataDto;
import com.jrp.pma.entities.Project;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProjectRepository extends CrudRepository<Project, Long> {

    @Override
    List<Project> findAll();

    @Query(nativeQuery = true, value = "SELECT stage AS Label, COUNT(project_id) as Value " +
            "FROM projects " +
            "GROUP BY stage;")
    List<ProjectChartDataDto> getProjectStatus();
}
