package ca.mcgill.ecse321.ParkingManagement.model;

import javax.persistence.Entity;
import java.sql.Date;
import java.time.LocalTime;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public abstract class TempSpot {
    // attributes
    private int id; // 1-20 for large, 21-270 for regular
    private int duration; // number of 15 minute intervals
    private Date date;
    private LocalTime startTime; // TODO should this be local time or time???
    // associations
    private Car car;

    // getters
    public Date getDate() {
        return this.date;
    }
    public LocalTime getStartTime() {
        return this.startTime;
    }
    @OneToOne
    public Car getCar() {
        return this.car;
    }
    // setters
    public void setDate(Date value) {
        this.date = value;
    }
    public void setStartTime(LocalTime value) {
        this.startTime = value;
    }
    public void setCar(Car car) {
        this.car = car;
    }

    public void setDuration(int value) {
        this.duration = value;
    }

    public int getDuration() {
        return this.duration;
    }

    

    public void setId(int value) {
        this.id = value;
    }

    @Id
    public int getId() {
        return this.id;
    }
}
