package com.github.jasongoetz.asanajama;

import com.github.jasongoetz.asanajama.domain.Field;
import com.github.jasongoetz.asanajama.domain.Item;
import com.github.jasongoetz.asanajama.domain.ItemType;
import com.github.jasongoetz.asanajama.domain.Project;
import com.github.jasongoetz.asanajama.domain.mapping.FieldMapping;
import com.github.jasongoetz.asanajama.exception.GatewayException;
import com.github.jasongoetz.asanajama.jama.JamaRestClient;
import com.github.jasongoetz.asanajama.util.JsonUtil;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.github.jasongoetz.asanajama.ConnectionProperties.*;

@Component
public class JamaGateway {

    Integer pageSize = 10;

    JamaRestClient jamaRestClient;

    public JamaGateway() throws GatewayException {
        jamaRestClient = JamaRestClient.create(getJamaUsername(), getJamaPassword(), getJamaURL());
    }

    public List<Project> getProjects(Integer workspaceId) throws GatewayException {
        return null;
    }

    public List<ItemType> getItemTypes() throws GatewayException {
        return null;
    }

    public List<Field> getFieldsForItemType(String projectId, String itemTypeId) throws GatewayException {
        return null;
    }

    public Item getItem(Integer itemId) throws GatewayException {
        String itemJson = jamaRestClient.get("items/"+itemId);
        Item item = JsonUtil.getDomainObject(itemJson, Item.class);
        return item;
    }

    public List<Item> getChildren(Integer parentId) throws GatewayException {
        List<Item> items = new ArrayList();
        Integer startAt = 0;
        Integer total = 999;
        while (startAt < total) {
            String url = "items/" + parentId + "/children?startAt=" + startAt;
            String itemsJson = jamaRestClient.get(url);
            items.addAll((List) JsonUtil.getDomainObjects(itemsJson, Item.class));
            try {
                total = new JSONObject(itemsJson).getJSONObject("meta").getJSONObject("pageInfo").getInt("totalResults");
            } catch (JSONException e) {
                throw new GatewayException("Could not parse total results from JSON", e);
            }
            startAt += pageSize;
        }
        return items;
    }

    public List<Item> getItemsFromFilter(Integer filterId, Integer projectId) throws GatewayException {
        List<Item> items = new ArrayList();
        Integer startAt = 0;
        Integer total = 999;
        while (startAt < total) {
            String url = String.format("filters/%d/results?startAt=%d", filterId, startAt);
            if (projectId != null) {
                url += String.format("&project=%d", projectId);
            }
            String itemsJson = jamaRestClient.get(url);
            items.addAll((List) JsonUtil.getDomainObjects(itemsJson, Item.class));
            try {
                total = new JSONObject(itemsJson).getJSONObject("meta").getJSONObject("pageInfo").getInt("totalResults");
            } catch (JSONException e) {
                throw new GatewayException("Could not parse total results from JSON", e);
            }
            startAt += pageSize;
        }
        return items;
    }

    public Item createItem(Item item, HashMap<Integer, FieldMapping> fieldMappings) throws GatewayException {
        String itemJson = JsonUtil.getJsonStringFromObject(item);
        String locationUrl = jamaRestClient.post("items", itemJson);
        String id = getIdFromPostLocationUrl(locationUrl);
        return getItem(new Integer(id));
    }

    public Item updateItem(Item item, HashMap<Integer, FieldMapping> fieldMappings) throws GatewayException {
        String itemJson = JsonUtil.getJsonStringFromObject(item);
        jamaRestClient.put(String.format("items/%d", item.getId()), itemJson);
        return getItem(item.getId());
    }

    private String getIdFromPostLocationUrl(String locationUrl) throws GatewayException {
        return locationUrl.substring(locationUrl.lastIndexOf("/") + 1, locationUrl.length());
    }

}
