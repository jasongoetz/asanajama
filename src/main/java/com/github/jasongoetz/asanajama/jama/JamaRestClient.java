package com.github.jasongoetz.asanajama.jama;

import com.github.jasongoetz.jamarest.Jama;
import com.github.jasongoetz.jamarest.domain.item.RequestItem;
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

    public void createItem(RequestItem item) {
        jama.items().createItem(item);
    }

}
