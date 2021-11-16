package com.cleanup.todoc.database;

import android.content.ContentValues;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.OnConflictStrategy;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.cleanup.todoc.database.dao.ProjectDao;
import com.cleanup.todoc.database.dao.TaskDao;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

@Database(entities = {Project.class, Task.class}, version = 1, exportSchema = false)
public abstract class TodocDatabase extends RoomDatabase {

    // --- SINGLETON ---
    private static volatile TodocDatabase INSTANCE;

    // --- DAO ---
    public abstract ProjectDao mProjectDao();
    public abstract TaskDao mTaskDao();

    // --- INSTANCE ---
    public static TodocDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (TodocDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            TodocDatabase.class, "TodocDatabase.db")
                            .fallbackToDestructiveMigration()
                            .addCallback(populateDatabase())
                            .build();
                }
            }
        }
        return INSTANCE;
    }
    private static Callback populateDatabase(){
        return new Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);

                ContentValues contentValues1 = new ContentValues();
                contentValues1.put("id", 1L);
                contentValues1.put("name", "Projet Tartampion");
                contentValues1.put("color", 0xFFEADAD1);

                db.insert("Project", OnConflictStrategy.IGNORE, contentValues1);

                ContentValues contentValues2 = new ContentValues();
                contentValues2.put("id", 2L);
                contentValues2.put("name", "Projet Lucidia");
                contentValues2.put("color", 0xFFB4CDBA);

                db.insert("Project", OnConflictStrategy.IGNORE, contentValues2);

                ContentValues contentValues3 = new ContentValues();
                contentValues3.put("id", 3L);
                contentValues3.put("name", "Projet Circus");
                contentValues3.put("color", 0xFFA3CED2);

                db.insert("Project", OnConflictStrategy.IGNORE, contentValues3);
            }
        };
    }

}
