
package modelo;

/**
 *
 * @author Brian54
 */
public class Doctor {
    private int doctorID;
    private String doctorName;
    private String doctorFatherName;
    private String doctorEmail;
    private String contactNo;
    private String qualifications;
    private String gender;
    private String bloodGroup;
    private String dateOfJoin;
    private String address;

    public Doctor(int doctorID, String doctorName, String doctorFatherName, String doctorEmail, String contactNo, String qualifications, String gender, String bloodGroup, String dateOfJoin, String address) {
        this.doctorID = doctorID;
        this.doctorName = doctorName;
        this.doctorFatherName = doctorFatherName;
        this.doctorEmail = doctorEmail;
        this.contactNo = contactNo;
        this.qualifications = qualifications;
        this.gender = gender;
        this.bloodGroup = bloodGroup;
        this.dateOfJoin = dateOfJoin;
        this.address = address;
    }

    public Doctor() {
    }

    public int getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(int doctorID) {
        this.doctorID = doctorID;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorFatherName() {
        return doctorFatherName;
    }

    public void setDoctorFatherName(String doctorFatherName) {
        this.doctorFatherName = doctorFatherName;
    }

    public String getDoctorEmail() {
        return doctorEmail;
    }

    public void setDoctorEmail(String doctorEmail) {
        this.doctorEmail = doctorEmail;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getQualifications() {
        return qualifications;
    }

    public void setQualifications(String qualifications) {
        this.qualifications = qualifications;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getDateOfJoin() {
        return dateOfJoin;
    }

    public void setDateOfJoin(String dateOfJoin) {
        this.dateOfJoin = dateOfJoin;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }  
}
