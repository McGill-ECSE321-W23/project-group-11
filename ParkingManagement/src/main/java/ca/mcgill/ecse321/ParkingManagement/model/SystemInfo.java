package ca.mcgill.ecse321.ParkingManagement.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Id;
import java.sql.Time;

@Entity
public class SystemInfo {
    // attributes
    @Id
    private int id;
    private Time openTime;
    private Time closeTime;
    private int largeTempSpotPrice;
    private int regTempSpotPrice;
    private int reservedSpotPrice;

    // associations
    @OneToOne(optional = false)
    private Manager manager;

    // getters
    public int getId() {
        return this.id;
    }
    public Time getOpenTime() {
        return this.openTime;
    }
    public int getLargeTempSpotPrice() {
        return this.largeTempSpotPrice;
    }
    public int getRegTempSpotPrice() {
        return this.regTempSpotPrice;
    }
    public Time getCloseTime() {
        return this.closeTime;
    }
    public Manager getManager() {
        return this.manager;
    }

    // setters
    public void setId(int value) {
        this.id = value;
    }
    public void setOpenTime(Time value) {
        this.openTime = value;
    }
    public void setLargeTempSpotPrice(int value) {
        this.largeTempSpotPrice = value;
    }
    public void setRegTempSpotPrice(int value) {
        this.regTempSpotPrice = value;
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
    public void setManager(Manager manager) {
        this.manager = manager;
    }
}
