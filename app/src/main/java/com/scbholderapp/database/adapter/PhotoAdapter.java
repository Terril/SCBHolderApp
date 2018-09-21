package com.scbholderapp.database.adapter;

import com.scbholderapp.database.model.Photo;
import com.scbholderapp.database.model.User;

import java.util.ArrayList;
import java.util.List;

public class PhotoAdapter {

    static Photo parse(com.scbholderapp.api.model.Photo photo)  {
        Photo photoData = new Photo();
        photoData.setId(photo.getId());
        photoData.setAlbumId(photo.getAlbumId());
        photoData.setTitle(photo.getTitle());
        photoData.setUrl(photo.getUrl());
        photoData.setThumbnailUrl(photo.getThumbnailUrl());

        return photoData;
    }

    public static List<Photo> parse(List<com.scbholderapp.api.model.Photo> photoList) {
        List<Photo> newPhotoList = new ArrayList<>();
        for (com.scbholderapp.api.model.Photo photo : photoList) {
            newPhotoList.add(parse(photo));
        }
        return newPhotoList;
    }
}
