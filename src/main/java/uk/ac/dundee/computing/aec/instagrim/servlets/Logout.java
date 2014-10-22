/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.dundee.computing.aec.instagrim.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 *
 * @author Madster
 */

    @WebServlet("/Logout")
public class Logout extends HttpServlet {

    @Override
    
    // From http://stackoverflow.com/questions/13707225/kill-session-and-redirect-to-login-page-on-click-of-logout-button

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath() + "/logout.jsp");
    }

}