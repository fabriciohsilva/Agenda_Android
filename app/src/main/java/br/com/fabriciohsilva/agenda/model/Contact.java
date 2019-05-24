package br.com.fabriciohsilva.agenda.model;

import android.support.annotation.NonNull;

import java.io.Serializable;

public class Contact implements Serializable {

    private Long id;
    private String name;
    private String telephone;
    private String email;

    public Contact(String name, String telephone, String email) {
        this.name = name;
        this.telephone = telephone;
        this.email = email;
    }

    public Contact() {

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getEmail() {
        return email;
    }

    @NonNull
    @Override
    public String toString() {
        return name + " - " + telephone;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public boolean validId() {
        return id > 0;
    }
}
