package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import org.springframework.core.env.Environment;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

public abstract class MailCreatorService {
    protected final TemplateEngine templateEngine;
    protected final AdminConfig adminConfig;
    protected final Environment environment;
    protected Context context;

    public String buildEmail(String message){
        context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "https://sebeni.github.io/");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_config", adminConfig);
        context.setVariable("goodbye_msg", "Have a nice day!");
        context.setVariable("company_det", getCompanyDetails());
        populateContext();
        
        return templateEngine.process(getTemplatePath(), context);
    }
    
    protected abstract void populateContext();
    
    protected abstract String getTemplatePath();

    protected String getCompanyDetails() {
        String companyName = environment.getProperty("info.company.name");
        String companyEmail = environment.getProperty("info.company.email");
        String comapnyPhone = environment.getProperty("info.company.phone");
        
        return String.format("%s || email: %s || phone number: %s", companyName, companyEmail, comapnyPhone);
    }

    public MailCreatorService(TemplateEngine templateEngine, AdminConfig adminConfig, Environment environment) {
        this.templateEngine = templateEngine;
        this.adminConfig = adminConfig;
        this.environment = environment;
    }
}
