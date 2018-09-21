package com.scbholderapp.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.scbholderapp.database.dao.AlbumDao;
import com.scbholderapp.database.dao.CommentDao;
import com.scbholderapp.database.dao.PhotoDao;
import com.scbholderapp.database.dao.PostDao;
import com.scbholderapp.database.dao.ToDoDao;
import com.scbholderapp.database.dao.UserDao;
import com.scbholderapp.database.model.Album;
import com.scbholderapp.database.model.Comment;
import com.scbholderapp.database.model.Photo;
import com.scbholderapp.database.model.Post;
import com.scbholderapp.database.model.ToDo;
import com.scbholderapp.database.model.User;

@Database(
        version = 1,
        entities = {
                User.class,
                Photo.class,
                Comment.class,
                Post.class,
                ToDo.class,
                Album.class})
public abstract class MainDatabase extends RoomDatabase {

    public abstract UserDao userDao();

    public abstract AlbumDao albumDao();

    public abstract PhotoDao photoDao();

    public abstract PostDao postDao();

    public abstract ToDoDao toDoDao();

    public abstract CommentDao commentDao();
}