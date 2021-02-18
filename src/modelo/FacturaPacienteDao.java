
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Brian54
 */
public class FacturaPacienteDao {
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;
    private Conexion conexion = new Conexion();
    
    public boolean RegistrarAltaPaciente(FacturaPaciente facturaPaciente){
       String sql= "INSERT INTO Bill_Room(DischargeID,BillingDate,RoomCharges,ServiceCharges,PaymentMode,PaymentModeDetails,TotalCharges,NoOfDays,TotalRoomCharges,BillNo)VALUES(?,?,?,?,?,?,?,?,?,?)";
        try {
            con=conexion.getConnection();
            ps=con.prepareStatement(sql);
            ps.setInt(1, facturaPaciente.getIdPaciente());
            ps.setString(2, facturaPaciente.getDateBill());
            ps.setInt(3, facturaPaciente.getChargesRoom());
            ps.setInt(4, facturaPaciente.getChargesService());
            ps.setString(5, facturaPaciente.getModePay());
            ps.setString(6, facturaPaciente.getDetailsModePay());
            ps.setInt(7, facturaPaciente.getTotalCharges());
            ps.setInt(8, facturaPaciente.getNoDays());
            ps.setInt(9, facturaPaciente.getTotalChargesRoom());
            ps.setInt(10, facturaPaciente.getNoBill());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }
    
    public boolean ActuslizarAltaPaciente(FacturaPaciente facturaPaciente){
       String sql= "UPDATE Bill_Room SET BillingDate=?,RoomCharges=?,ServiceCharges=?,PaymentMode=?,PaymentModeDetails=?,TotalCharges=?,NoOfDays=?,TotalRoomCharges=?,BillNo=? WHERE DischargeID=?";
        try {
            con=conexion.getConnection();
            ps=con.prepareStatement(sql);  
            ps.setString(1, facturaPaciente.getDateBill());
            ps.setInt(2, facturaPaciente.getChargesRoom());
            ps.setInt(3, facturaPaciente.getChargesService());
            ps.setString(4, facturaPaciente.getModePay());
            ps.setString(5, facturaPaciente.getDetailsModePay());
            ps.setInt(6, facturaPaciente.getTotalCharges());
            ps.setInt(7, facturaPaciente.getNoDays());
            ps.setInt(8, facturaPaciente.getTotalChargesRoom());
            ps.setInt(9, facturaPaciente.getNoBill());
            ps.setInt(10, facturaPaciente.getIdPaciente());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }
    
    public boolean EliminarAltaPaciente(int idPaciente){
        String sql="DELETE FROM bill_room WHERE DischargeID=?";
        try {
            con = conexion.getConnection();
            ps=con.prepareStatement(sql);
            ps.setInt(1, idPaciente);
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        } finally{
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }
    
    public List ListarFacturaPaciente(){
        String sql="Select BillNo as 'Bill No.',PatientRegistration.PatientID as 'Patient ID',PatientRegistration.PatientName as 'Patient Name',PatientRegistration.Gen as 'Gender',PatientRegistration.BG as 'Blood Group',Disease,AdmitDate as 'Admit Date',Room.RoomNo as 'Room No',Doctor.DoctorID as 'Doctor ID',DoctorName as 'Doctor Name',DischargeDate as 'Discharge Date',Bill_Room.RoomCharges as 'Room Charges',Bill_Room.ServiceCharges as 'Service Charges',Bill_Room.BillingDate as 'Billing Date',PaymentMode as 'Payement Mode',PaymentModeDetails as 'Payment Mode Details',TotalCharges as 'Total Charges',NoOfDays as 'No. Of Days',TotalRoomCharges as 'Total Room Charges' from Room,Doctor,PatientRegistration,AdmitPatient_Room,DischargePatient_Room,Bill_Room where Room.RoomNo=AdmitPatient_Room.RoomNo and Doctor.DoctorID=AdmitPatient_Room.DoctorID and PatientRegistration.PatientID=AdmitPatient_Room.PatientID  and AdmitPatient_Room.PatientID= DischargePatient_Room.admitID and Bill_Room.DischargeID=DischargePatient_Room.AdmitID  order by Billingdate"; 
        List<FacturaPaciente> ListaFacturaPaciente = new ArrayList();
        try {
            con=conexion.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()) {                
                FacturaPaciente facturaPaciente = new FacturaPaciente();
                facturaPaciente.setNoBill(rs.getInt("Bill No."));
                facturaPaciente.setIdPaciente(rs.getInt("Patient ID"));
                facturaPaciente.setNamePaciente(rs.getString("Patient Name"));
                facturaPaciente.setGender(rs.getString("Gender"));
                facturaPaciente.setBloodGroup(rs.getString("Blood Group"));
                facturaPaciente.setDisease(rs.getString(rs.getString("Disease")));
                facturaPaciente.setAdmitDate(rs.getString("Admit Date"));
                facturaPaciente.setNoRoom(rs.getInt("Room No"));
                facturaPaciente.setIdDoctor(rs.getInt("Doctor ID"));
                facturaPaciente.setNameDoctor(rs.getString("Doctor Name"));
                facturaPaciente.setCloseDate(rs.getString("Discharge Date"));
                facturaPaciente.setTotalChargesRoom(rs.getInt("Room Charges"));
                facturaPaciente.setChargesService(rs.getInt("Service Charges"));
                facturaPaciente.setDateBill(rs.getString("Billing Date"));
                facturaPaciente.setModePay(rs.getString("Payement Mode"));
                facturaPaciente.setDetailsModePay(rs.getString("Payement Mode Details"));
                facturaPaciente.setTotalCharges(rs.getInt("Total Charges"));
                facturaPaciente.setNoDays(rs.getInt("No. Of Days"));
                facturaPaciente.setTotalChargesRoom(rs.getInt("Total Room Charges"));        
                ListaFacturaPaciente.add(facturaPaciente);
            }
        } catch (SQLException e) {
        }
        return ListaFacturaPaciente;
    }
    
     public List ListarPaciente(){
        String sql="Select PatientRegistration.PatientID as 'Patient ID',PatientRegistration.PatientName as 'Patient Name',PatientRegistration.Gen as 'Gender',PatientRegistration.BG as 'Blood Group',Disease,AdmitDate as 'Admit Date',Room.RoomNo as 'Room No',RoomCharges as 'Room Charges',Doctor.DoctorID as 'Doctor ID',DoctorName as 'Doctor Name',DischargeDate as 'Discharge Date',DP_Remarks as 'Remarks' from Room,Doctor,PatientRegistration,AdmitPatient_Room,DischargePatient_Room where Room.RoomNo=AdmitPatient_Room.RoomNo and Doctor.DoctorID=AdmitPatient_Room.DoctorID and PatientRegistration.PatientID=AdmitPatient_Room.PatientID  and AdmitPatient_Room.PatientID= DischargePatient_Room.admitID order by Dischargedate";
        List<FacturaPaciente> ListaPaciente = new ArrayList();
        try {
            con=conexion.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()) {                
                FacturaPaciente facturaPaciente = new FacturaPaciente();
                facturaPaciente.setIdPaciente(rs.getInt("Patient ID"));
                facturaPaciente.setNamePaciente(rs.getString("Patient Name"));
                facturaPaciente.setGender(rs.getString("Gender"));
                facturaPaciente.setBloodGroup(rs.getString("Blood Group"));
                facturaPaciente.setDisease(rs.getString(rs.getString("Disease")));
                facturaPaciente.setAdmitDate(rs.getString("Admit Date"));
                facturaPaciente.setNoRoom(rs.getInt("Room No"));             
                facturaPaciente.setTotalChargesRoom(rs.getInt("Room Charges"));        
                facturaPaciente.setIdDoctor(rs.getInt("Doctor ID"));
                facturaPaciente.setNameDoctor(rs.getString("Doctor Name"));
                facturaPaciente.setCloseDate(rs.getString("Discharge Date"));
                facturaPaciente.setRemarks(rs.getString("Remarks"));
                ListaPaciente.add(facturaPaciente);
            }
        } catch (SQLException e) {
        }
        return ListaPaciente;
    }
   
}
