package com.scbholderapp.api;


import com.scbholderapp.api.model.Photo;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PhotoService {
    @GET("/photos")
    Observable<List<Photo>> getPhotoList(@Query("albumId") long albumId);

    @GET("/photos/{id}")
    Observable<Photo> getPhoto(@Path("id") long id);

    @POST("/photos")
    Observable<Photo> createPhoto(@Body Photo photo);
}
