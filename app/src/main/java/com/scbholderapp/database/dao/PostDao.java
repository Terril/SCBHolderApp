package com.scbholderapp.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.scbholderapp.database.model.Post;

import java.util.List;

@Dao
public interface PostDao extends AbstractDao<Post> {

    @Query("SELECT * FROM post")
    LiveData<List<Post>> getAll();

    @Query("SELECT * FROM post WHERE id = :id LIMIT 1")
    Post findById(long id);

    @Query("SELECT * FROM post WHERE userId = :userId")
    LiveData<List<Post>> findByUserId(long userId);
}
