package ru.levelp.java.junior.haw;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PostLoad;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class EmitMoneyServlet extends HttpServlet {
    private final MoneyFacadeDAO dao;

    @Autowired
    public EmitMoneyServlet(MoneyFacadeDAO dao) {
        this.dao = dao;
    }

    @PostConstruct
    void postLoad() {
        dao.ensureRootUser();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/emit.form")
    public String emit(@RequestParam double amount, ModelMap model) {
        dao.emitMoney(amount);
        model.put("value", dao.ensureRootUser().getBalance());

        return "emit.end";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/view-balance.form")
    public String viewRootUserBalance(ModelMap model) {
        model.put("value", dao.ensureRootUser().getBalance());

        return "view.root.balance";
    }
}
