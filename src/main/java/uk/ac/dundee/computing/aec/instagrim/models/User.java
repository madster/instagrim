/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * Author: Andy Cobley & Maddie Barker   
 */

package uk.ac.dundee.computing.aec.instagrim.models;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.security.NoSuchAlgorithmException;
import uk.ac.dundee.computing.aec.instagrim.lib.AeSimpleSHA1;
import uk.ac.dundee.computing.aec.instagrim.lib.Convertors;
import uk.ac.dundee.computing.aec.instagrim.stores.Pic;

/**
 * @author Administrator
 */
public class User {
    Cluster cluster;
    public User(){
        
    }
    
    /*
    @param username, password, email, firstname, surname
    */
    public boolean RegisterUser(String username, String password, String email, String firstname, String surname)
    {   
        String EncodedPassword=encodePassword(password);
        Session session = cluster.connect("instagrim");
        PreparedStatement ps = session.prepare("insert into userprofiles (login, password, email, firstname, surname) Values(?,?,?,?,?)");
       
        BoundStatement boundStatement = new BoundStatement(ps);
        session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        username, EncodedPassword, email, firstname, surname));
        //We are assuming this always works.  Also a transaction would be good here !
        
        return true;
    }
    
    public boolean IsValidUser(String username, String password)
    {
        String EncodedPassword=encodePassword(password);
        
        Session session = cluster.connect("instagrim");
        PreparedStatement ps = session.prepare("select password from userprofiles where login =?");
        ResultSet rs = null;
        BoundStatement boundStatement = new BoundStatement(ps);
        rs = session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        username));
        if (rs.isExhausted()) {
            System.out.println("No Images returned");
            return false;
        } else {
            for (Row row : rs) {
               
                String StoredPass = row.getString("password");
                if (StoredPass.compareTo(EncodedPassword) == 0)
                    return true;
            }
        }
    
    return false;  
    }
       public void setCluster(Cluster cluster) {
        this.cluster = cluster;
    }

    public String encodePassword(String password)
    {
        AeSimpleSHA1 sha1handler=  new AeSimpleSHA1();
        String EncodedPassword=null;
        try {
            EncodedPassword= sha1handler.SHA1(password);
        }catch (UnsupportedEncodingException | NoSuchAlgorithmException et){
            System.out.println("Can't check your password");
            return null;
        }
        return EncodedPassword;
    }

    public java.util.LinkedList<User> getUserInfo(String username) {
        java.util.LinkedList<User> Users = new java.util.LinkedList<>();
        
        try (Session session = cluster.connect("instagrim")) {
            try {
                PreparedStatement ps = session.prepare("select login from userprofiles where username =?");
                BoundStatement bs = new BoundStatement(ps);
                ResultSet rs = session.execute( // this is where the query is executed
                        bs.bind(username)); // here you are binding the 'boundStatement'
                
                if (rs.isExhausted()) {
                    System.out.println("No info returned");
                    return null;
                }
                else {
                    for (Row row : rs)
                    {
                        
                    }
                }
            } catch (Exception et) {
                System.out.println("Can't get info " + et);
                return null;
            }
            session.close();
        } 
        return Users;
    }
}