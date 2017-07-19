package ru.levelp.java.junior.haw;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Configuration
@ComponentScan(basePackages = "ru.levelp.java.junior.haw")
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
}
