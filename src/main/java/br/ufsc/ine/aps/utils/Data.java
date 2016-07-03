package br.ufsc.ine.aps.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by mmiola on 02/07/16.
 */
public class Data {

    public static Calendar dateToCalendar(Date date){
        if(date != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            return cal;
        }
        return null;
    }

}
