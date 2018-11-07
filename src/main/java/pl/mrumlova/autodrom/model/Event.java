package pl.mrumlova.autodrom.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @OneToMany(mappedBy = "category")
    private List<Message> offers;

    public boolean isSelected(Integer userId){
        if (userId != null) {
            return userId.equals(id);
        }
        return false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Message> getOffers() {
        return offers;
    }

    public void setOffers(List<Message> offers) {
        this.offers = offers;
    }
}
