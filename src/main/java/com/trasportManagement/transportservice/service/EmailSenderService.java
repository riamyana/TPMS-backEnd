package com.trasportManagement.transportservice.service;

import com.trasportManagement.transportservice.model.EmailData;
import freemarker.template.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Map;

@Service
public class EmailSenderService {

    private static final String fromEmail = "riamyana297@gmail.com";
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    Configuration fmConfiguration;

    public void sendSimpleEmail(EmailData mail) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(fromEmail);
        message.setTo(mail.getToEmail());
        message.setText(mail.getBody());
        message.setSubject(mail.getSubject());

        mailSender.send(message);
    }

    public void sendEmailWithTemplate(EmailData mail, String template) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        try {

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setSubject(mail.getSubject());
            mimeMessageHelper.setFrom(fromEmail);
            mimeMessageHelper.setTo(mail.getToEmail());
            mail.setBody(geContentFromTemplate(mail.getModel(), template));
            mimeMessageHelper.setText(mail.getBody(), true);

            mailSender.send(mimeMessageHelper.getMimeMessage());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public String geContentFromTemplate(Map< String, Object > model, String template)     {
        StringBuffer content = new StringBuffer();

        try {
            content.append(FreeMarkerTemplateUtils.processTemplateIntoString(fmConfiguration.getTemplate(template), model));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
    }
}
