package com.example.desafio.activeMQ;

import com.example.desafio.entities.Sms;
import com.example.desafio.entities.SmsRequest;
import com.example.desafio.services.RecipientService;
import com.example.desafio.services.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class ActiveMqConsumer {

    private final SmsService smsService;
    private final RecipientService recipientService;

    @Autowired
    public ActiveMqConsumer(SmsService smsService, RecipientService recipientService) {
        this.smsService = smsService;
        this.recipientService = recipientService;
    }

    @JmsListener(destination = "smsrequest-queue")
    public void receiveAndForwardMessageFromQueue(SmsRequest smsRequest) {
        for (Long id : smsRequest.getIds())
           smsService.sendSms(new Sms(recipientService.getRecipient(id).getPhoneNumber(), smsRequest.getMessage()));
    }

}
