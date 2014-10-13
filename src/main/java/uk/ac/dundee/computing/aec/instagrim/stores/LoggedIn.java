/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uk.ac.dundee.computing.aec.instagrim.stores;

/**
 *
 * @author Administrator
 */
public class LoggedIn {
    boolean loggedIn = false;
    String username = null;
    
    public LoggedIn(){
        
    }
    
    public void setUsername(String name){
        this.username=name;
    }
    
    public String getUsername(){
        return username;
    }
    
    public void setLoggedIn(){
        loggedIn=true;
    }
    
    public void setLoggedOut(){
        loggedIn=false;
    }
    
    public void setLoginState(boolean loggedin){
        this.loggedIn=loggedin;
    }
    
    public boolean getLoggedIn(){
        return loggedIn;
    }
}
