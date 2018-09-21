package com.scbholderapp.api;


import com.scbholderapp.api.model.Post;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PostService {

    @GET("/posts")
    Observable<List<Post>> getPostList(@Query("userId") long userId);

    @GET("/posts/{id}")
    Observable<Post> getPost(@Path("id") long id);

    @POST("/posts")
    Observable<Post> createPost(@Body Post post);
}
