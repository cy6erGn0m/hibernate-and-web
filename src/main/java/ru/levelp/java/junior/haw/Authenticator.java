package ru.levelp.java.junior.haw;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class Authenticator implements UserDetailsService {
    private final MoneyFacadeDAO dao;

    @Autowired
    public Authenticator(MoneyFacadeDAO dao) {
        this.dao = dao;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ru.levelp.java.junior.haw.User user = dao.findUser(username);
        if (user == null) throw new UsernameNotFoundException("No user " + username);

        ArrayList<SimpleGrantedAuthority> roles = new ArrayList<SimpleGrantedAuthority>();
        if (ru.levelp.java.junior.haw.User.RootUserName.equals(username)) {
            roles.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        roles.add(new SimpleGrantedAuthority("ROLE_USER"));

        return new User(username, user.getEncryptedPassword(), roles);
    }
}
