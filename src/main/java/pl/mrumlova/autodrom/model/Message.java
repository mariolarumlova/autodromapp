package pl.mrumlova.autodrom.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Long obiektowe, bo może być null
    private String name;
    private String surname;
    private String beginDate;
    private String endDate;
    private String saveDate;
    private String updateDate;
    private String city;
    private String phoneNumber;
    private String email;
    private String celebrity;
    @Column(length = 2048)
    private String description;
    //private Long categoryId;
    @ManyToOne
    private Event category;

    public Message() {
        this.name = "imie";
        //this.categoryId = Long.parseLong(Integer.toString(1));
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getSaveDate() {
        return saveDate;
    }

    public void setSaveDate(String saveDate) {
        this.saveDate = saveDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCelebrity() {
        return celebrity;
    }

    public void setCelebrity(String celebrity) {
        this.celebrity = celebrity;
    }

//    public int getCategoryId() {
//        return Integer.parseInt(categoryId.toString());
//    }
//
//    public void setCategoryId(int categoryId) {
//        this.categoryId = Long.parseLong(Integer.toString(categoryId));
//    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Event getCategory() {
        return category;
    }

    public void setCategory(Event category) {
        this.category = category;
    }
}
