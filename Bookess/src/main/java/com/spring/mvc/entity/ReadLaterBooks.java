package com.spring.mvc.entity;

import javax.persistence.*;

@Entity
@Table(name = "readLaterBooks")
public class ReadLaterBooks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    private String isbn;
    private String genre;
    private String description;
    private String coverImage;
    private double rating;
    private double price;

    // getters and setters

    public ReadLaterBooks() {

    }

    public ReadLaterBooks(Long id,String title, String author, String isbn, String genre, String description, double rating, double price, String coverImage) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.genre = genre;
        this.description = description;
        this.rating = rating;
        this.price = price;
        this.coverImage = coverImage;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ReadLaterBooks{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                ", genre='" + genre + '\'' +
                ", description='" + description + '\'' +
                ", coverImage='" + coverImage + '\'' +
                ", rating=" + rating +
                ", price=" + price +
                '}';
    }
}
