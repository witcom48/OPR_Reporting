package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class preview_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write('\n');
      out.write('\n');

    
String RefCode = "";
if (request.getParameter("token") != null) {
        RefCode = request.getParameter("token");
}
    

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>OPR</title>\n");
      out.write("        <script type=\"text/javascript\" src=\"Js/jquery-1.9.1.js\"></script>\n");
      out.write("        <script type=\"text/javascript\" src=\"Js/jquery-ui.js\"></script>\n");
      out.write("        <script type=\"text/javascript\" src=\"Js/jquery-ui-1.10.3.custom.js\"></script>\n");
      out.write("    </head>\n");
      out.write("    \n");
      out.write("    <script type=\"text/javascript\">  \n");
      out.write("        \n");
      out.write("        function doGetReport() {  \n");
      out.write("            \n");
      out.write("            var refcode = \"");
      out.print(RefCode);
      out.write("\"\n");
      out.write("            \n");
      out.write("            if(refcode == \"\"){\n");
      out.write("                return;\n");
      out.write("            }\n");
      out.write("            \n");
      out.write("            var param = \"?Refcode=\" + refcode;    \n");
      out.write("            \n");
      out.write("            var win = window.open(\"genreport\" + param, '_blank');\n");
      out.write("            win.focus();\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        setTimeout(function () {\n");
      out.write("            doGetReport()\n");
      out.write("        }, 300);\n");
      out.write("        \n");
      out.write("    </script>    \n");
      out.write("    <body>\n");
      out.write("        <h1>HR-Operation</h1>\n");
      out.write("        <small>Genarate report</small>               \n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
