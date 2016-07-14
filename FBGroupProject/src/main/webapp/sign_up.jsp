<%-- 
    Document   : sign_up
    Created on : Jul 8, 2016, 10:17:03 PM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Sign Up</title>
        <link href="main.css" type="text/css" rel="stylesheet" media="screen">
        <script>
            function skipLogin() {
                window.location.href = "inside.jsp";
            }
        </script>                    
    </head>x1
    <body>
        <% 
            Object phone ="",email ="", address = "";
            String update = request.getParameter("update");
            if(update != null)
            {
                    phone = session.getAttribute("myPhone");
                    address = session.getAttribute("myAddress");

            }

        
        %>
        <header>
            <img src="${userpict.protocol}://${userpict.host}${userpict.file}" alt="Profile Image"/>
            <div>
                <h1>${fbdata.name}</h1>
                <h3>${fbdata.email}</h3>
                <p>${fbdata.location.name}</p>
            </div>
        </header>
        <main>
                <h1>Provide your contact information</h1>
            <div id="indent">
                <form action="write_database" method="POST">
                    <input type="hidden" name="fb_id" value="${fbdata.id}">
                    <strong>${fbdata.name}</strong><br />
                    Phone Number*: <br />
                    <input type="text" name="phone" maxlength="10" value="<%=phone%>"/> TO_DO - check these inputs for errors<br /> <br /> 
                    E-mail*:<br />
                    <input type="text" name="email" maxlength="255" value="${fbdata.email}"/> <br /><br /> 
                    Address:<br />
                    <textarea type="password" name="address" maxlength="255" /><%=address%></textarea><br /><br /> 
                    <input type="submit" value="Finish" />
                </form>
                <p><br /><button type=button" onclick="skipLogin()">Temp: Skip Sign Up</button></p>
            </div>
        </main>
        <footer>
            <p><strong>Disclaimer:</strong> This web site is presented as course work for CS-313 at BYU-Idaho. 
            <p><strong>Review Committee Lead:</strong> Brother Lyon</p>
            <p><strong>App Developers:</strong> Blake Taylor, Jeffry Simpson, Jonny Job & Michael Cavey.</p>        
        </footer>
    </body>
</html>
