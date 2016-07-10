/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs313fbfriends;

import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.Reading;
import facebook4j.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author user
 */
@WebServlet(name = "opener", urlPatterns = {"/opener"})
public class opener extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) 
        {
            String user_fb_id=null;
                                                         
            // TODO - @Jeffry, recieve the user's facebook id here 
            Facebook facebook = (Facebook)request.getSession().getAttribute("facebook");
            try
            {
                User me = facebook.getMe();
                me = facebook.getMe(new Reading().fields("email,id,name"));
                user_fb_id = me.getId();
                
                  HttpSession session = request.getSession();
                  session.setAttribute("fbdata", me);
             

             } catch (FacebookException e) { 
                  e.printStackTrace(); 
             } 
           
            Boolean has_signed_up = false;

            try {
                // Registers the driver?   See   http://www.tutorialspoint.com/jdbc/jdbc-sample-code.htm
                Class.forName("com.mysql.jdbc.Driver");

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
                        // TODO - put these in a separate file 
                        user = "myUser";
                        pass = "myPass";
                        url = "jdbc:mysql://localhost:8889/contacts_list";
                    }
                                       
                    // Connect to the database 
                    Connection myConnection = DriverManager.getConnection(url,user,pass);

                    // Use MySQL query
                    Statement myStatement = myConnection.createStatement();

                    // Get all of the facebook IDs stored in the database
                    ResultSet r = myStatement.executeQuery("Select fb_id FROM contacts");

                    // Loop through all of the IDs or until a match is found 
                    while(r.next() && has_signed_up == false) {
                        String db_fb_id = r.getString("fb_id");
 
                        if(db_fb_id.equals(user_fb_id)){
                            has_signed_up = true;
                        }
                    }
                    
                    myStatement.close();
                    myConnection.close();
                    r.close();

                } catch (SQLException e){
                    out.println("Error:" + e);
                    request.setAttribute("error", "SQL exception" + e);
                    request.getRequestDispatcher("/failPage.jsp").forward(request, response);
                }
                
            } catch (ClassNotFoundException e) {
                out.println("Error:" + e);
                request.setAttribute("error", "failed to register driver?" + e);
                request.getRequestDispatcher("/failPage.jsp").forward(request, response);
            }                
                
            if (has_signed_up){
                response.sendRedirect("inside.jsp");
            } else {
                response.sendRedirect("sign_up.jsp");
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