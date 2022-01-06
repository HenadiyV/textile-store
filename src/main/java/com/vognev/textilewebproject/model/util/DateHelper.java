package com.vognev.textilewebproject.model.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * textilewebproject_2  22/10/2021-20:46
 */
public abstract class DateHelper {
    public static Date convertStringToDate(String dat){

        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

        format.applyPattern("dd.MM.yyyy");//"yyyy-MM-dd"

        try {
            Date docDate= format.parse(dat);

            java.sql.Date sqlDate = new java.sql.Date( docDate.getTime() );

            return sqlDate;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
