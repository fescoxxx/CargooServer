package com.passing_parcel.api.server.entity.entityDriver;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Класс таблицы photo_cars из БД
 */
@Entity
@Table(name = "photo_car")
public class PhotoCar {

    @Id
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;

    @Lob
    @Column(name = "cm_photo")
    private byte[] photo;


    public PhotoCar() {
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }
}
