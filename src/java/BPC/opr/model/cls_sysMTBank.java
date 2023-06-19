/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BPC.opr.model;

import java.util.Date;

/**
 *
 * @author witco
 */
public class cls_sysMTBank {
    String bank_id;
    String bank_code;
    String bank_name_th;
    String bank_name_en;
    String moified_by;
    Date moified_date;

    public cls_sysMTBank(String bank_id, String bank_code, String bank_name_th, String bank_name_en, String moified_by, Date moified_date) {
        this.bank_id = bank_id;
        this.bank_code = bank_code;
        this.bank_name_th = bank_name_th;
        this.bank_name_en = bank_name_en;
        this.moified_by = moified_by;
        this.moified_date = moified_date;
    }

    public String getBank_id() {
        return bank_id;
    }

    public void setBank_id(String bank_id) {
        this.bank_id = bank_id;
    }

    public String getBank_code() {
        return bank_code;
    }

    public void setBank_code(String bank_code) {
        this.bank_code = bank_code;
    }

    public String getBank_name_th() {
        return bank_name_th;
    }

    public void setBank_name_th(String bank_name_th) {
        this.bank_name_th = bank_name_th;
    }

    public String getBank_name_en() {
        return bank_name_en;
    }

    public void setBank_name_en(String bank_name_en) {
        this.bank_name_en = bank_name_en;
    }

    public String getMoified_by() {
        return moified_by;
    }

    public void setMoified_by(String moified_by) {
        this.moified_by = moified_by;
    }

    public Date getMoified_date() {
        return moified_date;
    }

    public void setMoified_date(Date moified_date) {
        this.moified_date = moified_date;
    }
    
    
}
