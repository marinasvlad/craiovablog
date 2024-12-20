package com.Proiect.Proiect.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "`users`")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;
    private String nameId;
    private String role;
    private boolean status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getNameId() {
        return nameId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNameId(String nameId) {
        this.nameId = nameId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole(){ return role;}

    public void setRole(String role) { this.role = role; }

    public boolean getStatus() { return status; }

    public void setStatus(boolean status) { this.status = status; }



}
