/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uk.ac.dundee.computing.aec.instagrimmjb.servlets;

import com.datastax.driver.core.Cluster;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uk.ac.dundee.computing.aec.instagrimmjb.lib.CassandraHosts;
import uk.ac.dundee.computing.aec.instagrimmjb.models.UserModel;
import uk.ac.dundee.computing.aec.instagrimmjb.stores.LoggedIn;

/**
 *
 * @author Administrator
 */
@WebServlet(name = "Login", urlPatterns = {"/Login"})

public class Login extends HttpServlet 
{
    Cluster cluster=null;
    String errorMsg;
    
    public Login()
    {
         String errorMsg = null;
    }
    public void init(ServletConfig config) throws ServletException {
        // TODO Auto-generated method stub
        cluster = CassandraHosts.getCluster();
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        username = username.toLowerCase();
        
        // Check username and password are not left blank
        if (!username.equals("") && !password.equals(""))
        {
            UserModel us=new UserModel();
            us.setCluster(cluster);
            boolean isValid=us.IsValidUser(username, password);
            
                HttpSession session=request.getSession();
                System.out.println("Session in servlet "+session);

                LoggedIn lg = new LoggedIn();
                lg.setLoggedIn();
                lg.setUsername(username);
                //request.setAttribute("LoggedIn", lg);

                session.setAttribute("LoggedIn", lg);
                System.out.println("Session in servlet "+session);

                String message = "You are logged in as: " + lg.getUsername(); 
                request.setAttribute("message", message);
                RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
                rd.forward(request, response);
           
             
        }
        else
        {
            errorMsg = "Username and/or password were incorrect. Please try again or register as a new user."; 
            request.setAttribute("errorMsg", errorMsg);
            RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
            rd.forward(request, response);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
