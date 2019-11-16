package Models;

import javax.persistence.Embeddable;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Embeddable
public class RecommendationId implements Serializable {
    @ManyToOne
    private Article article;
    @OneToOne
    private User user;

    public RecommendationId(Article article, User user) {
        this.article = article;
        this.user = user;
    }

    public RecommendationId() {

    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
