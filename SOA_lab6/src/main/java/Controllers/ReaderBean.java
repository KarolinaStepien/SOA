package Controllers;

import DatabaseOperations.ReaderDBO;

import javax.faces.bean.ManagedBean;
import java.util.List;

@ManagedBean(name = "reader")
public class ReaderBean {
    private int readerId;
    private String readerName;
    private String readerSurname;

    public List getAllDetails() {
        return ReaderDBO.getAllDetails();
    }

    //getters & setters
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
