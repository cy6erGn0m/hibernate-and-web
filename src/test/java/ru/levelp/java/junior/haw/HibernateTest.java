package ru.levelp.java.junior.haw;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceUnit;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class HibernateTest {
    @Autowired
    @PersistenceUnit
    private EntityManager em;

    @Autowired
    private MoneyFacadeDAO facade;

    @Test
    public void testUser() throws Exception {
        em.getTransaction().begin();

        User user = new User();
        user.setLogin("root");
        user.setBalance(1.0);

        em.persist(user);

        em.getTransaction().commit();

        assertEquals("root", em.find(User.class, user.getUserId()).getLogin());
    }

    @Test
    public void testTransactions() throws Exception {
        em.getTransaction().begin();

        User user = new User();
        user.setLogin("root");
        user.setBalance(1.0);

        em.persist(user);

        Transaction t = new Transaction();
        t.setDate(new Date());
        t.setAmount(10);
        t.setUser(user);
        t.setTarget(user);

        em.persist(t);

        user.setBalance(10.0);

        em.getTransaction().commit();

        em.refresh(user);

        List<Transaction> transactions = user.getTransactions();
        assertNotNull(transactions);
        assertEquals(1, transactions.size());
        assertEquals(t, transactions.get(0));
    }

    @Test
    public void testEnsureRootUSer() throws Exception {
        User user = facade.ensureRootUser();
        User user2 = facade.ensureRootUser();

        assertSame(user, user2);
    }

    @Test
    public void testEmitMoney() throws Exception {
        facade.ensureRootUser();

        facade.emitMoney(10.0);

        assertEquals(10.0, facade.findUser(User.RootUserName).getBalance(), 0.01);
        assertEquals(10.0, facade.findUser(User.RootUserName).getTransactions().get(0).getAmount(), 0.01);

        facade.emitMoney(7.0);

        assertEquals(17.0, facade.findUser(User.RootUserName).getBalance(), 0.01);
        assertEquals(7, facade.findUser(User.RootUserName).getTransactions().get(1).getAmount(), 0.01);
    }
}
