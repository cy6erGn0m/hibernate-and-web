package ru.levelp.java.junior.haw;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class HibernateTest {
    @Autowired
    private PostMessage post;

    @Autowired
    private EntityManager em;

    @Autowired
    private MoneyFacadeDAO dao;

    @Test
    public void testSocialNetwork() throws Exception {
        post.postToEvery("test me");
        post.postToFacebook("facebook");
    }

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
    @Transactional
    public void testEnsureRootUSer() throws Exception {
        User user = dao.ensureRootUser();
        User user2 = dao.ensureRootUser();

        assertSame(user, user2);
    }

    @Test
    @Transactional
    public void testEmitMoney() throws Exception {
        dao.ensureRootUser();

        dao.emitMoney(10.0);

        assertEquals(10.0, dao.findUser(User.RootUserName).getBalance(), 0.01);
        assertEquals(10.0, dao.findUser(User.RootUserName).getTransactions().get(0).getAmount(), 0.01);

        dao.emitMoney(7.0);

        assertEquals(17.0, dao.findUser(User.RootUserName).getBalance(), 0.01);
        assertEquals(7, dao.findUser(User.RootUserName).getTransactions().get(1).getAmount(), 0.01);
    }
}
