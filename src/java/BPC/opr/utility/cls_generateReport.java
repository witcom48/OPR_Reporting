/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BPC.opr.utility;

import BPC.opr.controller.ct_sysMTReportjob;
import BPC.opr.model.cls_sysMTReportjob;
import BPC.opr.service.cls_srvGenReport;
import BPC.opr.service.srvGenReport;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JRPropertiesUtil;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author witco
 */
@WebServlet(name = "cls_generateReport", urlPatterns = {"/genreport"})
public class cls_generateReport extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = null;
        try {
                             
            //-- Get parameter
            String report_ref = request.getParameter("Refcode");                       
            
            //-- Get data receipt
            srvGenReport srv_report = new cls_srvGenReport();
            cls_sysMTReportjob md_reportjob = srv_report.getReportDetail(report_ref);
            
            if(md_reportjob == null){
                
                response.setContentType("text/html;charset=UTF-8");
                try {
                    out = response.getWriter();
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>OPR</title>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h1>Report not found</h1>");
                    out.println("</body>");
                    out.println("</html>");
                } catch (IOException ex) {
                    //Logger.getLogger(DoGenerateReport.class.getName()).log(Level.SEVERE, null, ex);
                }
            }            
            
            HashMap parameters = new HashMap();       
            String outputFileName = "Receipt";
                
            //-- Parameter
            switch(md_reportjob.getReportjob_type()){
                
                case "SYS001":
                    parameters.put("P_COMPLOGO_PATH", request.getServletContext().getRealPath("/").replace("\\", "/") + "/jrxml/img/logo.jpg");                   
                    parameters.put("P_LANGUAGE", md_reportjob.getReportjob_language());
                    
                    parameters.put("P_REPORTCODE", "SYS-001");
                    parameters.put("P_COMPNAME", "Bhatarapro.co.th");
                    parameters.put("P_REPORTNAME", "Bank report");
                    parameters.put("P_ADDRESS1", "");
                    parameters.put("P_ADDRESS2", "");
                    
                    outputFileName = "SYS-001";
                    break;                
                
            }
            
            //-- Get data
            List<Map<String, Object>> dataList = srv_report.getReportSYS(md_reportjob);
                                 
            //-- Genarate report
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(dataList);
            Exat.mtc.utility.cls_ReportUtil reportUtil = new Exat.mtc.utility.cls_ReportUtil();
            JasperReport jasperReport = reportUtil.getCompiledReport(request.getServletContext(), "Jrxml", outputFileName);
            DefaultJasperReportsContext context = DefaultJasperReportsContext.getInstance();
            JRPropertiesUtil.getInstance(context).setProperty("net.sf.jasperreports.xpath.executer.factory", "net.sf.jasperreports.engine.util.xml.JaxenXPathExecuterFactory");        
            
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
            jasperPrint.setName(outputFileName);
            response.setContentType(Exat.mtc.utility.cls_HTTPHeaderUtil.getMimeTextFromFileType("PDF"));
            response.setHeader("Content-disposition", Exat.mtc.utility.cls_HTTPHeaderUtil.getContentDisposition(outputFileName, "PDF", false));
            reportUtil.exportReport("PDF", response.getOutputStream(), jasperPrint);
            
        }
        catch (Exception e) {
            response.setContentType("text/html;charset=UTF-8");
            try {
                out = response.getWriter();
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>OPR</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>" + e.getMessage() + "</h1>");
                out.println("</body>");
                out.println("</html>");
            } catch (IOException ex) {
                //Logger.getLogger(DoGenerateReport.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
