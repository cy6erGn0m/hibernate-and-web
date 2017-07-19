package ru.levelp.java.junior.haw;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VkNetwork implements SocialNetwork {
    private final MoneyFacadeDAO dao;

    @Autowired
    public VkNetwork(MoneyFacadeDAO dao) {
        this.dao = dao;
    }

    public void makePost(String text) {
        dao.findUser("user");
    }
}
