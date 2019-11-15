package Routes;

import Handlers.ArticleHandler;
import Handlers.CommentHandler;
import Handlers.TagHandler;
import Models.Article;
import Models.Comment;
import Models.Tag;
import Models.User;
import Utils.Filters;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

import java.sql.Timestamp;
import java.util.*;

import static spark.Spark.get;
import static spark.Spark.post;

public class ArticleRoute {

    public void start() {

        Map<String, Object> responseData = new HashMap<>();

        get("/article/view/:id", (request, response) -> {

            String id = request.params("id");

            User currentUser = request.session().attribute("currentUser");
            responseData.put("currentUser", currentUser);
            responseData.put("article", ArticleHandler.getInstance().find(id));

            return new FreeMarkerEngine().render(new ModelAndView(responseData, "view_article.ftl"));
        });

        Filters.filterAuthor("/article/write");
        get("/article/write", (request, response) -> {

            User currentUser = request.session().attribute("currentUser");
            String id = UUID.randomUUID().toString();
            Article article = new Article(id, "", "", currentUser, new Timestamp(new Date().getTime()), null, null);
            responseData.put("currentUser", currentUser);
            responseData.put("tags", TagHandler.getInstance().findAll());
            responseData.put("action", "Write");
            responseData.put("article", article);

            return new FreeMarkerEngine().render(new ModelAndView(responseData, "input_article.ftl"));
        });

        post("/article/write", (request, response) -> {

            responseData.clear();

            String title = request.queryParams("title");
            String body = request.queryParams("body");
            String[] tagNames = request.queryParamsValues("tagName");
            String[] tagIds = request.queryParamsValues("tagId");
            String id = UUID.randomUUID().toString();

            User currentUser = request.session().attribute("currentUser");
            Set<Tag> tags = new HashSet<>();
            Set<Comment> comments = new HashSet<>();

            if (tagNames != null && tagIds != null) {
                for (int i = 0; i < tagIds.length; i++) tags.add(new Tag(tagIds[i], tagNames[i]));
            }

            Article article = new Article(id, title, body, currentUser, new Timestamp(new Date().getTime()), comments, tags);

            if ((title == null || title.isEmpty())
                || (body == null || body.isEmpty())) {
                responseData.put("error", "Fields can't be empty");
                responseData.put("article", article);
                response.redirect("/article/write");
            } else {
                ArticleHandler.getInstance().crear(article);
                response.redirect(String.format("/article/view/%s",article.getId()));
            }

            return "";
        });

        Filters.filterLoggedIn("/article/comment/:id");
        post("/article/comment/:id", (request, response) -> {

            responseData.clear();

            User currentUser = request.session().attribute("currentUser");
            Article article = ArticleHandler.getInstance().find(request.params("id"));
            String commentBody = request.queryParams("comment");

            if (commentBody != null && !commentBody.isEmpty()) {

                String id = UUID.randomUUID().toString();
                Comment comment = new Comment(id, commentBody, currentUser, article);
                CommentHandler.getInstance().crear(comment);
                response.redirect(String.format("/article/view/%s", article.getId()));
            }

            return "";
        });

        Filters.filterAuthor("/article/delete/:id");
        Filters.filterAdmin("/article/delete/:id");
        get("/article/delete/:id", (request, response) -> {

            responseData.clear();

            String articleId = request.params("id");
            Article article = ArticleHandler.getInstance().find(articleId);

            if (article != null) {
                ArticleHandler.getInstance().eliminar(article.getId());
                responseData.put("info", "Article deleted successfully");
            } else {
                responseData.put("error", "Article not found");
            }

            response.redirect("/");
            return "";
        });

        Filters.filterAuthor("/article/edit/:id");
        get("/article/edit/:id", (request, response) -> {

            User currentUser = request.session().attribute("currentUser");
            String id = request.params("id");
            Article article = ArticleHandler.getInstance().find(id);
            responseData.put("currentUser", currentUser);
            responseData.put("tags", TagHandler.getInstance().findAll());
            responseData.put("action", "Edit");
            responseData.put("article", article);

            return new FreeMarkerEngine().render(new ModelAndView(responseData, "input_article.ftl"));
        });//taitel

        post("/article/edit/:id", (request, response) -> {

            responseData.clear();
            String id = request.params("id");

            String title = request.queryParams("title");
            String body = request.queryParams("body");
            Article article = ArticleHandler.getInstance().find(id);

            if ((title == null || title.isEmpty())
                    || (body == null || body.isEmpty())) {
                responseData.put("error", "Fields can't be empty");
                response.redirect(String.format("/article/edit/%s", id));
            } else {
                article.setTitle(title);
                article.setBody(body);
                ArticleHandler.getInstance().editar(article);
                responseData.put("success", "Article posted successfully");
                response.redirect(String.format("/article/view/%s", article.getId()));
            }
            return "";
        });
    }
}
