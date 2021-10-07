package com.cleanup.todoc.ui;


import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.repository.ProjectRepository;
import com.cleanup.todoc.repository.TaskRepository;


import java.util.List;
import java.util.concurrent.Executor;


public class TaskViewModel extends ViewModel {

    // REPOSITORIES
    private final ProjectRepository mProjectRepository;
    private final TaskRepository mTaskRepository;
    private final Executor executor;

    // DATA
    @Nullable
    private LiveData<List<Project>> mLiveProjects;

    public TaskViewModel(ProjectRepository projectRepository, TaskRepository taskRepository, Executor executor) {
        this.mProjectRepository = projectRepository;
        this.mTaskRepository = taskRepository;
        this.executor = executor;
        this.mLiveProjects = projectRepository.getProjects();

    }


    // -------------
    // FOR PROJECT
    // -------------

    public LiveData<List<Project>> getLiveProjects() {
        return this.mLiveProjects;  }

    // -------------
    // FOR TASK
    // -------------

    public LiveData<List<Task>> getTasks() {
        return mTaskRepository.getTasks();
    }

    public void createTask(Task task) {
        executor.execute(() -> {mTaskRepository.createTask(task);
        });
    }

    public void deleteTask(Task task) {
        executor.execute(() -> {mTaskRepository.deleteTask(task);
        });
    }

    public void updateTask(Task task) {
        executor.execute(() -> {mTaskRepository.updateTask(task);
        });
    }
}
