
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
import modelo.User;
import modelo.UserDao;
import vista.ListarUser;
import vista.NuevoUsuario;

/**
 *
 * @author Brian54
 */
public class CtrlNuevoUsuario implements ActionListener, MouseListener {

    private NuevoUsuario FrmNuevoUsuario = new NuevoUsuario();
    private ListarUser FrmListarUser = new ListarUser();
    private User user = new User();
    private UserDao userDao = new UserDao();
    private DefaultTableModel modelo = new DefaultTableModel();

    public CtrlNuevoUsuario(NuevoUsuario FrmNuevoUsuario) {
        this.FrmNuevoUsuario = FrmNuevoUsuario;
        this.FrmListarUser.TableUser.addMouseListener(this);
        this.FrmNuevoUsuario.btnSaveUser.addActionListener(this);
        this.FrmNuevoUsuario.btnUpdateUser.addActionListener(this);
        this.FrmNuevoUsuario.btnDeleteUser.addActionListener(this);
        this.FrmNuevoUsuario.btnCleanUser.addActionListener(this);
        this.FrmNuevoUsuario.btnListUser.addActionListener(this);
        this.FrmNuevoUsuario.lblCerrar.addMouseListener(this);
        this.FrmNuevoUsuario.lblMinimizar.addMouseListener(this);
        this.FrmListarUser.lblCerrar.addMouseListener(this);
    }

    public void mostrar() {
        FrmNuevoUsuario.setVisible(true);
        FrmNuevoUsuario.setTitle("Nuevo Usuario");
        FrmNuevoUsuario.setResizable(false);
        FrmNuevoUsuario.setLocationRelativeTo(null);
        FrmNuevoUsuario.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == FrmNuevoUsuario.btnSaveUser) {
            if (!"".equals(FrmNuevoUsuario.txtUserName.getText()) && !"".equals(FrmNuevoUsuario.txtUserName.getText()) && !"".equals(FrmNuevoUsuario.txtUserPass.getText()) && !"".equals(FrmNuevoUsuario.txtUserEmail.getText()) && !"".equals(FrmNuevoUsuario.txtUserPhone.getText())) {
                user.setName(FrmNuevoUsuario.txtUserName.getText());
                user.setUser_name(FrmNuevoUsuario.txtUser.getText());
                user.setPassword(FrmNuevoUsuario.txtUserPass.getText());
                user.setEmail_id(FrmNuevoUsuario.txtUserEmail.getText());
                if (FrmNuevoUsuario.txtUserPhone.getText().length() < 10) {
                    user.setContact_no(Integer.parseInt(FrmNuevoUsuario.txtUserPhone.getText()));
                    userDao.RegistrarUser(user);
                    JOptionPane.showMessageDialog(null, "Usuario Registrado");
                    LimpiarUser();
                } else {
                    JOptionPane.showMessageDialog(null, "Telefono no valido");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Ingresar datos");
            }
        }
        if (e.getSource() == FrmNuevoUsuario.btnUpdateUser) {
            if (!"".equals(FrmNuevoUsuario.txtUserName.getText()) && !"".equals(FrmNuevoUsuario.txtUserName.getText()) && !"".equals(FrmNuevoUsuario.txtUserPass.getText()) && !"".equals(FrmNuevoUsuario.txtUserEmail.getText()) && !"".equals(FrmNuevoUsuario.txtUserPhone.getText())) {
                user.setName(FrmNuevoUsuario.txtUserName.getText());
                user.setUser_name(FrmNuevoUsuario.txtUser.getText());
                user.setPassword(FrmNuevoUsuario.txtUserPass.getText());
                user.setEmail_id(FrmNuevoUsuario.txtUserEmail.getText());
                if (FrmNuevoUsuario.txtUserPhone.getText().length() < 10) {
                    user.setContact_no(Integer.parseInt(FrmNuevoUsuario.txtUserPhone.getText()));
                    userDao.ModificarUser(user);
                    JOptionPane.showMessageDialog(null, "Usuario Modificado");
                    LimpiarUser();
                } else {
                    JOptionPane.showMessageDialog(null, "Telefono no valido");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Ingresar datos");
            }
        }
        if (e.getSource() == FrmNuevoUsuario.btnDeleteUser) {
            if (!"".equals(FrmNuevoUsuario.txtUserName.getText())) {
                user.setUser_name(FrmNuevoUsuario.txtUser.getText());
                userDao.EliminarUser(user.getUser_name());
                JOptionPane.showMessageDialog(null, "Usuario Eliminado");
                LimpiarUser();
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese nombre de usuario");
            }
        }
        if (e.getSource() == FrmNuevoUsuario.btnCleanUser) {
            LimpiarUser();
        }
        if (e.getSource() == FrmNuevoUsuario.btnListUser) {
            FrmNuevoUsuario.dispose();
            FrmListarUser.setVisible(true);
            FrmListarUser.setResizable(false);
            FrmListarUser.setTitle("Listar");
            FrmListarUser.setLocationRelativeTo(null);
            FrmListarUser.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            LimpiarTable();
            ListarUsuario();
        }
    }

    public void ListarUsuario() {
        List<User> ListarUs = userDao.ListarUser();
        modelo = (DefaultTableModel) FrmListarUser.TableUser.getModel();
        Object[] ob = new Object[5];
        for (int i = 0; i < ListarUs.size(); i++) {
            ob[0] = ListarUs.get(i).getName();
            ob[1] = ListarUs.get(i).getUser_name();
            ob[2] = ListarUs.get(i).getPassword();
            ob[3] = ListarUs.get(i).getEmail_id();
            ob[4] = ListarUs.get(i).getContact_no();
            modelo.addRow(ob);
        }
        FrmListarUser.TableUser.setModel(modelo);
    }

    public void LimpiarTable() {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }

    private void LimpiarUser() {
        FrmNuevoUsuario.txtUser.setText("");
        FrmNuevoUsuario.txtUserEmail.setText("");
        FrmNuevoUsuario.txtUserName.setText("");
        FrmNuevoUsuario.txtUserPass.setText("");
        FrmNuevoUsuario.txtUserPhone.setText("");
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == FrmListarUser.TableUser) {
            int fila = FrmListarUser.TableUser.rowAtPoint(e.getPoint());
            FrmNuevoUsuario.txtUserName.setText(modelo.getValueAt(fila, 0).toString());
            FrmNuevoUsuario.txtUser.setText(modelo.getValueAt(fila, 1).toString());
            FrmNuevoUsuario.txtUserPass.setText(modelo.getValueAt(fila, 2).toString());
            FrmNuevoUsuario.txtUserEmail.setText(modelo.getValueAt(fila, 3).toString());
            FrmNuevoUsuario.txtUserPhone.setText(modelo.getValueAt(fila, 4).toString());
            FrmListarUser.dispose();
            FrmNuevoUsuario.setVisible(true);
            FrmNuevoUsuario.setLocationRelativeTo(null);
            FrmNuevoUsuario.setResizable(false);
            FrmNuevoUsuario.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        }
        if (e.getSource() == FrmNuevoUsuario.lblCerrar) {
            FrmNuevoUsuario.dispose();
        }
        if (e.getSource() == FrmNuevoUsuario.lblMinimizar) {
            FrmNuevoUsuario.setExtendedState(ICONIFIED);
        }
        if (e.getSource() == FrmListarUser.lblCerrar) {
            FrmListarUser.dispose();
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
