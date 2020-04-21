package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.mail.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.MailType;
import com.crud.tasks.service.TemplateEmailSendingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EmailScheduler {
    private static final String SUBJECT = "Tasks: Once a day email";
    
    @Autowired
    private TemplateEmailSendingService templateEmailSendingService;
    
    @Autowired
    private TaskRepository taskRepository;
    
    @Autowired
    private AdminConfig adminConfig;
    
    @Scheduled(cron = "0 0 10 * * *")
    public void sendInformationEmail() {
        long numOfTasks = taskRepository.count();
        if(numOfTasks > 0) {
            Mail mailToSend = new Mail(adminConfig.getAdminMail(), SUBJECT, getEmailMessage(numOfTasks), null);
            templateEmailSendingService.send(mailToSend, MailType.SCHEDULED);
        }
    }

    private String getEmailMessage(long numOfTasks) {
        return String.format("Currently in database you've got: %d %s.", numOfTasks, numOfTasks > 1 ? "tasks" : "task");
    }

}
