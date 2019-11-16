package Handlers;

import Models.Article;
import Models.Recommendation;
import Services.GestionDb;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class RecommendationHandler extends GestionDb<Recommendation> {

    private static RecommendationHandler instance;

    private RecommendationHandler() {
        super(Recommendation.class);
    }

    public static RecommendationHandler getInstance() {
        if (instance == null) {
            instance = new RecommendationHandler();
        }
        return instance;
    }

    public int numberOfRecommendations(Article article, Boolean criteria) {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("select r from Recommendation r where r.recommendationId.article = :article and r.isLike = :criteria");
        query.setParameter("article", article);
        query.setParameter("criteria", criteria);
        List<Recommendation> recommendations = query.getResultList();
        return recommendations.size();
    }

}
