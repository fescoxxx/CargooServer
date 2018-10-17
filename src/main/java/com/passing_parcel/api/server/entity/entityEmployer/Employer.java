package com.passing_parcel.api.server.entity.entityEmployer;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "employer")
public class Employer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;

    @Column(name = "username", length = 100)
    private String username;

    @Column(name = "phone_number", length = 50)
    private String phoneNumber;

    @Column(name = "full_name", length = 200)
    private String fullName;

    @Column(name = "city", length = 100)
    private String city;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "status_email")
    private boolean statusEmail;

    @Column(name = "status_documents")
    private boolean statusDocuments;

    public Employer() {
    }
}
