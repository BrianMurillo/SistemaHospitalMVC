
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
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

    public CtrlServicio(Services FrmServicio) {
        this.FrmServicio = FrmServicio;
        this.FrmServicio.btnGuardar.addActionListener(this);
        this.FrmServicio.btnActualizar.addActionListener(this);
        this.FrmServicio.btnBorrar.addActionListener(this);
        this.FrmServicio.btnNuevo.addActionListener(this);
        this.FrmServicio.btnObtenerDatos.addActionListener(this);
        this.FrmListaServicio.TableServicio.addMouseListener(this);
        this.FrmServicio.TablePaciente.addMouseListener(this);
    }

    public void mostrar() {
        FrmServicio.setVisible(true);
        FrmServicio.setLocationRelativeTo(null);
        FrmServicio.setResizable(false);
        FrmServicio.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == FrmServicio.btnGuardar) {
            obtenerDatos();
            if (!"".equals(FrmServicio.txtNombreServicio.getText()) && !"".equals(FrmServicio.txtFechaServicio.getText()) && !"".equals(FrmServicio.txtIdPaciente.getText()) && !"".equals(FrmServicio.txtCargosServicio.getText()) && !"".equals(FrmServicio.txtIdServicios.getText())) {
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
            obtenerDatos();
            if (!"".equals(FrmServicio.txtNombreServicio.getText()) && !"".equals(FrmServicio.txtFechaServicio.getText()) && !"".equals(FrmServicio.txtIdPaciente.getText()) && !"".equals(FrmServicio.txtCargosServicio.getText()) && !"".equals(FrmServicio.txtIdServicios.getText())) {
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
            if("".equals(FrmServicio.txtIdServicios)){
                if(servicioDao.BorrarServicio(Integer.parseInt(FrmServicio.txtIdServicios.getText()))){
                    limpiar();
                    JOptionPane.showMessageDialog(null, "Servicio Eliminado");
                }else{
                    JOptionPane.showMessageDialog(null, "Error al eliminar");
                }
            }else{
                JOptionPane.showMessageDialog(null,"Ingrese ID de serivicio");
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
            listarPaciente();
            
        }
    }

    public void obtenerDatos() {
        servicio.setServiceName(FrmServicio.txtNombreServicio.getText());
        servicio.setServiceDate(FrmServicio.txtFechaServicio.getText());
        servicio.setPatientId(Integer.parseInt(FrmServicio.txtIdPaciente.getText()));
        servicio.setServiceCharges(Integer.parseInt(FrmServicio.txtFechaServicio.getText()));
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
            FrmServicio.txtCargosServicio.setText(modelo.getValueAt(fila, 3).toString());
            FrmServicio.txtIdServicios.setText(modelo.getValueAt(fila, 4).toString());
            FrmListaServicio.dispose();
            FrmServicio.setVisible(true);
            FrmServicio.setLocationRelativeTo(null);
            FrmServicio.setResizable(false);
            FrmServicio.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        }
        if(e.getSource() == FrmServicio.TablePaciente){
            
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

    private void listarPaciente() {
        List<Servicio> ListaServicio= servicioDao.ListarServicio();
        modelo=(DefaultTableModel) FrmListaServicio.TableServicio.getModel();
        Object[] ob = new Object[5];
        for(int i=0;i<ListaServicio.size();i++){
            ob[0]=ListaServicio.get(i).getServiceName();
            ob[1]=ListaServicio.get(i).getServiceDate();
            ob[2]=ListaServicio.get(i).getPatientId();
            ob[3]=ListaServicio.get(i).getServiceCharges();
            ob[4]=ListaServicio.get(i).getServiceId();
            modelo.addRow(ob);
        }
        FrmListaServicio.TableServicio.setModel(modelo);
    }
}
