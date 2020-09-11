package ru.t_systems.alyona.sbb.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import ru.t_systems.alyona.sbb.timetable.TimetableUpdateDTO;

import javax.jms.Topic;

@Component
@Slf4j
public class TimetableUpdateSender {

    @Autowired
    JmsTemplate jmsTemplate;

    @Autowired
    Topic timetableTopic;

    public void sendMessageToTopic(TimetableUpdateDTO updates) {
        try {
            jmsTemplate.convertAndSend(timetableTopic, updates);
        } catch (Exception e) {
            log.error("Failed to send a message", e);
        }
    }
}
