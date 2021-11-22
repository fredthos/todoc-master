package com.cleanup.todoc.dao;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.InstrumentationRegistry;

import com.cleanup.todoc.LiveDataTestUtil;
import com.cleanup.todoc.database.TodocDatabase;
import com.cleanup.todoc.database.dao.TaskDao;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TaskDaoTest {

    // FOR DATA
    private TodocDatabase database;
    private TaskDao mTaskDao;

    @Rule
    public InstantTaskExecutorRule mInstantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void initDb() throws Exception {
        this.database = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                TodocDatabase.class)
                .allowMainThreadQueries()
                .build();
        mTaskDao = database.mTaskDao();
    }

    @After
    public void closeDb() throws Exception {
        database.close();
    }

    @Test
    public void insertAndGetTask() throws InterruptedException {
        // Given
        Task task1 = new Task(1,"Tache1",new Date().getTime());
        Task task2 = new Task(3,"Tache2",new Date().getTime());
        Task task3 = new Task(2,"Tache3",new Date().getTime());
        Task task4 = new Task(1,"Tache4",new Date().getTime());
        Project project1 = new Project(1L, "Projet Tartampion", 0xFFEADAD1);
        Project project2 = new Project(2L,"Projet Lucidia", 0xFFB4CDBA);
        Project project3 = new Project(3L, "Projet Circus", 0xFFA3CED2);

        mTaskDao.insertTask(task1);
        mTaskDao.insertTask(task2);
        mTaskDao.insertTask(task3);
        mTaskDao.insertTask(task4);
        database.mProjectDao().insert(project1);
        database.mProjectDao().insert(project2);
        database.mProjectDao().insert(project3);

        // When
        List<Task> tasks = LiveDataTestUtil.getValue(this.database.mTaskDao().getTasks());

        List<Task> expectedTasks = Arrays.asList(
                task1,
                task2,
                task3,
                task4
        );
        // Then
        assertEquals(tasks.size(),expectedTasks.size());
        assertEquals(tasks.get(0).getName(),task1.getName());
        assertEquals(tasks.get(1).getName(),task2.getName());
        assertEquals(tasks.get(2).getName(),task3.getName());
        assertEquals(tasks.get(3).getName(),task4.getName());
    }

    @Test
    public void insertAndDeleteTask() throws InterruptedException {
        // Given
        Task task1 = new Task(1,"Tache1",new Date().getTime());
        Task task2 = new Task(2,"Tache2",new Date().getTime());
        Task task3 = new Task(1,"Tache3",new Date().getTime());
        Task task4 = new Task(3,"Tache4",new Date().getTime());
        Project project1 = new Project(1L, "Projet Tartampion", 0xFFEADAD1);
        Project project2 = new Project(2L,"Projet Lucidia", 0xFFB4CDBA);
        Project project3 = new Project(3L, "Projet Circus", 0xFFA3CED2);

        mTaskDao.insertTask(task1);
        mTaskDao.insertTask(task2);
        mTaskDao.insertTask(task3);
        mTaskDao.insertTask(task4);
        database.mProjectDao().insert(project1);
        database.mProjectDao().insert(project2);
        database.mProjectDao().insert(project3);

        mTaskDao.deleteTask(task2);

        // When
        List<Task> tasks = LiveDataTestUtil.getValue(mTaskDao.getTasks());

        // Then
        assertEquals(4, tasks.size());
        assertEquals(task1.getName(), tasks.get(0).getName());
        assertEquals(task3.getName(), tasks.get(2).getName());
        assertEquals(task4.getName(), tasks.get(3).getName());
    }

}
