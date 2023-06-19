package Exat.mtc.utility;



import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class cls_Utility {

    private String gs_message;

    public String getGs_message() {
        return gs_message;
    }

    public cls_Utility() {
    }

    public String parse(String fns_input) throws UnsupportedEncodingException, UnsupportedEncodingException, UnsupportedEncodingException, UnsupportedEncodingException {
        String ls_result = "";
        if (fns_input != null) {
            ls_result = new String(fns_input.getBytes("ISO8859-1"), "UTF-8");
        }
        return ls_result;
    }

    public String checkNull(String fns_input) {
        String s_result = null;
        try {
            //-- Check null
            s_result = (fns_input == null) ? "" : fns_input;
        } catch (Exception ex) {
            this.gs_message = ex.getMessage();
            s_result = "";
        }
        return s_result;
    }

    public int checkNullInt(String fns_input) {
        int i_result = 0;
        try {
            //-- Check null
            i_result = (fns_input == null) ? 0 : Integer.parseInt(fns_input);
        } catch (Exception ex) {
            this.gs_message = ex.getMessage();
            i_result = 0;
        }
        return i_result;
    }

    public String checkTagHTML(String fns_input) {
        String s_result;
        //String s_arr[] = {"script", "html", "body", "style", "table", "tr", "td", "input", "textarea"};
        //String s_compare = null;
        //String s_replace = "";
        try {
            if (fns_input == null || fns_input.equals("")) {
                return fns_input;
            }

            s_result = fns_input.replace("<", "&lt;");
            s_result = s_result.replace(">", "&gt;");
            s_result = s_result.replace("\"", "&quot");
            s_result = s_result.replace("'", "&#039");
            /*
            for(int i=0; i<s_arr.length; i++){
                //-- Upper case
                //-- Open tag
                s_compare = "<" + s_arr[i] + ">";
                s_result = s_result.replace(s_compare, s_replace);
                //-- Close tag
                s_compare = "<" + s_arr[i] + "/>";
                s_result = s_result.replace(s_compare, s_replace);

                //-- Upper case
                //-- Open tag
                s_compare = "<" + s_arr[i].toUpperCase() + ">";
                s_result = s_result.replace(s_compare, s_replace);
                //-- Close tag
                s_compare = "<" + s_arr[i].toUpperCase() + "/>";
                s_result = s_result.replace(s_compare, s_replace);
            }
             */
            return s_result;
        } catch (Exception ex) {
            this.gs_message = ex.getMessage();
            return fns_input;
        }
    }

    ///Checked return 1
    public int checkValueCheckbox(String fns_value) {
        int li_result = 0;
        try {
            if (fns_value == null || "".equals(fns_value)) {
                return li_result;
            }

            li_result = Integer.parseInt(fns_value);
        } catch (Exception ex) {
            li_result = 0;
        }
        return li_result;
    }

    public String setFormat2Digit(int li_value) {
        DecimalFormat Oj_format = new DecimalFormat("00");
        return Oj_format.format(li_value);
    }

    public String setFormat3Digit(int li_value) {
        DecimalFormat Oj_format = new DecimalFormat("000");
        return Oj_format.format(li_value);
    }

    public String setFormat4Digit(int li_value) {
        DecimalFormat Oj_format = new DecimalFormat("0000");
        return Oj_format.format(li_value);
    }

    public String setFormat5Digit(int li_value) {
        DecimalFormat Oj_format = new DecimalFormat("00000");
        return Oj_format.format(li_value);
    }

    public String setFormat6Digit(int li_value) {
        DecimalFormat Oj_format = new DecimalFormat("000000");
        return Oj_format.format(li_value);
    }
    
    public String setFormat10Digit(int li_value) {
        DecimalFormat Oj_format = new DecimalFormat("0000000000");
        return Oj_format.format(li_value);
    }

    public static String double2Str(double d) {
        return String.format("%.2f", d);
    }
    
    public String getDateEN(Date value){
        
        String ls_result = "";
        
        try {
            
            SimpleDateFormat gsdf_date = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
         
            ls_result = gsdf_date.format(value);
                        
        } catch (Exception ex) {
           
        }        
        return ls_result;
        
    }
    
    
    public String getDateTH(Date value){
        
        String ls_result = "";
        
        try {
            
            SimpleDateFormat gsdf_dd = new SimpleDateFormat("dd", Locale.ENGLISH);
            SimpleDateFormat gsdf_MM = new SimpleDateFormat("MM", Locale.ENGLISH);
            SimpleDateFormat gsdf_yyyy = new SimpleDateFormat("yyyy", Locale.ENGLISH);
                                
            String ls_year = Integer.toString(Integer.parseInt(gsdf_yyyy.format(value)) + 543);
            ls_result = gsdf_dd.format(value) + " " + this.getTHAIMonth(gsdf_MM.format(value)) + " " + ls_year;
                        
        } catch (Exception ex) {
           
        }        
        return ls_result;
        
    }
    
    private String getTHAIMonth(String month) {
        String ex = month;
        if (month.length() < 2) {
            ex = "0" + month;
        }
        String[] MonthThai = {"", "มกราคม", "กุมภาพันธ์", "มีนาคม", "เมษายน", "พฤษภาคม", "มิถุนายน", "กรกฎาคม", "สิงหาคม", "กันยายน", "ตุลาคม", "พฤศจิกายน", "ธันวาคม"};
        String ret = MonthThai[Integer.parseInt(ex)];
        return ret;
    }
    
    public String getDateTimeTH(Date value){
        
        String ls_result = "";
        
        try {
            
            SimpleDateFormat gsdf_ddMM = new SimpleDateFormat("dd/MM/", Locale.ENGLISH);
            SimpleDateFormat gsdf_yyyy = new SimpleDateFormat("yyyy", Locale.ENGLISH);
            SimpleDateFormat gsdf_HHmm = new SimpleDateFormat("HH:mm", Locale.ENGLISH);                            
            String ls_year = Integer.toString(Integer.parseInt(gsdf_yyyy.format(value)) + 543);
            ls_result = gsdf_ddMM.format(value) + ls_year + " " + gsdf_HHmm.format(value);
            
            
        } catch (Exception ex) {
           
        }        
        return ls_result;
        
    }
}
