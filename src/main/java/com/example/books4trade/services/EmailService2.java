package com.example.books4trade.services;

import com.example.books4trade.models.User;
import com.sendgrid.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;

import java.io.IOException;


public class EmailService2 {

    @Value("${SG_API}")
    private static  String SENDGRID_API_KEY;

    @Value("${SG_FROM}")
    private String From;

    @Value("${SG_REG_TEMP_ID}")
    private String registrationtemplate;

    public void accountRegistration(User user) throws IOException {

        Email from = new Email();
        from.setName("Auto");
        from.setEmail(From);
        Email to = new Email(user.getEmail());
        String subject = "SwapABook Account Created";
        Content content = new Content("text/plain", "and easy to do with Java");
        Mail mail = new Mail(from, subject, to, content);
        mail.personalization.get(0).addSubstitution("weblink","swapabook.xyz/useractivate?user="+user.getId());
        mail.personalization.get(0).addSubstitution("unique_name", user.getUsername());
        mail.setTemplateId(registrationtemplate);

        SendGrid sendgrid = new SendGrid("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");
        Request request = new Request();
        try{
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sendgrid.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException iox){
            throw iox;
        }
    }

}
