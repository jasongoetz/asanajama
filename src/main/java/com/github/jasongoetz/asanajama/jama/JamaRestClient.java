package com.github.jasongoetz.asanajama.jama;

import com.github.jasongoetz.jamarest.Jama;
import com.github.jasongoetz.jamarest.domain.item.RequestItem;
import com.github.jasongoetz.jamarest.domain.user.User;
import com.github.jasongoetz.jamarest.domain.wrapper.PageResults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class JamaRestClient {

    @Value("${jama_url}")
    private String url;

    @Value("${jama_username}")
    private String username;

    @Value("${jama_password}")
    private String password;

    private Jama jama;

    @PostConstruct
    public void init() {
        jama = new Jama(url, username, password);
    }

    public int createItem(RequestItem item) {
        return jama.items().createItem(item);
    }

    public User getUserByEmail(String email) {
        PageResults<User> pageResults = jama.users().getUsers(null, email, null, null, null, null);
        if (pageResults.getPageInfo().getResultCount() > 0) {
            return pageResults.getResults().get(0);
        }
        return null;
    }
}
