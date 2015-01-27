package com.github.jasongoetz.asanajama.asana;

import net.joelinn.asana.Asana;
import net.joelinn.asana.tasks.Task;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class AsanaRestClient {

    @Value("${asanaApiKey}")
    private String apiKey;

    private Asana asanaClient;

    public AsanaRestClient() {}

    @PostConstruct
    public void init() {
        this.asanaClient = new Asana(apiKey);
    }

    public List<Task> getTasks(long asanaProjectId) {
        List<Task> tasks = asanaClient.projects().getTasks(asanaProjectId);
        List<Task> actualTasks = new ArrayList<>();
        for(Task task : tasks) {
            actualTasks.add(getTask(task.id));
        }
        return actualTasks;
    }

    public Task getTask(long taskId) {
        return asanaClient.tasks().getTask(taskId);
    }

    public List<Task> getTasks() {
        return asanaClient.tasks().getTasks();
    }

    public String getApiKey() {
        return apiKey;
    }
}
