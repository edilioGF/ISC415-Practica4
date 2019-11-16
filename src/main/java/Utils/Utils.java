package Utils;

import Handlers.RecommendationHandler;
import Models.Article;
import Models.Recommendation;
import Models.RecommendationId;
import Models.User;

public final class Utils {

    public static void likeDislike(Boolean likeDislike, Article article, User user) {
        Recommendation recommendation = RecommendationHandler.getInstance().find(new RecommendationId(article, user));

        RecommendationId recommendationId = new RecommendationId(article, user);
        if (recommendation == null) {
            recommendation = new Recommendation(recommendationId, likeDislike);
            RecommendationHandler.getInstance().crear(recommendation);
        } else if (recommendation.getLike() == !likeDislike) {
            recommendation = new Recommendation(recommendationId, likeDislike);
            RecommendationHandler.getInstance().editar(recommendation);
        } else {
            RecommendationHandler.getInstance().eliminar(recommendationId);
        }
    }


}
