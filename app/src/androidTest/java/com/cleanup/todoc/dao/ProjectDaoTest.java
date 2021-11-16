package com.cleanup.todoc.dao;

//import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.InstrumentationRegistry;

import com.cleanup.todoc.LiveDataTestUtil;
import com.cleanup.todoc.database.TodocDatabase;
import com.cleanup.todoc.database.dao.ProjectDao;
import com.cleanup.todoc.model.Project;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class ProjectDaoTest {

    // FOR DATA
    private TodocDatabase database;
    private ProjectDao mProjectDao;

    @Rule
    public InstantTaskExecutorRule mInstantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void initDb() {
        this.database = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                TodocDatabase.class)
                .allowMainThreadQueries()
                .build();
        mProjectDao = database.mProjectDao();
    }

    @After
    public void closeDb() throws Exception {
        database.close();
    }

    @Test
    public void getProjectsWhenNoProjectInserted() throws InterruptedException {
        // When
        List<Project> projects = LiveDataTestUtil.getValue(this.database.mProjectDao().getProjects());
        // Then
        assertTrue(projects.isEmpty());
    }

    @Test
    public void insertAndGetProjets() throws InterruptedException {
        // Given
       Project project1 = new Project(1L, "Projet Tartampion", 0xFFEADAD1);
       Project project2 = new Project(2L,"Projet Lucidia", 0xFFB4CDBA);
       Project project3 = new Project(3L, "Projet Circus", 0xFFA3CED2);

        // When : Insert 3 projects
        mProjectDao.insert(project1);
        mProjectDao.insert(project2);
        mProjectDao.insert(project3);
        List<Project> projects = LiveDataTestUtil.getValue(this.database.mProjectDao().getProjects());

        List<Project> expectedProjects = Arrays.asList(
                project1,
                project2,
                project3
        );

        // Then
        assertTrue(projects.size() == expectedProjects.size());


    }

}
