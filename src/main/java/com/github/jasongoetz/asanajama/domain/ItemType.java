package com.github.jasongoetz.asanajama.domain;

import java.util.List;

public class ItemType {

    private Integer id;
    private String typeKey;
    private String display;
    private String displayPlural;
    private String description;
    private String image;
    private Boolean isSystem;
    private List<ItemTypeField> fields;

}
