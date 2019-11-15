import Handlers.UserHandler;
import Models.User;
import Routes.ArticleRoute;
import Routes.IndexRoute;
import Routes.TagRoute;
import Routes.UserRoute;
import Services.Database;
import Services.DatabaseHelper;
import spark.Session;

import java.sql.SQLException;
import static spark.Spark.*;

public class Main {

    public static void main(String[] args) throws SQLException {

        staticFiles.location("/public");

        DatabaseHelper.startDatabase();
        Database.getInstance().testConnection();
        DatabaseHelper.createAdmin();

        new IndexRoute().start();
        new UserRoute().start();
        new TagRoute().start();
        new ArticleRoute().start();

        before((request, response) -> {

            if (request.cookie("USERID") != null) {

                String id = request.cookie("USERID");
                User user = UserHandler.getInstance().find(id);

                Session session = request.session(true);
                session.attribute("currentUser", user);
            }

        });
    }
}
