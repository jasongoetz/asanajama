package com.github.jasongoetz.asanajama;

import com.github.jasongoetz.asanajama.domain.Field;
import com.github.jasongoetz.asanajama.domain.Item;
import com.github.jasongoetz.asanajama.domain.ItemType;
import com.github.jasongoetz.asanajama.domain.Project;
import com.github.jasongoetz.asanajama.domain.mapping.FieldMapping;
import com.github.jasongoetz.asanajama.exception.GatewayException;
import com.github.jasongoetz.asanajama.jama.JamaRestClient;
import com.github.jasongoetz.asanajama.util.JsonUtil;

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
    public Item getItem(Integer itemId) throws GatewayException {
        String itemJson = jamaRestClient.get("items/"+itemId);
        Item item = JsonUtil.getDomainObject(itemJson, Item.class);
        return item;
    }

    @Override
    public List<Item> getItems(Integer project, Integer parentId, Integer filterId) throws GatewayException {
        return null;
    }

    @Override
    public Item createItem(Item item, HashMap<Integer, FieldMapping> fieldMappings) throws GatewayException {
        String itemJson = JsonUtil.getJsonStringFromObject(item);
        String locationUrl = jamaRestClient.post("items", itemJson);
        String id = getIdFromPostLocationUrl(locationUrl);
        return getItem(new Integer(id));
    }

    @Override
    public Item updateItem(Item item, HashMap<Integer, FieldMapping> fieldMappings) throws GatewayException {
        return null;
    }

    private String getIdFromPostLocationUrl(String locationUrl) throws GatewayException {
        return locationUrl.substring(locationUrl.lastIndexOf("/") + 1, locationUrl.length());
    }

}
