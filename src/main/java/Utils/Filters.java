package Utils;

import Models.User;
import spark.Session;

import static spark.Spark.before;

public final class Filters {

    public Filters() {

    }

    public static void filterLoggedIn(String subdomain) {

        before(subdomain, (request, response) -> {

            Session session = request.session(true);
            User currentUser = session.attribute("currentUser");

            if (currentUser == null) {

                response.redirect("/");
            }
        });
    }

    public static void filterAdmin(String subdomain) {

        before(subdomain, (request, response) -> {

            Session session = request.session(true);
            User currentUser = session.attribute("currentUser");

            if (currentUser == null || !currentUser.isAdmin()) {

                response.redirect("/");
            }
        });
    }

    public static void filterAuthor(String subdomain) {

        before(subdomain, (request, response) -> {

            Session session = request.session(true);
            User currentUser = session.attribute("currentUser");

            if (currentUser == null || !currentUser.isAuthor()) {

                response.redirect("/");
            }
        });
    }

}
