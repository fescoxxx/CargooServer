package com.passing_parcel.api.server.service.userService;

import com.passing_parcel.api.server.Utils.PasswordGenerator;
import com.passing_parcel.api.server.entity.entityDriver.Driver;
import com.passing_parcel.api.server.entity.entityUser.registeredUser.Authorities;
import com.passing_parcel.api.server.entity.entityUser.registeredUser.User;
import com.passing_parcel.api.server.entity.entityUser.unregisteredUser.Registration;
import com.passing_parcel.api.server.repository.driverRepository.DriverRepository;
import com.passing_parcel.api.server.repository.userRepository.AuthoritiesRepository;
import com.passing_parcel.api.server.repository.userRepository.RegistrationRepository;
import com.passing_parcel.api.server.repository.userRepository.UserRepository;
import com.passing_parcel.api.server.statusAndErrors.StatusAndErrors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;

import static com.sun.deploy.registration.InstallCommands.STATUS_OK;

/**
 * Класс отвечает за полученение и запись данных о пользователях
 */
@Service
@Transactional
public class UserRegistrationServiceImpl implements UserRegistrationService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RegistrationRepository registrationRepository;
    @Autowired
    private AuthoritiesRepository authoritiesRepository;
    @Autowired
    private DriverRepository driverRepository;


    private static final String CODE = "code123";
    private static final int INITIAL_VALUE = 1;
    private static final long ONE_DAY = 86400000;
//    private static final long ONE_DAY = 86400;
    private static final long TIME_OUT = 300000;
//    private static final long TIME_OUT = 300;

    //Controller
    /**
     * Метод добавляет номер и код в БД и отправляет смс с кодом
     * @param numberPhone Принимает в параметр номер телефона
     */
    @Override
    public void addNumberAndCodeToTheTable(String numberPhone){

        //Проверка на наличие зарегистрированного номера в БД
        //Возвращает true если пользователь не найден
        if (checkingTheNumberForPresenceInTheDatabase(numberPhone)){

            //проверка номера в регистрационной таблице(если не найден возвращает false)
            if (registrationRepository.exists(numberPhone)){

                //Обновляет значени в регистрационной таблице
                updateTheCodeAndNumberOfTheRequest(numberPhone);

            }else {
                //Добавить номер код и установить дату запроса в БД (отправка смс)
                createNewRegistration(numberPhone);
            }
        }else {
            //TODO выдаём сообщение что такой пользователь уже зарегистрирован.
            System.out.println("такой пользователь уже зарегистрирован+++++++++++");
        }
    }

    //Controller

    /**
     * Метод Принимает данны пользователя и проверяет их на ссообветствие с БД
     * @param numberPhone Номер телевона
     * @param code Код отправленный по смс
//     * @param tokenFireBase Принимает tokenFireBase
     * @return Возвращает AuthorizationToken
     */
    @Override
//    public StatusAndErrors confirmPhoneNumber(String numberPhone, String code, String tokenFireBase){
    public StatusAndErrors confirmPhoneNumber(String numberPhone, String code){
        Registration registrationUser;
        //проверка на совпадение номера и кода (если совпадают возвращает true)
        if (registrationRepository.findOne(numberPhone).getCode().equals(code)){
            registrationUser = registrationRepository.findOne(numberPhone);

            //Проверка актуальности кода
            if (getDate() <= registrationUser.getData_of_request()+TIME_OUT){

//                return registerUser(numberPhone, tokenFireBase);
                return new StatusAndErrors("OK", null);


            }else {
                //TODO Выдать сообщение что время вышло.
                System.out.println("время вышло+++++++++++");
            }
        }else {
            //TODO Выдать кокое нибудь сообщение что код или номер не верны.
            System.out.println("код или номер не верны+++++++++++");
        }
        return null;
    }

    @Override
    public StatusAndErrors verificationEmail(String email) {

        if (userRepository.exists(email)){
            return new StatusAndErrors("ERROR", "Такой email уже существует");
        }else {
            return new StatusAndErrors("OK", null);

        }

    }

    @Override
    public StatusAndErrors registrationUser(String numberPhone, String email, String password, String role, String fullName, String passportData, String driverLicenseData, byte[] photoAvatar, byte[] photoPassport, byte[] photoDriverLicense) {

        Driver driver = new Driver();
        driver.setPhoneNumber(numberPhone);
        driver.setEmail(email);
        driver.setUsername(email);
        driver.setFullName(fullName);
        driver.setPassportData(passportData);
        driver.setDriverLicenseData(driverLicenseData);
        driver.setVerificationOfDocuments(false);



        return null;
    }

    /**
     * Обновляет fireBase  у пользователя
     * @param number Номер телефона пользователя
     * @param fireBase fireBase пользователя
     */
    @Override
    public void addTokenFireBaseToDatabase(String number, String fireBase) {
        for (User user : userRepository.findAll()) {
            if (user.getPhoneNumber().equals(number)){
                user.setTokeFireBasePush(fireBase);
                userRepository.saveAndFlush(user);
                break;
            }
        }
    }



//    /**
//     * Регистрирует пользователя и добавлят его в БД пользователей
//     * @param numberPhone Номер телефона пользователя
//     * @param tokenFireBase
//     * @return Возвращает AuthorizationToken
//     */
//    private AuthorizationToken registerUser(String numberPhone, String tokenFireBase) {
//        String bases[] = passwordGeneration(numberPhone); //Гениратор пороля, логина и токена авторизации
//
//        System.out.println(bases[0]+"+++++++++++++++++++++");
//
//        String passwordEncodeBCrypt = bases[0]; //хеш пароля для БД
//        String secret = bases[1]; //токен для авторизации
//        String uName = bases[2]; //Имя пользователя
//
//        //Заполнение полей в таблице users
//        User user = new User();
//        user.setUsername(uName);
//        user.setPassword(passwordEncodeBCrypt);
//        user.setPhoneNumber(numberPhone);
//        user.setEnabled(true);
//        user.setTokeFireBasePush(tokenFireBase); //сохраняем токен fireBase для Push уведомлений
//        userRepository.save(user); //сохраняем в БД
//
//        //Заполнение полей в таблице authorities
//        Authorities authorities = new Authorities(uName, "ROLE_USER");
//        authoritiesRepository.save(authorities);
//
//        registrationRepository.delete(numberPhone);
//
//        return new AuthorizationToken(STATUS_OK, secret);
//    }

    //Обновляет значения в регистрационной таблице

    /**
     * Обновляет значения в регистрационной таблице (smsCode)
     * @param numberPhone Номер телефона пользователя
     */
    //TODO реализовать передачу обновления смс кода
    private void updateTheCodeAndNumberOfTheRequest(String numberPhone) {

        for (Registration registration : registrationRepository.findAll()) {
            if (registration.getPhoneNumber().equals(numberPhone)){
                if (registration.getNumber_of_request() < 3){

                    registration.setCode("new"+CODE);
                    registration.setData_of_request(getDate());
                    registration.setNumber_of_request(registration.getNumber_of_request()+1);
                    registrationRepository.saveAndFlush(registration);

                }else{
                    if (getDate() >= registration.getData_of_request()+ONE_DAY){
                        registrationRepository.delete(numberPhone);
                        createNewRegistration(numberPhone);
                    }else {
                        //TODO Выводим какое нибудь сообщение например (что повторить попытку можно только через сутки)
                        System.out.println("повторить попытку можно только через сутки+++++++++++");

                    }
                }
            }
        }
    }

    //Добавить номер код и установить дату запроса в БД (отправка смс)

    /**
     * Метод устанавливает данные для регистрации пользователя
     * @param numberPhone Номер телефона пользователя
     */
    //TODO Реализовать отправку смс кода
    private void createNewRegistration(String numberPhone) {
        Registration registration = new Registration(numberPhone, numberPhone+CODE, getDate(), INITIAL_VALUE);
        registrationRepository.save(registration);
    }



    /**
     * Метод проверяет на наличие зарегистрированного номера в БД
     * @param numberPhone Номер телефона пользователя
     * @return Возвращает true если пользователь не найден
     */
    private boolean checkingTheNumberForPresenceInTheDatabase(String numberPhone) {
        for (User user : userRepository.findAll()) {
            if (user.getPhoneNumber().equals(numberPhone)) return false;
        }
        return true;
    }

    /**
     * Метод гениратор пороля, логина и токена авторизации
     * @param numberPhone Номер телефона пользователя
     * @return Масив типа String (хеш пароля для БД, токен для авторизации, username)
     */
    private String[] passwordGeneration(String numberPhone){
        PasswordGenerator passwordGenerator = new PasswordGenerator.PasswordGeneratorBuilder()
                .useDigits(true)
                .useLower(true)
                .useUpper(true)
                .build();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(); //хеширование паролей для БД
        String passwordNotEncode = passwordGenerator.generate(100); //незакодированный пароль
        String passwordEncodeBCrypt = bCryptPasswordEncoder.encode(passwordNotEncode); //хеш пароля для БД
        Registration registration = registrationRepository.findOne(numberPhone); //Юзер из БД (registration)

        String secret = Base64.getEncoder()
                .encodeToString((registration.getPhoneNumber() + ":" + passwordNotEncode)
                        .getBytes(StandardCharsets.UTF_8)); //токен для авторизации

        String bases[] = new String[3];
        bases[0] = passwordEncodeBCrypt; //хеш пароля для БД
        bases[1] = secret; //токен для авторизации
        bases[2] = numberPhone+"ry"+Character.toString(passwordEncodeBCrypt.charAt((int)(Math.random()*9+1))); //username

        return bases;
    }

    /**
     * Метод определяет текущую дату
     * @return Возвращает текущую дату в милисекундах
     */
    private Long getDate(){
    Date date = new Date();
    return date.getTime();
    }


}
