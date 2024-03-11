package com.example.spring_mvc_jpa_blog.model.client;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;

public class BlogForm {
    private int id;

    private String title;
    private String content;
    private String dateSubmitted;
    private String author;
    private MultipartFile picture;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public MultipartFile getPicture() {
        return picture;
    }

    public void setPicture(MultipartFile picture) {
        this.picture = picture;
    }

    public BlogForm(int id, String title, String content, String dateSubmitted, String author, MultipartFile picture) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.dateSubmitted = dateSubmitted;
        this.author = author;
        this.picture = picture;
    }

    public BlogForm(String title, String content, String dateSubmitted, String author, MultipartFile picture, User user) {
        this.title = title;
        this.content = content;
        this.dateSubmitted = dateSubmitted;
        this.author = author;
        this.picture = picture;
        this.user = user;
    }

    public BlogForm() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
