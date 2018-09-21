package com.scbholderapp.api;

import com.scbholderapp.api.model.ToDo;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ToDoService {

    @GET("/todos")
    Observable<List<ToDo>> getToDoList(@Query("userId")long userId);

    @GET("/todos/{id}")
    Observable<ToDo> getToDo(@Path("id")long id);

    @POST("/todos")
    Observable<ToDo> createToDo(@Body ToDo todo);
}
