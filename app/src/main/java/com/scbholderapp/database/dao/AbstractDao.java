package com.scbholderapp.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

public interface AbstractDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> insertAll(List<T> tList);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(T t);
}
