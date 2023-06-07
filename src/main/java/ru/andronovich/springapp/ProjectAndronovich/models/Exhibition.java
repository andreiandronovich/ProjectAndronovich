package ru.andronovich.springapp.ProjectAndronovich.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Exhibition")
public class Exhibition {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "description")
    private String description;

    @Column (name = "date")
    private String date;

    @Column(name = "image")
    private String image;

    @Column(name = "text")
    private String text;

    @Column(name = "article")
    private String article;

    @Column(name = "amount ")
    private String amount;

    @Column(name = "address")
    private String address;

    @Column(name = "header")
    private String header;

    @ManyToMany(mappedBy = "exhibitions", fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.DETACH,CascadeType.PERSIST,CascadeType.MERGE})
    private List<Person> people;


    public Exhibition(String description) {
        this.description = description;
    }

    public Exhibition() {

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Person> getPeople() {
        return people;
    }

    public void setPeople(List<Person> people) {
        this.people = people;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Exhibition that = (Exhibition) o;
        return id == that.id && Objects.equals(description, that.description) && Objects.equals(date, that.date) && Objects.equals(image, that.image) && Objects.equals(text, that.text) && Objects.equals(article, that.article) && Objects.equals(amount, that.amount) && Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, date, image, text, article, amount, address);
    }
}
