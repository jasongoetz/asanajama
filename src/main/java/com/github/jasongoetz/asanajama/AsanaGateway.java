package com.github.jasongoetz.asanajama;

import com.github.jasongoetz.asanajama.domain.Field;
import com.github.jasongoetz.asanajama.domain.Item;
import com.github.jasongoetz.asanajama.domain.ItemType;
import com.github.jasongoetz.asanajama.domain.mapping.FieldMapping;
import com.github.jasongoetz.asanajama.exception.GatewayException;
import net.joelinn.asana.Asana;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
public class AsanaGateway implements AppGateway {

    private Asana asana;

    public AsanaGateway() {
        asana = new Asana(ConnectionProperties.getAsanaAPIKey());
    }

    @Override
    public List<com.github.jasongoetz.asanajama.domain.Project> getProjects(Integer workspaceId) throws GatewayException {
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
    public List<Item> getItems(Integer projectId, Integer parentId, Integer filterId) throws GatewayException {
        return null;
    }

    @Override
    public Item createItem(Item item, HashMap<Integer, FieldMapping> fieldMappings) throws GatewayException {
        return null;
    }

    @Override
    public Item updateItem(Item item, HashMap<Integer, FieldMapping> fieldMappings) throws GatewayException {
        return null;
    }

}
