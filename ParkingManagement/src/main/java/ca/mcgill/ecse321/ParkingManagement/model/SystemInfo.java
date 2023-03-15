package ca.mcgill.ecse321.ParkingManagement.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToOne;
import javax.persistence.Id;

@Entity
public class SystemInfo {

    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String openTime;
    private int largeTempSpotPrice;
    private int smallTempSpotPrice;
    private int reservedSpotPrice;
    private Manager manager;

    public void setId(int value) {
        this.id = value;
    }

    @Id
    public int getId() {
        return this.id;
    }

    public void setOpenTime(String value) {
        this.openTime = value;
    }

    public String getOpenTime() {
        return this.openTime;
    }

    public void setlargeTempSpotPrice(int value) {
        this.largeTempSpotPrice = value;
    }

    public int getlargeTempSpotPrice() {
        return this.largeTempSpotPrice;
    }


    public void setsmallTempSpotPrice(int value) {
        this.smallTempSpotPrice = value;
    }

    public int getsmallTempSpotPrice() {
        return this.smallTempSpotPrice;
    }


    public void setreservedSpotPrice(int value) {
        this.reservedSpotPrice = value;
    }

    public int getreservedSpotPrice() {
        return this.reservedSpotPrice;
    }
    private String closeTime;

    public void setCloseTime(String value) {
        this.closeTime = value;
    }

    public String getCloseTime() {
        return this.closeTime;
    }

    @OneToOne(optional = false)
    public Manager getManager() {
        return this.manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

}
