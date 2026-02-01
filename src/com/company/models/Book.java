package com.company.models;

public class Book {
    private int id;
    private String title;
    private int authorId;
    private String authorName;
    private String categoryName;

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
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return String.format("Book: %s | Author: %s | Category: %s",
                title,
                (authorName != null ? authorName : authorId),
                (categoryName != null ? categoryName : "None"));
    }
}
