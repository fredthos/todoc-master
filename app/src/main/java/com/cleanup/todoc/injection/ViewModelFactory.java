package com.cleanup.todoc.injection;


import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.cleanup.todoc.repository.ProjectDataRepository;
import com.cleanup.todoc.repository.TaskDataRepository;
import com.cleanup.todoc.ui.TaskViewModel;

import java.util.concurrent.Executor;


public class ViewModelFactory implements ViewModelProvider.Factory {

    private final ProjectDataRepository mProjectDataRepository;
    private final TaskDataRepository mTaskDataRepository;
    private final Executor mExecutor;

    public ViewModelFactory(ProjectDataRepository projectDataRepository, TaskDataRepository taskDataRepository, Executor executor) {
        this.mProjectDataRepository = projectDataRepository;
        this.mTaskDataRepository = taskDataRepository;
        this.mExecutor = executor;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(TaskViewModel.class)) {
            return (T) new TaskViewModel(mProjectDataRepository, mTaskDataRepository, mExecutor);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}