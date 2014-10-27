package uk.ac.dundee.computing.aec.instagrimmjb.servlets;

import com.datastax.driver.core.Cluster;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.util.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import uk.ac.dundee.computing.aec.instagrimmjb.lib.CassandraHosts;
import uk.ac.dundee.computing.aec.instagrimmjb.lib.Convertors;
import uk.ac.dundee.computing.aec.instagrimmjb.models.PicModel;
import uk.ac.dundee.computing.aec.instagrimmjb.stores.LoggedIn;
import uk.ac.dundee.computing.aec.instagrimmjb.stores.Pic;
import uk.ac.dundee.computing.aec.instagrimmjb.models.UserModel;

/**
 * Servlet implementation class Image
 */

 

//  Allows these url patterns to use this servlet.
@WebServlet(urlPatterns = {
    "/Image",
    "/Image/*",
    "/Thumb/*",
    "/Images",
    "/Images/*",
    "/Random"
})
@MultipartConfig

public class Image extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private Cluster cluster;
    private HashMap CommandsMap = new HashMap();
    String username = null;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Image() {
        super();
        // TODO Auto-generated constructor stub
        CommandsMap.put("Image", 1);
        CommandsMap.put("Images", 2);
        CommandsMap.put("Thumb", 3);
        CommandsMap.put("Random", 4);
        

    }

    public void init(ServletConfig config) throws ServletException {
        // TODO Auto-generated method stub
        cluster = CassandraHosts.getCluster();
    }

    /**
     * @param request, response
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String args[] = Convertors.SplitRequestPath(request); // gets URL and splits it up where the obliques/ are. instagrimmjb = args[0], Images = [1], User = args[2]
        int command;
        try {
            command = (Integer) CommandsMap.get(args[1]);
        } catch (Exception et) {
            error("Bad Operator in catch", response);
            return;
        }
        switch (command) {
            case 1:
                DisplayImage(Convertors.DISPLAY_PROCESSED, args[2], response);
                break;
            case 2:
                DisplayImageList(args[2], request, response);
                break;
            case 3:
                DisplayImage(Convertors.DISPLAY_THUMB, args[2], response);
                break;
            case 4:
                String Image = generatePicID();
                DisplayImage(Convertors.DISPLAY_IMAGE, Image, response);
            default:
                error("Bad Operator in switch", response);
        }
    }

    private void DisplayImageList(String User, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PicModel tm = new PicModel();
        tm.setCluster(cluster);
        LinkedList<Pic> lsPics = tm.getPicsForUser(User);
        RequestDispatcher rd = request.getRequestDispatcher("/UsersPics.jsp");
        request.setAttribute("Pics", lsPics);
        rd.forward(request, response);

    }

    private void DisplayImage(int type,String Image, HttpServletResponse response) throws ServletException, IOException 
    {
        if (Image.equals("ListEmpty"))
        {
            error("Sorry, there are no images to display.", response);
        }
        else 
        {
            PicModel tm = new PicModel();
            tm.setCluster(cluster);

            Pic p = tm.getPic(type,UUID.fromString(Image));

            OutputStream out = response.getOutputStream();

            response.setContentType(p.getType());
            response.setContentLength(p.getLength());
            //out.write(Image);
            InputStream is = new ByteArrayInputStream(p.getBytes());
            BufferedInputStream input = new BufferedInputStream(is);
            byte[] buffer = new byte[8192];
            for (int length = 0; (length = input.read(buffer)) > 0;) 
                {
                out.write(buffer, 0, length);
                }
                
        }
        out.close();
    }

    private String generatePicID()
    {
        PicModel pm = new PicModel();
        pm.setCluster(cluster);
        return pm.generateRandomImage();
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String picName=request.getParameter("picname");
        for (Part part : request.getParts()) {
            System.out.println("Part Name " + part.getName());

            String type = part.getContentType();
            String filename = part.getSubmittedFileName();
            
            InputStream is = request.getPart(part.getName()).getInputStream();
            int i = is.available();
            HttpSession session=request.getSession();
            LoggedIn lg= (LoggedIn)session.getAttribute("LoggedIn");
            String username="defaultuser";
            if (lg.getLoggedIn()){
                username=lg.getUsername();
            }
            if (i > 0) {
                byte[] b = new byte[i + 1];
                is.read(b);
                System.out.println("Length : " + b.length);
                PicModel tm = new PicModel();
                tm.setCluster(cluster);
                tm.insertPic(b, type, filename, username, picName);

                is.close();
            }
            RequestDispatcher rd = request.getRequestDispatcher("/upload.jsp");
            rd.forward(request, response);
        }

    }
   

    private void error(String errorMsg, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = null;
        out = new PrintWriter(response.getOutputStream());
        //out.println("<h1>You have a na error in your input</h1>");
        out.println("<h1>" + errorMsg + "</h1>");
        out.close();
        return;
    }
}