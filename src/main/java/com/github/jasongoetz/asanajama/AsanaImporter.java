package com.github.jasongoetz.asanajama;

import com.github.jasongoetz.asanajama.asana.AsanaRestClient;
import com.github.jasongoetz.asanajama.domain.Item;
import com.github.jasongoetz.asanajama.domain.Location;
import com.github.jasongoetz.asanajama.domain.Parent;
import com.github.jasongoetz.asanajama.exception.GatewayException;
import net.joelinn.asana.tasks.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AsanaImporter {

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

    @Autowired
    private JamaGateway jamaGateway;

    public AsanaImporter() {}

    public void importAsana() {
        List<Task> tasks = asanaRestClient.getTasks(asanaProjectId);
        List<Item> jamaItems = new ArrayList<>();
        for (Task task : tasks) {
            jamaItems.add(taskToItem(task));
        }

        importToJama(jamaItems);
    }

    private void importToJama(List<Item> jamaItems) {
        for(Item item : jamaItems) {
            try {
                jamaGateway.createItem(item, null);
            } catch (GatewayException e) {
                e.printStackTrace();
            }
        }

    }

    private Item taskToItem(Task task) {
        Location location = new Location();
        Parent parent = new Parent();
        parent.setItem(jamaTaskSetId);
        location.setParent(parent);
        Item item = new Item();
        item.setProject(jamaProjectId);
        item.setItemType(jamaTaskItemTypeId);
        item.setLocation(location);
        item.setFields(buildFieldsMap(task));


//        try {
//            item.setCreatedDate(dateFormatter.parse(task.createdAt));
//            item.setModifiedDate(dateFormatter.parse(task.createdAt));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

        return item;
    }

    private Map<String, Object> buildFieldsMap(Task task) {
        Map<String, Object> fieldsMap = new HashMap<>();
        fieldsMap.put(taskToItemMap.getName(), task.name);
        fieldsMap.put(taskToItemMap.getNotes(), task.notes);
        //fieldsMap.put(taskToItemMap.getDueDate(), dateFormatter.parse(task.dueOn));

        return fieldsMap;
    }
}
