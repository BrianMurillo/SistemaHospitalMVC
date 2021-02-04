
package controlador;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import modelo.LoginDao;
import modelo.login;
import vista.DatosAcceso;

/**
 *
 * @author Brian54
 */
public class CtrlTablaLogin implements MouseListener{
    DatosAcceso frmDatosLogin = new DatosAcceso();
    login log=new login();
    LoginDao logDao=new LoginDao();
    DefaultTableModel modelo = new DefaultTableModel();
    
    public CtrlTablaLogin(DatosAcceso frmDatosLogin){
        this.frmDatosLogin=frmDatosLogin;
        this.frmDatosLogin.lblCerrar.addMouseListener(this);
    }
    
    public void mostrar(){
        frmDatosLogin.setVisible(true);
        frmDatosLogin.setLocationRelativeTo(null);
        frmDatosLogin.setResizable(false);
        frmDatosLogin.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        LimpiarTable();
        ListarAcceso();
    }
    public void ListarAcceso() {
        List<login> ListarLog = logDao.ListarAcceso();
        modelo = (DefaultTableModel) frmDatosLogin.TableAcceso.getModel();
        Object[] ob = new Object[2];
        for (int i = 0; i < ListarLog.size(); i++) {
            ob[0] = ListarLog.get(i).getUser_Name();
            ob[1] = ListarLog.get(i).getPassword();
            modelo.addRow(ob);
        }
      frmDatosLogin.TableAcceso.setModel(modelo);
    }

    public void LimpiarTable() {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == frmDatosLogin.lblCerrar){
            frmDatosLogin.dispose();
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
