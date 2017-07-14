package ru.levelp.java.junior.haw;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "EmitMoneyServlet", urlPatterns = "/emit")
public class EmitMoneyServlet extends HttpServlet {
    private EntityManagerFactory emf;
    private EntityManager em;

    @Override
    public void init() throws ServletException {
        super.init();

        emf = Persistence.createEntityManagerFactory("NewPersistenceUnit");
        em = emf.createEntityManager();

        new MoneyFacadeDAO(em).ensureRootUser();
    }

    @Override
    public void destroy() {
        em.close();
        emf.close();

        super.destroy();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String amountString = request.getParameter("amount");
        if (amountString == null) throw new IllegalArgumentException("No amount specified");

        double amount = Double.parseDouble(amountString);

        MoneyFacadeDAO dao = new MoneyFacadeDAO(em);
        dao.emitMoney(amount);

        response.sendRedirect("/");
    }
}
