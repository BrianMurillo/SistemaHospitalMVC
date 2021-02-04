
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import modelo.LoginDaoPass;
import modelo.login;
import vista.CambiarPass;

/**
 *
 * @author Brian54
 */
public class CtrlCambiarPassUser implements ActionListener,MouseListener{
    private login log=new login();
    private LoginDaoPass logDao=new LoginDaoPass();
    private CambiarPass frmCambiarPass= new CambiarPass();
    
    public CtrlCambiarPassUser(CambiarPass frmCambiarPass){
        this.frmCambiarPass = frmCambiarPass;
        this.frmCambiarPass.btnAceptar.addActionListener(this);
        this.frmCambiarPass.lblCerrar.addMouseListener(this);
    }
    
    public void mostrar(){
        frmCambiarPass.setVisible(true);
        frmCambiarPass.setLocationRelativeTo(null);
        frmCambiarPass.setResizable(false);
        frmCambiarPass.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == frmCambiarPass.btnAceptar){
            if(!"".equals(frmCambiarPass.txtUser.getText()) && !"".equals(frmCambiarPass.txtPassAnterior.getText()) && !"".equals(frmCambiarPass.txtPassNuevo.getText()) && !"".equals(frmCambiarPass.txtPassConfirmar.getText())){
                log=logDao.ComfirmPassword(frmCambiarPass.txtUser.getText());
                if(log.getUser_Name().equals(frmCambiarPass.txtUser.getText()) && log.getPassword().equals(frmCambiarPass.txtPassAnterior.getText()) && frmCambiarPass.txtPassNuevo.getText().equals(frmCambiarPass.txtPassConfirmar.getText())){
                    login nuevoLog=new login();
                    nuevoLog.setUser_Name(frmCambiarPass.txtUser.getText());
                    nuevoLog.setPassword(frmCambiarPass.txtPassNuevo.getText());
                    if(logDao.ChangePassword(nuevoLog)) JOptionPane.showMessageDialog(null,"Contraseña Modificada");                 
                }else{
                   JOptionPane.showMessageDialog(null,"Datos incorrectos");
                }
                limpiar();
            }else{
                JOptionPane.showMessageDialog(null,"Rellenar datos");
            }
        }     
    }
    public void limpiar(){
        frmCambiarPass.txtPassAnterior.setText("");
        frmCambiarPass.txtPassConfirmar.setText("");
        frmCambiarPass.txtPassNuevo.setText("");
        frmCambiarPass.txtUser.setText("");
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == frmCambiarPass.lblCerrar){
            frmCambiarPass.dispose();
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
