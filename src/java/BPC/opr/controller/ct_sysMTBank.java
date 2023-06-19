/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BPC.opr.controller;

import BPC.opr.model.cls_sysMTBank;
import BPC.opr.utility.cls_DBConnector;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author witco
 */
public class ct_sysMTBank {
    String Message;
    
    public String getMessage() {
        return Message;
    }
    
    private String getCommand(String fns_condition){

        StringBuilder Oj_sb = new StringBuilder();

        Oj_sb.append(" SELECT BANK_ID");
        Oj_sb.append(", BANK_CODE");  
        Oj_sb.append(", BANK_NAME_TH"); 
        Oj_sb.append(", BANK_NAME_EN");  
        
        Oj_sb.append(", ISNULL(MODIFIED_BY, CREATED_BY) AS MODIFIED_BY"); 
        Oj_sb.append(", ISNULL(MODIFIED_DATE, CREATED_DATE) AS MODIFIED_DATE"); 
               
        Oj_sb.append(" FROM SYS_MT_BANK");
        Oj_sb.append(" ").append(fns_condition);

        return Oj_sb.toString();
    }

    private List<cls_sysMTBank> getData(cls_DBConnector fnOj_conn, String fns_condition){
        
        //-- Declare variable
        List<cls_sysMTBank> Ojarr_model = new ArrayList<cls_sysMTBank>();
        ResultSet Oj_rs;        
        String ls_temp;
      
        try{

            ls_temp = " WHERE 1=1 " + fns_condition;
            ls_temp += " ORDER BY BANK_CODE";

            Oj_rs = (ResultSet) fnOj_conn.getQuery(this.getCommand(ls_temp));
            
            Timestamp timestamp;
            java.util.Date date;

            while(Oj_rs.next()){      
                
                timestamp = Oj_rs.getTimestamp("MODIFIED_DATE");
                date = timestamp;
                                                       
                Ojarr_model.add( new cls_sysMTBank(
                          Oj_rs.getString("BANK_ID")  
                        , Oj_rs.getString("BANK_CODE")  
                        , Oj_rs.getString("BANK_NAME_TH")                        
                        , Oj_rs.getString("BANK_NAME_EN")                  
                        , Oj_rs.getString("MODIFIED_BY")                        
                        , date                         
                       ));
            }
            Oj_rs.close();            
            
        }
        catch(Exception ex){
            Message = "ERROR(" + ct_sysMTBank.class.getName() +".getData)::" + ex.getMessage();
        }
        return Ojarr_model;
    }
    
    public List<cls_sysMTBank> getData(cls_DBConnector fnOj_conn){
        
        return getData(fnOj_conn, "");
    
    }
    
    public List<cls_sysMTBank> getDataByFillter(cls_DBConnector fnOj_conn, String fns_code){
        
        String ls_fillter = "";
                
        if(!fns_code.equals(""))
            ls_fillter += " AND BANK_CODE='"+fns_code+"'";
                
        return getData(fnOj_conn, ls_fillter);
    
    }
}
