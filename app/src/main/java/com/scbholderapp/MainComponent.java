package com.scbholderapp;

import com.scbholderapp.api.ApiModule;
import com.scbholderapp.database.DatabaseModule;
import com.scbholderapp.fragment.AlbumFragment;
import com.scbholderapp.fragment.CommentFragment;
import com.scbholderapp.fragment.PhotoFragment;
import com.scbholderapp.fragment.PostFragment;
import com.scbholderapp.fragment.ToDoFragment;
import com.scbholderapp.fragment.UserDetailFragment;
import com.scbholderapp.fragment.UserFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        MainModule.class,
        DatabaseModule.class,
        ApiModule.class})
public interface MainComponent {

    void inject(UserFragment userFragment);

    void inject(AlbumFragment albumFragment);

    void inject(PhotoFragment photoFragment);

    void inject(PostFragment postFragment);

    void inject(ToDoFragment toDoFragment);

    void inject(UserDetailFragment userDetailFragment);

    void inject(CommentFragment commentFragment);
}