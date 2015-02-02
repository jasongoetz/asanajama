package com.github.jasongoetz.asanajama.asana;

import net.joelinn.asana.tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class SyncTask extends Task {

    public List<SyncTask> subTasks = new ArrayList<>();
    public int jamaItemId;

    public SyncTask(Task task) {
        this.id = task.id;
        this.assignee = task.assignee;
        this.assigneeStatus = task.assigneeStatus;
        this.createdAt = task.createdAt;
        this.completed = task.completed;
        this.completedAt = task.completedAt;
        this.dueOn = task.dueOn;
        this.followers = task.followers;
        this.hearted = task.hearted;
        this.hearts = task.hearts;
        this.numHearts = task.numHearts;
        this.modifiedAt = task.modifiedAt;
        this.name = task.name;
        this.notes = task.notes;
        this.projects = task.projects;
        this.parent = task.parent;
        this.workspace = task.workspace;
        this.tags = task.tags;
    }

    public SyncTask() {}
}
