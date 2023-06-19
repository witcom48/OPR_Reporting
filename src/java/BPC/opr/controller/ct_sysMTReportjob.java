/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BPC.opr.controller;

import BPC.opr.model.cls_sysMTReportjob;
import BPC.opr.model.cls_sysTRReportjobwhose;
import BPC.opr.utility.cls_DBConnector;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author witco
 */
public class ct_sysMTReportjob {
    String Message;
    
    public String getMessage() {
        return Message;
    }
    
    private String getCommand(String fns_condition){

        StringBuilder Oj_sb = new StringBuilder();

        Oj_sb.append(" SELECT REPORTJOB_ID");
        Oj_sb.append(", REPORTJOB_REF");  
        Oj_sb.append(", ISNULL(REPORTJOB_TYPE, '') AS REPORTJOB_TYPE"); 
        Oj_sb.append(", ISNULL(REPORTJOB_STATUS, '') AS REPORTJOB_STATUS"); 
        Oj_sb.append(", ISNULL(REPORTJOB_FROMDATE, '') AS REPORTJOB_FROMDATE"); 
        Oj_sb.append(", ISNULL(REPORTJOB_TODATE, '') AS REPORTJOB_TODATE"); 
        Oj_sb.append(", ISNULL(REPORTJOB_PAYDATE, '') AS REPORTJOB_PAYDATE"); 
        Oj_sb.append(", ISNULL(REPORTJOB_LANGUAGE, 'TH') AS REPORTJOB_LANGUAGE"); 
        Oj_sb.append(", ISNULL(REPORTJOB_NOTE, '') AS REPORTJOB_NOTE"); 
        
        Oj_sb.append(", ISNULL(COMPANY_CODE, '') AS COMPANY_CODE"); 
        Oj_sb.append(", ISNULL(PROJECT_CODE, '') AS PROJECT_CODE"); 
              
        Oj_sb.append(", ISNULL(MODIFIED_BY, CREATED_BY) AS MODIFIED_BY"); 
        Oj_sb.append(", ISNULL(MODIFIED_DATE, CREATED_DATE) AS MODIFIED_DATE"); 
               
        Oj_sb.append(" FROM SYS_MT_REPORTJOB");
        Oj_sb.append(" ").append(fns_condition);

        return Oj_sb.toString();
    }

    private List<cls_sysMTReportjob> getData(cls_DBConnector fnOj_conn, String fns_condition){
        
        //-- Declare variable
        List<cls_sysMTReportjob> Ojarr_model = new ArrayList<cls_sysMTReportjob>();
        ResultSet Oj_rs;        
        String ls_temp;
      
        try{

            ls_temp = " WHERE 1=1 " + fns_condition;
            ls_temp += " ORDER BY REPORTJOB_ID";

            Oj_rs = (ResultSet) fnOj_conn.getQuery(this.getCommand(ls_temp));
            
            Timestamp timestamp;
            java.util.Date date;

            while(Oj_rs.next()){      
                
                timestamp = Oj_rs.getTimestamp("MODIFIED_DATE");
                date = timestamp;
                                                       
                Ojarr_model.add( new cls_sysMTReportjob(
                          Oj_rs.getInt("REPORTJOB_ID")  
                        , Oj_rs.getString("REPORTJOB_REF")  
                         , Oj_rs.getString("REPORTJOB_TYPE")  
                         , Oj_rs.getString("REPORTJOB_STATUS")  
                         , Oj_rs.getDate("REPORTJOB_FROMDATE")  
                         , Oj_rs.getDate("REPORTJOB_TODATE")  
                         , Oj_rs.getDate("REPORTJOB_PAYDATE")  
                        , Oj_rs.getString("REPORTJOB_LANGUAGE")                        
                        , Oj_rs.getString("REPORTJOB_NOTE")     
                        
                        , Oj_rs.getString("COMPANY_CODE")     
                        , Oj_rs.getString("PROJECT_CODE")     
                        
                        , Oj_rs.getString("MODIFIED_BY")                        
                        , date                         
                       ));
            }
            Oj_rs.close();            
            
        }
        catch(Exception ex){
            Message = "ERROR(" + ct_sysMTReportjob.class.getName() +".getData)::" + ex.getMessage();
        }
        return Ojarr_model;
    }
    
    public List<cls_sysMTReportjob> getData(cls_DBConnector fnOj_conn){
        
        return getData(fnOj_conn, "");
    
    }
    
    public cls_sysMTReportjob getDataByRef(cls_DBConnector fnOj_conn, String fns_ref){
        
        String ls_fillter = " AND REPORTJOB_REF='"+fns_ref+"'";
        
        List<cls_sysMTReportjob> Ojarr_model = this.getData(fnOj_conn, ls_fillter);
        
        if(Ojarr_model.size() > 0)
            return Ojarr_model.get(0);
        else  
            return null;
    
    }
    
    public List<cls_sysTRReportjobwhose> getDataWhose(cls_DBConnector fnOj_conn, String fns_id){
        
        //-- Declare variable
        List<cls_sysTRReportjobwhose> Ojarr_model = new ArrayList<cls_sysTRReportjobwhose>();
        ResultSet Oj_rs;        
        String ls_temp;
      
        try{
            ls_temp = "SELECT REPORTJOB_ID, WORKER_CODE";
            ls_temp += " WHERE REPORTJOB_ID ='" + fns_id + "' ";
            ls_temp += " ORDER BY WORKER_CODE";

            Oj_rs = (ResultSet) fnOj_conn.getQuery(this.getCommand(ls_temp));            
            
            while(Oj_rs.next()){      
                         
                Ojarr_model.add( new cls_sysTRReportjobwhose(
                          Oj_rs.getInt("REPORTJOB_ID")  
                        , Oj_rs.getString("WORKER_CODE")                         
                       ));
            }
            Oj_rs.close();            
            
        }
        catch(Exception ex){
            Message = "ERROR(" + ct_sysMTReportjob.class.getName() +".getDataWhose)::" + ex.getMessage();
        }
        return Ojarr_model;
    }
}
