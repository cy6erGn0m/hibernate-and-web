package ru.levelp.java.junior.haw;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EmitMoneyController {
    private final MoneyFacadeDAO dao;

    @Autowired
    public EmitMoneyController(MoneyFacadeDAO dao) {
        this.dao = dao;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/admin/emit")
    public String emitMoney(@RequestParam double amount, ModelMap model) {
        dao.emitMoney(amount);
        double newBalance = dao.ensureRootUser().getBalance();

        model.put("amount", amount);
        model.put("balance", newBalance);

        return "emit-complete";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String index(ModelMap model) {
        BalanceBean bean = new BalanceBean();
        bean.setRootUserBalance(dao.ensureRootUser().getBalance());

        String name = "unknown";
        Object o = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (o instanceof User) {
            User u = (User) o;
            name = u.getUsername() + u.getAuthorities().toString();
        }

        model.put("balance", bean);
        model.put("user", name);

        return "index";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/admin/emit")
    public String emitPage() {
        return "emit";
    }
}
