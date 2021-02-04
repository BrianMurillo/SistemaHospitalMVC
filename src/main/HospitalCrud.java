
package main;

import controlador.CtrlLogin;
import modelo.LoginDao;
import modelo.login;
import vista.Login;

/**
 *
 * @author Brian54
 */
public class HospitalCrud {

    public static void main(String[] args) {
        login log = new login();
        LoginDao logDao = new LoginDao();
        Login frmLogin = new Login();
        CtrlLogin ctrlLogin = new CtrlLogin(log, logDao, frmLogin);

        ctrlLogin.Iniciar();
        frmLogin.setVisible(true);
    }
}
