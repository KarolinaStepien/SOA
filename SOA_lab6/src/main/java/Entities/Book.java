package Entities;

import javax.persistence.*;

@Entity
public class Book {
    @Id
    @GeneratedValue
    private int bookId;
    private String bookTitle;
    @ManyToOne
    @JoinColumn(name = "authorId")
    private Author bookAuthor;

    public Book() {
    }

    public Book(String bookTitle, Author bookAuthor) {
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public Author getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(Author bookAuthor) {
        this.bookAuthor = bookAuthor;
    }
}
