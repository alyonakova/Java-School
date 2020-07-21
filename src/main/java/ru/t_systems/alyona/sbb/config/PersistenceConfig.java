package ru.t_systems.alyona.sbb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

@Configuration
public class PersistenceConfig {

    @Bean
    public EntityManager entityManager() {
        return Persistence
                .createEntityManagerFactory("ru.t-systems.alyona.t_systems_sbb")
                .createEntityManager();
    }

}
