package ru.levelp.java.junior.haw;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class TestAuthenticator implements UserDetailsService {
    private final PasswordEncoder encoder;

    @Autowired
    public TestAuthenticator(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if ("test".equals(username)) {
            return new User(username, encoder.encode("test"), Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
        } else if ("admin".equals(username)) {
            return new User(username, encoder.encode("admin"), Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN")));
        } else {
            return null;
        }
    }
}
