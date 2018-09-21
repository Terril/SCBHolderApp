package com.scbholderapp.api;

import com.scbholderapp.api.model.Comment;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CommentService {

    @GET("/comments")
    Observable<List<Comment>> getCommentList(@Query("postId") long postId);

    @GET("/comments/{id}")
    Observable<Comment> getComment(@Path("id") long id);

    @POST("/comments")
    Observable<Comment> createComment(@Body Comment comment);
}
