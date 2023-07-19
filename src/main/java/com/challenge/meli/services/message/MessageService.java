package com.challenge.meli.services.message;

import com.challenge.meli.models.Satellite;
import com.challenge.meli.repositories.ISatelliteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MessageService implements IMessageService{

    @Autowired
    public MessageService() {
    }

    @Override
    public String getMessage(String[]... messages) {
        try {
            if (messages.length == 0)
                throw new Exception("Null message");

            int messageLength = 0;
            for (String[] message : messages) {
                if (message.length > messageLength) {
                    messageLength = message.length;
                }
            }

            String[] reconstructedMessage = new String[messageLength];
            for (int i = 0; i < messageLength; i++) {
                String word = "";
                for (String[] message : messages) {
                    if (i < message.length && !message[i].isEmpty()) {
                        word = message[i];
                        break;
                    }
                }
                reconstructedMessage[i] = word;
            }

            StringBuilder sb = new StringBuilder();
            for (String word : reconstructedMessage) {
                if (!word.isEmpty()) {
                    sb.append(word).append(" ");
                }
            }
            String msg = sb.toString().trim();

            return msg;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
