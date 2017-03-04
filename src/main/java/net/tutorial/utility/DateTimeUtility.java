package net.tutorial.utility;


import java.sql.Timestamp;
import java.util.Date;
/**
 * Created by pongpantola.
 */
public class DateTimeUtility {



    public static Timestamp getTimestamp() {
        Date date = new java.util.Date();

        return new Timestamp(date.getTime());
    }



}
