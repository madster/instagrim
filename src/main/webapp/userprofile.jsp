<%-- 
    Document   : userprofile
    Created on : 
    Author     : Administrator
--%>
<%@page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="uk.ac.dundee.computing.aec.instagrimmjb.stores.*" %>
<%@ page import="uk.ac.dundee.computing.aec.instagrimmjb.models.UserModel" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>instagrimmjb</title>
        <link rel="stylesheet" type="text/css" href="/instagrimmjb/Styles.css" />
    </head>
    <body>
        <header>

            <h1>instagrimmjb ! </h1>
            <h2>Your world in Black and White</h2>
        </header>

        <nav>
            <ul>
                <li class="nav"><a href="/instagrimmjb/upload.jsp">Upload</a></li>
                <li class="nav"><a href="/instagrimmjb/Random">Random Image</a></li>
            </ul>
        </nav>

        <article>
            <h1>${User.get(2)}'s Profile</h1>                           

            <li><b>First name:  </b>${User.get(1)}</li>
            <li><b>Surname:     </b>${User.get(0)}</li>
        </article>
        
        
        <article>
            <a href="/instagrimmjb/Images/${User.get(2)}">${User.get(2)}'s Pics<a/>
            
            <% LinkedList<Pic> lsPics = (LinkedList<Pic>) request.getAttribute("Pics");
            if (lsPics == null) 
            { %>
                <p>No Pictures found</p>
            <%  
            } 
            else
            {
                Iterator<Pic> iterator;
                iterator = lsPics.iterator();
                while (iterator.hasNext()) 
                {
                    Pic p = (Pic) iterator.next(); %>
                    <a href="/instagrimmjb/Images/<%=p.getSUUID()%>" ><img src="/instagrimmjb/Thumb/<%=p.getSUUID()%>"></a><br/>
                    
            <%  }
            }           
        %>
        </article>
        
        <footer>
            <ul>
                <li class="footer"><a href="/instagrimmjb">Home</a></li>
            </ul>
        </footer>
    </body>
</html>
