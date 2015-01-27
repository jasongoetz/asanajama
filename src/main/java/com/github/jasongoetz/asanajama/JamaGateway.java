package com.github.jasongoetz.asanajama;

import com.github.jasongoetz.asanajama.domain.Field;
import com.github.jasongoetz.asanajama.domain.Item;
import com.github.jasongoetz.asanajama.domain.ItemType;
import com.github.jasongoetz.asanajama.domain.Project;
import com.github.jasongoetz.asanajama.domain.mapping.FieldMapping;
import com.github.jasongoetz.asanajama.exception.GatewayException;
import com.github.jasongoetz.asanajama.jama.JamaRestClient;

import java.util.HashMap;
import java.util.List;

import static com.github.jasongoetz.asanajama.ConnectionProperties.*;

public class JamaGateway implements AppGateway {

    JamaRestClient jamaRestClient;

    public JamaGateway() throws GatewayException {
        jamaRestClient = JamaRestClient.create(getJamaUsername(), getJamaPassword(), getJamaURL());
    }

    @Override
    public List<Project> getProjects(Integer workspaceId) throws GatewayException {
        return null;
    }

    @Override
    public List<ItemType> getItemTypes() throws GatewayException {
        return null;
    }

    @Override
    public List<Field> getFieldsForItemType(String projectId, String itemTypeId) throws GatewayException {
        return null;
    }

    @Override
    public Item getItem(Integer item) throws GatewayException {
        return null;
    }

    @Override
    public List<Item> getItems(Integer project, Integer parentId, Integer filterId) throws GatewayException {
        return null;
    }

    @Override
    public Item createItem(Item item, HashMap<Integer, FieldMapping> fieldMappings) throws GatewayException {
//        Item requestItem = new Item();
//        requestItem.setFields(item.getFields());
//        requestItem.setProject()
//        jamaRestClient.post("items", itemJson);
        return null;
    }

    @Override
    public Item updateItem(Item item, HashMap<Integer, FieldMapping> fieldMappings) throws GatewayException {
        return null;
    }

}
