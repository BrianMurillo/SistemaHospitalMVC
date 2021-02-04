
package modelo;

/**
 *
 * @author Brian54
 */
public class Paciente {
    private int patientID;
    private String patientName;
    private String patientFatherName;
    private String patientEmail;
    private String patientContact;
    private int patientAge;
    private String patientRemarks;
    private String patientGen;
    private String patientGrupoS;
    private String patientAddress;

    public Paciente() {
    }

    public Paciente(int patientID, String patientName, String patientFatherName, String patientEmail, String patientContact, int patientAge, String patientRemarks, String patientGen, String patientGrupoS, String patientAddress) {
        this.patientID = patientID;
        this.patientName = patientName;
        this.patientFatherName = patientFatherName;
        this.patientEmail = patientEmail;
        this.patientContact = patientContact;
        this.patientAge = patientAge;
        this.patientRemarks = patientRemarks;
        this.patientGen = patientGen;
        this.patientGrupoS = patientGrupoS;
        this.patientAddress = patientAddress;
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

    public String getFatherName() {
        return patientFatherName;
    }

    public void setFatherName(String patientFatherName) {
        this.patientFatherName = patientFatherName;
    }

    public String getPatientEmail() {
        return patientEmail;
    }

    public void setPatientEmail(String patientEmail) {
        this.patientEmail = patientEmail;
    }

    public String getPatientContact() {
        return patientContact;
    }

    public void setPatientContact(String patientContact) {
        this.patientContact = patientContact;
    }

    public int getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(int patientAge) {
        this.patientAge = patientAge;
    }

    public String getPatientRemarks() {
        return patientRemarks;
    }

    public void setPatientRemarks(String patientRemarks) {
        this.patientRemarks = patientRemarks;
    }

    public String getPatientGen() {
        return patientGen;
    }

    public void setPatientGen(String patientGen) {
        this.patientGen = patientGen;
    }

    public String getPatientGrupoS() {
        return patientGrupoS;
    }

    public void setPatientGrupoS(String patientGrupoS) {
        this.patientGrupoS = patientGrupoS;
    }

    public String getPatientAddress() {
        return patientAddress;
    }

    public void setPatientAddress(String patientAddress) {
        this.patientAddress = patientAddress;
    }    
}
