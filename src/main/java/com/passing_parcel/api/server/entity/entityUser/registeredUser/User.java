package com.passing_parcel.api.server.entity.entityUser.registeredUser;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Класс таблицы users из БД
 */
@Entity
@Table(name = "users")
public class User {

    @Id
//    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "username", nullable = false, length = 50)
    private String username;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @Column(name = "phone_number", length = 50)
    private String phoneNumber;

    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    @Column(name = "toke_firebase_push", length = 100)
    private String tokeFireBasePush;

    @OneToOne()
    @JoinColumn(name = "username",referencedColumnName = "username")
//    private Set<Authorities> authorities;
    private Authorities authorities;
//    @OneToMany()
//    @JoinColumn(name = "username",referencedColumnName = "username")
//    private Authorities authorities;


    public User() {
    }

    public User(String username, String password, String phoneNumber) {
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    public User(String username, String password, String phoneNumber, boolean enabled) {
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.enabled = enabled;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getTokeFireBasePush() {
        return tokeFireBasePush;
    }

    public void setTokeFireBasePush(String tokeFireBasePush) {
        this.tokeFireBasePush = tokeFireBasePush;
    }
}
