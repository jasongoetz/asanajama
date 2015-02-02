package com.github.jasongoetz.asanajama;

import com.github.jasongoetz.asanajama.jama.JamaRestClient;
import com.github.jasongoetz.jamarest.domain.user.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class JamaUserLoader {

    private Map<String, User> jamaUsersByEmail = new HashMap();
    private List<String> emailsNotInJama = new ArrayList();

    public User getJamaUserWithEmail(String email, JamaRestClient jamaRestClient) {
        if (emailsNotInJama.contains(email)) {
            return null;
        }
        if (jamaUsersByEmail.containsKey(email)) {
            return jamaUsersByEmail.get(email);
        }
        else {
            User user = jamaRestClient.getUserByEmail(email);
            if (user == null) {
                emailsNotInJama.add(email);
                return null;
            }
            else {
                jamaUsersByEmail.put(email, user);
                return user;
            }
        }
    }
}
