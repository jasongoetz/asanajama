package com.github.jasongoetz.asanajama.resource;

import com.github.jasongoetz.asanajama.AsanaImporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class ImportResource {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AsanaImporter asanaImporter;

    @RequestMapping(method = RequestMethod.GET)
    @Valid
    public String entity() {
        asanaImporter.importAsana();
        return "OK";
    }
}