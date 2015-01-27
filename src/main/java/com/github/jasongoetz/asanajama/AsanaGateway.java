package com.github.jasongoetz.asanajama;

import com.github.jasongoetz.asanajama.domain.HubField;
import com.github.jasongoetz.asanajama.domain.HubItem;
import com.github.jasongoetz.asanajama.domain.HubItemType;
import com.github.jasongoetz.asanajama.domain.HubProject;
import com.github.jasongoetz.asanajama.domain.mapping.FieldMapping;
import com.github.jasongoetz.asanajama.exception.GatewayException;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
public class AsanaGateway implements AppGateway {


    @Override
    public List<HubProject> getProjects() throws GatewayException {
        return null;
    }

    @Override
    public List<HubItemType> getItemTypes() throws GatewayException {
        return null;
    }

    @Override
    public List<HubField> getFieldsForItemType(String projectId, String itemTypeId) throws GatewayException {
        return null;
    }

    @Override
    public HubItem getItem(Integer item) throws GatewayException {
        return null;
    }

    @Override
    public List<HubItem> getItems(String project, String set, Integer filterId) throws GatewayException {
        return null;
    }

    @Override
    public HubItem createItem(HubItem hubItem, HashMap<Integer, FieldMapping> fieldMappings) throws GatewayException {
        return null;
    }

    @Override
    public HubItem updateItem(HubItem hubItem, HashMap<Integer, FieldMapping> fieldMappings) throws GatewayException {
        return null;
    }

}
