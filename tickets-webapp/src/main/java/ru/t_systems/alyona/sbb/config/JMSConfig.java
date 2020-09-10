package ru.t_systems.alyona.sbb.config;

import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.wildfly.naming.client.WildFlyInitialContextFactory;

import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Topic;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.Properties;

@Configuration
public class JMSConfig {

    @SneakyThrows
    @Bean
    Context remoteJndiContext() {
        Properties properties = new Properties();
        properties.put(Context.INITIAL_CONTEXT_FACTORY, WildFlyInitialContextFactory.class.getName());
        properties.put(Context.SECURITY_PRINCIPAL, "sbb-ticket-service-user");
        properties.put(Context.SECURITY_CREDENTIALS, "12345678");
        properties.put(Context.PROVIDER_URL, "remote+http://localhost:18080");
        return new InitialContext(properties);
    }

    @SneakyThrows
    @Bean
    Topic timetableTopic() {
        return (Topic) remoteJndiContext().lookup("jms/topic/TimetableTopic");
    }

    @SneakyThrows
    @Bean
    ConnectionFactory connectionFactory() {
        return (ConnectionFactory) remoteJndiContext().lookup("jms/RemoteConnectionFactory");
    }

    @Bean
    JMSContext jmsContext() {
        return connectionFactory().createContext("sbb-ticket-service-user", "12345678");
    }
}
