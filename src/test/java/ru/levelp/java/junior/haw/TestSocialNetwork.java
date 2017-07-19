package ru.levelp.java.junior.haw;

import java.util.ArrayList;

public class TestSocialNetwork implements SocialNetwork {
    public ArrayList<String> posted = new ArrayList<String>();

    public void makePost(String text) {
        posted.add(text);
    }
}
