
package controlador;

import static java.awt.Frame.ICONIFIED;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import modelo.Conexion;
import modelo.FacturaPaciente;
import modelo.FacturaPacienteDao;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import vista.FacturaRoom;
import vista.ListaFacturaPaciente;
import vista.ListaPacienteFac;

/**
 *
 * @author Brian54
 */
public class CtrlFacturaPaciente implements ActionListener,MouseListener {
    private FacturaRoom frmFacturaRoom = new FacturaRoom();
    private ListaPacienteFac frmListaPacienteFac = new ListaPacienteFac();
    private ListaFacturaPaciente frmListaFacturaPaciente = new ListaFacturaPaciente();
    private FacturaPaciente facturaPaciente = new FacturaPaciente();
    private FacturaPacienteDao facturaPacienteDao = new FacturaPacienteDao();
    private DefaultTableModel modeloA = new DefaultTableModel();
    private DefaultTableModel modeloB = new DefaultTableModel();
    private DefaultTableModel modeloC = new DefaultTableModel();
    
    public CtrlFacturaPaciente(FacturaRoom FrmFacturaRoom){
        this.frmFacturaRoom = FrmFacturaRoom;
        this.frmFacturaRoom.btnGuardar.addActionListener(this);
        this.frmFacturaRoom.btnActualizar.addActionListener(this);
        this.frmFacturaRoom.btnBorrar.addActionListener(this);
        this.frmFacturaRoom.btnNuevo.addActionListener(this);
        this.frmFacturaRoom.btnObtenerDatos.addActionListener(this);
        this.frmFacturaRoom.btnListarPaciente.addActionListener(this);
        this.frmFacturaRoom.btnPdf.addActionListener(this);
        this.frmFacturaRoom.lblCerrar.addMouseListener(this);
        this.frmFacturaRoom.lblMinimiza.addMouseListener(this);
        this.frmListaFacturaPaciente.lblCerrar.addMouseListener(this);
        this.frmListaPacienteFac.lblCerrar.addMouseListener(this);
        this.frmFacturaRoom.TableFacturaRoom.addMouseListener(this);
        this.frmListaPacienteFac.TablePacienteFact.addMouseListener(this);
        this.frmListaFacturaPaciente.TableFacturaPaciente.addMouseListener(this);
    }
    
    public void mostrar(){
        frmFacturaRoom.setVisible(true);
        frmFacturaRoom.setLocationRelativeTo(null);
        frmFacturaRoom.setResizable(false);
        frmFacturaRoom.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        limpiarTablaPaciente();
        listarTablaPaciente();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == frmFacturaRoom.btnGuardar){
            obtenerDatos();
            if(!"".equals(frmFacturaRoom.txtRoomCharges.getText()) && !"".equals(frmFacturaRoom.txtIdPatient.getText()) && !"".equals(frmFacturaRoom.txtBillingDate.getText()) && !"".equals(frmFacturaRoom.txtPaymentModeDetails.getText()) && !"".equals(frmFacturaRoom.txtBillNo.getText())){
                if(facturaPacienteDao.RegistrarAltaPaciente(facturaPaciente)){
                    limpiar();
                    JOptionPane.showMessageDialog(null,"Factura Paciente Guardada");
                }else{
                    JOptionPane.showMessageDialog(null,"Error al Guaradar Factura Paciente");
                }
            }else{
                    JOptionPane.showMessageDialog(null,"Llenar Datos");
            }
        }
        if(e.getSource() == frmFacturaRoom.btnActualizar){
            obtenerDatos();
            if(!"".equals(frmFacturaRoom.txtRoomCharges.getText()) && !"".equals(frmFacturaRoom.txtIdPatient.getText()) && !"".equals(frmFacturaRoom.txtBillingDate.getText()) && !"".equals(frmFacturaRoom.txtPaymentModeDetails.getText()) && !"".equals(frmFacturaRoom.txtBillNo.getText())){
                if(facturaPacienteDao.ActualizarAltaPaciente(facturaPaciente)){
                    limpiar();
                    JOptionPane.showMessageDialog(null,"Factura Paciente Actualizada");
                }else{
                    JOptionPane.showMessageDialog(null,"Error al Actualizar");
                }
            }else{
                    JOptionPane.showMessageDialog(null,"Llenar Datos");
            }
        }
        if(e.getSource() == frmFacturaRoom.btnBorrar){
            if(!"".equals(frmFacturaRoom.txtIdPatient.getText())){
                if(facturaPacienteDao.EliminarAltaPaciente(Integer.parseInt(frmFacturaRoom.txtIdPatient.getText()))){
                    limpiar();
                    JOptionPane.showMessageDialog(null, "Factura Paciente Borrada con exito");
                }else{
                    JOptionPane.showMessageDialog(null, "Error al borrar Factura Paciente");
                }
            }else{
               JOptionPane.showMessageDialog(null, "Llenar Datos");
            }
        }
        if(e.getSource() == frmFacturaRoom.btnNuevo){
         limpiar();
        }
        if(e.getSource() == frmFacturaRoom.btnListarPaciente){
            frmFacturaRoom.dispose();
            frmListaPacienteFac.setVisible(true);
            frmListaPacienteFac.setResizable(false);
            frmListaPacienteFac.setLocationRelativeTo(null);
            frmListaPacienteFac.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            limpiarTablaPacienteDatos();
            listarTablaPacienteDatos();
            
        }
        if(e.getSource() == frmFacturaRoom.btnObtenerDatos){
            frmFacturaRoom.dispose();
            frmListaFacturaPaciente.setVisible(true);
            frmListaFacturaPaciente.setResizable(false);
            frmListaFacturaPaciente.setLocationRelativeTo(null);
            frmListaFacturaPaciente.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            limpiarTablaPacienteFactura();
            listarTablaPacienteFactura();
        }
        if(e.getSource() == frmFacturaRoom.btnPdf){
            if(!"".equals(frmFacturaRoom.txtIdPatient.getText())){
            JasperReport reporte = null;
            String path = "src\\reportes\\Factura.jasper";
            
            Map parametro=new HashMap();
            parametro.put("PacienteID",Integer.parseInt(frmFacturaRoom.txtIdPatient.getText()));
            
            try {
                Conexion con = new Conexion();
                Connection coon=con.getConnection();
                reporte = (JasperReport) JRLoader.loadObjectFromFile(path);
                JasperPrint jprint = JasperFillManager.fillReport(reporte,parametro,coon);
                
                JasperViewer view = new JasperViewer(jprint,false);
                
                view.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                view.setVisible(true);
            } catch (JRException ex) {
                Logger.getLogger(CtrlFacturaPaciente.class.getName()).log(Level.SEVERE, null, ex);
            }
            }else{
                JOptionPane.showMessageDialog(null, "Ingrese el Id del Paciente");
            }
        }
    }
    
    public void limpiar(){
        frmFacturaRoom.txtAdmitDate.setText("");
        frmFacturaRoom.txtBillNo.setText("");
        frmFacturaRoom.txtBillingDate.setText("");
        frmFacturaRoom.txtBloodGroup.setText("");
        frmFacturaRoom.txtDischargeDate.setText("");
        frmFacturaRoom.txtDisease.setText("");
        frmFacturaRoom.txtDoctorID.setText("");
        frmFacturaRoom.txtDoctorName.setText("");
        frmFacturaRoom.txtGender.setText("");
        frmFacturaRoom.txtIdPatient.setText("");
        frmFacturaRoom.txtNoDayRoom.setText("");
        frmFacturaRoom.txtPatientName.setText("");
        frmFacturaRoom.txtPaymentModeDetails.setText("");
        frmFacturaRoom.txtRoomCharges.setText("");
        frmFacturaRoom.txtRoomNo.setText("");
        frmFacturaRoom.txtServiceCharges.setText("");
        frmFacturaRoom.txtTotalCharges.setText("");
        frmFacturaRoom.txtTotalRoomCharges.setText("");
    }
    public void limpiarTablaPaciente(){
        for (int i = 0; i < modeloA.getRowCount(); i++) {
            modeloA.removeRow(i);
            i=i-1;
        }
    }
    public void limpiarTablaPacienteDatos(){
        for (int i = 0; i < modeloB.getRowCount(); i++) {
            modeloB.removeRow(i);
            i=i-1;
        }
    }
    public void limpiarTablaPacienteFactura(){
        for (int i = 0; i < modeloC.getRowCount(); i++) {
            modeloC.removeRow(i);
            i=i-1;
        }
    }
     
    public void listarTablaPaciente(){
        List<FacturaPaciente> ListaPaciente = facturaPacienteDao.ListarPaciente();
        modeloA = (DefaultTableModel) frmFacturaRoom.TableFacturaRoom.getModel();
        Object[] ob = new Object[3];
        for (int i = 0; i < ListaPaciente.size(); i++) {
            ob[0] = ListaPaciente.get(i).getIdPaciente();
            ob[1] = ListaPaciente.get(i).getNamePaciente();
            ob[2] = ListaPaciente.get(i).getChargesService();
            modeloA.addRow(ob);
        }
        frmFacturaRoom.TableFacturaRoom.setModel(modeloA);
    }
    
    public void listarTablaPacienteDatos(){
        List<FacturaPaciente> ListaPacienteDatos = facturaPacienteDao.ListarPacienteFactura();
        modeloB = (DefaultTableModel) frmListaPacienteFac.TablePacienteFact.getModel();
        Object[] ob = new Object[12];
        for (int i = 0; i < ListaPacienteDatos.size(); i++) {
            ob[0] = ListaPacienteDatos.get(i).getIdPaciente();
            ob[1] = ListaPacienteDatos.get(i).getNamePaciente();
            ob[2] = ListaPacienteDatos.get(i).getGender();
            ob[3] = ListaPacienteDatos.get(i).getBloodGroup();
            ob[4] = ListaPacienteDatos.get(i).getDisease();
            ob[5] = ListaPacienteDatos.get(i).getAdmitDate();
            ob[6] = ListaPacienteDatos.get(i).getNoRoom();
            ob[7] = ListaPacienteDatos.get(i).getChargesRoom();
            ob[8] = ListaPacienteDatos.get(i).getIdDoctor();
            ob[9] = ListaPacienteDatos.get(i).getNameDoctor();
            ob[10] = ListaPacienteDatos.get(i).getCloseDate();
            ob[11] = ListaPacienteDatos.get(i).getRemarks();
            modeloB.addRow(ob);
        }
        frmListaPacienteFac.TablePacienteFact.setModel(modeloB);
    }
    
    public void listarTablaPacienteFactura(){
        List<FacturaPaciente> ListaPacienteDatos= facturaPacienteDao.ListarFacturaPaciente();
        modeloC = (DefaultTableModel) frmListaFacturaPaciente.TableFacturaPaciente.getModel();
        Object[] ob = new Object[19];
        for (int i = 0; i < ListaPacienteDatos.size(); i++) {
            ob[0] = ListaPacienteDatos.get(i).getNoBill();
            ob[1] = ListaPacienteDatos.get(i).getIdPaciente();
            ob[2] = ListaPacienteDatos.get(i).getNamePaciente();
            ob[3] = ListaPacienteDatos.get(i).getGender();
            ob[4] = ListaPacienteDatos.get(i).getBloodGroup();
            ob[5] = ListaPacienteDatos.get(i).getDisease();
            ob[6] = ListaPacienteDatos.get(i).getAdmitDate();
            ob[7] = ListaPacienteDatos.get(i).getNoRoom();     
            ob[8] = ListaPacienteDatos.get(i).getIdDoctor();
            ob[9] = ListaPacienteDatos.get(i).getNameDoctor();
            ob[10] = ListaPacienteDatos.get(i).getCloseDate();
            ob[11] = ListaPacienteDatos.get(i).getChargesRoom();
            ob[12] = ListaPacienteDatos.get(i).getChargesService();
            ob[13] = ListaPacienteDatos.get(i).getDateBill();
            ob[14] = ListaPacienteDatos.get(i).getModePay();
            ob[15] = ListaPacienteDatos.get(i).getDetailsModePay();
            ob[16] = ListaPacienteDatos.get(i).getTotalCharges();
            ob[17] = ListaPacienteDatos.get(i).getNoDays();
            ob[18] = ListaPacienteDatos.get(i).getTotalChargesRoom();   
            modeloC.addRow(ob);
        }
        frmListaFacturaPaciente.TableFacturaPaciente.setModel(modeloC);
    }
    
    public void obtenerDatos(){
        facturaPaciente.setIdPaciente(Integer.parseInt(frmFacturaRoom.txtIdPatient.getText()));
        facturaPaciente.setNamePaciente(frmFacturaRoom.txtPatientName.getText());
        facturaPaciente.setGender(frmFacturaRoom.txtGender.getText());
        facturaPaciente.setBloodGroup(frmFacturaRoom.txtBloodGroup.getText());
        facturaPaciente.setDisease(frmFacturaRoom.txtDisease.getText());
        facturaPaciente.setAdmitDate(frmFacturaRoom.txtAdmitDate.getText());
        facturaPaciente.setNoRoom(Integer.parseInt(frmFacturaRoom.txtRoomNo.getText()));
        facturaPaciente.setIdDoctor(Integer.parseInt(frmFacturaRoom.txtDoctorID.getText()));
        facturaPaciente.setNameDoctor(frmFacturaRoom.txtDoctorName.getText());
        facturaPaciente.setCloseDate(frmFacturaRoom.txtDischargeDate.getText());
        facturaPaciente.setDateBill(frmFacturaRoom.txtBillingDate.getText());
        facturaPaciente.setChargesRoom(Integer.parseInt(frmFacturaRoom.txtRoomCharges.getText()));
        facturaPaciente.setChargesService(Integer.parseInt(frmFacturaRoom.txtServiceCharges.getText()));
        facturaPaciente.setModePay(frmFacturaRoom.cbxPaymentMode.getSelectedItem().toString());
        facturaPaciente.setDetailsModePay(frmFacturaRoom.txtPaymentModeDetails.getText());
        facturaPaciente.setNoDays(Integer.parseInt(frmFacturaRoom.txtNoDayRoom.getText()));
        facturaPaciente.setTotalCharges(Integer.parseInt(frmFacturaRoom.txtTotalCharges.getText()));
        facturaPaciente.setTotalChargesRoom(Integer.parseInt(frmFacturaRoom.txtTotalRoomCharges.getText()));
        facturaPaciente.setNoBill(Integer.parseInt(frmFacturaRoom.txtBillNo.getText()));
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == frmFacturaRoom.TableFacturaRoom){
            int fila=frmFacturaRoom.TableFacturaRoom.rowAtPoint(e.getPoint());
            frmFacturaRoom.txtServiceCharges.setText(modeloA.getValueAt(fila, 2).toString());
            int chargesServices = Integer.parseInt(frmFacturaRoom.txtServiceCharges.getText());
            int chargesRoom= Integer.parseInt(frmFacturaRoom.txtTotalRoomCharges.getText());
            int totalCharges=chargesServices+chargesRoom;
            frmFacturaRoom.txtTotalCharges.setText(Integer.toString(totalCharges));
        }
        if(e.getSource() == frmListaPacienteFac.TablePacienteFact){
            int fila=frmListaPacienteFac.TablePacienteFact.rowAtPoint(e.getPoint());
            frmFacturaRoom.txtIdPatient.setText(modeloB.getValueAt(fila, 0).toString());
            frmFacturaRoom.txtPatientName.setText(modeloB.getValueAt(fila, 1).toString());
            frmFacturaRoom.txtGender.setText(modeloB.getValueAt(fila, 2).toString());
            frmFacturaRoom.txtBloodGroup.setText(modeloB.getValueAt(fila, 3).toString());
            frmFacturaRoom.txtDisease.setText(modeloB.getValueAt(fila, 4).toString());
            frmFacturaRoom.txtAdmitDate.setText(modeloB.getValueAt(fila, 5).toString());
            frmFacturaRoom.txtRoomNo.setText(modeloB.getValueAt(fila, 6).toString());
            frmFacturaRoom.txtRoomCharges.setText(modeloB.getValueAt(fila, 7).toString());
            frmFacturaRoom.txtDoctorID.setText(modeloB.getValueAt(fila, 8).toString());
            frmFacturaRoom.txtDoctorName.setText(modeloB.getValueAt(fila, 9).toString());
            frmFacturaRoom.txtDischargeDate.setText(modeloB.getValueAt(fila, 10).toString());
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            String dateEnd=frmFacturaRoom.txtDischargeDate.getText();
            String dateStar=frmFacturaRoom.txtAdmitDate.getText();
            int roomCharges=Integer.parseInt(frmFacturaRoom.txtRoomCharges.getText());
            try {  
                //Number days
                Date endDate= sdf.parse(dateEnd);
                Date startDate= sdf.parse(dateStar);
                long diffInMillies = Math.abs(endDate.getTime() - startDate.getTime());
                long numOfDays = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
                frmFacturaRoom.txtNoDayRoom.setText(Integer.toString((int)numOfDays));
                //Room Charges
                int totalRoomCharges = (int)numOfDays*roomCharges;
                frmFacturaRoom.txtTotalRoomCharges.setText(Integer.toString(totalRoomCharges));               
            } catch (ParseException ex) {
                Logger.getLogger(CtrlFacturaPaciente.class.getName()).log(Level.SEVERE, null, ex);
            }           
            frmListaPacienteFac.dispose();
            frmFacturaRoom.setVisible(true);
            frmFacturaRoom.setResizable(false);
            frmFacturaRoom.setLocationRelativeTo(null);
            frmFacturaRoom.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        }
        if(e.getSource() == frmListaFacturaPaciente.TableFacturaPaciente){
            int fila=frmListaFacturaPaciente.TableFacturaPaciente.rowAtPoint(e.getPoint());
            frmFacturaRoom.txtBillNo.setText(modeloC.getValueAt(fila, 0).toString());
            frmFacturaRoom.txtIdPatient.setText(modeloC.getValueAt(fila, 1).toString());
            frmFacturaRoom.txtPatientName.setText(modeloC.getValueAt(fila, 2).toString());
            frmFacturaRoom.txtGender.setText(modeloC.getValueAt(fila, 3).toString());
            frmFacturaRoom.txtBloodGroup.setText(modeloC.getValueAt(fila, 4).toString());
            frmFacturaRoom.txtDisease.setText(modeloC.getValueAt(fila, 5).toString());
            frmFacturaRoom.txtAdmitDate.setText(modeloC.getValueAt(fila, 6).toString());
            frmFacturaRoom.txtRoomNo.setText(modeloC.getValueAt(fila, 7).toString());
            frmFacturaRoom.txtDoctorID.setText(modeloC.getValueAt(fila, 8).toString());
            frmFacturaRoom.txtDoctorName.setText(modeloC.getValueAt(fila, 9).toString());
            frmFacturaRoom.txtDischargeDate.setText(modeloC.getValueAt(fila, 10).toString());
            frmFacturaRoom.txtRoomCharges.setText(modeloC.getValueAt(fila, 11).toString());
            frmFacturaRoom.txtServiceCharges.setText(modeloC.getValueAt(fila, 12).toString());
            frmFacturaRoom.txtBillingDate.setText(modeloC.getValueAt(fila, 13).toString());
            frmFacturaRoom.cbxPaymentMode.setSelectedItem(modeloC.getValueAt(fila, 14).toString());
            frmFacturaRoom.txtPaymentModeDetails.setText(modeloC.getValueAt(fila, 15).toString());
            frmFacturaRoom.txtTotalCharges.setText(modeloC.getValueAt(fila, 16).toString());
            frmFacturaRoom.txtNoDayRoom.setText(modeloC.getValueAt(fila, 17).toString());
            frmFacturaRoom.txtTotalRoomCharges.setText(modeloC.getValueAt(fila, 18).toString());
            frmListaFacturaPaciente.dispose();
            frmFacturaRoom.setVisible(true);
            frmFacturaRoom.setResizable(false);
            frmFacturaRoom.setLocationRelativeTo(null);
            frmFacturaRoom.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        }
        if(e.getSource() == frmFacturaRoom.lblCerrar){
            frmFacturaRoom.dispose();
        }
        if(e.getSource() == frmListaFacturaPaciente.lblCerrar){
            frmListaFacturaPaciente.dispose();
        }
        if(e.getSource() == frmListaPacienteFac.lblCerrar){
            frmListaPacienteFac.dispose();
        }
        if(e.getSource() == frmFacturaRoom.lblMinimiza){
            frmFacturaRoom.setExtendedState(ICONIFIED);
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
