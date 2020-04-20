package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;

@Service
public class MailCreatorService {
    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;
    
    @Autowired
    private AdminConfig adminConfig;
    
    @Autowired
    private Environment environment;
    
    public String buildTrelloCardEmail(String message) {
        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with Trello Acoount");
        functionality.add("Application allows sending tasks to Trello");
        
        
        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "https://sebeni.github.io/");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_config", adminConfig);
        context.setVariable("goodbye_msg", "Have a nice day!");
        context.setVariable("company_det", getCompanyDetails());
        context.setVariable("show_button", false);
        context.setVariable("is_friend", false);
        context.setVariable("application_functionality", functionality);
        
        return templateEngine.process("mail/created-trello-card-mail", context);
    }
    
    private String getCompanyDetails() {
        String companyName = environment.getProperty("info.company.name");
        String companyEmail = environment.getProperty("info.company.email");
        String comapnyPhone = environment.getProperty("info.company.phone");
        
        return String.format("%s || email: %s || phone number: %s", companyName, companyEmail, comapnyPhone);
    }
}
