package com.scbholderapp.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.scbholderapp.database.model.Album;

import java.util.List;

@Dao
public interface AlbumDao extends AbstractDao<Album> {

    @Query("SELECT * FROM album")
    LiveData<List<Album>> getAll();

    @Query("SELECT * FROM album WHERE id = :id LIMIT 1")
    Album findById(long id);

    @Query("SELECT * FROM album WHERE userId = :userId")
    LiveData<List<Album>> findByUserId(long userId);

}
