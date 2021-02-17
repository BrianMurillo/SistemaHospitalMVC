
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
import modelo.AltaPaciente;
import modelo.AltaPacienteDao;
import modelo.PacienteAdmitido;
import modelo.PacienteAdmitidoDao;
import vista.AltaPacienteRoom;
import vista.ListaAltaPaciente;
import vista.ListaPacienteAdmitido;

/**
 *
 * @author Brian54
 */
public class CtrlAltaPaciente implements ActionListener,MouseListener {
    AltaPacienteRoom FrmAltaPacienteRoom = new AltaPacienteRoom();
    ListaAltaPaciente FrmListaAltaPaciente = new ListaAltaPaciente();
    ListaPacienteAdmitido FrmListaPacienteAdmitido = new ListaPacienteAdmitido();
    PacienteAdmitidoDao pacienteAdmitidoDao = new PacienteAdmitidoDao();
    AltaPaciente altaPaciente = new AltaPaciente();
    AltaPacienteDao altaPacienteDao = new AltaPacienteDao();
    DefaultTableModel model= new DefaultTableModel();
    DefaultTableModel modelC= new DefaultTableModel();
    
    public CtrlAltaPaciente(AltaPacienteRoom FrmAltaPacienteRoom){
        this.FrmAltaPacienteRoom = FrmAltaPacienteRoom;
        this.FrmAltaPacienteRoom.btnGuardar.addActionListener(this);
        this.FrmAltaPacienteRoom.btnActualizar.addActionListener(this);
        this.FrmAltaPacienteRoom.btnBorrar.addActionListener(this);
        this.FrmAltaPacienteRoom.btnNuevo.addActionListener(this);
        this.FrmAltaPacienteRoom.btnDatosPaciente.addActionListener(this);
        this.FrmAltaPacienteRoom.btnObtenerDatos.addActionListener(this);
        this.FrmAltaPacienteRoom.lblMinimiza.addMouseListener(this);
        this.FrmAltaPacienteRoom.lblCerrar.addMouseListener(this);
        this.FrmListaAltaPaciente.TableAltaPaciente.addMouseListener(this);
        this.FrmListaAltaPaciente.lblCerrar.addMouseListener(this);
        this.FrmListaPacienteAdmitido.TablePacienteAdmitido.addMouseListener(this);
        this.FrmListaPacienteAdmitido.lblCerrar.addMouseListener(this);
    }
    
    public void mostrar(){
        FrmAltaPacienteRoom.setVisible(true);
        FrmAltaPacienteRoom.setLocationRelativeTo(null);
        FrmAltaPacienteRoom.setResizable(false);
        FrmAltaPacienteRoom.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == FrmAltaPacienteRoom.btnGuardar){
            if(!"".equals(FrmAltaPacienteRoom.txtPatientID.getText()) && !"".equals(FrmAltaPacienteRoom.txtDischargeDate.getText()) && !"".equals(FrmAltaPacienteRoom.txtRemarks.getText())){
                obtenerDatos();
                if(altaPacienteDao.RegistrarAltaPaciente(altaPaciente)){
                     limpiar();
                     JOptionPane.showMessageDialog(null,"Alta de Paciente Registrada con exito");
                }else{
                     JOptionPane.showMessageDialog(null,"Alta de Paciente tuvo un Error");
                }
            } else {
                JOptionPane.showMessageDialog(null,"Ingrese Datos");
            }          
        }
        if(e.getSource() == FrmAltaPacienteRoom.btnActualizar){
           if(!"".equals(FrmAltaPacienteRoom.txtPatientID.getText()) && !"".equals(FrmAltaPacienteRoom.txtDischargeDate.getText()) && !"".equals(FrmAltaPacienteRoom.txtRemarks.getText())){
               obtenerDatos();
               if(altaPacienteDao.ActualizarAltaPaciente(altaPaciente)){
                   limpiar();
                   JOptionPane.showMessageDialog(null,"Alta de Paciente Actualizado con exito");
               } else{
                   JOptionPane.showMessageDialog(null,"Actualizacion Alta de Paciente tuvo un error");                   
               }
           } else {
                JOptionPane.showMessageDialog(null,"Ingrese Datos");               
           }
        }
        if(e.getSource() == FrmAltaPacienteRoom.btnBorrar){
            if(!"".equals(FrmAltaPacienteRoom.txtPatientID.getText())){
                if(altaPacienteDao.BorrarAltaPaciente(Integer.parseInt(FrmAltaPacienteRoom.txtPatientID.getText()))){
                    limpiar();
                    JOptionPane.showMessageDialog(null,"Alta de Paciente Borrado con exito");
                } else {
                    JOptionPane.showMessageDialog(null,"Error al Borrar Alta de Paciente");
                }
            }else{
                JOptionPane.showMessageDialog(null,"Ingrese Datos");
            }
        }
        if(e.getSource() == FrmAltaPacienteRoom.btnNuevo){
            limpiar();
        }
        if(e.getSource() == FrmAltaPacienteRoom.btnDatosPaciente){
            FrmAltaPacienteRoom.dispose();
            FrmListaPacienteAdmitido.setVisible(true);
            FrmListaPacienteAdmitido.setLocationRelativeTo(null);
            FrmListaPacienteAdmitido.setResizable(false);
            FrmListaPacienteAdmitido.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            limpiarPacienteA();
            listarPacienteA();
        }
        if(e.getSource() == FrmAltaPacienteRoom.btnObtenerDatos){
            FrmAltaPacienteRoom.dispose();
            FrmListaAltaPaciente.setVisible(true);
            FrmListaAltaPaciente.setLocationRelativeTo(null);
            FrmListaAltaPaciente.setResizable(false);
            FrmListaAltaPaciente.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            limpiarTable();
            listarTable();
        }
    }
    
    public void limpiarTable(){
        for (int i = 0; i < model.getRowCount(); i++) {
            model.removeRow(i);
            i=i-1;
        }
    }
    public void limpiarPacienteA(){
        for (int i = 0; i < modelC.getRowCount(); i++) {
            modelC.removeRow(i);
            i = i - 1;
        }
    }
    
    public void listarTable(){
        List<AltaPaciente> ListaAltaPaciente = altaPacienteDao.ListarAltaPaciente();
        model = (DefaultTableModel) FrmListaAltaPaciente.TableAltaPaciente.getModel();
        Object[] ob = new Object[11];
        for (int i = 0; i < ListaAltaPaciente.size(); i++) {
            ob[0]=ListaAltaPaciente.get(i).getPatientID();
            ob[1]=ListaAltaPaciente.get(i).getPatientName();
            ob[2]=ListaAltaPaciente.get(i).getGender();
            ob[3]=ListaAltaPaciente.get(i).getGroupBlood();
            ob[4]=ListaAltaPaciente.get(i).getDisease();
            ob[5]=ListaAltaPaciente.get(i).getAdmitDate();
            ob[6]=ListaAltaPaciente.get(i).getNoRoom();
            ob[7]=ListaAltaPaciente.get(i).getIdDoctor();
            ob[8]=ListaAltaPaciente.get(i).getNameDoctor();
            ob[9]=ListaAltaPaciente.get(i).getDateClose();
            ob[10]=ListaAltaPaciente.get(i).getObservation();
            model.addRow(ob);
        }
        FrmListaAltaPaciente.TableAltaPaciente.setModel(model);
    }
    
    public void listarPacienteA(){
       List<PacienteAdmitido> ListaPacienteA = pacienteAdmitidoDao.ListarPacienteA();
       modelC = (DefaultTableModel) FrmListaPacienteAdmitido.TablePacienteAdmitido.getModel();
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
       FrmListaPacienteAdmitido.TablePacienteAdmitido.setModel(modelC);
    }
    
    public void obtenerDatos(){
        altaPaciente.setAdmitDate(FrmAltaPacienteRoom.txtAdmitDate.getText());
        altaPaciente.setDateClose(FrmAltaPacienteRoom.txtDischargeDate.getText());
        altaPaciente.setDisease(FrmAltaPacienteRoom.txtDisease.getText());
        altaPaciente.setGender(FrmAltaPacienteRoom.txtGender.getText());
        altaPaciente.setGroupBlood(FrmAltaPacienteRoom.txtBloodGroup.getText());
        altaPaciente.setIdDoctor(Integer.parseInt(FrmAltaPacienteRoom.txtDoctorID.getText()));
        altaPaciente.setNameDoctor(FrmAltaPacienteRoom.txtDoctorName.getText());
        altaPaciente.setNoRoom(Integer.parseInt(FrmAltaPacienteRoom.txtRoomNo.getText()));
        altaPaciente.setObservation(FrmAltaPacienteRoom.txtRemarks.getText());
        altaPaciente.setPatientID(Integer.parseInt(FrmAltaPacienteRoom.txtPatientID.getText()));
        altaPaciente.setPatientName(FrmAltaPacienteRoom.txtPatientName.getText());
    }
    
    public void limpiar(){
        FrmAltaPacienteRoom.txtAdmitDate.setText("");
        FrmAltaPacienteRoom.txtBloodGroup.setText("");
        FrmAltaPacienteRoom.txtDischargeDate.setText("");
        FrmAltaPacienteRoom.txtDisease.setText("");
        FrmAltaPacienteRoom.txtDoctorID.setText("");
        FrmAltaPacienteRoom.txtDoctorName.setText("");
        FrmAltaPacienteRoom.txtGender.setText("");
        FrmAltaPacienteRoom.txtPatientID.setText("");
        FrmAltaPacienteRoom.txtPatientName.setText("");
        FrmAltaPacienteRoom.txtRemarks.setText("");
        FrmAltaPacienteRoom.txtRoomNo.setText("");
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == FrmListaAltaPaciente.TableAltaPaciente){
            int fila = FrmListaAltaPaciente.TableAltaPaciente.rowAtPoint(e.getPoint());
            FrmAltaPacienteRoom.txtPatientID.setText(model.getValueAt(fila, 0).toString());
            FrmAltaPacienteRoom.txtPatientName.setText(model.getValueAt(fila, 1).toString());
            FrmAltaPacienteRoom.txtGender.setText(model.getValueAt(fila, 2).toString());
            FrmAltaPacienteRoom.txtBloodGroup.setText(model.getValueAt(fila, 3).toString());
            FrmAltaPacienteRoom.txtDisease.setText(model.getValueAt(fila, 4).toString());
            FrmAltaPacienteRoom.txtAdmitDate.setText(model.getValueAt(fila, 5).toString());
            FrmAltaPacienteRoom.txtRoomNo.setText(model.getValueAt(fila, 6).toString());
            FrmAltaPacienteRoom.txtDoctorID.setText(model.getValueAt(fila, 7).toString());
            FrmAltaPacienteRoom.txtDoctorName.setText(model.getValueAt(fila, 8).toString());
            FrmAltaPacienteRoom.txtDischargeDate.setText(model.getValueAt(fila, 9).toString());
            FrmAltaPacienteRoom.txtRemarks.setText(model.getValueAt(fila, 10).toString());
            FrmListaAltaPaciente.dispose();
            FrmAltaPacienteRoom.setVisible(true);
            FrmAltaPacienteRoom.setLocationRelativeTo(null);
            FrmAltaPacienteRoom.setResizable(false);
            FrmAltaPacienteRoom.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        }
        if(e.getSource() == FrmListaPacienteAdmitido.TablePacienteAdmitido){
            int fila= FrmListaPacienteAdmitido.TablePacienteAdmitido.rowAtPoint(e.getPoint());
            FrmAltaPacienteRoom.txtPatientID.setText(modelC.getValueAt(fila, 0).toString());
            FrmAltaPacienteRoom.txtPatientName.setText(modelC.getValueAt(fila, 1).toString());
            FrmAltaPacienteRoom.txtGender.setText(modelC.getValueAt(fila, 2).toString());
            FrmAltaPacienteRoom.txtBloodGroup.setText(modelC.getValueAt(fila, 3).toString());
            FrmAltaPacienteRoom.txtDisease.setText(modelC.getValueAt(fila, 4).toString());
            FrmAltaPacienteRoom.txtAdmitDate.setText(modelC.getValueAt(fila, 5).toString());
            FrmAltaPacienteRoom.txtRoomNo.setText(modelC.getValueAt(fila, 6).toString());
            FrmAltaPacienteRoom.txtDoctorID.setText(modelC.getValueAt(fila, 7).toString());
            FrmAltaPacienteRoom.txtDoctorName.setText(modelC.getValueAt(fila, 8).toString());
            FrmListaPacienteAdmitido.dispose();
            FrmAltaPacienteRoom.setVisible(true);
            FrmAltaPacienteRoom.setLocationRelativeTo(null);
            FrmAltaPacienteRoom.setResizable(false);
            FrmAltaPacienteRoom.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        }
        if(e.getSource() == FrmListaPacienteAdmitido.lblCerrar){
            FrmListaPacienteAdmitido.dispose();
        }
        if(e.getSource() == FrmAltaPacienteRoom.lblCerrar){
            FrmAltaPacienteRoom.dispose();
        }
        if(e.getSource() == FrmAltaPacienteRoom.lblMinimiza){
            FrmAltaPacienteRoom.setExtendedState(ICONIFIED);
        }
        if(e.getSource() == FrmListaAltaPaciente.lblCerrar){
            FrmListaAltaPaciente.dispose();
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
