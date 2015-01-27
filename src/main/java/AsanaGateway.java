import domain.HubField;
import domain.HubItem;
import domain.HubItemType;
import domain.HubProject;
import domain.mapping.FieldMapping;
import exception.GatewayException;

import java.util.HashMap;
import java.util.List;

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
