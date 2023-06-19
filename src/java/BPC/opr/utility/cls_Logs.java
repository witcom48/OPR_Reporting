package Exat.mtc.utility;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.log4j.Logger;

/**
 *
 * @author WC48
 */
public class cls_Logs {
    
    static{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            System.setProperty("current_date", dateFormat.format(new Date()));
    }
    
    public static void doRecordLogs(String Type, String Message, Object Page){
        
        final Logger LOGGER = Logger.getLogger(Page.getClass().getSimpleName());
        
        switch(Type){
            
            case "INFO":
                LOGGER.info(Message);
                break;
                
            case "ERROR":
                LOGGER.error(Message);
                break;
            
        }
        
        
        
    }
    
}
