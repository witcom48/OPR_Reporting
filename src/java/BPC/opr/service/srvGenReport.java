/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BPC.opr.service;

import BPC.opr.model.cls_sysMTReportjob;
import java.util.List;
import java.util.Map;

/**
 *
 * @author witco
 */
public interface srvGenReport {
    public String getMessage();
    public List<Map<String, Object>> getReportSYS(cls_sysMTReportjob Job);
    
    public cls_sysMTReportjob getReportDetail(String Ref);
}
