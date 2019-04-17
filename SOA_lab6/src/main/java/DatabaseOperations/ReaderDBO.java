package DatabaseOperations;

import Entities.Reader;
import com.sun.istack.Nullable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.*;
import java.util.List;

public class ReaderDBO {
    private static final String PERSISTENCE_UNIT_NAME = "libraryApp";
    private static EntityManager entityManager = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();
    private static EntityTransaction entityTransaction = entityManager.getTransaction();

    public static List getAllDetails() {
        Query query = entityManager.createQuery("SELECT s FROM Reader s");
        @Nullable
        List list = query.getResultList();
        return list;
    }

    public static Reader getReader(int id) throws Exception {
        TypedQuery<Reader> user = entityManager.createQuery("SELECT a FROM Reader a where a.id =:id", Reader.class).setParameter("id", id);
        return user.getSingleResult();
    }

    public static String addReader(String name, String surname) {
        try {
            if (!entityTransaction.isActive()) {
                entityTransaction.begin();
            }
            Reader reader = new Reader(name, surname);
            entityManager.persist(reader);
            entityTransaction.commit();
            return "index.xhtml?faces-redirect=true";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage("error", new FacesMessage("Try again!"));
            return "index.xhtml";
        }
    }

    public static String deleteReader(int id) {
        try {
            if (!entityTransaction.isActive()) {
                entityTransaction.begin();
            }
            Reader reader = new Reader();
            if (readerIdExists(id)) {
                reader.setReaderId(id);
                entityManager.remove(entityManager.merge((reader)));
            }
            entityTransaction.commit();
            return "index.xhtml?faces-redirect=true";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage("error", new FacesMessage("Try again!"));
            return "index.xhtml";
        }
    }

    private static boolean readerIdExists(int id) {
        boolean idResult = false;
        Query queryObj = entityManager.createQuery("SELECT s FROM Reader s WHERE s.id = :id");
        queryObj.setParameter("id", id);
        Reader selectedId = (Reader) queryObj.getSingleResult();
        if(selectedId != null) {
            idResult = true;
        }
        return idResult;
    }
}
