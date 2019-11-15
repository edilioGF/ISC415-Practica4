package Handlers;

import Models.Article;
import Models.Tag;
import Models.User;
import Services.Database;
import Services.GestionDb;
import Utils.Parser;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticleHandler extends GestionDb<Article> {
    private static ArticleHandler instance;

    public ArticleHandler() {
        super(Article.class);
    }

    public static ArticleHandler getInstance() {
        if (instance == null) {
            instance = new ArticleHandler();
        }
        return instance;
    }

}
