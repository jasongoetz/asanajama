import com.github.jasongoetz.asanajama.JamaGateway;
import com.github.jasongoetz.asanajama.domain.Item;
import com.github.jasongoetz.asanajama.domain.Location;
import com.github.jasongoetz.asanajama.domain.Parent;
import com.github.jasongoetz.asanajama.exception.GatewayException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

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
    public void getItems() {

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
    public void updateItem() {

    }

}
