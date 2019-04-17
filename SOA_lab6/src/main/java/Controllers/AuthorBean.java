package Controllers;

import DatabaseOperations.AuthorDBO;

import javax.faces.bean.ManagedBean;
import java.util.List;

@ManagedBean(name = "author")
public class AuthorBean {
    private int authorId;
    private String authorName;
    private String authorSurname;

    public List getAllDetails() {
        return AuthorDBO.getAllDetails();
    }

    public String addAuthor() {
        return AuthorDBO.addAuthor(authorName, authorSurname);
    }

    public String deleteAuthor(int id) {
        return AuthorDBO.deleteAuthor(id);
    }

    //getters & setters
    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorSurname() {
        return authorSurname;
    }

    public void setAuthorSurname(String authorSurname) {
        this.authorSurname = authorSurname;
    }
}
