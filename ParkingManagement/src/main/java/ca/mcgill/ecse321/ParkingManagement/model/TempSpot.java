package ca.mcgill.ecse321.ParkingManagement.model;

import javax.persistence.Entity;
import java.sql.Date;
import java.time.LocalTime;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public abstract class TempSpot {

    private Car car;

    public void setCar(Car car) {
        this.car = car;
    }

    @OneToOne
    public Car getCar() {
        return this.car;
    }

    private LocalTime startTime;

    public void setStartTime(LocalTime value) {
        this.startTime = value;
    }

    public LocalTime getStartTime() {
        return this.startTime;
    }

    private Date date;

    public void setDate(Date value) {
        this.date = value;
    }

    public Date getDate() {
        return this.date;
    }

    private int duration; // number of 15 minute intervals

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
