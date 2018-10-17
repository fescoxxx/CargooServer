package com.passing_parcel.api.server.Utils;

import java.util.Date;

public class Data {

    /**
     * Метод определяет текущую дату
     * @return Возвращает текущую дату в милисекундах
     */
    public static Long getDate(){
        Date date = new Date();
        return date.getTime();
    }
}
