package com.cleanup.todoc.ui;


import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.repository.ProjectDataRepository;
import com.cleanup.todoc.repository.TaskDataRepository;

import java.util.List;
import java.util.concurrent.Executor;


public class TaskViewModel extends ViewModel {

    // REPOSITORIES
    private final ProjectDataRepository mProjectDataRepository;
    private final TaskDataRepository mTaskDataRepository;
    private final Executor mExecutor;

    // DATA
    @Nullable
    private LiveData<List<Project>> mLiveProjects;

    public TaskViewModel(ProjectDataRepository projectDataRepository, TaskDataRepository taskDataRepository, Executor executor) {
        this.mProjectDataRepository = projectDataRepository;
        this.mTaskDataRepository = taskDataRepository;
        this.mExecutor = executor;
        this.mLiveProjects = projectDataRepository.getProjects();

    }


    // -------------
    // FOR PROJECT
    // -------------

    public LiveData<List<Project>> getLiveProjects() {
        return this.mLiveProjects;
    }

    // -------------
    // FOR TASK
    // -------------

    public LiveData<List<Task>> getTasks() {
        return mTaskDataRepository.getTasks();
    }

    public void createTask(Task task) {
        mExecutor.execute(() -> {mTaskDataRepository.createTask(task);
        });
    }

    public void deleteTask(Task task) {
        mExecutor.execute(() -> {mTaskDataRepository.deleteTask(task);
        });
    }

    public void updateTask(Task task) {
        mExecutor.execute(() -> {mTaskDataRepository.updateTask(task);
        });
    }
}
