package core.programs; 
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RetriveProduct extends HttpServlet {
   // SQL query
   private static final String query = 
         "select * from product where product_id =?";

   @Override
   public void doGet(HttpServletRequest req, HttpServletResponse res) 
         throws ServletException, IOException {
	   PrintWriter pw = null;
      int sno = 0;
      Connection con = null;
      PreparedStatement ps = null;
      ResultSet rs = null;
      boolean flag = false;

      // set content type
      res.setContentType("text/html");
      // get Writer
      pw = res.getWriter();

      // get form data
      sno = Integer.parseInt(req.getParameter("product_id"));

      try {
         // register JDBC driver
         Class.forName("com.mysql.jdbc.Driver");
         // create JDBC connection
         con = DriverManager.getConnection(
        		 "jdbc:mysql://localhost:3306/db_world","root","root");
         // compile SQL query and store it in
         // PreparedStatement object
         if (con != null)
            ps = con.prepareStatement(query);
         // set input value to query parameter
         if (ps != null)
            ps.setInt(1, sno);
         // execute the query
         if (ps != null)
            rs = ps.executeQuery();

         // process the result
         if (rs != null) {
            while (rs.next()) {
               // display result
               flag = true;
               pw.println("<h1>product Details, </h1>" 
                    + "Product Name: " + rs.getString("pname") + "<br>" 
                   + "type: " + rs.getString("ptype") + "<br>" 
                    + "price: " + rs.getInt("pprice") + "<br>");
            }
         }

         // Student not found
         if (!flag) {
            pw.println("<h1>Student Not Found.</h1>");
         }

      } catch (SQLException se) {
         se.printStackTrace();
         pw.println("Error Occured");
      } catch (Exception e) {
         e.printStackTrace();
         pw.println("Unknown Exception Occured");
      } finally {
         // close JDBC connection
         try {
            if (rs != null)
               rs.close();
         } catch (SQLException se) {
            se.printStackTrace();
         }
         try {
            if (ps != null)
               ps.close();
         } catch (SQLException se) {
            se.printStackTrace();
         }
         try {
            if (con != null)
               con.close();
         } catch (SQLException se) {
            se.printStackTrace();
         }

         // Link to home
         pw.println("<h3><a href='product_id.html'>Home</a></h3>");
         // close stream
         pw.close();
      }
   }

   @Override
   public void doPost(HttpServletRequest req, HttpServletResponse res) 
         throws ServletException, IOException {
      doGet(req, res);
   }
}