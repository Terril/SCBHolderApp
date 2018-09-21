package com.scbholderapp.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.scbholderapp.database.model.User;

import java.util.List;

@Dao
public interface UserDao extends AbstractDao<User> {

    @Query("SELECT * FROM user")
    LiveData<List<User>> getAll();

    @Query("SELECT * FROM user WHERE id = :id LIMIT 1")
    User findById(long id);

    @Query("SELECT * FROM user WHERE id = :id LIMIT 1")
    User findByIdSync(long id);
}
