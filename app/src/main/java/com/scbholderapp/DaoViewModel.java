package com.scbholderapp;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

public class DaoViewModel<Dao> extends ViewModel {

    public Dao dao;

    public DaoViewModel(Dao val) {
        dao = val;
    }

    public static <D, VM extends DaoViewModel<D>> VM newInstance(Fragment fragment, Class<D> daoClass, D dao) {
        return ((VM) ViewModelProviders
                .of(fragment, new Factory(dao))
                .get(DaoViewModel.class));
    }

    static class Factory<D> extends ViewModelProvider.NewInstanceFactory {

        private D dao;

        Factory(D dao) {
            this.dao = dao;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return ((T) new DaoViewModel(dao));
        }
    }
}
