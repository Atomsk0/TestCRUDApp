package com.blogspot.atomskjournal.Services;

import java.util.List;

import com.blogspot.atomskjournal.dao.UserDAO;
import com.blogspot.atomskjournal.model.User;

import org.springframework.beans.factory.annotation.Autowired;

public class DataServiceImplement implements DataService {

    @Autowired
    UserDAO userDAO;

    @Override
    public int insertRow(User user) {
        return userDAO.insertRow(user);
    }

    @Override
    public List<User> userList() {
        return userDAO.userList();
    }

    @Override
    public List<User> searchForName(String key) {
        return userDAO.searchForName(key);
    }

    @Override
    public User getRowById(int id) {
        return userDAO.getRowById(id);
    }

    @Override
    public int updateRow(User user) {
        return userDAO.updateRow(user);
    }

    @Override
    public int deleteRow(int id) {
        return userDAO.deleteRow(id);
    }

}
