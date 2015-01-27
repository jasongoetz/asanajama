package com.github.jasongoetz.asanajama.domain;

import java.util.Date;

public class Lock {

    public static final String NAME = "lock";
    public static final String NAME_PLURAL = NAME + "s";

    private boolean locked;
    private Date lastLockedDate;
    private Integer lastLockedBy;

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public Date getLastLockedDate() {
        return lastLockedDate;
    }

    public void setLastLockedDate(Date lastLockedDate) {
        this.lastLockedDate = lastLockedDate;
    }

    public Integer getLastLockedBy() {
        return lastLockedBy;
    }

    public void setLastLockedBy(Integer lastLockedBy) {
        this.lastLockedBy = lastLockedBy;
    }
}
