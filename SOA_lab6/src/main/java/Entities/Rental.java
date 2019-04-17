package Entities;

import javax.persistence.*;

@Entity
public class Rental {
    @Id
    @GeneratedValue
    private int rentalId;
    @OneToOne
    @JoinColumn(name = "bookId")
    private Book book;
    @OneToOne
    @JoinColumn(name = "readerId")
    private Reader reader;
    private String rentalDate;
    private String returnDate;

    public Rental() {
    }

    public Rental(Book book, Reader reader, String rentalDate, String returnDate) {
        this.book = book;
        this.reader = reader;
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
    }

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
}
