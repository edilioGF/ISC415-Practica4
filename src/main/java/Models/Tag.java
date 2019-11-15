package Models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Tag implements Serializable {

    @Id
    private String id;
    private String name;
    @ManyToMany
    private Set<Article> articles;


    public Tag() {

    }

    public Tag(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Article> getArticles() {
        return articles;
    }

    public void setTags(Set<Article> articles) {
        this.articles = articles;
    }
}
