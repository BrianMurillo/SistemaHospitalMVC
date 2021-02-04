
package modelo;

/**
 *
 * @author Brian54
 */
public class login {
    private String user_name;
    private String password;
    
    public login(String user_name,String password){
        this.user_name=user_name;
        this.password=password;
    }
    
    public login(){
        
    }
    
    public String getUser_Name(){
        return user_name;
    }
    
    public void setUser_Name(String user_name){
        this.user_name=user_name;
    }
    
    public String getPassword(){
        return password;
    }
    
    public void setPassword(String password){
        this.password=password;
    }
}
