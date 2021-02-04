
package controlador;

import static java.awt.Frame.ICONIFIED;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import modelo.Paciente;
import modelo.Servicio;
import modelo.ServicioDao;
import vista.ListaServicio;
import vista.Services;

/**
 *
 * @author Brian54
 */
public class CtrlServicio implements ActionListener,MouseListener {

    private Services FrmServicio = new Services();
    private Servicio servicio = new Servicio();
    private ServicioDao servicioDao = new ServicioDao();
    private ListaServicio FrmListaServicio = new ListaServicio();
    private DefaultTableModel modelo=new DefaultTableModel();
    private DefaultTableModel model=new DefaultTableModel();


    public CtrlServicio(Services FrmServicio) {
        this.FrmServicio = FrmServicio;
        this.FrmServicio.btnGuardar.addActionListener(this);
        this.FrmServicio.btnActualizar.addActionListener(this);
        this.FrmServicio.btnBorrar.addActionListener(this);
        this.FrmServicio.btnNuevo.addActionListener(this);
        this.FrmServicio.btnObtenerDatos.addActionListener(this);
        this.FrmListaServicio.TableServicio.addMouseListener(this);
        this.FrmServicio.TablePaciente.addMouseListener(this);
        this.FrmServicio.lblMinimiza.addMouseListener(this);
        this.FrmServicio.lblCerrar.addMouseListener(this);
        this.FrmListaServicio.lblCerrar.addMouseListener(this);
    }

    public void mostrar() {
        FrmServicio.setVisible(true);
        FrmServicio.setLocationRelativeTo(null);
        FrmServicio.setResizable(false);
        FrmServicio.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        cleanTable();
        listarPaciente();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == FrmServicio.btnGuardar) {
            if (!"".equals(FrmServicio.txtNombreServicio.getText()) && !"".equals(FrmServicio.txtFechaServicio.getText()) && !"".equals(FrmServicio.txtIdPaciente.getText()) && !"".equals(FrmServicio.txtCargosServicio.getText()) && !"".equals(FrmServicio.txtIdServicios.getText())){
                obtenerDatos();
                if (servicioDao.RegistrarServicio(servicio)) {
                    limpiar();
                    JOptionPane.showMessageDialog(null, "Servicio Registrado");
                } else {
                    JOptionPane.showMessageDialog(null, "Error en Registro");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese Datos");
            }
        }
        if (e.getSource() == FrmServicio.btnActualizar) {        
            if (!"".equals(FrmServicio.txtNombreServicio.getText()) && !"".equals(FrmServicio.txtFechaServicio.getText()) && !"".equals(FrmServicio.txtIdPaciente.getText()) && !"".equals(FrmServicio.txtCargosServicio.getText()) && !"".equals(FrmServicio.txtIdServicios.getText())) {
                obtenerDatos();
                if (servicioDao.ActualizarServicio(servicio)) {
                    limpiar();
                    JOptionPane.showMessageDialog(null, "Servicio Actualizado");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al actualizar");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese Datos");
            }
        }
        if(e.getSource() == FrmServicio.btnBorrar){
            if(!"".equals(FrmServicio.txtIdServicios.getText())){
                obtenerDatos();
                if(servicioDao.BorrarServicio(servicio.getServiceId())){
                    limpiar();
                    JOptionPane.showMessageDialog(null, "Servicio Eliminado");
                }else{
                    JOptionPane.showMessageDialog(null, "Error al eliminar");
                }
            }else{
                JOptionPane.showMessageDialog(null,"Ingrese ID de servicio");
            }
        }
        if(e.getSource() == FrmServicio.btnNuevo){
            limpiar();
        }
        if(e.getSource() == FrmServicio.btnObtenerDatos){
            FrmServicio.dispose();
            FrmListaServicio.setVisible(true);
            FrmListaServicio.setResizable(false);
            FrmListaServicio.setLocationRelativeTo(null);
            FrmListaServicio.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            limpiarTable();
            listarServicio();            
        }
    }

    public void obtenerDatos() {
        servicio.setServiceName(FrmServicio.txtNombreServicio.getText());
        servicio.setServiceDate(FrmServicio.txtFechaServicio.getText());
        servicio.setPatientId(Integer.parseInt(FrmServicio.txtIdPaciente.getText()));
        servicio.setServiceCharges(Integer.parseInt(FrmServicio.txtCargosServicio.getText()));
        servicio.setServiceId(Integer.parseInt(FrmServicio.txtIdServicios.getText()));
    }

    public void limpiar() {
        FrmServicio.txtCargosServicio.setText("");
        FrmServicio.txtFechaServicio.setText("");
        FrmServicio.txtIdPaciente.setText("");
        FrmServicio.txtIdServicios.setText("");
        FrmServicio.txtNombrePaciente.setText("");
        FrmServicio.txtNombreServicio.setText("");
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == FrmListaServicio.TableServicio){
            int fila= FrmListaServicio.TableServicio.rowAtPoint(e.getPoint());
            FrmServicio.txtNombreServicio.setText(modelo.getValueAt(fila, 0).toString());
            FrmServicio.txtFechaServicio.setText(modelo.getValueAt(fila, 1).toString());
            FrmServicio.txtIdPaciente.setText(modelo.getValueAt(fila, 2).toString());
            FrmServicio.txtNombrePaciente.setText(modelo.getValueAt(fila, 3).toString());
            FrmServicio.txtCargosServicio.setText(modelo.getValueAt(fila, 4).toString());
            FrmServicio.txtIdServicios.setText(modelo.getValueAt(fila, 5).toString());
            FrmListaServicio.dispose();
            FrmServicio.setVisible(true);
            FrmServicio.setLocationRelativeTo(null);
            FrmServicio.setResizable(false);
            FrmServicio.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        }
        if(e.getSource() == FrmServicio.TablePaciente){
            int fila=FrmServicio.TablePaciente.rowAtPoint(e.getPoint());
            FrmServicio.txtIdPaciente.setText(model.getValueAt(fila, 0).toString());
            FrmServicio.txtNombrePaciente.setText(model.getValueAt(fila, 1).toString());
        }
        if(e.getSource() == FrmServicio.lblMinimiza){
            FrmServicio.setExtendedState(ICONIFIED);
        }
        if(e.getSource() == FrmServicio.lblCerrar){
            FrmServicio.dispose();
        }
        if(e.getSource() == FrmListaServicio.lblCerrar){
            FrmListaServicio.dispose();
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

    private void limpiarTable() {
        for(int i=0;i<modelo.getRowCount();i++){
            modelo.removeRow(i);
            i=i-1;
        }
    }

    private void listarServicio() {
        List<Servicio> ListaServicio= servicioDao.ListarServicio();
        modelo=(DefaultTableModel) FrmListaServicio.TableServicio.getModel();
        Object[] ob = new Object[6];
        for(int i=0;i < ListaServicio.size();i++){
            ob[0]=ListaServicio.get(i).getServiceName();
            ob[1]=ListaServicio.get(i).getServiceDate();
            ob[2]=ListaServicio.get(i).getPatientId();
            ob[3]=ListaServicio.get(i).getPatientName();
            ob[4]=ListaServicio.get(i).getServiceCharges();
            ob[5]=ListaServicio.get(i).getServiceId();
            modelo.addRow(ob);
        }
        FrmListaServicio.TableServicio.setModel(modelo);
    }
    
    private void listarPaciente(){
        List<Paciente> ListaPaciente = servicioDao.ListarPaciente();
        model=(DefaultTableModel) FrmServicio.TablePaciente.getModel();
        Object[] ob = new Object[2];
        for (int i = 0; i < ListaPaciente.size(); i++) {
            ob[0]=ListaPaciente.get(i).getPatientID();
            ob[1]=ListaPaciente.get(i).getPatientName();
            model.addRow(ob);
        }
        FrmServicio.TablePaciente.setModel(model);
    }
    
    private void cleanTable(){
        for (int i = 0; i<model.getRowCount(); i++) {
            model.removeRow(i);
            i=i-1;
        }
    }   
}
