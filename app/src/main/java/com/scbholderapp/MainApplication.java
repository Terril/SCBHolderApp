package com.scbholderapp;

import android.app.Application;

public class MainApplication extends Application {

    MainComponent sMainComp;
    @Override
    public void onCreate() {
        super.onCreate();
        sMainComp = DaggerMainComponent.builder()
                .mainModule(new MainModule(this))
                .build();
    }

    public MainComponent getComponentInstance() {
        return sMainComp;
    }
}
