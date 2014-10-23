/*
 * Author:  Maddie Barker
 */
package uk.ac.dundee.computing.aec.instagrim.servlets;

import com.datastax.driver.core.Cluster;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import uk.ac.dundee.computing.aec.instagrim.lib.CassandraHosts;
import uk.ac.dundee.computing.aec.instagrim.lib.Convertors;
import uk.ac.dundee.computing.aec.instagrim.models.UserModel;
import uk.ac.dundee.computing.aec.instagrim.stores.Pic;

@WebServlet(urlPatterns = {
    "/Profile",
    "/Profile/*",
    "/profile",
    "/profile/*"
})
@MultipartConfig

public class UserProfile extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private Cluster cluster;
    private HashMap CommandsMap = new HashMap();

   public UserProfile() {
        super();
        // TODO Auto-generated constructor stub
        CommandsMap.put("Profile",1);
        
    }
   
   public void init(ServletConfig config) throws ServletException {
        // TODO Auto-generated method stub
        cluster = CassandraHosts.getCluster();
    }
   
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        String args[] = Convertors.SplitRequestPath(request);
        int command;
        try {
            command = (Integer) CommandsMap.get(args[1]);
        } catch (Exception et) {
            error("Catch error - bad operator", response);
            return;
        }
        switch (command) {
            case 1:
                displayUserInfo(args[2], request, response);
                break;
            
            default:
                error("Switch statement default error - bad operator", response);
        }
    }
    
   private void displayUserInfo(String User, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserModel um = new UserModel();
        um.setCluster(cluster);
        LinkedList<String> lsUser = um.getInfoForUser(User);
        RequestDispatcher rd = request.getRequestDispatcher("/userprofile.jsp");
        request.setAttribute("User", lsUser);
        rd.forward(request, response);

    }
   
    // This is deffo wrong as it's a duplicate of the one in Image.java but leaving it in here just now.
   private void error(String errorMsg, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = null;
        out = new PrintWriter(response.getOutputStream());
        out.println("<h1>You have an error in your input</h1>");
        out.println("<h2>" + errorMsg + "</h2>");
        out.close();
        return;
    }  
}