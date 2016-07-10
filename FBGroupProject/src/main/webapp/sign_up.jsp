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
        <title>Sign Up</title>
        <script>
            function skipLogin (){
                window.location.href="inside.jsp";
            }
        </script>                    
    </head>
    <body>
        <h1>Provide your contact information</h1>
        <form action="write_database" method="POST">
            <input type="hidden" name="fb_id" value="${fbdata.id}">
            <strong>${fbdata.name}</strong><br />
            Phone Number*: <br />
            <input type="text" name="phone" maxlength="10"/> TO_DO - check these inputs for errors<br /> <br /> 
            E-mail*:<br />
            <input type="text" name="email" maxlength="255" value="${fbdata.email}"/> <br /><br /> 
            Address:<br />
            <textarea type="password" name="address" maxlength="255" /></textarea><br /><br /> 
            <input type="submit" value="Finish" />
        </form>
        <br /><button type=button" onclick="skipLogin()">Temp: Skip Sign Up</button>
    </body>
</html>
