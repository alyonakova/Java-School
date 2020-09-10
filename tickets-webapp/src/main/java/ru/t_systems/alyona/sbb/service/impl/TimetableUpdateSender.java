package ru.t_systems.alyona.sbb.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.t_systems.alyona.sbb.timetable.TimetableUpdateDTO;

import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.ObjectMessage;
import javax.jms.Topic;

// TODO: Maybe use Spring JMS API https://spring.io/guides/gs/messaging-jms/ instead of Java EE JMS API?
@Component
@Slf4j
public class TimetableUpdateSender {

    @Autowired
    JMSContext jmsContext;

    @Autowired
    Topic timetableTopic;

    public void sendMessageToTopic(TimetableUpdateDTO updates) {
        try {
            ObjectMessage message = jmsContext.createObjectMessage(updates);
            JMSProducer producer = jmsContext.createProducer();
            producer.send(timetableTopic, message);
        } catch (Exception e) {
            log.error("Failed to send a message", e);
        }
    }
}
