
package modelo;

/**
 *
 * @author Brian54
 */
public class FacturaPaciente {
    private int idPaciente;
    private String namePaciente;
    private String bloodGroup;
    private String disease;
    private String admitDate;
    private int noRoom;
    private int idDoctor;
    private String nameDoctor;
    private String closeDate;
    private int chargesRoom;
    private int noDays;
    private int totalChargesRoom;
    private int chargesService;
    private String dateBill;
    private String modePay;
    private String detailsModePay;
    private int totalCharges;
    private int noBill;
    private String gender;
    private String remarks;

    public FacturaPaciente(int idPaciente, String namePaciente, String bloodGroup, String disease, String admitDate, int noRoom, int idDoctor, String nameDoctor, String closeDate, int chargesRoom, int noDays, int totalChargesRoom, int chargesService, String dateBill, String modePay, String detailsModePay, int totalCharges, int noBill, String gender, String remarks) {
        this.idPaciente = idPaciente;
        this.namePaciente = namePaciente;
        this.bloodGroup = bloodGroup;
        this.disease = disease;
        this.admitDate = admitDate;
        this.noRoom = noRoom;
        this.idDoctor = idDoctor;
        this.nameDoctor = nameDoctor;
        this.closeDate = closeDate;
        this.chargesRoom = chargesRoom;
        this.noDays = noDays;
        this.totalChargesRoom = totalChargesRoom;
        this.chargesService = chargesService;
        this.dateBill = dateBill;
        this.modePay = modePay;
        this.detailsModePay = detailsModePay;
        this.totalCharges = totalCharges;
        this.noBill = noBill;
        this.gender = gender;
        this.remarks = remarks;
    }

    public FacturaPaciente() {
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    
    
    public int getIdPaciente() {
        return idPaciente;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    
    

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getNamePaciente() {
        return namePaciente;
    }

    public void setNamePaciente(String namePaciente) {
        this.namePaciente = namePaciente;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
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

    public String getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(String closeDate) {
        this.closeDate = closeDate;
    }

    public int getChargesRoom() {
        return chargesRoom;
    }

    public void setChargesRoom(int chargesRoom) {
        this.chargesRoom = chargesRoom;
    }

    public int getNoDays() {
        return noDays;
    }

    public void setNoDays(int noDays) {
        this.noDays = noDays;
    }

    public int getTotalChargesRoom() {
        return totalChargesRoom;
    }

    public void setTotalChargesRoom(int totalChargesRoom) {
        this.totalChargesRoom = totalChargesRoom;
    }

    public int getChargesService() {
        return chargesService;
    }

    public void setChargesService(int chargesService) {
        this.chargesService = chargesService;
    }

    public String getDateBill() {
        return dateBill;
    }

    public void setDateBill(String dateBill) {
        this.dateBill = dateBill;
    }

    public String getModePay() {
        return modePay;
    }

    public void setModePay(String modePay) {
        this.modePay = modePay;
    }

    public String getDetailsModePay() {
        return detailsModePay;
    }

    public void setDetailsModePay(String detailsModePay) {
        this.detailsModePay = detailsModePay;
    }

    public int getTotalCharges() {
        return totalCharges;
    }

    public void setTotalCharges(int totalCharges) {
        this.totalCharges = totalCharges;
    }

    public int getNoBill() {
        return noBill;
    }

    public void setNoBill(int noBill) {
        this.noBill = noBill;
    }
}
