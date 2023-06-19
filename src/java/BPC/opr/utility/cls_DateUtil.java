/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exat.mtc.utility;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author bundit_put
 */
public class cls_DateUtil {

    /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
     */
    /**
     *
     * @author bundit_put
     */
    public static void main(String[] args) throws ParseException, Exception {
        java.sql.Date sqlToday;
        java.util.Date today = new java.util.Date();

        System.out.println(new Timestamp(today.getTime()));

        //sqlToday = new java.sql.Date("");
        String sDate1 = "31/12/1998";
        Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);

        System.out.println(sDate1 + "\t" + date1);

        //System.out.println(FormateDate.getLastDayOfMonth("2559-02-01"));
        System.out.println(formatNOW("yyyy-MM-dd", false));
        System.out.println(formatNOW("yy", true));
        System.out.println(formatNOW("HH", true));
        System.out.println(cls_DateUtil.convertNVarcharDateOracleToWeb("20150515"));
        System.out.println(format("20151012", "yyyyMMdd", "dd/MM/yyyy", false, true));
        System.out.println(format("12/10/2560", "dd/MM/yyyy", "yy", false, false));
        System.out.println(format("12/10/2015", "dd/MM/yyyy", "yyyy-MM-dd", false, false));
        System.out.println(checkformatdate("12522008"));
        System.out.println(cls_DateUtil.format("31/05/2561", "dd/MM/yyyy", "yyyyMMdd", true, false));

        System.out.println(cls_DateUtil.format("2561-01-01", "yyyy-mm-dd", "yyyy-mm-dd 00:00:00.000", true, false));
        //1996-01-01 00:00:00.000

        System.out.println(cls_DateUtil.format("2012-03-30 00:00:00.000", "yyyy-MM-dd HH:mm:ss", "dd/MM/yyyy", false, true));

        System.out.println(cls_DateUtil.now());
        System.out.println(cls_DateUtil.nowEn());

        System.out.println(cls_DateUtil.format("12/10/2563", "dd/MM/yyyy", "ddMMyy", true, false));

        System.out.println(cls_DateUtil.format("121020", "ddMMyy", "ddMMyyyy", false, true));

        System.out.println(cls_DateUtil.format("1994-08-31 00:00:00.000", "yyyy-MM-dd HH:mm:ss", "ddMMyyyy", false, true));

    }

    /*
     *   ## FORMAT
     G   Era designator  Text    AD
     y   Year    Year    1996; 96
     Y   Week year   Year    2009; 09
     M   Month in year   Month   July; Jul; 07
     w   Week in year    Number  27
     W   Week in month   Number  2
     D   Day in year Number  189
     d   Day in month    Number  10
     F   Day of week in month    Number  2
     E   Day name in week    Text    Tuesday; Tue
     u   Day number of week (1 = Monday, ..., 7 = Sunday)    Number  1
     a   Am/pm marker    Text    PM
     H   Hour in day (0-23)  Number  0
     k   Hour in day (1-24)  Number  24
     K   Hour in am/pm (0-11)    Number  0
     h   Hour in am/pm (1-12)    Number  12
     m   Minute in hour  Number  30
     s   Second in minute    Number  55
     S   Millisecond Number  978
     z   Time zone   General time zone   Pacific Standard Time; PST; GMT-08:00
     Z   Time zone   RFC 822 time zone   -0800
     X   Time zone   ISO 8601 time zone  -08; -0800; -08:00
     * 
     *   ## EXAMPLES     
     "yyyy.MM.dd G 'at' HH:mm:ss z"  2001.07.04 AD at 12:08:56 PDT
     "EEE, MMM d, ''yy"  Wed, Jul 4, '01
     "h:mm a"    12:08 PM
     "hh 'o''clock' a, zzzz" 12 o'clock PM, Pacific Daylight Time
     "K:mm a, z" 0:08 PM, PDT
     "yyyyy.MMMMM.dd GGG hh:mm aaa"  02001.July.04 AD 12:08 PM
     "EEE, d MMM yyyy HH:mm:ss Z"    Wed, 4 Jul 2001 12:08:56 -0700
     "yyMMddHHmmssZ" 010704120856-0700
     "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"   2001-07-04T12:08:56.235-0700
     "yyyy-MM-dd'T'HH:mm:ss.SSSXXX"   2001-07-04T12:08:56.235-07:00
     "YYYY-'W'ww-u"  2001-W27-3
     * */
    public static boolean checkformatdate(String term) {
        try {
            // term : Format dd/MM/yyyy
            String temp = term;
            String[] parts = temp.split("/");
            if (parts.length != 3) {
                return false;
            }
            if (parts[0].length() > 2 || parts[1].length() > 2 || parts[2].length() != 4) {
                return false;
            }
            int day = Integer.parseInt(parts[0], 10);
            int month = Integer.parseInt(parts[1], 10);
            int year = Integer.parseInt(parts[2], 10);

            if (month == 0 || month > 12) {
                return false;
            }

            int[] monthLength = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

            // Adjust for leap years
            if (year % 400 == 0 || (year % 100 != 0 && year % 4 == 0)) {
                monthLength[1] = 29;
            }

            // Check the range of the day
            return day > 0 && day <= monthLength[month - 1];
        } catch (Exception e) {
            return false;
        }
    }

    public static String checkformat(String term) throws Exception {
        if (checkformatdate(term) == true) {
            return term;
        } else {
            throw new Exception("Dateformat Error " + term);
        }
    }

    public static String format(String date, String formatInput, String formatOutput, Boolean InputBuddhist, Boolean outputBuddhist) throws ParseException {

        // VARIABLE
        Locale locale;
        DateFormat dateDestination;

        // CHECK NULL INPUT DATE
        if (date != null) {

            // SET FORMAT YEAR INPUT
            if (InputBuddhist.booleanValue() == true) {
                locale = new Locale("th", "TH");
            } else {
                locale = Locale.ENGLISH;
            }

            // SET FORMAT DATE INPUT
            DateFormat dateSource = new SimpleDateFormat(formatInput, locale);
            Date d = dateSource.parse(date);

            // CHECK INFINITY DATE 9999-12-31
            // SET FORMAT OUTPUT DATE
            if (outputBuddhist.booleanValue() == true && d.after(getInfinity())) {
                // NOT SET FORMAT YEAR
                dateDestination = new SimpleDateFormat(formatOutput, locale);
            } else {

                if (outputBuddhist.booleanValue() == true) {
                    locale = new Locale("th", "TH");
                } else {
                    locale = Locale.ENGLISH;
                }
                dateDestination = new SimpleDateFormat(formatOutput, locale);
            }

            return dateDestination.format(d);
        } else {
            return null;
        }
    }

    public static Date convert(String date, String formatInput, Boolean InputBuddhist) throws ParseException {
        Locale locale;
        Date d = null;
        if (date != null) {

            if (InputBuddhist.booleanValue() == true) {
                locale = new Locale("th", "TH");
            } else {
                locale = Locale.ENGLISH;
            }
            DateFormat dateSource = new SimpleDateFormat(formatInput, locale);
            d = dateSource.parse(date);

            return d;
        } else {
            return d;
        }
    }

    public static String formatNOW(String formatOutput, Boolean outputBuddhist) throws ParseException {
        Locale locale;
        if (outputBuddhist.booleanValue() == true) {
            locale = new Locale("th", "TH");
        } else {
            locale = Locale.ENGLISH;
        }
        DateFormat dateDestination = new SimpleDateFormat(formatOutput, locale);

        return dateDestination.format(new Date());
    }

    public static String getTHAIMonth(String month) {
        String ex = month;
        if (month.length() < 2) {
            ex = "0" + month;
        }
        String[] MonthThai = {"", "มกราคม", "กุมภาพันธ์", "มีนาคม", "เมษายน", "พฤษภาคม", "มิถุนายน", "กรกฎาคม", "สิงหาคม", "กันยายน", "ตุลาคม", "พฤศจิกายน", "ธันวาคม"};
        String ret = MonthThai[Integer.parseInt(ex)];
        return ret;
    }

    public static int getIntMonth(String month) {
        int ret = 0;
        if (month != null) {
            ret = Integer.parseInt(month);
        }
        return ret;
    }

    public static Integer convertYear(Integer year, Boolean InputBuddhist, Boolean outputBuddhist) throws ParseException {
        Integer ret = null;
        if (year != null) {
            ret = Integer.parseInt(format(year.toString(), "yyyy", "yyyy", InputBuddhist, outputBuddhist));
        }
        return ret;
    }

    public static String convertYear(String year, Boolean InputBuddhist, Boolean outputBuddhist) throws ParseException {
        String ret = null;
        if (year != null) {

            if (Integer.parseInt(year) > (9999 - 543)
                    && outputBuddhist == true) {
                ret = year;
            } else {
                ret = format(year, "yyyy", "yyyy", InputBuddhist, outputBuddhist);
            }
        }
        return ret;
    }

    public static int getIntMonthBudgetYear(String month) {
        int ret = 0;
        switch (getIntMonth(month)) {
            case 1:
                ret = 4;
                break;
            case 2:
                ret = 5;
                break;
            case 3:
                ret = 6;
                break;
            case 4:
                ret = 7;
                break;
            case 5:
                ret = 8;
                break;
            case 6:
                ret = 9;
                break;
            case 7:
                ret = 10;
                break;
            case 8:
                ret = 11;
                break;
            case 9:
                ret = 12;
                break;
            case 10:
                ret = 1;
                break;
            case 11:
                ret = 2;
                break;
            case 12:
                ret = 3;
                break;
            default:
                ret = 0;
                break;
        }
        return ret;
    }

    public static Date getInfinity() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        return sdf.parse("9456-12-31");
    }

    public static String adjust(String date, String formatInput, String formatOutput, Boolean InputBuddhist, Boolean outputBuddhist, int Calandar, int value) throws ParseException {

        // How to use
//        adjust("30/09/2562", "dd/MM/yyyy", "yyyy", Boolean.TRUE, Boolean.TRUE, Calendar.MONTH, 3);
        // VARIABLE
        Locale locale;
        DateFormat dateDestination;
        Date d = convert(date, formatInput, InputBuddhist);

        // CHECK NULL INPUT DATE
        if (d != null) {

            // SET FORMAT YEAR INPUT
            if (InputBuddhist.booleanValue() == true) {
                locale = new Locale("th", "TH");
            } else {
                locale = Locale.ENGLISH;
            }

            Calendar calendar = Calendar.getInstance(locale);
            calendar.setTime(d);
            calendar.add(Calandar, value);

            // CHECK INFINITY DATE 9999-12-31
            // SET FORMAT OUTPUT DATE
            if (outputBuddhist.booleanValue() == true && d.after(getInfinity())) {
                // NOT SET FORMAT YEAR
                dateDestination = new SimpleDateFormat(formatOutput, locale);
            } else {

                if (outputBuddhist.booleanValue() == true) {
                    locale = new Locale("th", "TH");
                } else {
                    locale = Locale.ENGLISH;
                }
                dateDestination = new SimpleDateFormat(formatOutput, locale);
            }

            return dateDestination.format(calendar.getTime());
        } else {
            return null;
        }
    }

    public static Date adjust(String date, String formatInput, Boolean isBuddhist, int Calandar, int value) throws ParseException {

        // How to use
//        adjust("30/09/2562", "dd/MM/yyyy", "yyyy", Boolean.TRUE, Boolean.TRUE, Calendar.MONTH, 3);
        // VARIABLE
        Locale locale;
        Date d = convert(date, formatInput, isBuddhist);

        // CHECK NULL INPUT DATE
        if (d != null) {

            // SET FORMAT YEAR INPUT
            if (isBuddhist.booleanValue() == true) {
                locale = new Locale("th", "TH");
            } else {
                locale = Locale.ENGLISH;
            }

            Calendar calendar = Calendar.getInstance(locale);
            calendar.setTime(d);
            calendar.add(Calandar, value);

            return calendar.getTime();
        } else {
            return null;
        }
    }

    public static Date adjust(Date date, Boolean isBuddhist, int Calandar, int value) throws ParseException {

        // How to use
//        adjust("30/09/2562", "dd/MM/yyyy", "yyyy", Boolean.TRUE, Boolean.TRUE, Calendar.MONTH, 3);
        // VARIABLE
        Locale locale;
        Date d = date;
        // CHECK NULL INPUT DATE
        if (d != null) {

            // SET FORMAT YEAR INPUT
            if (isBuddhist.booleanValue() == true) {
                locale = new Locale("th", "TH");
            } else {
                locale = Locale.ENGLISH;
            }

            Calendar calendar = Calendar.getInstance(locale);
            calendar.setTime(d);
            calendar.add(Calandar, value);

            return calendar.getTime();
        } else {
            return null;
        }
    }

    public static String convert(Date date, String formatOutput, Boolean outputBuddhist) throws ParseException {
        Locale locale;
        DateFormat dateDestination;
        String d = null;
        if (date != null) {
            // CHECK INFINITY DATE 9999-12-31
            // SET FORMAT OUTPUT DATE
            if (outputBuddhist.booleanValue() == true && date.after(getInfinity())) {
                // NOT SET FORMAT YEAR
                dateDestination = new SimpleDateFormat(formatOutput, Locale.ENGLISH);
            } else {

                if (outputBuddhist.booleanValue() == true) {
                    locale = new Locale("th", "TH");
                } else {
                    locale = Locale.ENGLISH;
                }
                dateDestination = new SimpleDateFormat(formatOutput, locale);
            }

            return dateDestination.format(date);
        } else {
            return d;
        }
    }

    public static String convertFirstDateOfFiscalyear(Date date, String formatOutput, Boolean outputBuddhist) throws ParseException {
        //@Exam
        //@param d -> 31/01/2014
        //@result -> 31/01/2557

        String Adate[] = new String[3];
        int month = 0;
        int year = 0;
        Adate = convert(date, "dd/MM/yyyy", Boolean.FALSE).split("/");
        month = (Integer.parseInt(Adate[1]));
        year = (Integer.parseInt(Adate[2]));
        if (month < 10) {
            year--;
        }
        String sdate = year + "-10-01";
        return format(sdate, "yyyy-MM-dd", formatOutput, Boolean.FALSE, outputBuddhist);

    }

    public static String convertNVarcharDateOracleToWeb(String date) throws ParseException {
        if (date == null || date.equals("")) {
            return "";
        }
        Locale locale = new Locale("th", "TH");
        Locale locale2 = new Locale("en", "EN");
        DateFormat dateSource = new SimpleDateFormat("yyyyMMdd", locale2);
        Date d = dateSource.parse(date);
        DateFormat dateDestination = new SimpleDateFormat("dd/MM/yyyy", locale2);
        return convertAddYearBuddhist(dateDestination.format(d));
    }

    public static String convertAddYearBuddhist(String d) {
        //@Exam
        //@param d -> 31/01/2014
        //@result -> 31/01/2557
        String Adate[] = new String[3];
        int year = 0;
        Adate = d.split("/");
        year = (Integer.parseInt(Adate[2]) + 543);
        String date = Adate[0] + "/" + Adate[1] + "/" + year;
        return date;
    }

    public static String now() {
        Locale locale = new Locale("th", "TH");
        SimpleDateFormat simple_date_format
                = new SimpleDateFormat("dd/MM/yyyy", locale);
        String dt = simple_date_format.format(new Date());

        return dt;
    }

    public static String nowEn() {
        Locale locale = new Locale("en", "US");
        SimpleDateFormat simple_date_format
                = new SimpleDateFormat("dd/MM/yyyy", locale);
        String dt = simple_date_format.format(new Date());

        return dt;
    }

}
