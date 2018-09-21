package com.scbholderapp.database.adapter;
import com.scbholderapp.database.model.Post;

import java.util.ArrayList;
import java.util.List;

public class PostAdapter {
    static Post parse(com.scbholderapp.api.model.Post post)  {
        Post postData = new Post();
        postData.setId(post.getId());
        postData.setUserId(post.getUserId());
        postData.setTitle(post.getTitle());
        postData.setBody(post.getBody());

        return postData;
    }

    public static List<Post> parse(List<com.scbholderapp.api.model.Post> postList) {
        List<Post> newPostList = new ArrayList<>();
        for (com.scbholderapp.api.model.Post post : postList) {
            newPostList.add(parse(post));
        }
        return newPostList;
    }
}
