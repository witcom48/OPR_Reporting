package BPC.opr.utility;


import java.util.HashMap;
import java.util.Map;

public class cls_Config {
    //-- Database Config Detail

    public static String gs_DBDriverSQLServer = "jdbc:sqlserver:";
    public static String gs_DBServerSQLServer = "localhost\\SQL2019";    
    public static String gs_DBDriverOracle =  "jdbc:oracle:thin:";
    public static String gs_DBUser = "sa";
    public static String gs_DBPassword = "2019";   
    public static String gs_DBServerOracle = "localhost:1521";
    public static String gs_DBName = "OPR";  
       
    public static String gs_SDFDMY = "dd/MM/yyyy";
    public static String gs_SDFINPUT = "ddMMyyyy";
    public static String gs_SDFSQL = "M/dd/yyyy";

    public static String gs_projectversion = "version 1.0";
    public static String gs_projectupdate = "01 April 2023";
    public static String gs_projectcontact = "0868824792";
    public static String gs_projectby = "Bhataraprogress.co.th";

    public static int gs_SessionTimeOut = 3600; //3600 =20นาที

}
