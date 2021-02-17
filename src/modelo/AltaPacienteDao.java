
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
public class AltaPacienteDao {
    private Connection con;
    private ResultSet rs;
    private PreparedStatement ps;
    private Conexion conexion = new Conexion();
    
    public boolean RegistrarAltaPaciente(AltaPaciente altaPaciente){
        String sql="INSERT INTO dischargepatient_room(AdmitID,DischargeDate,DP_Remarks)VALUES(?,?,?)";
        try {
            con=conexion.getConnection();
            ps=con.prepareStatement(sql);
            ps.setInt(1, altaPaciente.getPatientID());
            ps.setString(2, altaPaciente.getDateClose());
            ps.setString(3, altaPaciente.getObservation());
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
    
    public boolean ActualizarAltaPaciente(AltaPaciente altaPaciente){
       String sql="UPDATE  dischargepatient_room SET DischargeDate=?,DP_Remarks=? WHERE AdmitID=?";
        try {
            con=conexion.getConnection();
            ps=con.prepareStatement(sql);
            ps.setString(1, altaPaciente.getDateClose());
            ps.setString(2, altaPaciente.getObservation());       
            ps.setInt(3, altaPaciente.getPatientID());
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
    
    public boolean BorrarAltaPaciente(int admitID){
        String sql="DELETE FROM dischargepatient_room WHERE AdmitID=?";
        try {
            con=conexion.getConnection();
            ps=con.prepareStatement(sql);
            ps.setInt(1, admitID);
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
    
    public List ListarAltaPaciente(){
        List<AltaPaciente> ListaAltaPaciente = new ArrayList();
        String sql="Select PatientRegistration.PatientID as 'Patient ID',PatientRegistration.PatientName as 'Patient Name',PatientRegistration.Gen as 'Gender',PatientRegistration.BG as 'Blood Group',Disease,AdmitDate as 'Admit Date',Room.RoomNo as 'Room No',Doctor.DoctorID as 'Doctor ID',DoctorName as 'Doctor Name',DischargeDate as 'Discharge Date',DP_Remarks as 'Remarks' from Room,Doctor,PatientRegistration,AdmitPatient_Room,DischargePatient_Room where Room.RoomNo=AdmitPatient_Room.RoomNo and Doctor.DoctorID=AdmitPatient_Room.DoctorID and PatientRegistration.PatientID=AdmitPatient_Room.PatientID  and AdmitPatient_Room.PatientID= DischargePatient_Room.admitID order by Dischargedate";
        try {
            con=conexion.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                AltaPaciente altapaciente = new AltaPaciente();
                altapaciente.setPatientID(rs.getInt("Patient ID"));
                altapaciente.setPatientName(rs.getString("Patient Name"));
                altapaciente.setGender(rs.getString("Gender"));
                altapaciente.setGroupBlood(rs.getString("Blood Group"));
                altapaciente.setDisease(rs.getString("Disease"));
                altapaciente.setAdmitDate(rs.getString("Admit Date"));
                altapaciente.setNoRoom(rs.getInt("Room No"));
                altapaciente.setIdDoctor(rs.getInt("Doctor ID"));
                altapaciente.setNameDoctor(rs.getString("Doctor Name"));
                altapaciente.setDateClose(rs.getString("Discharge Date"));
                altapaciente.setObservation(rs.getString("Remarks"));
                ListaAltaPaciente.add(altapaciente);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return ListaAltaPaciente;
    } 
    
    public List ListarPacienteA(){
       List<AltaPaciente> ListaPacienteA = new ArrayList();
       String sql="Select PatientRegistration.PatientID as 'Patient ID',PatientRegistration.PatientName,PatientRegistration.Gen as 'Gender',PatientRegistration.BG as 'Blood Group',Disease,AdmitDate as 'Admit Date',Room.RoomNo as 'Room No',Doctor.DoctorID as 'Doctor ID',DoctorName as 'Doctor Name',AdmitPatient_Room.AP_Remarks as 'Remarks' from Room,Doctor,PatientRegistration,AdmitPatient_Room where Room.RoomNo=AdmitPatient_Room.RoomNo and Doctor.DoctorID=AdmitPatient_Room.DoctorID and PatientRegistration.PatientID=AdmitPatient_Room.PatientID order by admitdate";
        try {
            con=conexion.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                AltaPaciente altaPaciente = new AltaPaciente();
                altaPaciente.setPatientID(rs.getInt("Patient ID"));
                altaPaciente.setPatientName(rs.getString("PatientName"));
                altaPaciente.setGender(rs.getString("Gender"));
                altaPaciente.setDisease(rs.getString("Disease"));
                altaPaciente.setAdmitDate(rs.getString("Admit Date"));
                altaPaciente.setGroupBlood(rs.getString("Blood Group"));
                altaPaciente.setNoRoom(rs.getInt("Room No"));
                altaPaciente.setIdDoctor(rs.getInt("Doctor ID"));
                altaPaciente.setNameDoctor(rs.getString("Doctor Name"));
                altaPaciente.setObservation(rs.getString("Remarks"));
                ListaPacienteA.add(altaPaciente);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return ListaPacienteA;
    }
}
