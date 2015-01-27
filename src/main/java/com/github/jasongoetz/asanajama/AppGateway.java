package com.github.jasongoetz.asanajama;

import com.github.jasongoetz.asanajama.domain.HubField;
import com.github.jasongoetz.asanajama.domain.HubItem;
import com.github.jasongoetz.asanajama.domain.HubItemType;
import com.github.jasongoetz.asanajama.domain.HubProject;
import com.github.jasongoetz.asanajama.domain.mapping.FieldMapping;
import com.github.jasongoetz.asanajama.exception.GatewayException;

import java.util.HashMap;
import java.util.List;

public interface AppGateway {

    public List<HubProject> getProjects() throws GatewayException;

    public List<HubItemType> getItemTypes() throws GatewayException;

    public List<HubField> getFieldsForItemType(String projectId, String itemTypeId) throws GatewayException;

    public HubItem getItem(Integer item) throws GatewayException;

    public List<HubItem> getItems(String project, String set, Integer filterId) throws GatewayException;

    public HubItem createItem(HubItem hubItem, HashMap<Integer, FieldMapping> fieldMappings) throws GatewayException;

    public HubItem updateItem(HubItem hubItem, HashMap<Integer, FieldMapping> fieldMappings) throws GatewayException;

}
