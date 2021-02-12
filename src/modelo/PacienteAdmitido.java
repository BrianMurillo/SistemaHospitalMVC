
package modelo;

/**
 *
 * @author Brian54
 */
public class PacienteAdmitido {
    private int patientID;
    private String disease;
    private String admintDate;
    private int roomNo;
    private int doctorID;
    private String ap_Remarks;
    private String patientName;
    private String gen;
    private String blood;
    private String doctorName;
    
    public PacienteAdmitido(int patientID,String disease,String admintDate,int roomNo,int doctorID,String ap_Remarks,String patientName,String gen,String blood,String doctorName){
        this.patientID=patientID;
        this.disease=disease;
        this.admintDate=admintDate;
        this.roomNo=roomNo;
        this.doctorID=doctorID;
        this.ap_Remarks=ap_Remarks;
        this.patientName=patientName;
        this.gen=gen;
        this.blood=blood;
        this.doctorName=doctorName;
    }

    public PacienteAdmitido() {
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getGen() {
        return gen;
    }

    public void setGen(String gen) {
        this.gen = gen;
    }

    public String getBlood() {
        return blood;
    }

    public void setBlood(String blood) {
        this.blood = blood;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }
    
    public int getPatientID(){
        return patientID;
    }
    
    public void setPatientID(int patientID){
        this.patientID=patientID;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getAdmintDate() {
        return admintDate;
    }

    public void setAdmintDate(String admintDate) {
        this.admintDate = admintDate;
    }

    public int getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(int roomNo) {
        this.roomNo = roomNo;
    }

    public int getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(int doctorID) {
        this.doctorID = doctorID;
    }

    public String getAp_Remarks() {
        return ap_Remarks;
    }

    public void setAp_Remarks(String ap_Remarks) {
        this.ap_Remarks = ap_Remarks;
    }
       
}
