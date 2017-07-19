package ru.levelp.java.junior.haw;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("facebook")
public class FacebookNetwork implements SocialNetwork {
    public void makePost(String text) {
    }
}
