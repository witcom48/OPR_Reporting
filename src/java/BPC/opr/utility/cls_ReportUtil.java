/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exat.mtc.utility;

import java.io.File;
import java.io.OutputStream;
import javax.servlet.ServletContext;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JROriginExporterFilter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.JRTextExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.oasis.JROdtExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleCsvExporterConfiguration;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.export.SimpleWriterExporterOutput;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import net.sf.jasperreports.export.XlsReportConfiguration;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author bundit_put
 */
public class cls_ReportUtil {

    public JasperReport getCompiledReport(ServletContext servletContext, String path, String fileName) throws JRException, Exception {
        if (path.endsWith("/")) {
            path = path.substring(0, path.length() - 1);
        }
        if (path.startsWith("/")) {
            path = path.substring(0, 1);
        }
        String servletPath = servletContext.getRealPath("/").replace("\\", "/");
        String fullPath = servletPath + path + "/" + fileName;

        if (!fullPath.endsWith(".jrxml") && !fullPath.endsWith(".jasper")) {
            fullPath = fullPath + ".jrxml";
        }

        File jasperFile = new File(fullPath.replace("jrxml", "jasper"));
        File jrxmlFile = new File(fullPath.replace("jasper", "jrxml"));
        JasperReport jasperReport;
        if (jasperFile.exists()) {
            jasperReport = (JasperReport) JRLoader.loadObject(jasperFile);
        } else if (jrxmlFile.exists()) {
            JasperCompileManager.compileReportToFile(jrxmlFile.getAbsolutePath());
            jasperReport = (JasperReport) JRLoader.loadObject(jasperFile);
        } else {
            throw new Exception("ไม่พบไฟล์ " + fileName + " ตามที่อยู่นี้ : " + fullPath);
        }
        return jasperReport;
    }

    public void exportReport(String outputType, OutputStream outputStream, JasperPrint jasperPrint, JROriginExporterFilter exportFilter) throws JRException {
        exportReportCore(outputType, outputStream, jasperPrint, exportFilter);
    }

    public void exportReport(String outputType, OutputStream outputStream, JasperPrint jasperPrint) throws JRException {
        exportReportCore(outputType, outputStream, jasperPrint, null);
    }

    public void exportReportCore(String outputType, OutputStream outputStream, JasperPrint jasperPrint, JROriginExporterFilter exportFilter) throws JRException {        
        outputType = outputType.trim().replace(" ", "").toUpperCase();
        switch (outputType) {
            case "PDF":
                JRPdfExporter exporter = new JRPdfExporter();
                exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
                exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
                SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
                exporter.setConfiguration(configuration);
                exporter.exportReport();
                break;

            case "XLSX":
                JRXlsxExporter jrexcelx = new JRXlsxExporter();
                jrexcelx.setExporterInput(new SimpleExporterInput(jasperPrint));
                jrexcelx.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
                SimpleXlsxReportConfiguration jrexcelxcon = new SimpleXlsxReportConfiguration();
                //ถ้าคอลัมน์หรือแถวไหนว่าง ให้มัน Remove ออกเลยจะได้ไม่เป็นขยะ
                jrexcelxcon.setRemoveEmptySpaceBetweenRows(true);
                jrexcelxcon.setRemoveEmptySpaceBetweenColumns(true);                
                jrexcelxcon.setOnePagePerSheet(false);
                jrexcelxcon.setDetectCellType(true);
                jrexcelxcon.setWhitePageBackground(false);

                if (exportFilter != null) {
                    jrexcelxcon.setExporterFilter(exportFilter);
                }

                jrexcelx.setConfiguration(jrexcelxcon);
                jrexcelx.exportReport();
                break;

            case "XLS":
                JRXlsExporter jrexcel = new JRXlsExporter();                
                jrexcel.setExporterInput(new SimpleExporterInput(jasperPrint));
                jrexcel.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
                SimpleXlsReportConfiguration xlsReportConfiguration = new SimpleXlsReportConfiguration();
                xlsReportConfiguration.setOnePagePerSheet(false);
                xlsReportConfiguration.setRemoveEmptySpaceBetweenRows(true);
                xlsReportConfiguration.setDetectCellType(true);
                xlsReportConfiguration.setWhitePageBackground(false);
                jrexcel.setConfiguration(xlsReportConfiguration);
                jrexcel.exportReport();
                break;

            case "CSV":
                JRCsvExporter csvExp = new JRCsvExporter();
                SimpleCsvExporterConfiguration csvcfg = new SimpleCsvExporterConfiguration();
                csvExp.setConfiguration(csvcfg);
                csvExp.setExporterInput(new SimpleExporterInput(jasperPrint));
                csvExp.setExporterOutput(new SimpleWriterExporterOutput(outputStream));
                csvExp.exportReport();
                break;

            case "DOCX":
                JRDocxExporter docxExp = new JRDocxExporter();
                docxExp.setExporterInput(new SimpleExporterInput(jasperPrint));
                docxExp.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
                docxExp.exportReport();
                break;

            case "TXT":
                JRTextExporter textExp = new JRTextExporter();
                textExp.setExporterInput(new SimpleExporterInput(jasperPrint));
                textExp.setExporterOutput(new SimpleWriterExporterOutput(outputStream));
                textExp.exportReport();
                break;

            case "HTML":
                HtmlExporter htmlExp = new HtmlExporter();
                htmlExp.setExporterInput(new SimpleExporterInput(jasperPrint));
                htmlExp.setExporterOutput(new SimpleHtmlExporterOutput(outputStream));
                htmlExp.exportReport();
                break;

            case "ODT":
                JROdtExporter odtExp = new JROdtExporter();
                odtExp.setExporterInput(new SimpleExporterInput(jasperPrint));
                odtExp.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
                odtExp.exportReport();
                break;

            case "RTF":
                JRRtfExporter rtfExp = new JRRtfExporter();
                rtfExp.setExporterInput(new SimpleExporterInput(jasperPrint));
                rtfExp.setExporterOutput(new SimpleWriterExporterOutput(outputStream));
                rtfExp.exportReport();
                break;

            case "VIEWER":
                JasperViewer.viewReport(jasperPrint);
                break;
        }

    }
}
