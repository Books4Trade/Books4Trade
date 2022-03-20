package com.example.books4trade.services;

import com.example.books4trade.models.User;

import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SendGridMail {

    @Value("${SG_API}")
    private String SENDGRID_API_KEY;

    @Value("${SG_FROM}")
    private String From;

    @Value("${SG_REG_TEMP_ID}")
    private String registrationtemplate;

    public void accountRegistrationSG(long id, String username, String toEmail, String temp) throws IOException {

        Mail mail = new Mail();
        Email from = new Email();
        from.setName("Auto");
        from.setEmail(From);
        mail.setFrom(from);

        Personalization personalization = new Personalization();
        Email to = new Email(toEmail);
        personalization.addTo(to);
        personalization.addDynamicTemplateData("unique_user", username);
        personalization.addDynamicTemplateData("temp_pass", temp);
        personalization.addDynamicTemplateData("login_link", "swapabook.xyz/login");
        mail.addPersonalization(personalization);
        mail.setTemplateId(registrationtemplate);

        // Fixed
        SendGrid sendgrid = new SendGrid(SENDGRID_API_KEY);
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
