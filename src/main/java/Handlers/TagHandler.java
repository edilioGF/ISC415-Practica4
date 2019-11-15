package Handlers;

import Models.Tag;
import Models.User;
import Services.Database;
import Services.GestionDb;
import Utils.Parser;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TagHandler extends GestionDb<Tag> {
    private static TagHandler instance;

    public TagHandler() {
        super(Tag.class);
    }

    public static TagHandler getInstance() {
        if (instance == null) {
            instance = new TagHandler();
        }
        return instance;
    }

}
