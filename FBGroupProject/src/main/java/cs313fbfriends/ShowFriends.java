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
                  
                  out.write("<h2>Friends</h2>" );
                  for (Friend friend : list) 
                  { 
                       String friendID = friend.getId();                      //Get the current Facebook Friend ID
                       String friendName = friend.getName();                  //Get the current Facebook Friend Name
                       
                       String friendGender = friend.getGender();
                       String friendBday = friend.getBirthday();
                      // URL profilePict = facebook.getPictureURL();           //Get the current Facebook Friend profile Pict
                       URL profilePict = facebook.getPictureURL(friendID, PictureSize.small);   

                    //@Blake we can get various image sizes depending on what you want.
                       
                       
                       //@jonny add code here to Get from database?  email, phone, address
                       
                       
                       //@blake Here is where we need the table added
                       out.write("<p> <img id=\"picts\" src=\"" + profilePict.getProtocol() + "://" 
                                 + profilePict.getHost() + profilePict.getFile() + "\" height=\"50\" width=\"50\">" 
                                 + friendName + " "  + " " + friendBday + " " 
                                 + friendGender + "<br/>"); 
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
