<%--
    Document   : index
    Created on : Sep 28, 2014, 7:01:44 PM
    Author     : Administrator
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uk.ac.dundee.computing.aec.instagrimmjb.stores.*" %>
<%@page import="uk.ac.dundee.computing.aec.instagrimmjb.servlets.Logout" %>

<!DOCTYPE html>
<html>
    <head>
        <title>instagrimmjb</title>
        <link rel="stylesheet" type="text/css" href="Styles.css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <header>
            <h1>instagrimmjb ! </h1>
            <h2>Your world in Black and White</h2>
        </header>
        <nav>
            <ul>
                     
                        <li><a href="/instagrimmjb/upload.jsp">Upload</a></li>
                        <li><a href="/instagrimmjb/Random">Random Image</a></li>
                        <%
                            LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
                            if (lg != null)
                            {  // if user is logged in
                                if (lg.getLoggedIn())
                                {  // again, if user is logged in
                                    String username = lg.getUsername(); %>
                        
                                    <li><a href="/instagrimmjb/Profile/<%=lg.getUsername()%>">Your Profile</a></li>
                                    <li><a href="/instagrimmjb/Images/<%=lg.getUsername()%>">Your Images</a></li>
                        
                                    <form action="Logout" method="POST">
                                    <input type="submit" value="Logout" />
                                    </form>
                            <%  }
                        } 
                        else
                        {%>
                        <font color="#00FF00"> <h3>${message}</h3> </font>
                            <li><a href="/instagrimmjb/register.jsp">Register</a></li>
                            <li><a href="/instagrimmjb/login.jsp">Login</a></li>
                            
                       <%}%>
            </ul>
        </nav>
        <footer>
            <ul>
                <li class="footer"><a href="/instagrimmjb">Home</a></li>
                <li>&COPY; Maddie Barker</li>
            </ul>
        </footer>
    </body>
</html>
