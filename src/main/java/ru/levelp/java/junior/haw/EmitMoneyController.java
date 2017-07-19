package ru.levelp.java.junior.haw;

import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping(method = RequestMethod.POST, value = "/emit")
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

        model.put("balance", bean);

        return "index";
    }
}
