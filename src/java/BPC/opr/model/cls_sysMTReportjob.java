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
public class cls_sysMTReportjob {
    int reportjob_id;
    String reportjob_ref;
    String reportjob_type;
    String reportjob_status;
    Date reportjob_fromdate;
    Date reportjob_todate;
    Date reportjob_paydate;
    String reportjob_language;
    String reportjob_note;
    
    String company_code;
    String project_code;
    
    String moified_by;
    Date moified_date;

    public cls_sysMTReportjob(int reportjob_id, String reportjob_ref, String reportjob_type, String reportjob_status, Date reportjob_fromdate, Date reportjob_todate, Date reportjob_paydate, String reportjob_language, String reportjob_note, String company_code, String project_code, String moified_by, Date moified_date) {
        this.reportjob_id = reportjob_id;
        this.reportjob_ref = reportjob_ref;
        this.reportjob_type = reportjob_type;
        this.reportjob_status = reportjob_status;
        this.reportjob_fromdate = reportjob_fromdate;
        this.reportjob_todate = reportjob_todate;
        this.reportjob_paydate = reportjob_paydate;
        this.reportjob_language = reportjob_language;
        this.reportjob_note = reportjob_note;
        this.company_code = company_code;
        this.project_code = project_code;
        this.moified_by = moified_by;
        this.moified_date = moified_date;
    }

    public int getReportjob_id() {
        return reportjob_id;
    }

    public void setReportjob_id(int reportjob_id) {
        this.reportjob_id = reportjob_id;
    }

    public String getReportjob_ref() {
        return reportjob_ref;
    }

    public void setReportjob_ref(String reportjob_ref) {
        this.reportjob_ref = reportjob_ref;
    }

    public String getReportjob_type() {
        return reportjob_type;
    }

    public void setReportjob_type(String reportjob_type) {
        this.reportjob_type = reportjob_type;
    }

    public String getReportjob_status() {
        return reportjob_status;
    }

    public void setReportjob_status(String reportjob_status) {
        this.reportjob_status = reportjob_status;
    }

    public Date getReportjob_fromdate() {
        return reportjob_fromdate;
    }

    public void setReportjob_fromdate(Date reportjob_fromdate) {
        this.reportjob_fromdate = reportjob_fromdate;
    }

    public Date getReportjob_todate() {
        return reportjob_todate;
    }

    public void setReportjob_todate(Date reportjob_todate) {
        this.reportjob_todate = reportjob_todate;
    }

    public Date getReportjob_paydate() {
        return reportjob_paydate;
    }

    public void setReportjob_paydate(Date reportjob_paydate) {
        this.reportjob_paydate = reportjob_paydate;
    }

    public String getReportjob_language() {
        return reportjob_language;
    }

    public void setReportjob_language(String reportjob_language) {
        this.reportjob_language = reportjob_language;
    }

    public String getReportjob_note() {
        return reportjob_note;
    }

    public void setReportjob_note(String reportjob_note) {
        this.reportjob_note = reportjob_note;
    }

    public String getCompany_code() {
        return company_code;
    }

    public void setCompany_code(String company_code) {
        this.company_code = company_code;
    }

    public String getProject_code() {
        return project_code;
    }

    public void setProject_code(String project_code) {
        this.project_code = project_code;
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
