package Models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Recommendation implements Serializable {
    @EmbeddedId
    private RecommendationId recommendationId;
    private Boolean isLike;

    public Recommendation(RecommendationId recommendationId, Boolean isLike) {
        this.recommendationId = recommendationId;
        this.isLike = isLike;
    }

    public RecommendationId getRecommendationId() {
        return recommendationId;
    }

    public void setRecommendationId(RecommendationId recommendationId) {
        this.recommendationId = recommendationId;
    }

    public Boolean getLike() {
        return isLike;
    }

    public void setLike(Boolean like) {
        isLike = like;
    }

    public Recommendation() {

    }

}
