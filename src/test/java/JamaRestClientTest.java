import com.fasterxml.jackson.core.type.TypeReference;
import com.github.jasongoetz.asanajama.exception.GatewayException;
import com.github.jasongoetz.asanajama.jama.JamaRestClient;
import com.github.jasongoetz.asanajama.domain.User;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class JamaRestClientTest {

    JamaRestClient jama;

    @Before
    public void setupClient() throws GatewayException {
        jama = JamaRestClient.create("jgoetz", "password", "http://localhost:8080/contour");
    }

    @Test
    public void testGetUsers() throws GatewayException, IOException {
        String response = jama.get("users");
        List<User> users = jama.readList(new TypeReference<User>() {
        }, response);
        for (User user : users) {
            System.out.println(user);
        }
    }

}
