package com.example.desafio.activeMQ;

import com.example.desafio.entities.SmsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class ActiveMqProducer {
    private final JmsTemplate jmsTemplate;

    @Autowired
    public ActiveMqProducer(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void sendToQueue(SmsRequest smsRequest) {
        try {
            jmsTemplate.convertAndSend("smsrequest-queue", smsRequest);
        }
        catch (Exception ex) {
            System.out.println("ERROR in sending message to queue");
        }
    }
}
