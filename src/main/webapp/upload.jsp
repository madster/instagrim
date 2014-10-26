<%-- 
    Document   : upload
    Created on : Sep 22, 2014, 6:31:50 PM
    Author     : Administrator
--%>

<%@page import="uk.ac.dundee.computing.aec.instagrim.stores.LoggedIn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Instagrim</title>
        <link rel="stylesheet" type="text/css" href="Styles.css" />
    </head>
    <body>
        <h1>InstaGrim ! </h1>
        <h2>Your world in Black and White</h2>
        <nav>
            <ul>
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
                            <li><a href="/Instagrim/register.jsp">Register</a></li>
                            <li><a href="/Instagrim/login.jsp">Login</a></li>
                            
                       <%}%>
                <li class="nav"><a href="/Instagrim/upload.jsp">Upload</a></li>
                <li class="nav"><a href="/Instagrim/Random">Random Images</a></li>
            </ul>
        </nav>
 
        <article>
            <h3>File Upload</h3>
            <form method="POST" enctype="multipart/form-data" action="Image">
                File to upload: <input type="file" name="upfile"><br/>

                <br/>
                Please enter a name for your photo <input type="text" name="picname">
                <input type="submit" value="Press"> to upload the file!
            </form>

        </article>
        <footer>
            <ul>
                <li class="footer"><a href="/Instagrim">Home</a></li>
            </ul>
        </footer>
    </body>
</html>
