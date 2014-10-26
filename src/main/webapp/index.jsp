<%--
    Document   : index
    Created on : Sep 28, 2014, 7:01:44 PM
    Author     : Administrator
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uk.ac.dundee.computing.aec.instagrim.stores.*" %>
<%@page import="uk.ac.dundee.computing.aec.instagrim.servlets.Logout" %>

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
                     
                        <li><a href="/Instagrim/upload.jsp">Upload</a></li>
                        <li><a href="/Instagrim/Random">Random Image</a></li>
                        <%
                            LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
                            if (lg != null)
                            {  // if user is logged in
                                if (lg.getLoggedIn())
                                {  // again, if user is logged in
                                    String username = lg.getUsername(); %>
                        
                                    <li><a href="/Instagrim/Profile/<%=lg.getUsername()%>">Your Profile</a></li>
                                    <li><a href="/Instagrim/Images/<%=lg.getUsername()%>">Your Images</a></li>
                        
                                    <form action="Logout" method="POST">
                                    <input type="submit" value="Logout" />
                                    </form>
                            <%  }
                        } 
                        else
                        {%>
                        <font color="#00FF00"> <h3>${message}</h3> </font>
                            <li><a href="/Instagrim/register.jsp">Register</a></li>
                            <li><a href="/Instagrim/login.jsp">Login</a></li>
                            
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
