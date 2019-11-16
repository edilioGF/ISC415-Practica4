package Routes;

import Handlers.ArticleHandler;
import Handlers.LoginHandler;
import Handlers.UserHandler;
import Models.Article;
import Models.User;
import Utils.Filters;
import Utils.Parser;
import spark.ModelAndView;
import spark.Session;
import spark.template.freemarker.FreeMarkerEngine;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class IndexRoute {

    public void start() {

        Map<String, Object> responseData = new HashMap<>();

        get("/", (request, response) -> {

            responseData.clear();
            responseData.put("currentUser", request.session().attribute("currentUser"));

            int pageNumber = (request.queryParams("page") != null) ? Integer.parseInt(request.queryParams("page")) : 1;
            int articles = ArticleHandler.getInstance().findAll().size();
            List<Article> articlesList = ArticleHandler.getInstance().lazyFind(pageNumber);
            int totalPages = (int) (Math.ceil((double) articles / 5)) + 1;

            responseData.put("articles", articlesList);
            responseData.put("pages", totalPages);

            return new FreeMarkerEngine().render(new ModelAndView(responseData, "index.ftl"));
        });

        get("/login", (request, response) -> {

            if (request.session().attribute("currentUser") != null) {
                response.redirect("/");
                return "";
            }
            return new FreeMarkerEngine().render(new ModelAndView(responseData, "login.ftl"));
        });

        post("/login", (request, response) -> {

            responseData.clear();

            String username = request.queryParams("username");
            String password = request.queryParams("password");
            boolean remember = request.queryParams("remember") != null;

            if (!username.isEmpty() && !password.isEmpty()) {

                responseData.putAll(LoginHandler.login(username, password));

                if ((boolean) responseData.get("result")) {

                    User user = UserHandler.getInstance().findByUsername(username);

                    Session session = request.session(true);
                    session.attribute("currentUser", user);

                    if (remember) {
                        response.cookie("USERID", user.getId(), 604800);
                    }

                    response.redirect("/");
                } else {
                    response.redirect("/login");
                }
            } else {
                responseData.put("error", "Fields can't be empty");
                response.redirect("/login");
            }
            return "";
        });

        get("/resetpassword", (request, response) -> new FreeMarkerEngine().render(new ModelAndView(responseData, "reset_password.ftl")));

        post("/resetpassword", (request, response) -> {

            responseData.clear();

            String username = request.queryParams("username");
            String newPassword = request.queryParams("password");
            String newPasswordConfirm = request.queryParams("confirm-password");

            if ((username == null || username.trim().isEmpty())
                || (newPassword == null || newPassword.isEmpty())
                || (newPasswordConfirm == null || newPasswordConfirm.isEmpty())) {

                responseData.put("error", "Fields can't be empty");
                response.redirect("/resetpassword");

            } else if (!newPassword.equals(newPasswordConfirm)) {
                responseData.put("error", "Passwords do not match");
                response.redirect("/resetpassword");
            } else {

                User user = UserHandler.getInstance().findByUsername(username);

                if (user != null) {
                    user.setPassword(Parser.getHashedPassword(newPassword));
                    UserHandler.getInstance().editar(user);
                    responseData.put("success", "Password changed successfully");
                    response.redirect("/");

                } else {
                    responseData.put("error", String.format("We couldn't find any user with the username: %s", username));
                }
            }

            return "";
        });

        Filters.filterLoggedIn("/logout");
        get("/logout", (request, response) -> {

            responseData.clear();

            response.cookie("USERID", null, 0);
            Session session = request.session(true);
            session.invalidate();
            responseData.put("info", "Logged out");
            response.redirect("/");
            return "";
        });
    }
}
