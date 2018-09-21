package com.scbholderapp.api;


import com.scbholderapp.api.model.User;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UserService {

    @GET("/users")
    Observable<List<User>> getUserList();

    @GET("/users/{id}")
    Observable<User> getUser(@Path("id") long id);

    @POST("/users")
    Observable<User> createUser(@Body User user);
}
