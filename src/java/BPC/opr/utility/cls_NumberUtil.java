/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exat.mtc.utility;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 *
 * @author bundit_put
 */
public class cls_NumberUtil {

    public static double round(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }

//        BigDecimal bd = new BigDecimal(value);
//        bd = bd.setScale(places, RoundingMode.HALF_UP);
//        return bd.doubleValue();
        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
    
    public static String format(Double value) {
        return format(value.doubleValue(), "#,##0.00");
    }

    public static String format(Double value, String pattern) {
        if (value == null || pattern == null) {
            throw new IllegalArgumentException();
        }
        NumberFormat formatter = new DecimalFormat(pattern);
        return formatter.format(value.doubleValue());
    }

    public static String formatMoneyDoubleToBathThai(Double d) {
        // แปลงได้ไม่เกินหลักล้าน
        String fin = d.toString();
        fin = fin.replace(".", "/");
        fin = fin.replace(",", "");
        String[] spl = fin.split("/");
        String d1 = spl[0];
        String d2 = spl[1];
        if (d2.length() > 1) {
            d2.substring(0, 2);
        }
        String BathText = "";
        String Satang = "";
        String[] numThai = {"ศูนย์", "หนึ่ง", "สอง", "สาม", "สี่", "ห้า", "หก", "เจ็ด", "แปด", "เก้า", "เอ็ด", "ยี่"};
        String[] Tmp = {"", "", "สิบ", "ร้อย", "พัน", "หมื่น", "แสน", "ล้าน", "สิบ", "ร้อย", "พัน", "หมื่น", "แสน", "ล้าน"};
        if (d1.length() > 0) {
            for (int i = 1; i < d1.length() + 1; i++) {
                String a = d1.substring(d1.length() - i, (1 + d1.length()) - i);
                if (Integer.parseInt(a) == 0) {
                } else if ((i == 1) && (Integer.parseInt(a) == 1)) {
                    if (d1.length() > 1) {
                        String chk = d1.substring(d1.length() - 2, d1.length() - 1);
                        if (Integer.parseInt(chk) == 0) {
                            BathText = numThai[Integer.parseInt(a)] + Tmp[i] + BathText;
                        } else {
                            BathText = numThai[10] + Tmp[i] + BathText;
                        }
                    }
                } else if ((i == 2) && (Integer.parseInt(a) == 1)) {
                    BathText = Tmp[i] + BathText;
                } else if ((i == 2) && (Integer.parseInt(a) == 2)) {
                    BathText = numThai[11] + Tmp[i] + BathText;
                } else {
                    BathText = numThai[Integer.parseInt(a)] + Tmp[i] + BathText;
                }
            }
            BathText = BathText + "บาท";
        }
        // Process สตางค์
        // ** tol ผลบวกของสตางค์ ถ้าได้ 0 ให้แสดง "ถ้วน"
        // Check ex.50.50 splite ได้ [1]50 [2]5
        int tol = 0;
        if (d2.length() == 1) {
            String a = d2;
            if (Integer.parseInt(a) == 1) {
                Satang = Tmp[2];
            } else if (Integer.parseInt(a) == 2) {
                Satang = numThai[11] + Tmp[2];
            } else {
                Satang = numThai[Integer.parseInt(a)] + Tmp[2];
            }
            tol += Integer.parseInt(a);
        } else {
            for (int i = 1; i < d2.length() + 1; i++) {
                String a = d2.substring(d2.length() - i, (1 + d2.length()) - i);
                tol += Integer.parseInt(a);
                if (Integer.parseInt(a) == 0) {
                } else if ((i == 1) && (Integer.parseInt(a) == 1)) {
                    String chk = d2.substring(d2.length() - 2, d2.length() - 1);
                    if (Integer.parseInt(chk) == 0) {
                        Satang = numThai[Integer.parseInt(a)] + Tmp[i] + Satang;
                    } else {
                        Satang = numThai[10] + Tmp[i] + Satang;
                    }
                } else if ((i == 2) && (Integer.parseInt(a) == 1)) {
                    Satang = Tmp[i] + Satang;
                } else if ((i == 2) && (Integer.parseInt(a) == 2)) {
                    Satang = numThai[11] + Tmp[i] + Satang;
                } else {
                    Satang = numThai[Integer.parseInt(a)] + Tmp[i] + Satang;
                }
            }
        }
        if (tol == 0) {
            BathText = BathText + "ถ้วน";
        } else {
            BathText = BathText + Satang + "สตางค์";
        }
        return BathText;
    }

    public static String NumberFormat(String patten, double value) {

        /*Patten 
         * 1.#,###     -----> 1,000
         * 2.#,###.##  -----> 1,000.55
         * 3.#,###.00  -----> 1,000.00
         */
        DecimalFormat fmt = new DecimalFormat(patten);
        String strOut = fmt.format(value);
        return strOut;
    }

    //format costcenter
    public static String maskZero(String format, String value) {
        try {
            return new DecimalFormat(format).format(Double.valueOf(value));
        } catch (Exception ex) {
            return "";
        }

    }

    public static void main(String[] args) {
        //System.out.println(convertMoneyDoubleToBathThai(10.57));
        System.out.println(NumberFormat("#,###.00", 10));
        System.out.println(maskZero("00000000.00", String.valueOf(1277.00)) + maskZero("00000000.00", "2298.60"));

        double x = 66350.0 / 210;
        double x1 = round(66350.0 / 210, 2);
        System.out.println("x : " + x);
        System.out.println("x : " + x1);
        System.out.println("x : " + round(66350.0 / 210, 2));

        double y = x * 1.5;
        double y1 = round((x1 * 1.5), 2);
        System.out.println("y : " + y);
        System.out.println("y : " + y1);
        System.out.println("y : " + round(y, 2));

        double z = y * 20;
        double z1 = round(y, 2) * 20;
        System.out.println("z : " + z);
        System.out.println("z : " + y1 + "  - " + z1);
        System.out.println("z : " + round(round(66350.0 / 210, 2) * 1.5, 2) * 20);

        double salary = 66350.0;
        double formulaHour = 1.5;
        double hour = 20;
        double salary_per_hr = round(salary / 210,3);
        System.out.println("salary_per_hr = " + salary_per_hr);
        double salary_per_hr_ROUND = round(salary_per_hr, 2);
        System.out.println("salary_per_hr_ROUND = " + salary_per_hr_ROUND);
        double sal_x_formula = round(salary_per_hr_ROUND * formulaHour,3);
        System.out.println("sal_x_formula = " + sal_x_formula);
        double sal_x_formula_ROUND = round(sal_x_formula,2);
        System.out.println("sal_x_formula_ROUND = " + sal_x_formula_ROUND);
        double result = sal_x_formula_ROUND * hour;
        System.out.println("result = " + result);

    }
}
