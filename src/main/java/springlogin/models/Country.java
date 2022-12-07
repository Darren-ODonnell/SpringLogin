package springlogin.models;

import javax.persistence.*;

@Entity
@Table(name = "country")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", length = 45)
    private String name;

    @Column(name = "week_number")
    private Integer weekNumber;

    @Column(name = "daily_occupancy")
    private Float dailyOccupancy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getWeekNumber() {
        return weekNumber;
    }

    public void setWeekNumber(Integer weekNumber) {
        this.weekNumber = weekNumber;
    }

    public Float getDailyOccupancy() {        return dailyOccupancy;    }

    public void setDailyOccupancy(Float dailyOccupancy) {
        this.dailyOccupancy = dailyOccupancy;
    }

}