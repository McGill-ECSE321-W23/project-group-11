package ca.mcgill.ecse321.ParkingManagement.model;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Id;

@Entity
public class SystemInfo{
   private String openTime;

public void setOpenTime(String value) {
    this.openTime = value;
}
public String getOpenTime() {
    return this.openTime;
}
private String closeTime;

public void setCloseTime(String value) {
    this.closeTime = value;
}
public String getCloseTime() {
    return this.closeTime;
}
private Manager manager;

@OneToOne(mappedBy="systemInfo" , optional=false)
public Manager getManager() {
   return this.manager;
}

public void setManager(Manager manager) {
   this.manager = manager;
}

private int id;

public void setId(int value) {
    this.id = value;
}
@Id
public int getId() {
    return this.id;
}
}