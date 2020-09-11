package ru.t_systems.alyona.sbb.config;

import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.UserCredentialsConnectionFactoryAdapter;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.SimpleMessageConverter;
import org.wildfly.naming.client.WildFlyInitialContextFactory;

import javax.jms.ConnectionFactory;
import javax.jms.Topic;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.Properties;

@Configuration
public class JMSConfig {

    public static final String SECURITY_PRINCIPAL = "sbb-ticket-service-user";
    public static final String SECURITY_CREDENTIAL = "12345678";

    @SneakyThrows
    @Bean
    Context remoteJndiContext() {
        Properties properties = new Properties();
        properties.put(Context.INITIAL_CONTEXT_FACTORY, WildFlyInitialContextFactory.class.getName());
        properties.put(Context.SECURITY_PRINCIPAL, SECURITY_PRINCIPAL);
        properties.put(Context.SECURITY_CREDENTIALS, SECURITY_CREDENTIAL);
        properties.put(Context.PROVIDER_URL, "remote+http://localhost:18080");
        return new InitialContext(properties);
    }

    @SneakyThrows
    @Bean
    ConnectionFactory jmsConnectionFactory() {
        ConnectionFactory connectionFactory = (ConnectionFactory) remoteJndiContext().lookup("jms/RemoteConnectionFactory");
        UserCredentialsConnectionFactoryAdapter factoryAdapter = new UserCredentialsConnectionFactoryAdapter();
        factoryAdapter.setUsername(SECURITY_PRINCIPAL);
        factoryAdapter.setPassword(SECURITY_CREDENTIAL);
        factoryAdapter.setTargetConnectionFactory(connectionFactory);
        return factoryAdapter;
    }

    @Bean
    public MessageConverter jmsMessageConverter() {
        return new SimpleMessageConverter();
    }

    @SneakyThrows
    @Bean
    Topic timetableTopic() {
        return (Topic) remoteJndiContext().lookup("jms/topic/TimetableTopic");
    }

    @Bean
    JmsTemplate jmsTemplate() {
        return new JmsTemplate(jmsConnectionFactory());
    }
}
