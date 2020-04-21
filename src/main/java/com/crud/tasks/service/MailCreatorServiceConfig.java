package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.thymeleaf.TemplateEngine;

@Configuration
public class MailCreatorServiceConfig {
    @Autowired
    @Qualifier("templateEngine")
    protected TemplateEngine templateEngine;
    @Autowired
    protected AdminConfig adminConfig;
    @Autowired
    protected Environment environment;
    @Autowired
    private DbService dbService;

    @Bean(name = "trelloCardMail")
    public MailCreatorService getTrelloCardMailCreator() {
        return new TrelloCardMailCreatorService(templateEngine, adminConfig, environment);
    }

    @Bean(name = "scheduledMail")
    public MailCreatorService getScheduledMailCreator() {
        return new ScheduledMailCreatorService(templateEngine, adminConfig, environment, dbService);
    }


}
