package ru.levelp.java.junior.haw;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class BalanceBean {
    public double getRootUserBalance() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("NewPersistenceUnit");
        EntityManager em = emf.createEntityManager();

        try {
            return new MoneyFacadeDAO(em).ensureRootUser().getBalance();
        } finally {
            em.close();
            emf.close();
        }
    }

    public int getBalanceQuantity() {
        return 10;
    }
}
