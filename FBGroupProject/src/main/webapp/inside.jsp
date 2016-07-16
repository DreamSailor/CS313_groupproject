<%-- 
    Document   : inside
    Created on : Jun 22, 2016, 7:57:49 AM
    Author     : jsimpson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html> 
    <head> 
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>CIS-313 Friends Contact App</title> 
        <link href="main.css" type="text/css" rel="stylesheet" media="screen">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script>
        $(document).ready(function(){
            $("#friendsBtn").click(function(){
                $("#friendslist").load('ShowFriends?process=u');
            });
        });
        </script>
        <script>
            function Update() {
                window.location.href = "sign_up.jsp?update=Y";
            }
        </script> 
    </head> 
    <body> 
        <header>
            <img src="${userpict.protocol}://${userpict.host}${userpict.file}" alt="Profile Image"/>
            <div>
                <h1>${fbdata.name}</h1>
                <h3>${fbdata.email}</h3>
                <p>${fbdata.location.name}</p>
            </div>
        </header>

        <main> 
            <h2>Friends Data</h2>
           
            <p>
                <button id="friendsBtn">Show Friends</button>
                <button id="updateBtn" onclick="Update()">Update Info</button>
                <!-- <a href="sign_up.jsp?update=Y">Update Info</a>  -->
            
            </p>
            <div id="friendslist"></div>

            <br />
            <br />
        </main> 
        <footer>
            <p><strong>Disclaimer:</strong> This web site is presented as course work for CS-313 at BYU-Idaho. 
            <p><strong>Review Committee Lead:</strong> Brother Lyon</p>
            <p><strong>App Developers:</strong> Blake Taylor, Jeffry Simpson, Jonny Job & Michael Cavey.</p>        
        </footer>
    </body>
</html> 

