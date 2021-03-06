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
            function skipDBUpdate() {
                window.location.href = "inside.jsp";
            }
        </script>
        <script>
            function validatePhone(myField, thisClass)
            {

                 //Check for valid SSN.
                if((myField.search(/^\s*\d{10}\s*$/)) == 0)
                {
                    document.getElementsByClassName(thisClass)[0].style.visibility = 'hidden';
                    document.getElementById("validPhone").value = "valid";
                }
                else
                {
                    document.getElementsByClassName(thisClass)[0].style.visibility = 'visible';
                    document.getElementById("validPhone").value = "invalid";
                }
            }
            
            function readyToSubmit()
            {                
                validatePhone(document.getElementById("phoneInput").value, 'phoneErr'); 

                status = document.getElementById("validPhone").value;

                if(status === "valid") {
                    return true;
                } else {
                    return false;
                }

            }      
         </script>
    </head>
    <body>
        <% 
            Object phone ="",email ="", address = "";
            Object update = request.getParameter("update");
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
                <p>Adding your phone number and address will allow only your friends to see it.
            <div id="indent">
                <form  action="write_database" method="POST">
                    <input type="hidden" name="fb_id" value="${fbdata.id}">
                    <input type="hidden" name="email" value="${fbdata.email}">
                    <input type="hidden" id="validPhone" value="valid">
 <!--                   <strong>${fbdata.name}</strong>  -->
                    <p>                                       
                        <strong>E-mail:</strong> ${fbdata.email} <em> *Retrieved from your Facebook login</em><br /><br />
    <!--                    <strong>Birthday:</strong> ${fbdata.birthday}<br /> -->

                        <strong>Phone Number:</strong> <br />
                        <input type="text" name="phone" maxlength="10" id="phoneInput" value="<%=phone%>" onblur="validatePhone(this.value,'phoneErr')"/>
                        <span class="phoneErr" style='color:red; visibility: hidden;'>Please enter ten digits (no hyphen or letters)</span>
                        <br /> <br />

                        <strong>Address:</strong><br />
                        <textarea type="password" name="address" maxlength="255" style="font-family:Ariel" /><%=address%></textarea><br /><br />
                        <input type="submit" value="Finish" onclick="return readyToSubmit()"/><button type=button" onclick="skipDBUpdate()">Cancel</button>        
                    </p>
                </form>
                
            </div>
        </main>
        <footer>
            <p><strong>Disclaimer:</strong> This web site is presented as course work for CS-313 at BYU-Idaho. 
            <p><strong>Review Committee Lead:</strong> Brother Lyon</p>
            <p><strong>App Developers:</strong> Blake Taylor, Jeffry Simpson, Jonny Job & Michael Cavey.</p>        
        </footer>
    </body>
</html>
