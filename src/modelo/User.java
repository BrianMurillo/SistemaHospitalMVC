
package modelo;

/**
 *
 * @author Brian54
 */
public class User {
    private String name;
    private String user_name;
    private String password;
    private String email_id;
    private int contact_no;

    public User(String name, String user_name, String password, String email_id, int contact_no) {
        this.name = name;
        this.user_name = user_name;
        this.password = password;
        this.email_id = email_id;
        this.contact_no = contact_no;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail_id() {
        return email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

    public int getContact_no() {
        return contact_no;
    }

    public void setContact_no(int contact_no) {
        this.contact_no = contact_no;
    } 
}
