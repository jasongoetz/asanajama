package com.github.jasongoetz.asanajama;

import com.github.jasongoetz.asanajama.asana.AsanaRestClient;
import com.github.jasongoetz.asanajama.jama.JamaRestClient;
import com.github.jasongoetz.jamarest.domain.item.Location;
import com.github.jasongoetz.jamarest.domain.item.Parent;
import com.github.jasongoetz.jamarest.domain.item.RequestItem;
import com.github.jasongoetz.jamarest.exception.ApiException;
import net.joelinn.asana.tasks.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AsanaSync {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AsanaRestClient asanaRestClient;

    @Autowired
    private TaskToItemMap taskToItemMap;

    @Value("${asanaProjectId}")
    private Long asanaProjectId;

    @Value("${jamaProjectId}")
    private Integer jamaProjectId;

    @Value("${jamaTaskItemTypeId}")
    private Integer jamaTaskItemTypeId;

    @Value("${jamaTaskSetId}")
    private Integer jamaTaskSetId;

    private SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

    private SimpleDateFormat simpleDateFormatter = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    private JamaRestClient jamaGateway;

    public AsanaSync() {}

    public void sync() {
        List<Task> tasks = asanaRestClient.getTasks(asanaProjectId);
        for (Task task : tasks) {
            if (!existsInJama(task)) {
                try {
                    createInJama(taskToItem(task));
                }
                catch (ApiException e) {
                    logger.info("Could not create Jama item for task with ID " + task.id + " Name: " + task.name + "Cause: " + e.getMessage());
                }
            }
        }
    }

    private boolean existsInJama(Task task) {
        return false; //Note: eventually this would actually check whether the item already exists already via key mapping of some sort
    }

    private void createInJama(RequestItem item) throws ApiException {
        jamaGateway.createItem(item);
    }

    private RequestItem taskToItem(Task task) {
        Location location = new Location();
        Parent parent = new Parent();
        parent.setItem(jamaTaskSetId);
        location.setParent(parent);
        RequestItem item = new RequestItem();
        item.setProject(jamaProjectId);
        item.setItemType(jamaTaskItemTypeId);
        item.setLocation(location);
        item.setFields(buildFieldsMap(task));

        return item;
    }

    private Map<String, Object> buildFieldsMap(Task task) {
        Map<String, Object> fieldsMap = new HashMap<>();
        fieldsMap.put(taskToItemMap.getName(), task.name);
        fieldsMap.put(taskToItemMap.getNotes(), task.notes);
        if (task.dueOn != null) {
            fieldsMap.put(taskToItemMap.getDueDate(), task.dueOn);
        }

        return fieldsMap;
    }
}
