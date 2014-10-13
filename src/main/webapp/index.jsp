<%-- 
    Document   : index
    Created on : Sep 28, 2014, 7:01:44 PM
    Author     : Administrator
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uk.ac.dundee.computing.aec.instagrim.stores.*" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Instagrim</title>
        <link rel="stylesheet" type="text/css" href="Styles.css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <header>
            <h1>InstaGrim ! </h1>
            <h2>Your world in Black and White</h2>
        </header>
        <nav>
            <ul>
                <li><a href="upload.jsp">Upload</a></li>
                    <%
                        LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
                        if (lg != null){  // if user is logged in
                            String UserName = lg.getUsername();
                        if (lg.getLoggedIn())  // again, if user is logged in
                    %>
                        <li><a href="/Instagrim/Images/<%=lg.getUsername()%>">Your Images</a></li>
                        <li><a href="/Instagrim/Profile/<%=lg.getUsername()%>">Your Profile</a></li>
                        <li><a href="/Instagrim/logout/">Logout</a></li>                     
                            
                    <%}
                        
                        else
                        {%>
                            <li><a href="register.jsp">Register</a></li>
                            <li><a href="login.jsp">Login</a></li>
                       <%}%>
            </ul>
        </nav>
        <footer>
            <ul>
                <li class="footer"><a href="/Instagrim">Home</a></li>
                <li>&COPY; Maddie Barker</li>
            </ul>
        </footer>
    </body>
</html>