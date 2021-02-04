
package controlador;

import static java.awt.Frame.ICONIFIED;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import modelo.LoginDao;
import modelo.login;
import vista.Login;
import vista.Menu;

/**
 *
 * @author Brian54
 */
public class CtrlLogin implements ActionListener,MouseListener {

    private login log;
    private LoginDao loginDao;
    private Login frmLogin;

    public CtrlLogin(login log, LoginDao loginDao, Login frmLogin) {
        this.log = log;
        this.loginDao = loginDao;
        this.frmLogin = frmLogin;
        this.frmLogin.btnIngresar.addActionListener(this);
        this.frmLogin.lblCerrar.addMouseListener(this);
        this.frmLogin.lblMinimizar.addMouseListener(this);
    }

    public void Iniciar() {
        frmLogin.setTitle("LOGIN");
        frmLogin.setResizable(true);
        frmLogin.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == frmLogin.btnIngresar) {
            String user = frmLogin.txtUser.getText();
            String pass = frmLogin.txtPassword.getText();
            if ("".equals(frmLogin.txtUser.getText())) {
                JOptionPane.showMessageDialog(null, "Ingrese Usuario");
            }
            if ("".equals(frmLogin.txtPassword.getText())) {
                JOptionPane.showMessageDialog(null, "Ingrese Contraseña");
            }
            if (!"".equals(frmLogin.txtUser.getText()) && !"".equals(frmLogin.txtPassword.getText())) {
                log = loginDao.getUsuario(user, pass);
                if (log.getUser_Name() != null && log.getPassword() != null) {
                    frmLogin.dispose();
                    Menu frmMenu = new Menu();
                    CtrlMenu menu = new CtrlMenu(frmMenu);
                    menu.MostrarMenu();
                } else {
                    JOptionPane.showMessageDialog(null, "Error al ingresar");
                    Limpiar();
                }
            }
        }
    }

    private void Limpiar() {
        frmLogin.txtUser.setText("");
        frmLogin.txtPassword.setText("");
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == frmLogin.lblCerrar){
            System.exit(0);
        }
        if(e.getSource() == frmLogin.lblMinimizar){
            frmLogin.setExtendedState(ICONIFIED);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
