<%-- 
    Document   : invalidlogin.jsp
    Created on : Oct 5, 2014, 3:04:14 PM
    Author     : Andy Cobley & Maddie Barker
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Instagrim</title>
        <link rel="stylesheet" type="text/css" href="Styles.css" />

    </head>
    
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
            <h3><FONT COLOR="#FF0000">Your login details were invalid. Please try again or register as a new user:</FONT></h3>
            <form method="POST"  action="Login">
                <ul>
                    <li>User Name <input type="text" name="username"></li>
                    <li>Password <input type="password" name="password"></li>
                </ul>
                <br/>
                <input type="submit" value="Login"> 
        </article>
    
        <li><a href="/Instagrim/register.jsp">Register as a new user</a></li>
        
  
        <footer>
            
            <ul>
                <li class="footer"><a href="/Instagrim">Home</a></li>
            </ul>
        </footer>
    
</html>
