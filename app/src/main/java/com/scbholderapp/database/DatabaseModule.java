package com.scbholderapp.database;

import android.arch.persistence.room.Room;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class DatabaseModule {

    @Provides
    @Singleton
    public MainDatabase mainDatabase(Context context) {
        return Room.databaseBuilder(context,
                MainDatabase.class, MainDatabase.class.getSimpleName()).build();
    }
}
