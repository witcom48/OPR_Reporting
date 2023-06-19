package BPC.opr.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class cls_DBConnector {
    private Connection gOb_con;
    private Statement gOb_stmt;
    private String gs_connectionUrl;
    private static String gs_errorMessage = null;
    
    public cls_DBConnector() {
         //this._connectionUrl = "jdbc:sqlserver://localhost:1433;";
         //this._connectionUrl += "databaseName=DL;user=sa;password=1234;";
    }
    
    public void setConnectionUrl(String _connectionUrl) {
        this.gs_connectionUrl = _connectionUrl;
    }

    public Connection getConnection() {
        return gOb_con;
    }

    public String getConnectionUrl() {
        return gs_connectionUrl;
    }

    public String getErrorMessage() {
        return gs_errorMessage;
    }

    public void openSQLServer(){
        try{
            this.gs_connectionUrl = cls_Config.gs_DBDriverSQLServer + "//" + cls_Config.gs_DBServerSQLServer + ";databaseName="+cls_Config.gs_DBName;
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
           gOb_con = DriverManager.getConnection(this.gs_connectionUrl, cls_Config.gs_DBUser, cls_Config.gs_DBPassword);
                                   
            gOb_stmt = gOb_con.createStatement();
        }
        catch(Exception ex){
            gOb_con = null;
            gs_errorMessage = "(cls_DBConnector.openSQLServer):" + ex.getMessage();
        }
    }

    public void openOracle(){
        try{
            this.gs_connectionUrl = cls_Config.gs_DBDriverOracle + "@" + cls_Config.gs_DBServerOracle + ":" + cls_Config.gs_DBName;
            DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
            gOb_con = DriverManager.getConnection (this.gs_connectionUrl, cls_Config.gs_DBUser, cls_Config.gs_DBPassword);
            gOb_stmt = gOb_con.createStatement();
            this.execute("alter session set nls_date_language='THAI'");
        }
        catch(Exception ex){
            gOb_con = null;
            gs_errorMessage = "(cls_DBConnector.openOracle):" + ex.getMessage();
        }
    }  
    
    
    public void openOracle(String server, String db, String user, String pass){
        try{
            this.gs_connectionUrl = cls_Config.gs_DBDriverOracle + "@" + server + ":" + db;
            DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
            gOb_con = DriverManager.getConnection (this.gs_connectionUrl, user, pass);
            gOb_stmt = gOb_con.createStatement();
            this.execute("alter session set nls_date_language='THAI'");
        }
        catch(Exception ex){
            gOb_con = null;
            gs_errorMessage = "(cls_DBConnector.openOracle):" + ex.getMessage();
        }
    } 

    public void close(){
        try{
            this.gOb_con.close();
            this.gOb_stmt.close();
        }
        catch(Exception ex){
            this.gOb_con = null;
            gs_errorMessage = ex.getMessage();
        }
    }

    public boolean execute(String fns_sql){
        try{
            this.gOb_stmt.execute(fns_sql);
            gs_errorMessage = "execute " + fns_sql;
            return true;
        }
        catch(Exception e){
            gs_errorMessage = "(cls_DBConnector.execute):" + e.getMessage();
        }        
        return false;
    }

    public boolean executeTrans(ArrayList fnarr_listSQL){
        try{
            this.gOb_con.setAutoCommit(false);

            for(int i=0;i<fnarr_listSQL.size();i++){
                this.gOb_stmt.addBatch(fnarr_listSQL.get(i).toString());
            }
            gs_errorMessage = "";
            gOb_stmt.executeBatch();
            this.gOb_con.commit();
            return true;
        }
        catch(Exception ex){
            gs_errorMessage = "(cls_DBConnector.executeTrans):" + ex.getMessage();
            this.rollbackTran();
        }
        return false;
    }

    public boolean openTran(){
        try{
            this.gOb_con.setAutoCommit(false);
            return true;
        }
        catch(Exception ex){
            gs_errorMessage = "(cls_DBConnector.openTran):" + ex.getMessage();
            return false;
        }
    }

    public boolean closeTran(){
        try{
            this.gOb_con.setAutoCommit(true);
            return true;
        }
        catch(Exception ex){
            gs_errorMessage = "(cls_DBConnector.closeTran):" + ex.getMessage();
            return false;
        }
    }
    
    public boolean commitTran(){
        try{
            this.gOb_con.commit();
            return true;
        }
        catch(Exception ex){
            gs_errorMessage = "(cls_DBConnector.commitTran):" + ex.getMessage();
            this.rollbackTran();
            return false;
        }
    }
    
    public boolean rollbackTran(){
        try{
            this.gOb_con.rollback();
            return true;
        }
        catch(Exception ex){
            gs_errorMessage = "(cls_DBConnector.rollbackTran):" + ex.getMessage();
            return false;
        }
    }

    public boolean executeTrans(String fns_sql){
        try{
            this.gOb_stmt.addBatch(fns_sql);
            gs_errorMessage = "";
            gOb_stmt.executeBatch();
            this.gOb_con.commit();
            return true;
        }
        catch(Exception ex){
            gs_errorMessage = "(cls_DBConnector.executeTrans):" + ex.getMessage();
            this.rollbackTran();
        }        
        return false;
    }

    public ResultSet getQuery(String fns_sql){
        ResultSet Oj_rs;
        try{
            Oj_rs = gOb_stmt.executeQuery(fns_sql);
            return Oj_rs;
        }
        catch(Exception ex){
            gs_errorMessage = "(cls_DBConnector.getQuery):" + ex.getMessage();
        }
        return null;
    }

}//end class
