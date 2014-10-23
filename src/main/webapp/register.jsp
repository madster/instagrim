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
                <li><a href="/Instagrim/Images/sample">Sample Images</a></li>
            </ul>
        </nav>
       
        <article>
            <h3>Register as user</h3>
            <form method="POST"  action="Register">
                <ul>
                    <li>User Name <input type="text" name="username"></li>
                    
                    
                    <form  name="pwdCheck" onsubmit="return false">
                    <li>Password <input type="password" name="pwd1" id="pwd1"></li>
                    <li>Confirm Password <input type="password" name="pwd2" id="pwd2">
                    </form>
                    <div id="pwdMsg"></div> 
                    <script src="CheckOnEnter.js"></script> 
                    </li>
                    
                    
                    <form  name="emailCheck" onsubmit="return false">
                    <li>Email address <input type="text" name="email" id="email1"></li>
                    <li>Confirm Email <input type="text" name="email2" id="email2">
                    </form>
                    <div id="emailMsg"></div> 
                    <script src="CheckOnEnter.js"></script> 
                    </li>
                  
                    
                    <li>First name <input type="text" name="firstname"></li>
                    <li>Surname <input type="text" name="surname"></li>
                </ul>
                <br/>
                <input type="submit" value="Register"> 
            </form>

        </article>
        <footer>
            <ul>
                <li class="footer"><a href="/Instagrim">Home</a></li>
            </ul>
        </footer>
    </body>
</html>