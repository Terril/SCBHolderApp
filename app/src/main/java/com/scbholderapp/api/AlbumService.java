package com.scbholderapp.api;

import com.scbholderapp.api.model.Album;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface AlbumService {

    @GET("/albums")
    Observable<List<Album>> getAlbumList(@Query("userId") long userId);

    @GET("/albums/{id}")
    Observable<Album> getAlbum(@Path("id") long id);

    @POST("/albums")
    Observable<Album> createAlbum(@Body Album album);
}
