package DatabaseOperations;

import Entities.Book;
import Entities.Reader;
import Entities.Rental;
import com.sun.istack.Nullable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.*;
import java.util.List;

public class RentalDBO {
    private static final String PERSISTENCE_UNIT_NAME = "libraryApp";
    private static EntityManager entityManager = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();
    private static EntityTransaction entityTransaction = entityManager.getTransaction();

    public static List getAllDetails() {
        Query query = entityManager.createQuery("SELECT s FROM Rental s");
        @Nullable
        List list = query.getResultList();
        return list;
    }

    public static Rental getRental(int id) throws Exception {
        TypedQuery<Rental> user = entityManager.createQuery("SELECT a FROM Rental a where a.id =:id", Rental.class).setParameter("id", id);
        return user.getSingleResult();
    }

    public static String addRental(int bookId, int readerId, String rentalDate, String returnDate) {
        try {
            if (!entityTransaction.isActive()) {
                entityTransaction.begin();
            }
            Book book = BookDBO.getBook(bookId);
            Reader reader = ReaderDBO.getReader(readerId);

            Rental rental = new Rental(book, reader, rentalDate, returnDate);
            entityManager.persist(rental);
            entityTransaction.commit();
            return "index.xhtml?faces-redirect=true";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage("error", new FacesMessage("Try again!"));
            return "index.xhtml";
        }
    }

    public static String deleteRental(int id) {
        try {
            if (!entityTransaction.isActive()) {
                entityTransaction.begin();
            }
            Rental rental = new Rental();
            if (rentalIdExists(id)) {
                rental.setRentalId(id);
                entityManager.remove(entityManager.merge((rental)));
            }
            entityTransaction.commit();
            return "index.xhtml?faces-redirect=true";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage("error", new FacesMessage("Try again!"));
            return "index.xhtml";
        }
    }

        private static boolean rentalIdExists(int id) {
            boolean idResult = false;
            Query queryObj = entityManager.createQuery("SELECT s FROM Rental s WHERE s.id = :id");
            queryObj.setParameter("id", id);
            Rental selectedId = (Rental) queryObj.getSingleResult();
            if(selectedId != null) {
                idResult = true;
            }
            return idResult;
        }
}
