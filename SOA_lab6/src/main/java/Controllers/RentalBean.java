package Controllers;

import DatabaseOperations.RentalDBO;
import Entities.Book;
import Entities.Reader;

import javax.faces.bean.ManagedBean;
import java.util.List;

@ManagedBean(name = "rental")
public class RentalBean {
    private int rentalId;
    private Book book;
    private Reader reader;
    private String rentalDate;
    private String returnDate;

    private int bookId;
    private int readerId;

    public List getAllDetails() {
        return RentalDBO.getAllDetails();
    }

    public String addRental() {
        return RentalDBO.addRental(bookId, readerId, rentalDate, returnDate);
    }

    public String deleteRental(int id) {
        return RentalDBO.deleteRental(id);
    }

    //getters & setters
    public int getRentalId() {
        return rentalId;
    }

    public void setRentalId(int rentalId) {
        this.rentalId = rentalId;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public String getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(String rentalDate) {
        this.rentalDate = rentalDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getReaderId() {
        return readerId;
    }

    public void setReaderId(int readerId) {
        this.readerId = readerId;
    }
}
