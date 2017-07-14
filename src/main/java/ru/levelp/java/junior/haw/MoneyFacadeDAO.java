package ru.levelp.java.junior.haw;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

public class MoneyFacadeDAO {
    private final EntityManager em;

    public MoneyFacadeDAO(EntityManager em) {
        this.em = em;
    }

    public User findUser(String login) {
        try {
            return em.createNamedQuery(User.FindByLogin, User.class)
                    .setParameter("login", login)
                    .getSingleResult();
        } catch (NoResultException notFound) {
            return null;
        }
    }

    public User createUser(String login) throws EntityExistsException {
        User user = new User();
        user.setLogin(login);
        user.setBalance(0.0);

        em.persist(user);

        return user;
    }

    public User ensureRootUser() {
        em.getTransaction().begin();

        try {
            User root = findUser(User.RootUserName);
            if (root == null) {
                root = createUser(User.RootUserName);
            }

            em.getTransaction().commit();

            return root;
        } catch (Throwable t) {
            em.getTransaction().rollback();
            throw new IllegalStateException(t);
        }
    }
}
