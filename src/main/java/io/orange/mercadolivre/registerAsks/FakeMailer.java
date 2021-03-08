package io.orange.mercadolivre.registerAsks;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Primary
public class FakeMailer implements Mailer{

    @Override
    public void send(String mailBody, String fromUser, String subject, LocalDateTime dateAsk, String ask) {
        System.out.println("Send:");
        System.out.println("Body:"+mailBody);
        System.out.println("From User:"+fromUser);
        System.out.println("Subject:"+subject);
        System.out.println("Date/Hours:"+dateAsk);
        System.out.println("Ask:"+ask);

    }
}
