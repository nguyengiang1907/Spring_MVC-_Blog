package com.example.spring_mvc_jpa_blog.model.client;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "blog")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 1000)
    private String title;
    @Column(length = 5000)
    private String content;
    private String dateSubmitted;
    @Column(length = 1000)
    private String author;
    private String picture;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDateSubmitted() {
        return dateSubmitted;
    }

    public void setDateSubmitted(String dateSubmitted) {
        this.dateSubmitted = dateSubmitted;
    }

    public Blog() {
    }
    public String getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", dateSubmitted='" + dateSubmitted + '\'' +
                ", author='" + author + '\'' +
                '}';
    }

    public void setAuthor(String author) {
        this.author = author;
    }


    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Blog(int id, String title, String content, String dateSubmitted, String author, String picture) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.dateSubmitted = dateSubmitted;
        this.author = author;
        this.picture = picture;
    }

    public Blog(String title, String content, String dateSubmitted, String author, String picture, User user) {
        this.title = title;
        this.content = content;
        this.dateSubmitted = dateSubmitted;
        this.author = author;
        this.picture = picture;
        this.user = user;
    }
}
