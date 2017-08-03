package ru.levelp.java.junior.haw;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Configuration
@ComponentScan(basePackages = "ru.levelp.java.junior.haw", excludeFilters = { @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Configuration.class) })
public class TestConfiguration {
    @Bean
    public SocialNetwork network() {
        return new TestSocialNetwork();
    }

    @Bean
    @Qualifier("facebook")
    public SocialNetwork facebook() {
        return new TestSocialNetwork();
    }

    @Bean
    public EntityManagerFactory getEmf() {
        return Persistence.createEntityManagerFactory("TestPersistenceUnit");
    }

    @Bean
    public EntityManager getEntityManager(EntityManagerFactory emf) {
        return emf.createEntityManager();
    }

    @Bean
    public PasswordEncoder encoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public UserDetailsService auth(PasswordEncoder encoder) {
        return new TestAuthenticator(encoder);
    }
}
