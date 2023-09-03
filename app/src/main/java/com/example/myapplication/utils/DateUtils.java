package com.example.myapplication.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

    // date utility class
    public static String convertToDDMMYYYY(Date d){
        SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        return sdfDate.format(d);
    }
}
