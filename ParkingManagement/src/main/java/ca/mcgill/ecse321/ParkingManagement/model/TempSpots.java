package ca.mcgill.ecse321.ParkingManagement.model;
import javax.persistence.Entity;
import java.sql.Date;
import javax.persistence.Id;

@Entity
public abstract class TempSpot{
   private Date startTime;

public void setStartTime(Date value) {
    this.startTime = value;
}
public Date getStartTime() {
    return this.startTime;
}
private int duration;

public void setDuration(int value) {
    this.duration = value;
}
public int getDuration() {
    return this.duration;
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
