package ru.levelp.java.junior.haw;

import org.springframework.stereotype.Service;

@Service
public interface SocialNetwork {
    void makePost(String text);
}
