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
import modelo.DoctorDao;
import vista.ListaDoctor;
import vista.RegistroDoctor;

/**
 *
 * @author Brian54
 */
public class CtrlNuevoDoctor implements ActionListener, MouseListener {

    private Doctor doctor = new Doctor();
    private DoctorDao doctorDao = new DoctorDao();
    private RegistroDoctor frmRegistroDoctor = new RegistroDoctor();
    private ListaDoctor frmListaDoctor = new ListaDoctor();
    private DefaultTableModel modelo = new DefaultTableModel();

    public CtrlNuevoDoctor(RegistroDoctor frmRegistroDoctor) {
        this.frmRegistroDoctor = frmRegistroDoctor;
        this.frmRegistroDoctor.btnGuardar.addActionListener(this);
        this.frmRegistroDoctor.btnActualizar.addActionListener(this);
        this.frmRegistroDoctor.btnBorrar.addActionListener(this);
        this.frmRegistroDoctor.btnNuevo.addActionListener(this);
        this.frmRegistroDoctor.btnObtenerDatos.addActionListener(this);
        this.frmListaDoctor.TableDoctores.addMouseListener(this);
        this.frmListaDoctor.lblCerrar.addMouseListener(this);
        this.frmRegistroDoctor.lblCerrar.addMouseListener(this);
        this.frmRegistroDoctor.lblMinimiza.addMouseListener(this);
    }

    public void mostrar() {
        frmRegistroDoctor.setVisible(true);
        frmRegistroDoctor.setLocationRelativeTo(null);
        frmRegistroDoctor.setResizable(false);
        frmRegistroDoctor.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == frmRegistroDoctor.btnGuardar) {
            if (!"".equals(frmRegistroDoctor.txtAddressDoctor.getText()) && !"".equals(frmRegistroDoctor.txtEmailDoctor.getText()) && !"".equals(frmRegistroDoctor.txtFatherDoctor.getText()) && !"".equals(frmRegistroDoctor.txtFechaDoctor.getText()) && !"".equals(frmRegistroDoctor.txtIdDoctor.getText()) && !"".equals(frmRegistroDoctor.txtNameDoctor.getText()) && !"".equals(frmRegistroDoctor.txtNumberDoctor.getText()) && !"".equals(frmRegistroDoctor.txtQualificationsDoctor.getText())) {
                obtenerDatos();
                if (doctorDao.RegistrarDoctor(doctor)) {
                    limpiar();
                    JOptionPane.showMessageDialog(null, "Doctor Registrado");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al Registrar");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Rellenar Datos");
            }
        }
        if (e.getSource() == frmRegistroDoctor.btnActualizar) {
            if (!"".equals(frmRegistroDoctor.txtAddressDoctor.getText()) && !"".equals(frmRegistroDoctor.txtEmailDoctor.getText()) && !"".equals(frmRegistroDoctor.txtFatherDoctor.getText()) && !"".equals(frmRegistroDoctor.txtFechaDoctor.getText()) && !"".equals(frmRegistroDoctor.txtIdDoctor.getText()) && !"".equals(frmRegistroDoctor.txtNameDoctor.getText()) && !"".equals(frmRegistroDoctor.txtNumberDoctor.getText()) && !"".equals(frmRegistroDoctor.txtQualificationsDoctor.getText())) {
                obtenerDatos();
                if (doctorDao.ActualizarDoctor(doctor)) {
                    limpiar();
                    JOptionPane.showMessageDialog(null, "Doctor Actualizado");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al Actualizar");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Rellenar Datos");
            }
        }
        if (e.getSource() == frmRegistroDoctor.btnBorrar) {
            if (!"".equals(frmRegistroDoctor.txtIdDoctor.getText())) {
                doctor.setDoctorID(Integer.parseInt(frmRegistroDoctor.txtIdDoctor.getText()));
                if (doctorDao.borrarDoctor(doctor.getDoctorID())) {
                    limpiar();
                    JOptionPane.showMessageDialog(null, "Doctor Borrado");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al borrar");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Rellenar campo ID Doctor");
            }
        }
        if (e.getSource() == frmRegistroDoctor.btnNuevo) {
            limpiar();
        }
        if (e.getSource() == frmRegistroDoctor.btnObtenerDatos) {
            frmRegistroDoctor.dispose();
            frmListaDoctor.setVisible(true);
            frmListaDoctor.setResizable(false);
            frmListaDoctor.setLocationRelativeTo(null);
            frmListaDoctor.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            limpiarTable();
            listarPaciente();
        }
    }

    public void limpiar() {
        frmRegistroDoctor.txtAddressDoctor.setText("");
        frmRegistroDoctor.txtEmailDoctor.setText("");
        frmRegistroDoctor.txtFatherDoctor.setText("");
        frmRegistroDoctor.txtFechaDoctor.setText("");
        frmRegistroDoctor.txtIdDoctor.setText("");
        frmRegistroDoctor.txtNameDoctor.setText("");
        frmRegistroDoctor.txtNumberDoctor.setText("");
        frmRegistroDoctor.txtQualificationsDoctor.setText("");
    }

    private void obtenerDatos() {
        doctor.setDoctorID(Integer.parseInt(frmRegistroDoctor.txtIdDoctor.getText()));
        doctor.setDoctorName(frmRegistroDoctor.txtNameDoctor.getText());
        doctor.setDoctorFatherName(frmRegistroDoctor.txtFatherDoctor.getText());
        doctor.setDoctorEmail(frmRegistroDoctor.txtEmailDoctor.getText());
        doctor.setContactNo(frmRegistroDoctor.txtNumberDoctor.getText());
        doctor.setQualifications(frmRegistroDoctor.txtQualificationsDoctor.getText());
        doctor.setGender(frmRegistroDoctor.cbxGenero.getSelectedItem().toString());
        doctor.setBloodGroup(frmRegistroDoctor.cbxGrupo.getSelectedItem().toString());
        doctor.setDateOfJoin(frmRegistroDoctor.txtFechaDoctor.getText());
        doctor.setAddress(frmRegistroDoctor.txtAddressDoctor.getText());
    }

    private void limpiarTable() {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }

    private void listarPaciente() {
        List<Doctor> ListaDoctor = doctorDao.ListarDoctor();
        modelo = (DefaultTableModel) frmListaDoctor.TableDoctores.getModel();
        Object[] ob = new Object[10];
        for (int i = 0; i < ListaDoctor.size(); i++) {
            ob[0] = ListaDoctor.get(i).getDoctorID();
            ob[1] = ListaDoctor.get(i).getDoctorName();
            ob[2] = ListaDoctor.get(i).getDoctorFatherName();
            ob[3] = ListaDoctor.get(i).getAddress();
            ob[4] = ListaDoctor.get(i).getContactNo();
            ob[5] = ListaDoctor.get(i).getDoctorEmail();
            ob[6] = ListaDoctor.get(i).getQualifications();
            ob[7] = ListaDoctor.get(i).getGender();
            ob[8] = ListaDoctor.get(i).getBloodGroup();
            ob[9] = ListaDoctor.get(i).getDateOfJoin();
            modelo.addRow(ob);
        }
        frmListaDoctor.TableDoctores.setModel(modelo);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == frmListaDoctor.TableDoctores) {
            int fila = frmListaDoctor.TableDoctores.rowAtPoint(e.getPoint());
            frmRegistroDoctor.txtIdDoctor.setText(modelo.getValueAt(fila, 0).toString());
            frmRegistroDoctor.txtNameDoctor.setText(modelo.getValueAt(fila, 1).toString());
            frmRegistroDoctor.txtFatherDoctor.setText(modelo.getValueAt(fila, 2).toString());
            frmRegistroDoctor.txtAddressDoctor.setText(modelo.getValueAt(fila, 3).toString());
            frmRegistroDoctor.txtNumberDoctor.setText(modelo.getValueAt(fila, 4).toString());
            frmRegistroDoctor.txtEmailDoctor.setText(modelo.getValueAt(fila, 5).toString());
            frmRegistroDoctor.txtQualificationsDoctor.setText(modelo.getValueAt(fila, 6).toString());
            frmRegistroDoctor.cbxGenero.setSelectedItem(modelo.getValueAt(fila, 7).toString());
            frmRegistroDoctor.cbxGrupo.setSelectedItem(modelo.getValueAt(fila, 8).toString());
            frmRegistroDoctor.txtFechaDoctor.setText(modelo.getValueAt(fila, 9).toString());
            frmListaDoctor.dispose();
            frmRegistroDoctor.setVisible(true);
            frmRegistroDoctor.setResizable(false);
            frmRegistroDoctor.setLocationRelativeTo(null);
            frmRegistroDoctor.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        }
        if (e.getSource() == frmListaDoctor.lblCerrar) {
            frmListaDoctor.dispose();
        }
        if (e.getSource() == frmRegistroDoctor.lblCerrar) {
            frmRegistroDoctor.dispose();
        }
        if (e.getSource() == frmRegistroDoctor.lblMinimiza) {
            frmRegistroDoctor.setExtendedState(ICONIFIED);
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
