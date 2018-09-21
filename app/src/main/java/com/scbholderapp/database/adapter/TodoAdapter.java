package com.scbholderapp.database.adapter;

import com.scbholderapp.database.model.ToDo;

import java.util.ArrayList;
import java.util.List;

public class TodoAdapter {

    static ToDo parse(com.scbholderapp.api.model.ToDo todo)  {
        ToDo postData = new ToDo();
        postData.setId(todo.getId());
        postData.setUserId(todo.getUserId());
        postData.setTitle(todo.getTitle());
        postData.setCompleted(todo.isCompleted());

        return postData;
    }

    public static List<ToDo> parse(List<com.scbholderapp.api.model.ToDo> todoList) {
        List<ToDo> newToDoList = new ArrayList<>();
        for (com.scbholderapp.api.model.ToDo todo : todoList) {
            newToDoList.add(parse(todo));
        }
        return newToDoList;
    }
}
