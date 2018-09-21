package com.scbholderapp.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.scbholderapp.database.model.Photo;

import java.util.List;

@Dao
public interface PhotoDao extends AbstractDao<Photo> {

    @Query("SELECT * FROM photo")
    LiveData<List<Photo>> getAll();

    @Query("SELECT * FROM photo WHERE id = :id LIMIT 1")
    Photo findById(long id);

    @Query("SELECT * FROM photo WHERE albumId = :albumId")
    LiveData<List<Photo>> findByAlbumId(long albumId);
}
