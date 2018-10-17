package com.passing_parcel.api.server.entity.entityDriver;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "photo_driver_documents")
public class PhotoDriverDocuments {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;

    @Lob
    @Column(name = "photo_avatar")
    private byte[] photoAvatar;

    @Lob
    @Column(name = "photo_passport")
    private byte[] photoPassport;

    @Lob
    @Column(name = "photo_driver_license")
    private byte[] photoDriverLicense;
}
