package Models;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@Entity
public class Article implements Serializable {

    @Id
    private String id;
    private String title;
    @Column(columnDefinition="TEXT")
    private String body;
    @ManyToOne
    private User author;
    private Timestamp date;
    @OneToMany(mappedBy = "article", fetch = FetchType.EAGER)
    private Set<Comment> comments;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "ARTICLE_TAG", joinColumns = @JoinColumn(name = "ARTICLES_ID"), inverseJoinColumns = @JoinColumn(name = "TAGS_ID"))
    private Set<Tag> tags;

    public Article() {

    }

    public Article(String id, String title, String body, User author, Timestamp date, Set<Comment> comments, Set<Tag> tags) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.author = author;
        this.date = date;
        this.comments = comments;
        this.tags = tags;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }
}
