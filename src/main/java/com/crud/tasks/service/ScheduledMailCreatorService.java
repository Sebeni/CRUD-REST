package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import org.springframework.core.env.Environment;
import org.thymeleaf.TemplateEngine;

import java.util.List;
import java.util.stream.Collectors;

public class ScheduledMailCreatorService extends MailCreatorService {
    private static final String SCHEDULED_TEMPLATE_PATH = "mail/scheduled-mail.html";
    private static final int LATEST_TASK_LIMIT = 3;
    private DbService dbService;
    
    
    @Override
    protected void populateContext() {
        List<String> latestTaskTitles = getLatestTasksTitles(LATEST_TASK_LIMIT);
        boolean moreThanOneTask = latestTaskTitles.size() > 1;
        context.setVariable("more_than_one_task", moreThanOneTask);
        context.setVariable("latest_tasks_list", latestTaskTitles);
        context.setVariable("alone_task", latestTaskTitles.get(0));
    }

    @Override
    protected String getTemplatePath() {
        return SCHEDULED_TEMPLATE_PATH;
    }

    public ScheduledMailCreatorService(TemplateEngine templateEngine, AdminConfig adminConfig, Environment environment, DbService dbService) {
        super(templateEngine, adminConfig, environment);
        this.dbService = dbService;
    }
    
    private List<String> getLatestTasksTitles(int taskLimit) {
        return dbService.getLatestTasks(taskLimit).stream().map(task -> task.getTitle()).collect(Collectors.toList());
    }
}
