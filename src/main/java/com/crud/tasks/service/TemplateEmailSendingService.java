package com.crud.tasks.service;

import com.crud.tasks.domain.mail.Mail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
public class TemplateEmailSendingService {
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    @Qualifier("trelloCardMail")
    private MailCreatorService trelloCardMailCreator;
    
    @Autowired
    @Qualifier("scheduledMail")
    private MailCreatorService scheduledMailCreator;

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleMailMessage.class);
    
    public void send(Mail mailToSend, MailType mailType) {
        try {
            javaMailSender.send(createMimeMessage(mailToSend, mailType));
            LOGGER.info("Email has been sent");
        } catch (MailException e) {
            LOGGER.error("Failed to process email sending: " + e.getMessage(), e);
        }
    }

    private MimeMessagePreparator createMimeMessage(Mail mail, MailType mailType) {
        return mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setTo(mail.getMailTo());
            messageHelper.setSubject(mail.getSubject());
            switch(mailType){
                case TRELLO_CARD:
                    messageHelper.setText(trelloCardMailCreator.buildEmail(mail.getMessage()), true);
                    break;
                case SCHEDULED:
                    messageHelper.setText(scheduledMailCreator.buildEmail(mail.getMessage()), true);
                    break;
            }
            
        };
    }

}
