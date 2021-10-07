package com.cleanup.todoc;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.InstrumentationRegistry;

import com.cleanup.LiveDataTestUtil;
import com.cleanup.todoc.database.SaveMyTasksDatabase;
import com.cleanup.todoc.database.dao.ProjectDao;
import com.cleanup.todoc.database.dao.TaskDao;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class TaskDaoTest {

    // FOR DATA
    private SaveMyTasksDatabase database;
    private TaskDao mTaskDao;

    @Rule
    public InstantTaskExecutorRule mInstantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void initDb() throws Exception {
        this.database = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                SaveMyTasksDatabase.class)
                .allowMainThreadQueries()
                .build();
    }

    @After
    public void closeDb() throws Exception {
        database.close();
    }

    @Test
    public void insertAndGetTask() throws InterruptedException {
        // When
        List<Task> tasks = LiveDataTestUtil.getValue(this.database.mTaskDao().getTasks());
        // Then
        assertTrue(tasks.isEmpty());
    }

    @Test
    public void insertTask() {


    }

}
