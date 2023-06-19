/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exat.mtc.utility;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 *
 * @author bundit_put
 */
public class cls_HTTPHeaderUtil {

    public static String getMimeTextFromFileType(String fileType) throws Exception {
        switch (fileType.trim().toUpperCase()) {
            case "PDF":
                return "application/pdf";
            case "XLS":
                return "application/vnd.ms-excel";
            case "XLSX":
                return "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
            case "DOC":
                return "application/msword";
            case "DOCX":
                return "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
            case "CSV":
                return "text/csv";
            case "TXT":
                return "text/plain";
            case "EXE":
                return "application/octet-stream";
            default:
                throw new Exception("ไม่พบประเภทไฟล์ : " + fileType);
        }
    }

    public static String getContentDisposition(String fileName, String fileType, boolean isDownload) throws UnsupportedEncodingException {
        return (isDownload ? "attachment" : "inline") + "; filename=" + URLEncoder.encode(fileName, "UTF-8").replace("+", " ") + "." + fileType.trim().toLowerCase();
    }
}
