
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
import modelo.Room;
import modelo.RoomDao;
import vista.RegistrarRoom;

/**
 *
 * @author Brian54
 */
public class CtrlNuevaRoom implements ActionListener,MouseListener{
    RegistrarRoom frmRegistrarRoom = new RegistrarRoom();
    Room room = new Room();
    RoomDao roomDao = new RoomDao();
    DefaultTableModel model = new DefaultTableModel();
    
    public CtrlNuevaRoom(RegistrarRoom frmRegistrarRoom){
        this.frmRegistrarRoom=frmRegistrarRoom;
        this.frmRegistrarRoom.btnGuardar.addActionListener(this);
        this.frmRegistrarRoom.btnActualizar.addActionListener(this);
        this.frmRegistrarRoom.btnBorrar.addActionListener(this);
        this.frmRegistrarRoom.btnNuevo.addActionListener(this);
        this.frmRegistrarRoom.btnObtenerDatos.addActionListener(this);
        this.frmRegistrarRoom.TableRoom.addMouseListener(this);
        this.frmRegistrarRoom.lblCerrar.addMouseListener(this);
        this.frmRegistrarRoom.lblMinimiza.addMouseListener(this);
    }
    
    public void mostrar(){
        frmRegistrarRoom.setVisible(true);
        frmRegistrarRoom.setResizable(false);
        frmRegistrarRoom.setLocationRelativeTo(null);
        frmRegistrarRoom.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        limpiarTable();
        listarRoom();
    }
    
    public void obtener(){
        room.setRoomNo(Integer.parseInt(frmRegistrarRoom.txtNoRoom.getText()));
        room.setRoomType(frmRegistrarRoom.cbxTipoRoom.getSelectedItem().toString());
        room.setRoomCharges(Integer.parseInt(frmRegistrarRoom.txtChargesRoom.getText()));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == frmRegistrarRoom.btnGuardar){
            if(!"".equals(frmRegistrarRoom.txtNoRoom.getText()) && !"".equals(frmRegistrarRoom.txtChargesRoom.getText())){
                obtener();
                if(roomDao.RegistrarRoom(room)){
                   limpiar();
                   JOptionPane.showMessageDialog(null,"Room Registrada"); 
                   limpiarTable();
                   listarRoom();
                }else{
                   JOptionPane.showMessageDialog(null,"Error al Registrar Room");                    
                }
            }else{
                JOptionPane.showMessageDialog(null,"Rellenar Datos");
            }
        }
        if(e.getSource() == frmRegistrarRoom.btnActualizar){
            if(!"".equals(frmRegistrarRoom.txtNoRoom.getText()) && !"".equals(frmRegistrarRoom.txtChargesRoom.getText())){
                obtener();
                if(roomDao.ActualizarRoom(room)){
                  limpiar();
                  JOptionPane.showMessageDialog(null,"Room Actualizada"); 
                  limpiarTable();
                  listarRoom();
                }else{
                  JOptionPane.showMessageDialog(null,"Error al Actualizar Room");
                }
            }else{
                JOptionPane.showMessageDialog(null,"Rellenar Datos");
            }
        }
        if (e.getSource() == frmRegistrarRoom.btnBorrar) {
            if (!"".equals(frmRegistrarRoom.txtNoRoom.getText())) {
                if (roomDao.BorrarRoom(Integer.parseInt(frmRegistrarRoom.txtNoRoom.getText()))) {
                    limpiar();
                    JOptionPane.showMessageDialog(null, "Room Eliminada"); 
                    limpiarTable();
                    listarRoom();
                } else {
                    JOptionPane.showMessageDialog(null,"Error al eliminar Room");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Rellenar No de Room");    
            }
        }
        if(e.getSource() == frmRegistrarRoom.btnObtenerDatos){
            limpiarTable();
            listarRoom();
        }
    }
    
    public void limpiar(){
        frmRegistrarRoom.txtNoRoom.setText("");
        frmRegistrarRoom.txtChargesRoom.setText("");
    }
    
    private void limpiarTable() {
        for (int i = 0; i < model.getRowCount(); i++) {
            model.removeRow(i);
            i=i-1;
        }
    }

    private void listarRoom() {
        List<Room> ListaRoom = roomDao.ListarRoom();
        model =(DefaultTableModel) frmRegistrarRoom.TableRoom.getModel();
        Object[] ob = new Object[4];
        for (int i = 0; i < ListaRoom.size(); i++) {
            ob[0]= ListaRoom.get(i).getRoomNo();
            ob[1]= ListaRoom.get(i).getRoomType();
            ob[2]=ListaRoom.get(i).getRoomCharges();
            ob[3]=ListaRoom.get(i).getRoomStatus();
            model.addRow(ob);
        }
        frmRegistrarRoom.TableRoom.setModel(model);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == frmRegistrarRoom.TableRoom){
            int fila = frmRegistrarRoom.TableRoom.rowAtPoint(e.getPoint());
            frmRegistrarRoom.txtNoRoom.setText(model.getValueAt(fila, 0).toString());
            frmRegistrarRoom.cbxTipoRoom.setSelectedItem(model.getValueAt(fila, 1).toString());
            frmRegistrarRoom.txtChargesRoom.setText(model.getValueAt(fila, 2).toString());
        }
        if(e.getSource() == frmRegistrarRoom.lblCerrar){
            frmRegistrarRoom.dispose();
        }
        if(e.getSource() == frmRegistrarRoom.lblMinimiza){
            frmRegistrarRoom.setExtendedState(ICONIFIED);
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
