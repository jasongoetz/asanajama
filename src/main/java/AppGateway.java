import domain.HubField;
import domain.HubItem;
import domain.HubItemType;
import domain.HubProject;
import domain.mapping.FieldMapping;
import exception.GatewayException;

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
