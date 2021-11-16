package com.cleanup.todoc.injection;

import android.content.Context;

import com.cleanup.todoc.database.TodocDatabase;
import com.cleanup.todoc.repository.ProjectDataRepository;
import com.cleanup.todoc.repository.TaskDataRepository;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class Injection {

    public static TaskDataRepository provideTaskRepository(Context context) {
        TodocDatabase database = TodocDatabase.getInstance(context);
        return new TaskDataRepository(database.mTaskDao());
    }

    public static ProjectDataRepository provideProjectRepository(Context context) {
        TodocDatabase database = TodocDatabase.getInstance(context);
        return new ProjectDataRepository(database.mProjectDao());
    }

    public static Executor provideExecutor(){ return Executors.newSingleThreadExecutor(); }

    public static ViewModelFactory provideViewModelFactory(Context context) {
        TaskDataRepository taskDataRepository = provideTaskRepository(context);
        ProjectDataRepository projectDataRepository = provideProjectRepository(context);
        Executor executor = provideExecutor();
        return new ViewModelFactory(projectDataRepository, taskDataRepository, executor);
    }
}
