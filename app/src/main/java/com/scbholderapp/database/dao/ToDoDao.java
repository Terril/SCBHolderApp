package com.scbholderapp.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.scbholderapp.database.model.ToDo;

import java.util.List;

@Dao
public interface ToDoDao extends AbstractDao<ToDo> {

    @Query("SELECT * FROM todo")
    LiveData<List<ToDo>> getAll();

    @Query("SELECT * FROM todo WHERE id = :id LIMIT 1")
    ToDo findById(long id);

    @Query("SELECT * FROM todo WHERE userId = :userId")
    LiveData<List<ToDo>> findByUserId(long userId);
}
