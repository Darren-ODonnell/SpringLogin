package springlogin.models;

public class CountryModel {
    private String name;
    private Integer weekNumber;
    private Float dailyOccupancy;

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
    public Float getDailyOccupancy() {
        return dailyOccupancy;
    }
    public void setDailyOccupancy(Float dailyOccupancy) {
        this.dailyOccupancy = dailyOccupancy;
    }

    public Country translateModelToCountry(){
        Country country = new Country();

        country.setName(this.name);
        country.setWeekNumber(this.weekNumber);
        country.setDailyOccupancy(this.dailyOccupancy);

        return country;
    }

}