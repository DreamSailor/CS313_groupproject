/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs313fbfriends;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author user
 */
@WebServlet(name = "write_database", urlPatterns = {"/write_database"})
public class write_database extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {           

            // See the doPost function
            
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
                           
            // TODO - Have this recieve the facebook id from a previous page 
            String fb_id = "117108935383689";

            String phone = request.getParameter("phone");
            String email = request.getParameter("email");
            String address = request.getParameter("address");
            
            try {
                // Registers the driver?   See   http://www.tutorialspoint.com/jdbc/jdbc-sample-code.htm
                Class.forName("com.mysql.jdbc.Driver");

                // TODO - Make all connections to the database happen in one place
                try { 
                    // Set the connection parameters based on whether you are in the home environment or openshift
                    String user = null;
                    String pass = null;
                    String url = null;
                     
                    if(System.getenv("OPENSHIFT_MYSQL_DB_USERNAME") != null) {
                        user = System.getenv("OPENSHIFT_MYSQL_DB_USERNAME");
                        pass = System.getenv("OPENSHIFT_MYSQL_DB_PASSWORD");
                        url = "jdbc:mysql://" + System.getenv("OPENSHIFT_MYSQL_DB_HOST") + ":" + System.getenv("OPENSHIFT_MYSQL_DB_PORT") + "/contacts_list";
                    } else {
                        // TODO - save these in a file so that they aren't so easy to see?
                        user = "myUser";
                        pass = "myPass";
                        url = "jdbc:mysql://localhost/contacts_list";
                    }
                                       
                    // Connect to the database 
                    Connection myConnection = DriverManager.getConnection(url,user,pass);

                    // Write the new user information to the database
                    Statement myStatement = myConnection.createStatement();
                    myStatement.executeUpdate("INSERT INTO contacts VALUES (NULL,'" + fb_id + "','" + phone + "','" + email + "','" + address + "')");
                    
                    myStatement.close();
                    myConnection.close();

                } catch (SQLException e){
                    request.setAttribute("error", "SQL exception" + e);
                    request.getRequestDispatcher("/failPage.jsp").forward(request, response);
                }
            } catch (ClassNotFoundException e) {
                request.setAttribute("error", "failed to register driver?" + e);
                request.getRequestDispatcher("/failPage.jsp").forward(request, response);
            }                
                        
            response.sendRedirect("inside.jsp");
                      
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
