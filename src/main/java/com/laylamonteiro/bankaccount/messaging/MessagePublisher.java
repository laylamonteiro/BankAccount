package com.laylamonteiro.bankaccount.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.laylamonteiro.bankaccount.dto.response.AccountDTO;
import com.laylamonteiro.bankaccount.dto.response.CreateTransactionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MessagePublisher {

    private final MessageChannel output;

    @Autowired
    public MessagePublisher(@Qualifier("nullChannel") MessageChannel output) {
        this.output = output;
    }

    public void publish(AccountDTO createdAccount) throws JsonProcessingException {
        boolean sent = output.send(MessageBuilder.withPayload(createdAccount).build());
        validate(sent, createdAccount, "New account");
    }

    public void publish(CreateTransactionDTO createdTransaction) throws JsonProcessingException {
        boolean sent = output.send(MessageBuilder.withPayload(createdTransaction).build());
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