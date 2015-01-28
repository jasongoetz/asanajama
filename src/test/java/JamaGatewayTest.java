import com.github.jasongoetz.asanajama.JamaGateway;
import com.github.jasongoetz.asanajama.domain.Item;
import com.github.jasongoetz.asanajama.domain.Location;
import com.github.jasongoetz.asanajama.domain.Parent;
import com.github.jasongoetz.asanajama.exception.GatewayException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

public class JamaGatewayTest {

    private JamaGateway gateway;

    @Before
    public void setup() throws GatewayException {
        gateway = new JamaGateway();
    }

    @Test
    public void getProjects() {

    }

    @Test
    public void getItemTypes() {

    }

    @Test
    public void getFieldsForItemType() {

    }

    @Test
    public void getItem() {

    }

    @Test
    public void getChildren() throws GatewayException {
        Integer parentId = 2736824;
        List<Item> items = gateway.getChildren(parentId);
        Assert.assertTrue(items.size() > 0);
        for (Item item : items) {
            Assert.assertEquals(parentId, item.getLocation().getParent().getItem());
        }
    }

    @Test
    public void getItems() throws GatewayException {
        List<Item> items = gateway.getItemsFromFilter(56923, null);
        Assert.assertTrue(items.size() > 100);
    }

    @Test
    public void createItem() throws GatewayException {
        Item item = new Item();
        item.setProject(24114);
        item.setItemType(107368);

        Parent parent = new Parent();
        parent.setItem(2736824);
        Location location = new Location();
        location.setParent(parent);
        item.setLocation(location);

        item.setFields(new HashMap<String, Object>());
        item.getFields().put("name", "This is a test task");

        Item returnedItem = gateway.createItem(item, null);
        Assert.assertEquals(item.getItemType(), returnedItem.getItemType());
        Assert.assertEquals(item.getProject(), returnedItem.getProject());
        Assert.assertEquals(item.getFields().get("name"), returnedItem.getFields().get("name"));
    }

    @Test
    public void updateItem() throws GatewayException {
        Item item = gateway.getItem(2736824);
        String oldName = (String) item.getFields().get("name");
        System.out.println("Old Name: " + oldName);
        String newName = "New Tasks Set Name";
        item.getFields().put("name", newName);
        Item returnedItem = gateway.updateItem(item, null);
        System.out.println("New Name: " + returnedItem.getFields().get("name"));
        Assert.assertEquals(newName, returnedItem.getFields().get("name"));
        item.getFields().put("name", oldName);
        returnedItem = gateway.updateItem(item, null);
        Assert.assertEquals(oldName, returnedItem.getFields().get("name"));
    }

}
