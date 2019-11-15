package Handlers;

import Models.Comment;
import Services.Database;
import Services.GestionDb;
import Utils.Parser;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentHandler extends GestionDb<Comment> {
    private static CommentHandler instance;

    public CommentHandler() {
        super(Comment.class);
    }

    public static CommentHandler getInstance() {
        if (instance == null) {
            instance = new CommentHandler();
        }
        return instance;
    }

}
