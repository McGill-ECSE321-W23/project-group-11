package ca.mcgill.ecse321.ParkingManagement.dto;

import java.sql.Time;

public class SystemInfoDto {
    
    //attributes
    private int id;
    private Time openTime;
    private Time closeTime;
    private int largeTempSpotPrice;
    private int regTempSpotPrice;
    private int reservedSpotPrice;
    //association
    private ManagerDto managerDto;

    //constructors
    public SystemInfoDto(){

    }

    public SystemInfoDto(int id, ManagerDto managerDto, Time openTime, Time closeTime, int largeTempSpotPrice, int regTempSpotPrice, int reservedSpotPrice){
        this.id = id;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.largeTempSpotPrice = largeTempSpotPrice;
        this.regTempSpotPrice = regTempSpotPrice;
        this.reservedSpotPrice = reservedSpotPrice;
        this.managerDto = managerDto;

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

    public ManagerDto getManager(){
        return this.managerDto;
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

    public void setManager(ManagerDto managerDto){
        this.managerDto = managerDto;
    }
}
