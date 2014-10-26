<%-- 
    Document   : register.jsp
    Created on : Sep 28, 2014, 6:29:51 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Instagrim</title>
        <link rel="stylesheet" type="text/css" href="Styles.css" />
        
    </head>
    <body>
        <header>
        <h1>InstaGrim ! </h1>
        <h2>Your world in Black and White</h2>
        </header>
        <nav>
            <ul>
                <li><a href="/Instagrim/Images/Random">Random Image</a></li>
            </ul>
        </nav>   
        
        <h3><font color="red">${errorMsg}</font></h3>
            <h4>Register as user</h4>
            <form method="POST"  action="Register">
                <ul>
                    <li>User Name <input type="text" name="username"></li>
                    
                    <!-- <form  name="pwdCheck" onsubmit="return false"> -->
                    <li>Password <input type="password" name="password" id="password"></li>
                    <li>Confirm Password <input type="password" name="password2" id="password2"></li>
                    <!--</form> -->
                    <div id="pwdMsg"></div> 
                    
                    
                    <!--<div id="emailCheck" onsubmit="return false">-->
                    <li>Email Address <input type="text" name="email" id="email"></li>
                    <li>Confirm Email <input type="text" name="email2" id="email2"></li>
                    <!--</form>-->
                    <!--<div id="emailMsg"></div> -->
                    
                    <li>First Name <input type="text" name="firstname"></li>
                    <li>Surname <input type="text" name="surname"></li>
                </ul>
                <br/>
                <input type="submit" value="Register"> 
            </form>

        
        <footer>
            <ul>
                <li class="footer"><a href="/Instagrim">Home</a></li>
            </ul>
        </footer>
    </body>
</html>