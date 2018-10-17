package com.passing_parcel.api.server.entity.entityUser.registeredUser;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Класс таблицы authorities из БД
 */
@Entity
@Table(name = "authorities", indexes = { @Index(name = "ix_auth_username", columnList = "username, authority")})
public class Authorities {

    @Id
//    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "username", nullable = false, length = 50)
    private String username;

    @Column(name = "authority", nullable = false, length = 50)
    private String authority;

    public Authorities() {
    }

    public Authorities(String username, String authority) {
        this.username = username;
        this.authority = authority;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}

