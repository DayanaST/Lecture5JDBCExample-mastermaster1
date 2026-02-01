package com.company.models;

public class Book {
    private int id;
    private String title;
    private int authorId;
    private int publishedYear;

    public Book(int id, String title, int authorId, int publishedYear, String authorName, String categoryName) {
        this.id = id;
        this.title = title;
        this.authorId = authorId;
        this.publishedYear = publishedYear;
    }

    public Book(int id, String title, int authorId, int publishedYear) {
    }

    public String getTitle() {
        return title;
    }

    public int getAuthorId() {

        return authorId;
    }

    public int getPublishedYear() { return publishedYear; }
    public void setPublishedYear(int publishedYear) { this.publishedYear = publishedYear; }

    public void setAuthorName(String authorName) {

    }
    public void setCategoryName(String categoryName) {
    }
    @Override
    public String toString() {
        return "Book ID: " + id +
                " | Title: '" + title + '\'' +
                " | Year: " + (publishedYear == 0 ? "Unknown" : publishedYear) +
                " | Author ID: " + authorId;
    }
}
