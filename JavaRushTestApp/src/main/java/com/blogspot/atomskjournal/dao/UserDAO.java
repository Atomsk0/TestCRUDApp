package com.blogspot.atomskjournal.dao;

import com.blogspot.atomskjournal.model.User;

import java.util.List;

/**
 * Created by Максим on 18.02.2016.
 */
public interface UserDAO {

    public int insertRow(User user);

    public List<User> userList();

    public User getRowById(int id);

    public int updateRow(User user);

    public int deleteRow(int id);

    List<User> searchForName(String key);
}
