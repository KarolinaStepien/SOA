package DatabaseOperations;

import Entities.Author;
import Entities.Book;
import com.sun.istack.Nullable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.*;
import java.util.List;

public class BookDBO {
    private static final String PERSISTENCE_UNIT_NAME = "libraryApp";
    private static EntityManager entityManager = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();
    private static EntityTransaction entityTransaction = entityManager.getTransaction();

    public static List getAllDetails() {
        Query query = entityManager.createQuery("SELECT s FROM Book s");
        @Nullable
        List list = query.getResultList();
        return list;
    }

    public static Book getBook(int id) throws Exception {
        TypedQuery<Book> user = entityManager.createQuery("SELECT a FROM Book a where a.id =:id", Book.class).setParameter("id", id);
        return user.getSingleResult();
    }

    public static String addBook(int authorId, String title) {
        try {
            if (!entityTransaction.isActive()) {
                entityTransaction.begin();
            }
            Author author = AuthorDBO.getAuthor(authorId);
            Book book = new Book(title, author);
            entityManager.persist(book);
            entityTransaction.commit();
            return "index.xhtml?faces-redirect=true";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage("error", new FacesMessage("Try again!"));
            return "index.xhtml";
        }
    }

    public static String deleteBook(int id) {
        try {
            if (!entityTransaction.isActive()) {
                entityTransaction.begin();
            }
            Book book = new Book();
            if (bookIdExists(id)) {
                book.setBookId(id);
                entityManager.remove(entityManager.merge((book)));
            }
            entityTransaction.commit();
            return "index.xhtml?faces-redirect=true";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage("error", new FacesMessage("Try again!"));
            return "index.xhtml";
        }
    }

    private static boolean bookIdExists(int id) {
        boolean idResult = false;
        Query queryObj = entityManager.createQuery("SELECT s FROM Book s WHERE s.id = :id");
        queryObj.setParameter("id", id);
        Book selectedId = (Book) queryObj.getSingleResult();
        if(selectedId != null) {
            idResult = true;
        }
        return idResult;
    }
}
