package Handlers;

import Models.User;
import Services.Database;
import Services.GestionDb;
import Utils.Parser;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.sql.*;
import java.util.List;

public class UserHandler extends GestionDb<User> {
    private static UserHandler instance;

    private UserHandler(){
        super(User.class);
    }

    public static UserHandler getInstance() {
        if (instance == null) {
            instance = new UserHandler();
        }
        return instance;
    }

    public User findByUsername(String username) {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("select u from User u where u.username = :username");
        query.setParameter("username", username);
        List<User> list = query.getResultList();
        if (list.size() > 0)
            return list.get(0);
        else
            return null;
    }

}
