/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uk.ac.dundee.computing.aec.instagrim.servlets;

import com.datastax.driver.core.Cluster;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import uk.ac.dundee.computing.aec.instagrim.lib.CassandraHosts;
import uk.ac.dundee.computing.aec.instagrim.models.UserModel;

/**
 *
 * @author Administrator
 */
@WebServlet(name = "Register", urlPatterns = {"/Register"})

public class Register extends HttpServlet 
{
    String errorMsg = null;
    Cluster cluster = null;
            
    /*public Register()
    {
        errorMsg = null;
        us = new UserModel();
    }*/
    
    public void init(ServletConfig config) throws ServletException 
    {
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        String username=request.getParameter("username");
        username = username.toLowerCase();
        String password=request.getParameter("password");
        String password2=request.getParameter("password2");
        String email=request.getParameter("email");
        String email2=request.getParameter("email2");
        String firstname=request.getParameter("firstname");
        String surname=request.getParameter("surname");
        
        UserModel us = new UserModel();
        us.setCluster(cluster);
             
        if (username.equals("") || password.equals("") || password2.equals("") || email.equals("") || email2.equals("") || firstname.equals("") || surname.equals(""))
        { 
            errorMsg = "A field was not completed. Please complete all fields and re-submit."; 
            request.setAttribute("errorMsg", errorMsg);
            RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
            rd.forward(request, response);
        }
        else if (!email.equals(email2) && password.equals(password2))
        { 
            errorMsg = "Email addresses entered do not match. Please try again.";
            request.setAttribute("errorMsg", errorMsg);
            RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
            rd.forward(request, response);
        }     
        else if (!password.equals(password2) && email.equals(email2))
        { 
            errorMsg = "Passwords entered do not match. Please try again."; 
            request.setAttribute("errorMsg", errorMsg);
            RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
            rd.forward(request, response);

        }    
        else if (!password.equals(password2) && !email.equals(email2))
        { 
            errorMsg = "Email addresses entered do not match. Passwords entered also do not match. Please try again."; 
            request.setAttribute("errorMsg", errorMsg);
            RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
            rd.forward(request, response);
        }    
        
        else if (us.isLoginTaken(username))
        {
            errorMsg = "Username is already taken. Please try again with another username."; 
            request.setAttribute("errorMsg", errorMsg);
            RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
            rd.forward(request, response);
        }
        
        else if (checkIfSame(email, email2) && checkIfSame(password, password2))
        {
            us.RegisterUser(username, password, email, firstname, surname);
            errorMsg = "<h3> Login " + username + " has been created. Please login below. <h3>";
            response.sendRedirect("login.jsp");
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

    public Boolean checkIfSame(String check1, String check2)
    {
        if(check1.equals(check2))
            return true; 
        else
            return false;
    }
 
    
}