package com.Proiect.Proiect.DTOS;

public class UserDTO {

    private Long id;
    private String name;
    private String nameId;

    public UserDTO(Long id, String name, String nameId) {
        this.id = id;
        this.name = name;
        this.nameId = nameId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameId() {
        return nameId;
    }

    public void setNameId(String nameId) {
        this.nameId = nameId;
    }
}
