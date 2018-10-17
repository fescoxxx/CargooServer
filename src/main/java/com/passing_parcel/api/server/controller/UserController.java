package com.passing_parcel.api.server.controller;


import com.passing_parcel.api.server.service.userService.UserRegistrationService;
import com.passing_parcel.api.server.statusAndErrors.StatusAndErrors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * Контроллер для регистрации и обновления пользовательских данных
 */
@RestController
@RequestMapping("header") //TODO Переделать название header на более подходящее
public class UserController {

    @Autowired
    private UserRegistrationService userRegistrationService;

    /**
     * Примимает номер телефона для регистрации в БД и отправи на него кода подтверждения
     * @param numberPhone Номер телефона
     */
    @RequestMapping(value = "/phone", method = RequestMethod.POST)
    @ResponseBody
//    @Secured("permitAll")
    public void setNumberPhone(@RequestParam String numberPhone){

        userRegistrationService.addNumberAndCodeToTheTable(numberPhone);
    }

    /**
     * Метож отправляет на проверку код подтверждения
     * @param numberPhone Номер телефона
     * @param code Код подтверждения
     * @return  Возвраащет статус
     */
    @RequestMapping(value = "/phoneAndCode", method = RequestMethod.POST)
    @ResponseBody
    public StatusAndErrors confirmPhoneNumber(@RequestParam String numberPhone,
                                      @RequestParam String code){

        return userRegistrationService.confirmPhoneNumber(numberPhone, code);
    }

    @RequestMapping(value = "/verificationEmail", method = RequestMethod.POST)
    @ResponseBody
    public StatusAndErrors verificationEmail(@RequestParam String email){

        return userRegistrationService.verificationEmail(email);
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    @ResponseBody
    public StatusAndErrors registration(@RequestParam String numberPhone,
                                        @RequestParam String email,
                                        @RequestParam String password,
                                        @RequestParam String role,
                                        @RequestParam String fullName,
                                        @RequestParam String passportData,
                                        @RequestParam String driverLicenseData,
                                        @RequestParam byte[] photoAvatar,
                                        @RequestParam byte[] photoPassport,
                                        @RequestParam byte[] photoDriverLicense){

        return registration(numberPhone, email, password, role, fullName, passportData,
                driverLicenseData, photoAvatar, photoPassport, photoDriverLicense);
    }




        /**
         * Метод обновляет токен для push уведомлений
         * @param numberPhone Номер телефона
         * @param fireBase Новый токен fireBase
         */
    @RequestMapping(value = "/fireBase", method = RequestMethod.POST)
    @ResponseBody
    public void setFireBase(@RequestParam String numberPhone, @RequestParam String fireBase){

        userRegistrationService.addTokenFireBaseToDatabase(numberPhone, fireBase);
    }

}