package com.github.jasongoetz.asanajama.asana;

import net.joelinn.asana.Asana;
import net.joelinn.asana.tasks.Task;
import net.joelinn.asana.users.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class AsanaRestClient {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${asanaApiKey}")
    private String apiKey;

    private Asana asanaClient;

    public AsanaRestClient() {}

    @PostConstruct
    public void init() {
        this.asanaClient = new Asana(apiKey);
    }

    public List<SyncTask> getTasks(long asanaProjectId) {
        List<Task> tasks = asanaClient.projects().getTasks(asanaProjectId);
        List<SyncTask> actualTasks = new ArrayList<>();
        for(Task task : tasks) {
            SyncTask syncTask = new SyncTask(getTask(task.id));
            populateSubTasks(syncTask);
            actualTasks.add(syncTask);
        }
        return actualTasks;
    }

    public void populateSubTasks(SyncTask task) {
        for(Task subTask : asanaClient.tasks().getSubtasks(task.id)) {
            SyncTask syncSubTask = new SyncTask(getTask(subTask.id));
            populateSubTasks(syncSubTask);
            task.subTasks.add(syncSubTask);
        }
    }

    public Task getTask(long taskId) {
        return asanaClient.tasks().getTask(taskId);
    }

    public List<Task> getTasks() {
        return asanaClient.tasks().getTasks();
    }

    public User getUser(long userId) {
        return asanaClient.users().getUser(userId);
    }

    public String getApiKey() {
        return apiKey;
    }
}
