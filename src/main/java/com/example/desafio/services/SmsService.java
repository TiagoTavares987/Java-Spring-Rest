package com.example.desafio.services;

import com.example.desafio.configs.TwilioConfiguration;
import com.example.desafio.entities.Sms;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class SmsService {
    private final TwilioConfiguration twilioConfiguration;
    private static final Logger LOGGER = LoggerFactory.getLogger(TwilioConfiguration.class);

    @Autowired
    public SmsService(TwilioConfiguration twilioConfiguration) {
        this.twilioConfiguration = twilioConfiguration;
    }

    @Async
    public void sendSms(Sms sms) {
        PhoneNumber to = new PhoneNumber(sms.getPhoneNumber());
        PhoneNumber from = new PhoneNumber(twilioConfiguration.getTrialNumber());
        String message = sms.getMessage();
        MessageCreator creator = Message.creator(to, from, message);
        creator.create();
        LOGGER.info("Send sms {}", sms.getPhoneNumber());
    }
}
