package ca.mcgill.ecse321.ParkingManagement.dto;

import java.sql.Time;

import ca.mcgill.ecse321.ParkingManagement.model.Manager;

public class SystemInfoDto {
    
    //attributes
    private int id;
    private Time openTime;
    private Time closeTime;
    private int largeTempSpotPrice;
    private int regTempSpotPrice;
    private int reservedSpotPrice;
    //association
    private Manager manager;

    //constructors
    public SystemInfoDto(){

    }

    public SystemInfoDto(int id, Time openTime, Time closeTime, int largeTempSpotPrice, int regTempSpotPrice, int reservedSpotPrice, Manager manager){
        this.id = id;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.largeTempSpotPrice = largeTempSpotPrice;
        this.regTempSpotPrice = regTempSpotPrice;
        this.reservedSpotPrice = reservedSpotPrice;
        this.manager = manager;

    }

    //getters
    public int getId(){
        return this.id;
    }

    public Time getOpenTime(){
        return this.openTime;
    }

    public Time getCloseTime(){
        return this.closeTime;
    }

    public int getLargeTempSpotPrice(){
        return this.largeTempSpotPrice;
    }

    public int getRegTempSpotPrice(){
        return this.regTempSpotPrice;
    }

    public int getReservedSpotPrice(){
        return this.reservedSpotPrice;
    }

    public Manager getManager(){
        return this.manager;
    }

    //setters
    public void setId(int id){
        this.id = id;
    }

    public void setOpenTime(Time openTime){
        this.openTime = openTime;
    }

    public void setCloseTime(Time closeTime){
        this.closeTime = closeTime;
    }

    public void setLargeTempSpotPrice(int largeTempSpotPrice){
        this.largeTempSpotPrice = largeTempSpotPrice;
    }

    public void setRegTempSpotPrice(int regTempSpotPrice){
       this.regTempSpotPrice = regTempSpotPrice;
    }

    public void setReservedSpotPrice(int reservedSpotPrice){
        this.reservedSpotPrice = reservedSpotPrice;
    }

    public void setManager(Manager manager){
        this.manager = manager;
    }
}
