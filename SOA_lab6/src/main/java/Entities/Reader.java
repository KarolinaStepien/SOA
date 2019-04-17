package Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Reader {
    @Id
    @GeneratedValue
    private int readerId;
    private String readerName;
    private String readerSurname;

    public Reader() {
    }

    public Reader(String readerName, String readerSurname) {
        this.readerName = readerName;
        this.readerSurname = readerSurname;
    }

    public int getReaderId() {
        return readerId;
    }

    public void setReaderId(int readerId) {
        this.readerId = readerId;
    }

    public String getReaderName() {
        return readerName;
    }

    public void setReaderName(String readerName) {
        this.readerName = readerName;
    }

    public String getReaderSurname() {
        return readerSurname;
    }

    public void setReaderSurname(String readerSurname) {
        this.readerSurname = readerSurname;
    }
}
