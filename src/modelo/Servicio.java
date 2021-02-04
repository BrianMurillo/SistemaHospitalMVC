
package modelo;

/**
 *
 * @author Brian54
 */
public class Servicio {
    private String serviceName;
    private String serviceDate;
    private int patientId;
    private int serviceCharges;
    private int serviceId;
    
    public Servicio(String serviceName,String serviceDate,int patientId,int serviceCharges,int servicesId){
        this.serviceName=serviceName;
        this.serviceDate=serviceDate;
        this.patientId=patientId;
        this.serviceCharges=serviceCharges;
        this.serviceId=servicesId;
    }
    public Servicio(){
        
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(String serviceDate) {
        this.serviceDate = serviceDate;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getServiceCharges() {
        return serviceCharges;
    }

    public void setServiceCharges(int serviceCharges) {
        this.serviceCharges = serviceCharges;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    } 
}
