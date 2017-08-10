package ru.levelp.java.junior.haw;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Configuration
@ComponentScan(basePackages = "ru.levelp.java.junior.haw")
@Import(SecurityConfig.class)
@EnableWebMvc
@EnableTransactionManagement
public class ProductionConfiguration extends WebMvcConfigurerAdapter {

    @Bean
    public EntityManagerFactory getEmf() {
        return Persistence.createEntityManagerFactory("NewPersistenceUnit");
    }

    @Bean
    public EntityManager getEntityManager(EntityManagerFactory emf) {
        return emf.createEntityManager();
    }

    @Bean
    public ViewResolver getResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();

        resolver.setPrefix("/pages/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);

        return resolver;
    }

    @Bean
    public PlatformTransactionManager tx(EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/styles/*.css").addResourceLocations("/styles/");
    }
}
