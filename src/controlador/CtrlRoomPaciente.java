
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
import modelo.Doctor;
import modelo.Paciente;
import modelo.PacienteAdmitido;
import modelo.PacienteAdmitidoDao;
import vista.InfoPacienteAdmitido;
import vista.ListaPacienteAdmitido;

/**
 *
 * @author Brian54
 */
public class CtrlRoomPaciente implements ActionListener,MouseListener {
    private InfoPacienteAdmitido frmPacienteAdmitido = new InfoPacienteAdmitido();
    private PacienteAdmitido pacienteAdmitido = new PacienteAdmitido();
    private PacienteAdmitidoDao pacienteAdmitidoDao = new PacienteAdmitidoDao();
    private ListaPacienteAdmitido frmListPacienteAdmitido = new ListaPacienteAdmitido();
    private DefaultTableModel modelA = new DefaultTableModel();
    private DefaultTableModel modelB = new DefaultTableModel();
    private DefaultTableModel modelC = new DefaultTableModel();
    
    public CtrlRoomPaciente(InfoPacienteAdmitido frmPacienteAdmitido){
        this.frmPacienteAdmitido=frmPacienteAdmitido;
        this.frmPacienteAdmitido.btnGuardar.addActionListener(this);
        this.frmPacienteAdmitido.btnActualizar.addActionListener(this);
        this.frmPacienteAdmitido.btnBorrar.addActionListener(this);
        this.frmPacienteAdmitido.btnObtenerDatos.addActionListener(this);
        this.frmPacienteAdmitido.btnNuevo.addActionListener(this);
        this.frmPacienteAdmitido.lblCerrar.addMouseListener(this);
        this.frmPacienteAdmitido.lblMinimiza.addMouseListener(this);
        this.frmListPacienteAdmitido.lblCerrar.addMouseListener(this);
        this.frmPacienteAdmitido.TableDoctor.addMouseListener(this);
        this.frmPacienteAdmitido.TablePaciente.addMouseListener(this);
        this.frmListPacienteAdmitido.TablePacienteAdmitido.addMouseListener(this);
    }
    
    public void mostrar(){
        frmPacienteAdmitido.setVisible(true);
        frmPacienteAdmitido.setLocationRelativeTo(null);
        frmPacienteAdmitido.setResizable(false);
        frmPacienteAdmitido.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        limpiarDoctor();
        limpiarPaciente();
        listarDoctor();
        listarPaciente();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == frmPacienteAdmitido.btnGuardar){
            if(!"".equals(frmPacienteAdmitido.txtIdPaciente.getText()) && !"".equals(frmPacienteAdmitido.txtFechaAdmin.getText()) && !"".equals(frmPacienteAdmitido.txtEnfermedadPaciente.getText()) && !"".equals(frmPacienteAdmitido.txtDoctorID.getText()) && !"".equals(frmPacienteAdmitido.txtDoctorName.getText())){
                obtenerDatos();
                if(pacienteAdmitidoDao.VerificarRoom(Integer.parseInt(frmPacienteAdmitido.cbxNoRoom.getSelectedItem().toString()))){
                    if(pacienteAdmitidoDao.RegitrarPacienteA(pacienteAdmitido)){
                       limpiar();
                       JOptionPane.showMessageDialog(null,"Paciente Registrado en Habitación"); 
                       pacienteAdmitidoDao.ActulizarRoom(pacienteAdmitido);
                    } else {
                       JOptionPane.showMessageDialog(null,"Error al Registrar"); 
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"Habitacion Ocupada");
                }         
            } else {
                JOptionPane.showMessageDialog(null,"Rellenar Datos");
            }
        }
        if(e.getSource() == frmPacienteAdmitido.btnActualizar){
            if(!"".equals(frmPacienteAdmitido.txtIdPaciente.getText()) && !"".equals(frmPacienteAdmitido.txtFechaAdmin.getText()) && !"".equals(frmPacienteAdmitido.txtEnfermedadPaciente.getText()) && !"".equals(frmPacienteAdmitido.txtDoctorID.getText()) && !"".equals(frmPacienteAdmitido.txtDoctorName.getText())){
                obtenerDatos();
                if(pacienteAdmitidoDao.VerificarRoom(Integer.parseInt(frmPacienteAdmitido.cbxNoRoom.getSelectedItem().toString()))){
                    if(pacienteAdmitidoDao.ActualizarPacienteA(pacienteAdmitido)){
                       limpiar();
                       JOptionPane.showMessageDialog(null,"Paciente Actualizado correctamente"); 
                       pacienteAdmitidoDao.ActulizarRoom(pacienteAdmitido);
                    } else {
                       JOptionPane.showMessageDialog(null,"Error al Registrar"); 
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"Habitacion Ocupada");
                }      
            } else {
                JOptionPane.showMessageDialog(null,"Rellenar Datos");
            }            
        }
        if(e.getSource() == frmPacienteAdmitido.btnBorrar){
            if(!"".equals(frmPacienteAdmitido.txtIdPaciente.getText())){
                obtenerDatos();
                if(pacienteAdmitidoDao.BorrarPacienteA(Integer.parseInt(frmPacienteAdmitido.txtIdPaciente.getText()))){
                    limpiar();
                    JOptionPane.showMessageDialog(null,"Paciente Eliminado de Habitación");
                    pacienteAdmitidoDao.ActualizarRoomV(pacienteAdmitido);
                }else{
                    JOptionPane.showMessageDialog(null,"Error al borrar");
                }
            }else{
               JOptionPane.showMessageDialog(null,"Rellenar campo ID Paciente"); 
            }
        }
        if(e.getSource() == frmPacienteAdmitido.btnNuevo){
            limpiar();
        }
        if(e.getSource() == frmPacienteAdmitido.btnObtenerDatos){
            frmPacienteAdmitido.dispose();
            frmListPacienteAdmitido.setVisible(true);
            frmListPacienteAdmitido.setLocationRelativeTo(null);
            frmListPacienteAdmitido.setResizable(false);
            frmListPacienteAdmitido.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            limpiarPacienteA();
            listarPacienteA();
        }
    }
    
    public void limpiar(){
        frmPacienteAdmitido.txtDoctorID.setText("");
        frmPacienteAdmitido.txtDoctorName.setText("");
        frmPacienteAdmitido.txtEnfermedadPaciente.setText("");
        frmPacienteAdmitido.txtFechaAdmin.setText("");
        frmPacienteAdmitido.txtGeneroPaciente.setText("");
        frmPacienteAdmitido.txtGrupoSanguineoPaciente.setText("");
        frmPacienteAdmitido.txtIdPaciente.setText("");
        frmPacienteAdmitido.txtNamePaciente.setText("");
        frmPacienteAdmitido.txtObservacion.setText("");
    }
    
    public void obtenerDatos(){
        pacienteAdmitido.setPatientID(Integer.parseInt(frmPacienteAdmitido.txtIdPaciente.getText()));
        pacienteAdmitido.setDisease(frmPacienteAdmitido.txtEnfermedadPaciente.getText());
        pacienteAdmitido.setAdmintDate(frmPacienteAdmitido.txtFechaAdmin.getText());
        pacienteAdmitido.setRoomNo(Integer.parseInt(frmPacienteAdmitido.cbxNoRoom.getSelectedItem().toString()));
        pacienteAdmitido.setDoctorID(Integer.parseInt(frmPacienteAdmitido.txtDoctorID.getText()));
        pacienteAdmitido.setAp_Remarks(frmPacienteAdmitido.txtObservacion.getText());
    }
    
    public void limpiarPaciente(){
        for (int i = 0; i < modelA.getRowCount(); i++) {
            modelA.removeRow(i);
            i = i - 1;
        }
    }
    public void limpiarDoctor(){
        for (int i = 0; i < modelB.getRowCount(); i++) {
            modelB.removeRow(i);
            i = i - 1;
        }
    }
    
    public void limpiarPacienteA(){
        for (int i = 0; i < modelC.getRowCount(); i++) {
            modelC.removeRow(i);
            i = i - 1;
        }
    }
      
    public void listarDoctor(){
        List<Doctor> ListaDoctor = pacienteAdmitidoDao.ListarDoctor();
        modelB = (DefaultTableModel) frmPacienteAdmitido.TableDoctor.getModel();
        Object[] ob = new Object[2];
        for (int i = 0; i < ListaDoctor.size(); i++) {
            ob[0]=ListaDoctor.get(i).getDoctorID();
            ob[1]=ListaDoctor.get(i).getDoctorName();
            modelB.addRow(ob);
        }
        frmPacienteAdmitido.TableDoctor.setModel(modelB);
    }
    
    public void listarPaciente(){
        List<Paciente> ListaPaciente = pacienteAdmitidoDao.ListarPaciente();
        modelA = (DefaultTableModel) frmPacienteAdmitido.TablePaciente.getModel();
        Object[] ob = new Object[4];
        for (int i = 0; i < ListaPaciente.size(); i++) {
            ob[0] = ListaPaciente.get(i).getPatientID();
            ob[1] = ListaPaciente.get(i).getPatientName();
            ob[2] = ListaPaciente.get(i).getPatientGen();
            ob[3] = ListaPaciente.get(i).getPatientGrupoS();
            modelA.addRow(ob);
        }
        frmPacienteAdmitido.TablePaciente.setModel(modelA);
    }
    
    public void listarPacienteA(){
       List<PacienteAdmitido> ListaPacienteA = pacienteAdmitidoDao.ListarPacienteA();
       modelC = (DefaultTableModel) frmListPacienteAdmitido.TablePacienteAdmitido.getModel();
       Object[] ob = new Object[10];
        for (int i = 0; i < ListaPacienteA.size(); i++) {
            ob[0] = ListaPacienteA.get(i).getPatientID();
            ob[1] = ListaPacienteA.get(i).getPatientName();
            ob[2] = ListaPacienteA.get(i).getGen();
            ob[3] = ListaPacienteA.get(i).getBlood();
            ob[4] = ListaPacienteA.get(i).getDisease();
            ob[5] = ListaPacienteA.get(i).getAdmintDate();
            ob[6] = ListaPacienteA.get(i).getRoomNo();
            ob[7] = ListaPacienteA.get(i).getDoctorID();
            ob[8] = ListaPacienteA.get(i).getDoctorName();
            ob[9] = ListaPacienteA.get(i).getAp_Remarks();
            modelC.addRow(ob);
        }
       frmListPacienteAdmitido.TablePacienteAdmitido.setModel(modelC);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == frmPacienteAdmitido.TableDoctor){
            int fila = frmPacienteAdmitido.TableDoctor.rowAtPoint(e.getPoint());
            frmPacienteAdmitido.txtDoctorID.setText(modelB.getValueAt(fila, 0).toString());
            frmPacienteAdmitido.txtDoctorName.setText(modelB.getValueAt(fila, 1).toString());
        }
        if(e.getSource() == frmPacienteAdmitido.TablePaciente){
            int fila = frmPacienteAdmitido.TablePaciente.rowAtPoint(e.getPoint());
            frmPacienteAdmitido.txtIdPaciente.setText(modelA.getValueAt(fila, 0).toString());
            frmPacienteAdmitido.txtNamePaciente.setText(modelA.getValueAt(fila, 1).toString());
            frmPacienteAdmitido.txtGeneroPaciente.setText(modelA.getValueAt(fila, 2).toString());
            frmPacienteAdmitido.txtGrupoSanguineoPaciente.setText(modelA.getValueAt(fila, 3).toString());
        }
        if(e.getSource() == frmListPacienteAdmitido.TablePacienteAdmitido){
            int fila = frmListPacienteAdmitido.TablePacienteAdmitido.rowAtPoint(e.getPoint());
            frmPacienteAdmitido.txtIdPaciente.setText(modelC.getValueAt(fila, 0).toString());
            frmPacienteAdmitido.txtNamePaciente.setText(modelC.getValueAt(fila, 1).toString());
            frmPacienteAdmitido.txtGeneroPaciente.setText(modelC.getValueAt(fila, 2).toString());
            frmPacienteAdmitido.txtGrupoSanguineoPaciente.setText(modelC.getValueAt(fila, 3).toString());
            frmPacienteAdmitido.txtEnfermedadPaciente.setText(modelC.getValueAt(fila, 4).toString());
            frmPacienteAdmitido.txtFechaAdmin.setText(modelC.getValueAt(fila, 5).toString());
            frmPacienteAdmitido.cbxNoRoom.setSelectedItem(modelC.getValueAt(fila, 6).toString());
            frmPacienteAdmitido.txtDoctorID.setText(modelC.getValueAt(fila, 7).toString());
            frmPacienteAdmitido.txtDoctorName.setText(modelC.getValueAt(fila, 8).toString());
            frmPacienteAdmitido.txtObservacion.setText(modelC.getValueAt(fila, 9).toString());
            frmListPacienteAdmitido.dispose();
            frmPacienteAdmitido.setVisible(true);
            frmPacienteAdmitido.setLocationRelativeTo(null);
            frmPacienteAdmitido.setResizable(false);
            frmPacienteAdmitido.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        }
        if(e.getSource() == frmPacienteAdmitido.lblCerrar){
            frmPacienteAdmitido.dispose();
        }
        if(e.getSource() == frmPacienteAdmitido.lblMinimiza){
            frmPacienteAdmitido.setExtendedState(ICONIFIED);
        }
        if(e.getSource() == frmListPacienteAdmitido.lblCerrar){
            frmListPacienteAdmitido.dispose();
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
