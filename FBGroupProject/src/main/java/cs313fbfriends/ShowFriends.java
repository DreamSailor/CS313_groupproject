/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs313fbfriends;

import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.Friend;
import facebook4j.PictureSize;
import facebook4j.Reading;
import facebook4j.ResponseList;
import facebook4j.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
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
 * @author jsimpson
 */
@WebServlet(name = "ShowFriends", urlPatterns =
    {
        "/ShowFriends"
})
public class ShowFriends extends HttpServlet
{

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
            throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter())
        {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ShowFriends</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ShowFriends at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
            throws ServletException, IOException
        { 
             Facebook facebook = (Facebook)request.getSession().getAttribute("facebook");

             PrintWriter out = response.getWriter();

             try { 
                 
                 //Get list of frends from Facebook
                  ResponseList<Friend> list = facebook.getFriends(new Reading().fields("id,birthday,bio,name,first_name,last_name,middle_name,gender"));
                  
                try { 
                     
                    // Access the connection to the database as established in opener.java
                    Connection myConnection = (Connection)request.getSession().getAttribute("connection");                                    
                  
                    out.write("<h2>Friends' Information</h2>" );
//                    out.write("<table style=\'width:100%\'>");
                    out.write("<table  style=\"border:none; width:100%\">");
/*                    out.write("<tr>");
                    out.write("<th>Profile Picture*</th>");
                    out.write("<th>Name</th>");
                    out.write("<th>Phone</th>");
                    out.write("<th>E-Mail*</th>");
                    out.write("<th>Birthday*</th>");
                    out.write("<th>Address</th>");
                    out.write("</tr>");
*/                    // For each friend...
                    for (Friend friend : list) 
                    { 
                       String friendID = friend.getId();                      //Get the current Facebook Friend ID
                       String friendName = friend.getName();                  //Get the current Facebook Friend Name
                       
                       String friendGender = friend.getGender();
                       String friendBday = friend.getBirthday();
                      // URL profilePict = facebook.getPictureURL();           //Get the current Facebook Friend profile Pict
                       URL profilePict = facebook.getPictureURL(friendID, PictureSize.small);   

                    //@Blake we can get various image sizes depending on what you want.
                       
                        // Use MySQL query to access database info
                        Statement myStatement = myConnection.createStatement();
                        ResultSet r = myStatement.executeQuery("Select address, phone_number, email, fb_id FROM contacts WHERE fb_id = " + friendID);
                                        
                        String db_fb_id = "none";
                        String address = "none";
                        String phone_number = "none";
                        String email = "none";
                        
                        // There should only be one result, loop once
                        while(r.next()) {  
                            
                            // Store the database values
                            db_fb_id = r.getString("fb_id"); 
                            phone_number = r.getString("phone_number");                            
                            email = r.getString("email");                            
                            address = r.getString("address");
                            
                            // Have something to show for an empty address
                            if (address.equals("")){
                                address = "...";
                            }
                            
                            // Display the phone number in format (xxx) xxx-xxxx
                            if (phone_number.length() == 10) {
                                phone_number = "(" + phone_number.substring(0,3) + ") " + phone_number.substring(3,6) + "-" + phone_number.substring(6,10);
                            } else if (phone_number.length() == 7) {
                                phone_number = phone_number.substring(0,3) + "-" + phone_number.substring(3,7);
                            }

                        }                                                 
                 
                        myStatement.close();                                                                                            
                       
                       //@blake Here is where we need the table added
/*                       out.write("<tr>");
                       out.write("<td> <img id=\"picts\" src=\"" + profilePict.getProtocol() + "://" 
                                + profilePict.getHost() + profilePict.getFile() + "\" height=\"50\" width=\"50\"></td>");
                        out.write("<td>" + friendName + "</td>");
                        out.write("<td>" + phone_number + "</td>");
                        out.write("<td>" + email + "</td>");  
                        out.write("<td>" + friendBday + "</td>");                                                        
                        out.write("<td>" + address + "</td>");
                        out.write("</tr>");
//                                + friendGender + "<br/>"); 
*/
                       out.write("<tr>");
                       out.write("<td rowspan=\"3\" style=\"border:0; width:60px\"> <img id=\"picts\" src=\"" + profilePict.getProtocol() + "://" 
                                + profilePict.getHost() + profilePict.getFile() + "\" height=\"50\" width=\"50\"></td>");
                        out.write("<td style=\"font-size:20px\">" + friendName + "</td>");
                        out.write("<td> Birthday: " + (friendBday != null ? friendBday : " " ) + "</td>");                // So that it doesn't say "null"                        
                        out.write("</tr>");
                        out.write("<tr>");                           
                        out.write("<td> Email: " + email + "</td>");
                        out.write("<td> Phone: " + phone_number + "</td>");                        
                        out.write("</tr>");                       
                        out.write("<tr>");                                                
                        out.write("<td colspan = \"2\"> Address: " + address + "</td>");
                        out.write("</tr>");
                        out.write("<tr height=\"15px\"></tr>");

                    }
                    out.write("</table>");
//                    out.write("Field with an * indicate that information came from Facebook other fields come from the application database, assuming your friend has entered it. <p>");
                    

               // myConnection.close();   // Note: Once this is closed, it will not let you access it again until it is created again, if added features require us to access the database again after this point, we may no longer be able to store the connection as a session variable
                    
                } catch (SQLException e){
                    out.println("Error: " + e);
                    request.setAttribute("error", "SQL exception: " + e);
                    request.getRequestDispatcher("/failPage.jsp").forward(request, response);
                }
             } catch (IllegalStateException e) { 
                  e.printStackTrace(); 
             } catch (FacebookException e) { 
                  e.printStackTrace(); 
             } 
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
            throws ServletException, IOException
    {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo()
    {
        return "Short description";
    }// </editor-fold>

}