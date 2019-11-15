package Models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Entity
public class Comment implements Serializable {

    @Id
    private String id;
    private String comment;
    @ManyToOne
    private User author;
    @ManyToOne
    private Article article;

    public Comment() {

    }

    public Comment(String id, String comment, User author, Article article) {
        this.id = id;
        this.comment = comment;
        this.author = author;
        this.article = article;
    }

    public String getId() {
        return id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

}
