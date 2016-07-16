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
            String fb_id = request.getParameter("fb_id");

            String phone = request.getParameter("phone");
            String email = request.getParameter("email");
            String address = request.getParameter("address");
            
             request.getSession().setAttribute("myPhone", phone);
             request.getSession().setAttribute("myAddress", address);
                     
             
            
            try { 

                // Connect to the database 
                Connection myConnection = (Connection)request.getSession().getAttribute("connection");
                
                //Check if the record exists
                Statement myStatement = myConnection.createStatement();
                 ResultSet r = myStatement.executeQuery("Select fb_id FROM contacts WHERE fb_id = " + fb_id);
                 
                 if(r.next())  //found one
                 {
                    myStatement = myConnection.createStatement();
                    myStatement.executeUpdate("UPDATE contacts SET email = '" + email + "',phone_number='" + phone + "',address='" + address + "' WHERE fb_id = " + fb_id);                     
                 }
                 else //no record found in the DB
                 {
                    // Write the new user information to the database
                    myStatement = myConnection.createStatement();
                    myStatement.executeUpdate("INSERT INTO contacts VALUES (NULL,'" + fb_id + "','" + phone + "','" + email + "','" + address + "')");
                 }
                 
                myStatement.close();
//                    myConnection.close();   // This needs to be open for later to show the friends list,  TODO - is that ok?

            } catch (SQLException e){
                request.setAttribute("error", "SQL exception" + e);
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
