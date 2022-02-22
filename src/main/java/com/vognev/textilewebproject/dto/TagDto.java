package com.vognev.textilewebproject.dto;

/**
 * textile-store  16/02/2022-21:37
 */
public class TagDto {
    private int id;
    private String name;
    private boolean active;

    public TagDto() {
    }

    public TagDto(int id, String name, boolean active) {
        this.id = id;
        this.name = name;
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isActive() {
        return active;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
