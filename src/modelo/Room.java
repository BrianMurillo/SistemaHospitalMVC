
package modelo;

/**
 *
 * @author Brian54
 */
public class Room {
    private int roomNo;
    private String roomType;
    private int roomCharges;
    private String roomStatus;
    
    public Room(int roomNo,String roomType,int roomCharges,String roomStatus){
        this.roomNo=roomNo;
        this.roomType=roomType;
        this.roomCharges=roomCharges;
        this.roomStatus=roomType;
    }
    public Room(){
        
    }

    public int getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(int roomNo) {
        this.roomNo = roomNo;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public int getRoomCharges() {
        return roomCharges;
    }

    public void setRoomCharges(int roomCharges) {
        this.roomCharges = roomCharges;
    }

    public String getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(String roomStatus) {
        this.roomStatus = roomStatus;
    }   
}
