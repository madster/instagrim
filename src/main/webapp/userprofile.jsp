<%-- 
    Document   : UsersPics
    Created on : Sep 24, 2014, 2:52:48 PM
    Author     : Administrator
--%>
<%@page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="uk.ac.dundee.computing.aec.instagrim.stores.*" %>
<%@ page import="uk.ac.dundee.computing.aec.instagrim.models.UserModel" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Instagrim</title>
        <link rel="stylesheet" type="text/css" href="/Instagrim/Styles.css" />
    </head>
    <body>
        <header>

            <h1>InstaGrim ! </h1>
            <h2>Your world in Black and White</h2>
        </header>

        <nav>
            <ul>
                <li class="nav"><a href="/Instagrim/upload.jsp">Upload</a></li>
                <li class="nav"><a href="/Instagrim/Images/sample">Sample Images</a></li>
            </ul>
        </nav>

        <article>
            <h1>Your Profile</h1>

            <%-- Put in link to user info, i.e.:
                 Username: + $username
                 Email: + $email --%>
            
            <p> Username:     <% LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
                                 String username = lg.getUsername(); 
                                 out.println(username);       %>
                Name:         <% String firstName = lg.getUsername();   /// SORT THIS
                                 out.println(username);   
                %>   
            </p>


        </article>
        <footer>
            <ul>
                <li class="footer"><a href="/Instagrim">Home</a></li>
            </ul>
        </footer>
    </body>
</html>
