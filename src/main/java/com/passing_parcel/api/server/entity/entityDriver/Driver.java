package com.passing_parcel.api.server.entity.entityDriver;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "driver")
public class Driver {

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

    @Column(name = "passport_data", length = 100)
    private String passportData;

    @Column(name = "driver_license_data", length = 100)
    private String driverLicenseData;

    @Column(name = "verification_of_documents")
    private boolean verificationOfDocuments;

    @Column(name = "category_car", length = 50)
    private String categoryCar;

    @OneToOne()
    @JoinColumn(name = "id",referencedColumnName = "id")
    private PhotoDriverDocuments photoDriverDocuments;

    @OneToOne()
    @JoinColumn(name = "id",referencedColumnName = "id")
    private PhotoCar photoCar;

    public Driver() {
    }

    public Driver(String username, String phoneNumber, String fullName, String city, String email, String passportData, String driverLicenseData, String categoryCar) {
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.fullName = fullName;
        this.city = city;
        this.email = email;
        this.passportData = passportData;
        this.driverLicenseData = driverLicenseData;
        this.categoryCar = categoryCar;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassportData() {
        return passportData;
    }

    public void setPassportData(String passportData) {
        this.passportData = passportData;
    }

    public String getDriverLicenseData() {
        return driverLicenseData;
    }

    public void setDriverLicenseData(String driverLicenseData) {
        this.driverLicenseData = driverLicenseData;
    }

    public boolean isVerificationOfDocuments() {
        return verificationOfDocuments;
    }

    public void setVerificationOfDocuments(boolean verificationOfDocuments) {
        this.verificationOfDocuments = verificationOfDocuments;
    }

    public String getCategoryCar() {
        return categoryCar;
    }

    public void setCategoryCar(String categoryCar) {
        this.categoryCar = categoryCar;
    }

    public PhotoDriverDocuments getPhotoDriverDocuments() {
        return photoDriverDocuments;
    }

    public void setPhotoDriverDocuments(PhotoDriverDocuments photoDriverDocuments) {
        this.photoDriverDocuments = photoDriverDocuments;
    }

    public PhotoCar getPhotoCar() {
        return photoCar;
    }

    public void setPhotoCar(PhotoCar photoCar) {
        this.photoCar = photoCar;
    }
}
