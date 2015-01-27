import exception.GatewayException;
import net.joelinn.asana.Asana;
import net.joelinn.asana.users.User;
import net.joelinn.asana.users.Users;
import net.joelinn.asana.users.UsersClient;
import org.junit.Before;
import org.junit.Test;

public class AsanaRestClientTest {

    Asana asana;

    @Before
    public void setupClient() throws GatewayException {
        asana = new Asana("77H6ILO3.8RMTGiDJECdEpVpF3ZcMSb8");
    }

    @Test
    public void testGetUsers() {
        UsersClient usersClient = asana.users();
        Users users = usersClient.getUsers();
        for (User user : users) {
            System.out.println(user);
        }
    }

}