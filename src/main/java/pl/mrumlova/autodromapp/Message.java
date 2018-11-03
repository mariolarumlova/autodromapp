package pl.mrumlova.autodromapp;

import java.sql.Date;

public class Message {

    private long id;
    private String name;
    private String surname;
    private Date beginDate;
    private Date endDate;
    private String city;
    private String phoneNumber;
    private String email;
    private String celebrity;
    private long categoryId;
    private Event category;

    public Message() {
    }

    public Message(String name, long categoryId) {
        this.name = name;
        this.categoryId = categoryId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public Event getCategory() {
        return category;
    }

    public void setCategory(Event category) {
        this.category = category;
    }
}
