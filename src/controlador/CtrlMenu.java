
package controlador;

import static java.awt.Frame.ICONIFIED;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import vista.CambiarPass;
import vista.DatosAcceso;
import vista.Menu;
import vista.NuevoUsuario;
import vista.RegistroPaciente;

/**
 *
 * @author Brian54
 */
public class CtrlMenu implements ActionListener, MouseListener {

    private Menu FrmMenu = new Menu();
    


    public CtrlMenu(Menu FrmMenu) {
        this.FrmMenu = FrmMenu;
        this.FrmMenu.JMNuevoUsuario.addActionListener(this);
        this.FrmMenu.JMCambiarContraseña.addActionListener(this);
        this.FrmMenu.JMDetalleRegistro.addActionListener(this);
        this.FrmMenu.JMPacienteRegistro.addActionListener(this);
        this.FrmMenu.lblCerrar.addMouseListener(this);
        this.FrmMenu.lblMinimizar.addMouseListener(this);   
    }
    
    public CtrlMenu() {
    }

    public void MostrarMenu() {
        FrmMenu.setVisible(true);
        FrmMenu.setLocationRelativeTo(null);
        FrmMenu.setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == FrmMenu.JMNuevoUsuario) {
            NuevoUsuario frmNuevoUsuario = new NuevoUsuario();
            CtrlNuevoUsuario ctrlUser=new CtrlNuevoUsuario(frmNuevoUsuario);
            ctrlUser.mostrar();
        }  
        if (e.getSource() == FrmMenu.JMCambiarContraseña){
            CambiarPass frmCambiarPass=new CambiarPass();
            CtrlCambiarPassUser ctrlChangePass= new CtrlCambiarPassUser(frmCambiarPass);
            ctrlChangePass.mostrar();
        }
        if(e.getSource() == FrmMenu.JMDetalleRegistro){
            DatosAcceso frmDatosAcceso=new DatosAcceso();
            CtrlTablaLogin ctrlTablaLogin=new CtrlTablaLogin(frmDatosAcceso);
            ctrlTablaLogin.mostrar();
        }
        if(e.getSource() == FrmMenu.JMPacienteRegistro){
            RegistroPaciente frmRegistroPaciente=new RegistroPaciente();
            CtrlNuevoPaciente ctrlNuevoPaciente=new CtrlNuevoPaciente(frmRegistroPaciente);
            ctrlNuevoPaciente.mostrar();
        }
        if(e.getSource() == FrmMenu.JMPacienteServicio){
            
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        
        if (e.getSource() == FrmMenu.lblCerrar) {
            System.exit(0);
        }
        if (e.getSource() == FrmMenu.lblMinimizar) {
            FrmMenu.setExtendedState(ICONIFIED);
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
