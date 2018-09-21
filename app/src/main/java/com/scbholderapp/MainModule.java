package com.scbholderapp;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

    private final Application mApplication;

    public MainModule(Application application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    public Context context()  {
        return mApplication;
    }

    @Provides
    @Singleton
    public Application application() {
        return mApplication;
    }
}
