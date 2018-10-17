package com.passing_parcel.api.server.service.userService;


import com.passing_parcel.api.server.statusAndErrors.StatusAndErrors;

public interface UserRegistrationService {

    void addNumberAndCodeToTheTable(String numberPhone);

//    StatusAndErrors confirmPhoneNumber(String numberPhone, String code, String tokenFireBase);
    StatusAndErrors confirmPhoneNumber(String numberPhone, String code);

    void addTokenFireBaseToDatabase(String numberPhone, String fireBase);

    StatusAndErrors verificationEmail(String email);

    StatusAndErrors registrationUser(String numberPhone,
                                     String email,
                                     String password,
                                     String role,
                                     String fullName,
                                     String passportData,
                                     String driverLicenseData,
                                     byte[] photoAvatar,
                                     byte[] photoPassport,
                                     byte[] photoDriverLicense);

}
