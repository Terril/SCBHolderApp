package com.scbholderapp.database.adapter;


import com.scbholderapp.database.model.Album;

import java.util.ArrayList;
import java.util.List;

public class AlbumAdapter {
    static Album parse(com.scbholderapp.api.model.Album album)  {
        Album albumData = new Album();
        albumData.setId(album.getId());
        albumData.setUserId(album.getUserId());
        albumData.setTitle(album.getTitle());

        return albumData;
    }

    public static List<Album> parse(List<com.scbholderapp.api.model.Album> photoList) {
        List<Album> newPhotoList = new ArrayList<>();
        for (com.scbholderapp.api.model.Album photo : photoList) {
            newPhotoList.add(parse(photo));
        }
        return newPhotoList;
    }
}
