package com.jwt.models;

import com.jwt.repositories.ClubRepository;
import com.jwt.repositories.CompetitionRepository;

import java.sql.Date;
import java.sql.Time;

public class FixtureModel {

    private Long competitionId;
    private Long homeTeamId;
    private Long awayTeamId;
    private Date fixtureDate;
    private Time fixtureTime;
    private Integer season;
    private Integer round;

    public Integer getRound() {
        return round;
    }
    public void setRound(Integer round) {
        this.round = round;
    }
    public Integer getSeason() {
        return season;
    }
    public void setSeason(Integer season) {
        this.season = season;
    }
    public Date getFixtureDate() {        return fixtureDate;    }
    public void setFixtureDate(Date fixtureDate) {        this.fixtureDate = fixtureDate;    }
    public Time getFixtureTime() {        return fixtureTime;    }
    public void setFixtureTime(Time fixtureTime) {        this.fixtureTime = fixtureTime;    }
    public Long getAwayTeam() {
        return awayTeamId;
    }
    public void setAwayTeam(Long awayTeamId) {
        this.awayTeamId = awayTeamId;
    }
    public Long getHomeTeam() {
        return homeTeamId;
    }
    public void setHomeTeam(Long homeTeamId) {
        this.homeTeamId = homeTeamId;
    }
    public Long getCompetitionId() {
        return competitionId;
    }
    public void setCompetitionId(Long competitionId) {
        this.competitionId = competitionId;
    }

    public Fixture translateModelToFixture(CompetitionRepository competitionRepository, ClubRepository clubRepository) {
        Fixture fixture = new Fixture();

        fixture.setCompetition(competitionRepository.getById(this.competitionId));
        fixture.setHomeTeam(clubRepository.findClubById(this.homeTeamId));
        fixture.setAwayTeam(clubRepository.findClubById(this.awayTeamId));
        fixture.setFixtureDate(this.fixtureDate);
        fixture.setFixtureTime(this.fixtureTime);
        fixture.setSeason(this.season);
        fixture.setRound(this.round);

        return fixture;
    }
}