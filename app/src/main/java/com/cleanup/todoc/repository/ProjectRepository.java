package com.cleanup.todoc.repository;


import androidx.lifecycle.LiveData;

import com.cleanup.todoc.database.dao.ProjectDao;
import com.cleanup.todoc.model.Project;

import java.util.List;


public class ProjectRepository {

    private final ProjectDao mProjectDao;

    private LiveData<List<Project>> projects;

    public ProjectRepository(ProjectDao projectDao) {
        this.mProjectDao = projectDao;
        this.projects = projectDao.getProjects();
    }

    // --- GET USER ---
    public LiveData<List<Project>> getProjects() {
        return this.mProjectDao.getProjects();
    }
}
