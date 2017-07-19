package ru.levelp.java.junior.haw;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Configuration
@ComponentScan(basePackages = "ru.levelp.java.junior.haw")
public class ProductionConfiguration {

    @Bean
    public EntityManagerFactory getEmf() {
        return Persistence.createEntityManagerFactory("NewPersistenceUnit");
    }

    @Bean
    public EntityManager getEntityManager(EntityManagerFactory emf) {
        return emf.createEntityManager();
    }
}
