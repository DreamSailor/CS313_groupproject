<%-- 
    Document   : inside
    Created on : Jun 22, 2016, 7:26:17 AM
    Author     : jsimpson
--%>

<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html> 
    <head> 
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Facebook Demo Login</title> 
        <link href="main.css" type="text/css" rel="stylesheet" media="screen">
    </head> 
    <body> 
        <header>
            <img src="mvp-logo.png" alt="Graphic Logo"/>
            <div>
                <h1>CS313 Friends MVP</h1>
                <h3>Facebook-API App Project Demo</h3>
                <p>Web Design / App Development</p>
            </div>
        </header>
        <main> 
            <h2>Introduction</h2>
            <p>This Facebook Application will help you and your Facebook friends share profile  
                data that exists on Facebook, but may not be visible.  </p>
            <p class="warning">Please read the App Store information below before logging in.</p>
            <div> 
                <p><a href="SignIn"><img src="facebook-login-button.png"></a></p>
            </div> 

            <h2>App Store Information</h2>
            <P>In April 2015 Facebook started limiting applications ability to find data about your friends.
                They also require friends to "accept" a given app before they will display their data.
                Our applications allows friends to share their contact information with each other outside of
                Facebook via an SQL database.
                
            <p>When you login to Facebook through the link (above) and you will authorize this 
                application to share information with your friends.   After login, you will be given the  
                opportunity to input the additonal information you want to share with your friends. Then 
                when your friends also login using the application, your information will be visible to them.</p>
            
            <p class="warning">Login Accounts</p>
            <p>As noted above, you will only see Friends information if they have ALSO accepted our application.
                Since this is a class assignment and since most of us in this class are not "friends" on facebook,
                we have created a set of "test" users that are already friends with each other.   This is done via 
                Facebook's developer site.  
            <p>If you login with your own Facebook profile and NOT one of the test users below, 
                you will see nothing and it will be a lonely place, 
            because none of yourfriends will have previously used our application.</p>
            
            <p class='warning'>To demonstrate the functionality of this app please login with any of the following logins:<p /> <br />
                <strong>User Email:</strong> <br />
                mark_nsxtvtw_warmanwitz@tfbnw.net <br />
                karen_rnoccko_qinson@tfbnw.net <br />
                bob_bqtpczr_wongwitz@tfbnw.net <br />
                robert_yivdlif_romanescu@tfbnw.net <br />
                mary_zmxibqa_bowerssen@tfbnw.net <br />
                linda_emmvxfy_changson@tfbnw.net <br />
                will_ttgyocr_narayananstein@tfbnw.net <br />
                james_buggwjb_bharambeberg@tfbnw.net <br />
                tom_eouxgro_martinazziwitz@tfbnw.net <br />
                richard_lhgopyg_sadansky@tfbnw.net <br />
                rick_ixugpjd_wongberg@tfbnw.net <br />
                donna_rprcrvl_letuchyberg@tfbnw.net <br />
                thomas_axhqzti_moiduson@tfbnw.net <br />
                dorothy_vvesrzv_carrieroman@tfbnw.net <br />
                will_plabbgy_okelolason@tfbnw.net <br />
                htelzad_sidhuson_1466643592@tfbnw.net <br />
                yzsicfq_carrieroescu_1466643560@tfbnw.net <br />
                ipgblfd_chengwitz_1466643557@tfbnw.net <br />
                mhahkhf_moidusen_1466643555@tfbnw.net <br />
                hmwqzuw_shepardsky_1466643558@tfbnw.net <br />
                bill_ohvzzmm_sidhuescu@tfbnw.net<br /> <br />
                <strong>Common Password:</strong> pass313</p>
            
            <p><strong>To verify the app</strong>, make note of one of the friends of your login persona and then
                login with that friend's name. Check to see if your input information is shared. </p>

            <h2>Contact Us</h2>
            <p>Your feedback is valuable to us. Please leave a comment in our discussion thread for week 14</p>
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
