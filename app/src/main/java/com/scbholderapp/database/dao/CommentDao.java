package com.scbholderapp.database.dao;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.scbholderapp.database.model.Comment;

import java.util.List;

@Dao
public interface CommentDao extends AbstractDao<Comment> {

    @Query("SELECT * FROM comment")
    LiveData<List<Comment>> getAll();

    @Query("SELECT * FROM comment WHERE id = :id LIMIT 1")
    Comment findById(long id);

    @Query("SELECT * FROM comment WHERE postId = :postId")
    LiveData<List<Comment>> findByPostId(long postId);

}
