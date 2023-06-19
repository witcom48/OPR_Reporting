/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BPC.opr.service;

import BPC.opr.controller.ct_sysMTBank;
import BPC.opr.controller.ct_sysMTReportjob;
import BPC.opr.model.cls_sysMTBank;
import BPC.opr.model.cls_sysMTReportjob;
import BPC.opr.utility.cls_DBConnector;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 *
 * @author witco
 */
public class cls_srvGenReport implements srvGenReport{
    
    String _message = "";

    @Override
    public String getMessage() {
        return this._message;
    }

    @Override
    public List<Map<String, Object>> getReportSYS(cls_sysMTReportjob Job) {
        cls_DBConnector Oj_conn = new cls_DBConnector();        
        List<Map<String, Object>> Ojarr_output = new ArrayList<>();
        
        SimpleDateFormat sdf_date = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.ENGLISH);
                       
        try {           
                      
            Oj_conn.openSQLServer();
            
            switch(Job.getReportjob_type()){
                
                case "SYS001":
                    //-- Bank master
                    ct_sysMTBank ct_bank = new ct_sysMTBank();
                    List<cls_sysMTBank> arr_bank = ct_bank.getData(Oj_conn);
                    
                    for(cls_sysMTBank model:arr_bank){
                        Map m = new HashMap();
                        m.put("bank_code", model.getBank_code());
                        m.put("bank_name_th", model.getBank_name_th());
                        m.put("bank_name_en", model.getBank_name_en());
                        m.put("modified_by", model.getMoified_by());
                        m.put("modified_date", sdf_date.format( model.getMoified_date()));
                        Ojarr_output.add(m);          
                    }                    
                    break;
                    
            }
            
            
            
            
        } catch (Exception ex) {
            this._message = "ERROR(" + cls_srvGenReport.class.getName() + ".getReportSYS)::" + ex.getMessage();
        } finally {
            Oj_conn.close();            
        }

        return Ojarr_output; 
    }

    @Override
    public cls_sysMTReportjob getReportDetail(String Ref) {
        
        cls_sysMTReportjob result = null;
        cls_DBConnector Oj_conn = new cls_DBConnector();  
        
        try {             
            Oj_conn.openSQLServer();
            ct_sysMTReportjob ct_reportjob = new ct_sysMTReportjob();
            result = ct_reportjob.getDataByRef(Oj_conn, Ref);
            
        } catch (Exception ex) {
            this._message = "ERROR(" + cls_srvGenReport.class.getName() + ".getReportDetail)::" + ex.getMessage();
        } finally {
            Oj_conn.close();            
        }
        
        return result;
    }
    
}
