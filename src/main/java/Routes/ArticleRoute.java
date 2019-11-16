package Routes;

import Handlers.ArticleHandler;
import Handlers.CommentHandler;
import Handlers.RecommendationHandler;
import Handlers.TagHandler;
import Models.*;
import Utils.Filters;
import Utils.Utils;
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
            Article article = ArticleHandler.getInstance().find(id);
            Recommendation recommendation = RecommendationHandler.getInstance().find(new RecommendationId(article, currentUser));
            Boolean userRecomendation = recommendation != null ? recommendation.getLike() : null;
            int likesTotal = RecommendationHandler.getInstance().numberOfRecommendations(article, true);
            int dislikesTotal = RecommendationHandler.getInstance().numberOfRecommendations(article, false);

            responseData.put("currentUser", currentUser);
            responseData.put("article", article);
            responseData.put("like", String.valueOf(userRecomendation));
            responseData.put("likesTotal", likesTotal);
            responseData.put("dislikesTotal", dislikesTotal);

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
            article.getTags().clear();
            ArticleHandler.getInstance().editar(article);
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
            String[] tagNames = request.queryParamsValues("tagName");
            String[] tagIds = request.queryParamsValues("tagId");
            Article article = ArticleHandler.getInstance().find(id);

            Set<Tag> tags = new HashSet<>();
            if (tagNames != null && tagIds != null) {
                for (int i = 0; i < tagIds.length; i++) tags.add(new Tag(tagIds[i], tagNames[i]));
            }

            if ((title == null || title.isEmpty())
                    || (body == null || body.isEmpty())) {
                responseData.put("error", "Fields can't be empty");
                response.redirect(String.format("/article/edit/%s", id));
            } else {
                article.setTitle(title);
                article.setBody(body);
                article.setTags(tags);
                ArticleHandler.getInstance().editar(article);
                responseData.put("success", "Article posted successfully");
                response.redirect(String.format("/article/view/%s", article.getId()));
            }
            return "";
        });

        post("/articles/:id/like", (request, response) -> {
            Article article = ArticleHandler.getInstance().find(request.params("id"));
            User user = request.session().attribute("currentUser");
            Utils.likeDislike(true, article, user);
            response.redirect("/article/view/" + request.params("id"));
            return "";
        });

        post("/articles/:id/dislike", (request, response) -> {
            Article article = ArticleHandler.getInstance().find(request.params("id"));
            User user = request.session().attribute("currentUser");
            Recommendation recommendation = RecommendationHandler.getInstance().find(new RecommendationId(article, user));
            RecommendationId recommendationId = new RecommendationId(article, user);

            Utils.likeDislike(false, article, user);

            response.redirect("/article/view/" + request.params("id"));
            return "";
        });

    }
}
