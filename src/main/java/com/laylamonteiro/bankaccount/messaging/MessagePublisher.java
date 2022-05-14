package com.laylamonteiro.bankaccount.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.laylamonteiro.bankaccount.dto.response.AccountDTO;
import com.laylamonteiro.bankaccount.dto.response.CreateTransactionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@EnableBinding(Source.class)
public class MessagePublisher {

    @Autowired
    private Source source;

    public void publish(AccountDTO createdAccount) throws JsonProcessingException {
        boolean sent = source.output().send(MessageBuilder.withPayload(createdAccount).setHeader("", "").build());
        validate(sent, createdAccount, "New account");
    }

    public void publish(CreateTransactionDTO createdTransaction) throws JsonProcessingException {
        boolean sent = source.output().send(MessageBuilder.withPayload(createdTransaction).setHeader("", "").build());
        validate(sent, createdTransaction, "New transaction");
    }

    private void validate(boolean sent, Object object, String type) throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

        if (sent) {
            Map<String, String> msg = new HashMap<>();
            msg.put(type, ow.writeValueAsString(object));
            System.out.println("Message published to RabbitMQ: \n'" + msg);
        } else {
            System.out.println("[x] Message not published to RabbitMQ [x]");
        }
    }
}