package com.scbholderapp.api;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Module
public class ApiModule {

    @Provides
    @Singleton
    public Retrofit retrofit()  {
        return new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    public AlbumService albumService(Retrofit retrofit) {
        return retrofit.create(AlbumService.class);
    }

    @Provides
    @Singleton
    public CommentService commentService(Retrofit retrofit) {
        return retrofit.create(CommentService.class);
    }

    @Provides
    @Singleton
    public PhotoService photoService(Retrofit retrofit) {
        return retrofit.create(PhotoService.class);
    }

    @Provides
    @Singleton
    public PostService postService(Retrofit retrofit)  {
        return retrofit.create(PostService.class);
    }

    @Provides
    @Singleton
    public ToDoService toDoService(Retrofit retrofit) {
        return retrofit.create(ToDoService.class);
    }

    @Provides
    @Singleton
    public UserService userService(Retrofit retrofit) {
        return retrofit.create(UserService.class);
    }
}
