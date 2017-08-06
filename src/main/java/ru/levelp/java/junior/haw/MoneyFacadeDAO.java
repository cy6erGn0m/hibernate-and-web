package ru.levelp.java.junior.haw;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.Date;

@Service
public class MoneyFacadeDAO {
    private final EntityManager em;
    private final PasswordEncoder encoder;

    @Autowired
    public MoneyFacadeDAO(EntityManager em, PasswordEncoder encoder) {
        this.em = em;
        this.encoder = encoder;
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
        user.setEncryptedPassword(encoder.encode(User.RootDefaultPassword));

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

    public void emitMoney(double amount) {
        if (amount < 0.0) throw new IllegalArgumentException();

        em.getTransaction().begin();
        try {
            User root = findUser(User.RootUserName);
            if (root == null) throw new IllegalStateException("No root user");

            Transaction t = new Transaction();
            t.setDate(new Date());
            t.setAmount(amount);
            t.setUser(root);
            t.setTarget(root);

            em.persist(t);
            em.refresh(root);

            root.setBalance(root.getBalance() + amount);

            em.getTransaction().commit();
        } catch (Throwable t) {
            em.getTransaction().rollback();
            throw new IllegalStateException(t);
        }
    }
}
