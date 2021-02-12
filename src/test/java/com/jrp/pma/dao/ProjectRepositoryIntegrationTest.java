package com.jrp.pma.dao;

import com.jrp.pma.ProjectManagementApplication;
import com.jrp.pma.entities.Project;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration(classes = {ProjectManagementApplication.class})
@ExtendWith(SpringExtension.class)
@DataJpaTest // specific to junit repositories
@SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:schema.sql", "classpath:data.sql"}),
        // not needed since spring drops tables automatically
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:drop.sql")
})
class ProjectRepositoryIntegrationTest {

    @Autowired
    ProjectRepository repository;

    @Test
    public void ifNewProjectSaved_thenSuccess() {
        Project project = new Project("New Test Project", "COMPLETED", "Test Description");
        repository.save(project);

        assertEquals(5, repository.findAll().size());
    }
}