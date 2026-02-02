package com.company.models;

public class Book {
    private int id;
    private String title;
    private int authorId;
    private int publishedYear;
    private String authorName;
    private String categoryName;


    public Book(int id, String title, int authorId, int publishedYear) {
        this.id = id;
        this.title = title;
        this.authorId = authorId;
        this.publishedYear = publishedYear;
    }

    public String getTitle() {
        return title;
    }

    public int getAuthorId() {

        return authorId;
    }

    public int getPublishedYear() { return publishedYear; }

    public void setAuthorName(String authorName) {

    }
    public void setCategoryName(String categoryName) {
    }

    @Override
    public String toString() {
        return "Book ID: " + id +
                " | Title: '" + (title == null ? "N/A" : title) + '\'' +
                " | Year: " + (publishedYear == 0 ? "Unknown" : publishedYear) +
                " | Author: " + (authorName == null ? "ID " + authorId : authorName);
    }
}
