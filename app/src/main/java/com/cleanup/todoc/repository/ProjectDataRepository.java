package com.cleanup.todoc.repository;


import androidx.lifecycle.LiveData;

import com.cleanup.todoc.database.dao.ProjectDao;
import com.cleanup.todoc.model.Project;

import java.util.List;


public class ProjectDataRepository {

    private final ProjectDao mProjectDao;

    private LiveData<List<Project>> projects;

    public ProjectDataRepository(ProjectDao projectDao) {
        this.mProjectDao = projectDao;
        this.projects = projectDao.getProjects();
    }

    public void insert(Project project){mProjectDao.insert(project);}

    public void delete(Project project){mProjectDao.delete(project);}

    // --- GET USER ---
    public LiveData<List<Project>> getProjects() {
        return this.mProjectDao.getProjects();
    }
}
