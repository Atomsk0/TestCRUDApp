package com.blogspot.atomskjournal.dao;

import com.blogspot.atomskjournal.model.User;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.search.FullTextSession;

import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



public class UserDAOImplement implements UserDAO {

    @Autowired
    SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    @Override
    @Transactional
    public int insertRow(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(user);
        transaction.commit();
        Serializable id = session.getIdentifier(user);
        session.close();
        return (Integer) id;
    }

    @Override
    public List<User> userList() {
        Session session = sessionFactory.openSession();
        List<User> userList = session.createQuery("from User").list();
        session.close();
        return userList;
    }

    @Override
    public User getRowById(int id) {
        Session session = sessionFactory.openSession();
        User user = (User) session.load(User.class, id);
        return user;
    }

    @Override
    public int updateRow(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(user);
        transaction.commit();
        Serializable id = session.getIdentifier(user);
        session.close();
        return (Integer) id;
    }

    @Override
    public int deleteRow(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        User user = (User) session.load(User.class, id);
        session.delete(user);
        transaction.commit();
        Serializable ids = session.getIdentifier(user);
        session.close();
        return (Integer) ids;
    }

    @Transactional
    @Override
    public List<User> searchForName(String key) {

        Session session = sessionFactory.openSession();
        FullTextSession fullTextSession = Search.getFullTextSession(session);
        try {
            fullTextSession.createIndexer().startAndWait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Transaction transaction = fullTextSession.beginTransaction();

        QueryBuilder qb = fullTextSession.getSearchFactory()
                .buildQueryBuilder().forEntity(User.class).get();
        org.apache.lucene.search.Query query = qb
                .keyword().onFields("name")
                .matching(key)
                .createQuery();


        org.hibernate.Query hibQuery = fullTextSession.createFullTextQuery(query, User.class);


        List<User> results = hibQuery.list();
        Collections.sort(results);

        transaction.commit();
        session.close();
        return results;


    }

}

