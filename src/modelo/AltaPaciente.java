
package modelo;

/**
 *
 * @author Brian54
 */
public class AltaPaciente {
    private int patientID;
    private String patientName;
    private String gender;
    private String groupBlood;
    private String disease;
    private String admitDate;
    private int noRoom;
    private int idDoctor;
    private String nameDoctor;
    private String dateClose;
    private String observation;
    
    public AltaPaciente(int patientID,String patientName,String gender,String groupBlood,String disease,String admitDate,int noRoom,String nameDoctor,int idDoctor,String dateClose,String observation){
        this.patientID=patientID;
        this.patientName=patientName;
        this.gender=gender;
        this.groupBlood=groupBlood;
        this.disease=disease;
        this.admitDate=admitDate;
        this.noRoom=noRoom;
        this.idDoctor=idDoctor;
        this.nameDoctor=nameDoctor;
        this.dateClose=dateClose;
        this.observation=observation;
    }
    
    public AltaPaciente(){
        
    }

    public int getPatientID() {
        return patientID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGroupBlood() {
        return groupBlood;
    }

    public void setGroupBlood(String groupBlood) {
        this.groupBlood = groupBlood;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getAdmitDate() {
        return admitDate;
    }

    public void setAdmitDate(String admitDate) {
        this.admitDate = admitDate;
    }

    public int getNoRoom() {
        return noRoom;
    }

    public void setNoRoom(int noRoom) {
        this.noRoom = noRoom;
    }

    public int getIdDoctor() {
        return idDoctor;
    }

    public void setIdDoctor(int idDoctor) {
        this.idDoctor = idDoctor;
    }

    public String getNameDoctor() {
        return nameDoctor;
    }

    public void setNameDoctor(String nameDoctor) {
        this.nameDoctor = nameDoctor;
    }

    public String getDateClose() {
        return dateClose;
    }

    public void setDateClose(String dateClose) {
        this.dateClose = dateClose;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }
}
