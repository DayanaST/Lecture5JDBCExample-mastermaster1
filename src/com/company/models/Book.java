package com.company.models;

public class Book {
    private int id;
    private String title;
    private int authorId;
    private String authorName;

    public Book(int id, String title, int authorId) {
        this.id = id;
        this.title = title;
        this.authorId = authorId;
    }

    public String getTitle() {
        return title;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    @Override
    public String toString() {
        return "Book: " + title + " | Author: " +
                (authorName != null ? authorName : "ID " + authorId);
    }
}
