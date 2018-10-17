package com.passing_parcel.api.server.entity.entityUser.unregisteredUser;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Класс таблицы registration из БД
 */
@Entity
@Table(name = "registration")
public class Registration {

    @Id
//    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "phone_number", nullable = false, length = 50)
    private String phoneNumber;

    @Column(name = "code", length = 50)
    private String code;

    @Column(name = "data_of_request")
    private Long data_of_request;

    @Column(name = "number_of_request")
    private int number_of_request;

    public Registration() {
    }

    public Registration(String phoneNumber, String code, Long data_of_request, int number_of_request) {
        this.phoneNumber = phoneNumber;
        this.code = code;
        this.data_of_request = data_of_request;
        this.number_of_request = number_of_request;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getData_of_request() {
        return data_of_request;
    }

    public void setData_of_request(Long data_of_request) {
        this.data_of_request = data_of_request;
    }

    public int getNumber_of_request() {
        return number_of_request;
    }

    public void setNumber_of_request(int number_of_request) {
        this.number_of_request = number_of_request;
    }
}
