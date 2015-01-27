package com.github.jasongoetz.asanajama;

import com.github.jasongoetz.asanajama.domain.Field;
import com.github.jasongoetz.asanajama.domain.Item;
import com.github.jasongoetz.asanajama.domain.ItemType;
import com.github.jasongoetz.asanajama.domain.Project;
import com.github.jasongoetz.asanajama.domain.mapping.FieldMapping;
import com.github.jasongoetz.asanajama.exception.GatewayException;

import java.util.HashMap;
import java.util.List;

public interface AppGateway {

    public List<Project> getProjects(Integer workspaceId) throws GatewayException;

    public List<ItemType> getItemTypes() throws GatewayException;

    public List<Field> getFieldsForItemType(String projectId, String itemTypeId) throws GatewayException;

    public Item getItem(Integer item) throws GatewayException;

    public List<Item> getItems(Integer project, Integer parentId, Integer filterId) throws GatewayException;

    public Item createItem(Item item, HashMap<Integer, FieldMapping> fieldMappings) throws GatewayException;

    public Item updateItem(Item item, HashMap<Integer, FieldMapping> fieldMappings) throws GatewayException;

}
