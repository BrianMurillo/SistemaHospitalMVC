
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
import modelo.PacienteDao;
import vista.ListaPaciente;
import vista.RegistroPaciente;

/**
 *
 * @author Brian54
 */
public class CtrlNuevoPaciente implements ActionListener,MouseListener {

    private Paciente paciente = new Paciente();
    private PacienteDao pacienteDAo = new PacienteDao();
    private RegistroPaciente frmRegistroPaciente = new RegistroPaciente();
    private ListaPaciente frmListaPaciente = new ListaPaciente();
    private DefaultTableModel modelo = new DefaultTableModel();


    public CtrlNuevoPaciente(RegistroPaciente frmRegistroPaciente) {
        this.frmRegistroPaciente = frmRegistroPaciente;
        this.frmRegistroPaciente.btnGuardar.addActionListener(this);
        this.frmRegistroPaciente.btnActualizar.addActionListener(this);
        this.frmRegistroPaciente.btnBorrar.addActionListener(this);
        this.frmRegistroPaciente.btnNuevo.addActionListener(this);
        this.frmRegistroPaciente.btnObtenerDatos.addActionListener(this);
        this.frmListaPaciente.TablePacientes.addMouseListener(this);
        this.frmRegistroPaciente.lblCerrar.addMouseListener(this);
        this.frmRegistroPaciente.lblMinimiza.addMouseListener(this);
        this.frmListaPaciente.lblCerrar.addMouseListener(this);
    }

    public void mostrar() {
        frmRegistroPaciente.setVisible(true);
        frmRegistroPaciente.setLocationRelativeTo(null);
        frmRegistroPaciente.setResizable(false);
        frmRegistroPaciente.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == frmRegistroPaciente.btnGuardar) {
            if (!"".equals(frmRegistroPaciente.txtAddressPaciente.getText()) && !"".equals(frmRegistroPaciente.txtAgePaciente.getText()) && !"".equals(frmRegistroPaciente.txtFamilyPaciente.getText()) && !"".equals(frmRegistroPaciente.txtIdPaciente.getText()) && !"".equals(frmRegistroPaciente.txtInfoPaciente.getText()) && !"".equals(frmRegistroPaciente.txtNamePaciente.getText())) {
                recuperaPaciente();
                if (pacienteDAo.RegistrarPaciente(paciente)) {
                    limpiar();
                    JOptionPane.showMessageDialog(null, "Paciente Registrado");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al Registrar");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Rellenar Datos");
            }
        }
        if (e.getSource() == frmRegistroPaciente.btnActualizar) {
            if (!"".equals(frmRegistroPaciente.txtAddressPaciente.getText()) && !"".equals(frmRegistroPaciente.txtAgePaciente.getText()) && !"".equals(frmRegistroPaciente.txtFamilyPaciente.getText()) && !"".equals(frmRegistroPaciente.txtIdPaciente.getText()) && !"".equals(frmRegistroPaciente.txtInfoPaciente.getText()) && !"".equals(frmRegistroPaciente.txtNamePaciente.getText())) {
                recuperaPaciente();
                if (pacienteDAo.ActualizarPaciente(paciente)) {
                    limpiar();
                    JOptionPane.showMessageDialog(null, "Paciente Modificado");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al Modificar");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Rellenar Datos");
            }
        }
        if (e.getSource() == frmRegistroPaciente.btnNuevo) {
            limpiar();
        }
        if (e.getSource() == frmRegistroPaciente.btnBorrar) {
            if (!"".equals(frmRegistroPaciente.txtIdPaciente.getText())) {
                paciente.setPatientID(Integer.parseInt(frmRegistroPaciente.txtIdPaciente.getText()));
                if (pacienteDAo.BorrarPaciente(paciente.getPatientID())) {
                    limpiar();
                    JOptionPane.showMessageDialog(null, "Paciente Eliminado");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al eliminar");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Seleccionar un ID");
            }
        }
        if (e.getSource() == frmRegistroPaciente.btnObtenerDatos) {
            frmRegistroPaciente.dispose();
            frmListaPaciente.setVisible(true);
            frmListaPaciente.setLocationRelativeTo(null);
            frmListaPaciente.setResizable(false);
            frmListaPaciente.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            LimpiarTable();
            ListarPaciente();
        }
    }
    
    public void ListarPaciente() {
        List<Paciente> ListarPaciente = pacienteDAo.ListarUser();
        modelo = (DefaultTableModel) frmListaPaciente.TablePacientes.getModel();
        Object[] ob = new Object[10];
        for (int i = 0; i < ListarPaciente.size(); i++) {
            ob[0] = ListarPaciente.get(i).getPatientID();
            ob[1] = ListarPaciente.get(i).getPatientName();
            ob[2] = ListarPaciente.get(i).getFatherName();
            ob[3] = ListarPaciente.get(i).getPatientEmail();
            ob[4] = ListarPaciente.get(i).getPatientContact();
            ob[5] = ListarPaciente.get(i).getPatientAge();
            ob[6] = ListarPaciente.get(i).getPatientRemarks();
            ob[7] = ListarPaciente.get(i).getPatientGen();
            ob[8] = ListarPaciente.get(i).getPatientGrupoS();
            ob[9] = ListarPaciente.get(i).getPatientAddress();
            modelo.addRow(ob);
        }
        frmListaPaciente.TablePacientes.setModel(modelo);
    }

    public void LimpiarTable() {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }

    public void limpiar() {
        frmRegistroPaciente.txtAddressPaciente.setText("");
        frmRegistroPaciente.txtAgePaciente.setText("");
        frmRegistroPaciente.txtContactPaciente.setText("");
        frmRegistroPaciente.txtEmailPaciente.setText("");
        frmRegistroPaciente.txtFamilyPaciente.setText("");
        frmRegistroPaciente.txtIdPaciente.setText("");
        frmRegistroPaciente.txtInfoPaciente.setText("");
        frmRegistroPaciente.txtNamePaciente.setText("");
    }

    public void recuperaPaciente() {
        paciente.setPatientID(Integer.parseInt(frmRegistroPaciente.txtIdPaciente.getText()));
        paciente.setPatientName(frmRegistroPaciente.txtNamePaciente.getText());
        paciente.setFatherName(frmRegistroPaciente.txtFamilyPaciente.getText());
        paciente.setPatientEmail(frmRegistroPaciente.txtEmailPaciente.getText());
        paciente.setPatientContact(frmRegistroPaciente.txtContactPaciente.getText());
        paciente.setPatientAge(Integer.parseInt(frmRegistroPaciente.txtAgePaciente.getText()));
        paciente.setPatientRemarks(frmRegistroPaciente.txtInfoPaciente.getText());
        paciente.setPatientGen(frmRegistroPaciente.cbxGenero.getSelectedItem().toString());
        paciente.setPatientGrupoS(frmRegistroPaciente.cbxGrupo.getSelectedItem().toString());
        paciente.setPatientAddress(frmRegistroPaciente.txtAddressPaciente.getText());
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == frmListaPaciente.TablePacientes){
            int fila = frmListaPaciente.TablePacientes.rowAtPoint(e.getPoint());
            frmRegistroPaciente.txtIdPaciente.setText(modelo.getValueAt(fila, 0).toString());
            frmRegistroPaciente.txtNamePaciente.setText(modelo.getValueAt(fila, 1).toString());
            frmRegistroPaciente.txtFamilyPaciente.setText(modelo.getValueAt(fila, 2).toString());
            frmRegistroPaciente.txtEmailPaciente.setText(modelo.getValueAt(fila, 3).toString());
            frmRegistroPaciente.txtContactPaciente.setText(modelo.getValueAt(fila, 4).toString());
            frmRegistroPaciente.txtAgePaciente.setText(modelo.getValueAt(fila, 5).toString());
            frmRegistroPaciente.txtInfoPaciente.setText(modelo.getValueAt(fila, 6).toString());
            frmRegistroPaciente.cbxGenero.setSelectedItem(modelo.getValueAt(fila, 7).toString());
            frmRegistroPaciente.cbxGrupo.setSelectedItem(modelo.getValueAt(fila, 8).toString());
            frmRegistroPaciente.txtAddressPaciente.setText(modelo.getValueAt(fila, 9).toString());
            frmListaPaciente.dispose();
            frmRegistroPaciente.setVisible(true);
            frmRegistroPaciente.setLocationRelativeTo(null);
            frmRegistroPaciente.setResizable(false);
            frmRegistroPaciente.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        }
        if(e.getSource() == frmRegistroPaciente.lblCerrar){
            frmRegistroPaciente.dispose();
        }
        if(e.getSource() == frmRegistroPaciente.lblMinimiza){
            frmRegistroPaciente.setExtendedState(ICONIFIED);
        }
        if(e.getSource() == frmListaPaciente.lblCerrar){
            frmListaPaciente.dispose();
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
