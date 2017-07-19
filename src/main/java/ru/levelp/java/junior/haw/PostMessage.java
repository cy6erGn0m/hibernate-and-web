package ru.levelp.java.junior.haw;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostMessage {
    private final List<SocialNetwork> allNetworks;
    private final SocialNetwork facebookNetwork;

    @Autowired
    public PostMessage(List<SocialNetwork> allNetworks,
                       @Qualifier("facebook") SocialNetwork facebookNetwork) {
        this.allNetworks = allNetworks;
        this.facebookNetwork = facebookNetwork;
    }

    public void postToEvery(String text) {
        for (SocialNetwork network : allNetworks) {
            network.makePost(text);
        }
    }

    public void postToFacebook(String text) {
        facebookNetwork.makePost(text);
    }
}
