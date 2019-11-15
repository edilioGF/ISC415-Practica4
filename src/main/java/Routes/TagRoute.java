package Routes;

import Handlers.ArticleHandler;
import Handlers.TagHandler;
import Models.Tag;
import Utils.Filters;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static spark.Spark.*;

public class TagRoute {

    public void start() {

        Map<String, Object> responseData = new HashMap<>();

        Filters.filterAuthor("/tags");
        get("/tags", (request, response) -> {

            responseData.put("currentUser", request.session().attribute("currentUser"));
            responseData.put("tags", TagHandler.getInstance().findAll());

            return new FreeMarkerEngine().render(new ModelAndView(responseData, "tags.ftl"));
        });

        Filters.filterAuthor("/tags/register");
        post("/tags/register", (request, response) -> {

            String tagName = request.queryParams("tag");

            if (tagName == null || tagName.isEmpty()) {
                responseData.put("error", "Tag name can't be empty");
                response.redirect("/tags");
            } else if (tagName.length() > 32) {
                responseData.put("error", "Tag name can't have more than 32 characters");
                response.redirect("/tags");
            } else {
                String id = UUID.randomUUID().toString();
                Tag newTag = new Tag(id, tagName);
                TagHandler.getInstance().crear(newTag);
                responseData.put("success", "Tag registered successfully");
                response.redirect("/tags");
            }

            return "";
        });

        Filters.filterAuthor("/tags/delete/:id");
        get("/tags/delete/:id", (request, response) -> {

            responseData.clear();
            String id = request.params("id");
            Tag deleteTag = TagHandler.getInstance().find(id);

            if (deleteTag != null) {
                TagHandler.getInstance().eliminar(deleteTag.getId());
                responseData.put("success", String.format("Tag deleted: %s", deleteTag.getName()));
            } else {
                responseData.put("error", "Tag was not found");
            }

            response.redirect("/tags");
            return "";
        });
    }
}
