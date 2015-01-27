package com.github.jasongoetz.asanajama;

import com.github.jasongoetz.asanajama.asana.AsanaRestClient;
import com.github.jasongoetz.asanajama.domain.Item;
import com.github.jasongoetz.asanajama.domain.Location;
import net.joelinn.asana.tasks.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class AsanaImporter {

    @Autowired
    private AsanaRestClient asanaRestClient;

    @Autowired
    private TaskToItemMap taskToItemMap;

    @Value("${asanaPojectId}")
    private Long asanaProjectId;

    @Value("${jamaProjectId}")
    private Integer jamaProjectId;

    public AsanaImporter() {}

    public void importAsana() {
        List<Task> tasks = asanaRestClient.getTasks(asanaProjectId);
        List<Item> jamaItems = new ArrayList<>();
        for (Task task : tasks) {
            jamaItems.add(tasktToItem(task));
        }

        importToJama(jamaItems);
    }

    private void importToJama(List<Item> jamaItems) {

    }

    private Item tasktToItem(Task task) {
        Location location = new Location();
        location.setParent(null);
        Item item = new Item();
        item.setProject(jamaProjectId);
        item.setLocation(location);
        item.setFields(buildFieldsMap(task));
        item.setCreatedDate(new Date(task.createdAt));
        item.setModifiedDate(new Date(task.createdAt));
        return item;
    }

    private Map<String, Object> buildFieldsMap(Task task) {
        Map<String, Object> fieldsMap = new HashMap<>();
        fieldsMap.put(taskToItemMap.getName(), task.name);
        fieldsMap.put(taskToItemMap.getNotes(), task.notes);

        return fieldsMap;
    }
}
