package Controllers;

import DatabaseOperations.BookDBO;
import Entities.Author;

import javax.faces.bean.ManagedBean;
import java.util.List;

@ManagedBean(name = "book")
public class BookBean {
    private int bookId;
    private String bookTitle;
    private Author bookAuthor;

    private int authorId;

    public List getAllDetails() {
        return BookDBO.getAllDetails();
    }

    public String addBook() {
        return BookDBO.addBook(authorId, bookTitle);
    }

    public String deleteBook(int id) {
        return BookDBO.deleteBook(id);
    }

    //getters & setters
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

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }
}
