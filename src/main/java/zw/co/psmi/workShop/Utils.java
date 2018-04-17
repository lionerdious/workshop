/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.psmi.workShop;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.joda.time.DateTime;

/**
 *
 * @author lionel
 */
public class Utils {
    public static String prepairString(String email) {
        if (email == null || email.trim().isEmpty()) {
            return "";
        }
        return email;
    }
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public static String prepairStringForQuery(String val) {
        return "%" + prepairString(val).replaceAll("\\s", "%") + "%";
    }

    public static boolean empty(String name) {
        if (name == null) {
            return true;
        }
        if (name.trim().isEmpty()) {
            return true;
        }
        return false;
    }

    public static Integer parseInt(String val, String defaultVal) {
        try {
            return Integer.valueOf(val);
        } catch (Exception e) {
        }
        try {
            return Integer.valueOf(defaultVal);
        } catch (Exception e) {
        }
        return 0;
    }

    public static Long parseLong(String val, String defaultVal) {
        try {
            return Long.valueOf(val);
        } catch (Exception e) {
        }
        try {
            return Long.valueOf(defaultVal);
        } catch (Exception e) {
        }
        return 0l;
    }

    public static String parseDefaultIfEmpty(String get, String defaultValue) {
        if (!empty(get)) {
            return prepairString(get);
        }
        return prepairString(defaultValue);
    }

    public static Date parseDateDayStart(String get, String defaultValue) {
        Date d;
        try {
            d = sdf.parse(get);
            return d;
        } catch (Exception ex) {
            try {
                d = sdf.parse(defaultValue);
            } catch (Exception ex1) {
            }
        }
        d = new Date();
        return dateStart(d);
    }

    public static Date parseDateNullable(String get) {
        Date d;
        try {
            d = sdf.parse(get);
            return d;
        } catch (Exception ex) {
        }
        return null;
    }

    public static Date parseDateDayEnd(String get, String defaultValue) {
        Date d = parseDateDayStart(get, defaultValue);
        return new Date(d.getTime() + (24 * 60 * 60 * 1000 - 1));
    }

    public static Object parseIfEmpty(String get, String defaultValue) {
        if (!empty(get)) {
            return prepairString(get);
        }
        return prepairString(defaultValue);
    }

    public static Object parseLikeDefaultIfEmpty(String get, String defaultValue) {
        if (!empty(get)) {
            return prepairStringForQuery(get);
        }
        return prepairStringForQuery(defaultValue);
    }

    private static Date dateStart(Date d) {
        return new DateTime(d).withTimeAtStartOfDay().toDate();
    }
}
