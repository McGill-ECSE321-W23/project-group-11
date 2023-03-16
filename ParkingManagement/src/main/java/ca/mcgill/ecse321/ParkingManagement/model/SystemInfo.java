package ca.mcgill.ecse321.ParkingManagement.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToOne;
import javax.persistence.Id;
import java.sql.Time;

@Entity
public class SystemInfo {

    @Id
    private int id;

    private Time openTime;
    private Time closeTime;
    private int largeTempSpotPrice;
    private int smallTempSpotPrice;
    private int reservedSpotPrice;
    
    @OneToOne(optional = false)
    private Manager manager;

    public void setId(int value) {
        this.id = value;
    }

    public int getId() {
        return this.id;
    }

    public void setOpenTime(Time value) {
        this.openTime = value;
    }

    public Time getOpenTime() {
        return this.openTime;
    }

    public void setLargeTempSpotPrice(int value) {
        this.largeTempSpotPrice = value;
    }

    public int getLargeTempSpotPrice() {
        return this.largeTempSpotPrice;
    }


    public void setSmallTempSpotPrice(int value) {
        this.smallTempSpotPrice = value;
    }

    public int getSmallTempSpotPrice() {
        return this.smallTempSpotPrice;
    }


    public void setReservedSpotPrice(int value) {
        this.reservedSpotPrice = value;
    }

    public int getReservedSpotPrice() {
        return this.reservedSpotPrice;
    }

    public void setCloseTime(Time value) {
        this.closeTime = value;
    }

    public Time getCloseTime() {
        return this.closeTime;
    }

    public Manager getManager() {
        return this.manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

}
