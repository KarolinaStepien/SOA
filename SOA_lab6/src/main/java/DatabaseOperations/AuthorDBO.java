package DatabaseOperations;

import Entities.Author;
import Entities.Book;
import com.sun.istack.Nullable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class AuthorDBO {
    private static final String PERSISTENCE_UNIT_NAME = "libraryApp";
    private static EntityManager entityManager = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();
    private static EntityTransaction entityTransaction = entityManager.getTransaction();

    public static List getAllDetails() {
        //JPQL
        Query query = entityManager.createQuery("SELECT s FROM Author s");
        @Nullable
        List list = query.getResultList();
        return list;

        //        //Criteria API
//        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Author> criteriaQuery = criteriaBuilder.createQuery(Author.class);
//
//        Root<Author> root = criteriaQuery.from(Author.class);
//        criteriaQuery.select(root);
//
//        TypedQuery<Author> typedQuery = entityManager.createQuery(criteriaQuery);
//        List list = typedQuery.getResultList();
//        if (list != null && list.size() > 0){
//            return list;
//        }
//        else {
//            return null;
//        }
    }

    public static Author getAuthor(int id) throws Exception {
        TypedQuery<Author> user = entityManager.createQuery("SELECT a FROM Author a where a.id =:id", Author.class).setParameter("id", id);
        return user.getSingleResult();
    }

    public static String addAuthor(String name, String surname) {
        try {
            if (!entityTransaction.isActive()) {
                entityTransaction.begin();
            }
            Author author = new Author(name, surname);
            entityManager.persist(author);
            entityTransaction.commit();
            return "index.xhtml?faces-redirect=true";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage("error", new FacesMessage("Try again!"));
            return "index.xhtml";
        }
    }

    public static String deleteAuthor(int id) {
        try {
            if (!entityTransaction.isActive()) {
                entityTransaction.begin();
            }
            Author author = new Author();
            if (authorIdExists(id)) {
                author.setAuthorId(id);
                entityManager.remove(entityManager.merge((author)));
            }
            entityTransaction.commit();
            return "index.xhtml?faces-redirect=true";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage("error", new FacesMessage("Try again!"));
            return "index.xhtml";
        }
    }

    private static boolean authorIdExists(int id) {
        boolean idResult = false;
        Query queryObj = entityManager.createQuery("SELECT s FROM Author s WHERE s.id = :id");
        queryObj.setParameter("id", id);
        Author selectedId = (Author) queryObj.getSingleResult();
        if(selectedId != null) {
            idResult = true;
        }
        return idResult;
    }
}
