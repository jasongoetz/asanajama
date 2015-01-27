package com.github.jasongoetz.asanajama.domain;

public class Location {
    private Integer sortOrder;
    private Integer globalSortOrder;
    private String sequence;
    private Parent parent;

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Integer getGlobalSortOrder() {
        return globalSortOrder;
    }

    public void setGlobalSortOrder(Integer globalSortOrder) {
        this.globalSortOrder = globalSortOrder;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public Parent getParent() { return parent; }

    public void setParent(Parent parent) { this.parent = parent; }
}
