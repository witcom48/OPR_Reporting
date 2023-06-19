<%-- 
    Document   : preview
    Created on : Apr 27, 2023, 10:14:16 AM
    Author     : witco
--%>

<%
    
String RefCode = "";
if (request.getParameter("token") != null) {
        RefCode = request.getParameter("token");
}
    
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>OPR</title>
        <script type="text/javascript" src="Js/jquery-1.9.1.js"></script>
        <script type="text/javascript" src="Js/jquery-ui.js"></script>
        <script type="text/javascript" src="Js/jquery-ui-1.10.3.custom.js"></script>
    </head>
    
    <script type="text/javascript">  
        
        function doGetReport() {  
            
            var refcode = "<%=RefCode%>"
            
            if(refcode == ""){
                return;
            }
            
            var param = "?Refcode=" + refcode;    
            
            var win = window.open("genreport" + param, '_blank');
            win.focus();
        }
        
        setTimeout(function () {
            doGetReport()
        }, 300);
        
    </script>    
    <body>
        <h1>HR-Operation</h1>
        <small>Genarate report</small>               
    </body>
</html>
