package ca.mcgill.ecse321.ParkingManagement.dto;
import ca.mcgill.ecse321.ParkingManagement.model.Manager;
import ca.mcgill.ecse321.ParkingManagement.model.ServiceType;

public class ServiceTypeDto {

    //Contructor
    public ServiceTypeDto(String name, int cost, int duration, Manager manager) {
        this.name = name;
        this.cost = cost;
        this.duration = duration;
        this.manager = manager;
    }

    //Getters and Setters
    private String name;

    public void setName(String value) {
        this.name = value;
    }

    public String getName() {
        return this.name;
    }

    private int cost;

    public void setCost(int value) {
        this.cost = value;
    }

    public int getCost() {
        return this.cost;
    }

    private int duration;

    public void setDuration(int value) {
        this.duration = value;
    }

    public int getDuration() {
        return this.duration;
    }

    private Manager manager;

    public Manager getManager() {
        return this.manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public ServiceType toModel(){
        ServiceType s = new ServiceType(name,cost,duration,manager);
        return s;
    }
}
