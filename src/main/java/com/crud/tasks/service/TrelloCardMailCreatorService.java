package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;

public class TrelloCardMailCreatorService extends MailCreatorService {
    private static final String TRELLO_TEMPLATE_PATH = "mail/created-trello-card-mail";
    

    @Override
    protected void populateContext() {
        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with Trello Acoount");
        functionality.add("Application allows sending tasks to Trello");
        
        context.setVariable("show_button", false);
        context.setVariable("is_friend", false);
        context.setVariable("application_functionality", functionality);
       
    }

    @Override
    protected String getTemplatePath() {
        return TRELLO_TEMPLATE_PATH;
    }

    public TrelloCardMailCreatorService(TemplateEngine templateEngine, AdminConfig adminConfig, Environment environment) {
        super(templateEngine, adminConfig, environment);
    }
}
