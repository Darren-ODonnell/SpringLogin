package com.jwt.controllers;

import com.jwt.models.ClubModel;
import com.jwt.models.Fixture;
import com.jwt.models.FixtureModel;
import com.jwt.payload.response.MessageResponse;
import com.jwt.services.FixtureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Darren O'Donnell
 */
@Controller
@RequestMapping("/fixture")
public class FixtureController {
    public final FixtureService fixtureService;

    @Autowired
    public FixtureController(FixtureService fixtureService) {
        this.fixtureService = fixtureService;
    }

    // return all Fixtures

    @GetMapping(value={"/","/list" })
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public @ResponseBody List<Fixture> list(){
        return fixtureService.findAll();
    }

    // delete by id

    @DeleteMapping(value="/deleteById/")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<MessageResponse> deleteById(@RequestParam("id") Long id){
        return fixtureService.deleteById(id);
    }

    // return Fixture by id

    @GetMapping(value="/findById/")
    @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
    public @ResponseBody Fixture findById(@RequestParam("id") Long id){
        return fixtureService.findById(id);
    }

    // return Fixtures By Club name

    @GetMapping(value="/findByClub/")
    @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
    public @ResponseBody List<Fixture> findByClub(@ModelAttribute ClubModel clubModel)  {
        return fixtureService.getClubFixtures(clubModel);
    }

    // return next Fixture By Club name

    @GetMapping(value="/findNextByClub/")
    @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
    public @ResponseBody Fixture findNextFixture(@ModelAttribute ClubModel clubModel) {
        return fixtureService.getNextClubFixture(clubModel);
    }

    // return Home Fixtures By Club name

    @GetMapping(value="/findByHomeByClub/")
    @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
    public @ResponseBody List<Fixture> findByHomeByClub(@ModelAttribute ClubModel clubModel) {
        return fixtureService.getClubHomeFixtures(clubModel);
    }

    // return Away Fixtures By Club name

    @GetMapping(value="/findByAwayByClub/")
    @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
    public @ResponseBody List<Fixture> findByAwayByClub(@ModelAttribute ClubModel clubModel)  {
        return fixtureService.getClubAwayFixtures(clubModel.getName());
    }

//    // add new fixture

    @PostMapping(value="/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public @ResponseBody  ResponseEntity<MessageResponse> add(@ModelAttribute FixtureModel fixtureModel) {
        return fixtureService.add(fixtureModel);
    }
    @GetMapping(value="/findByCompetitionHomeTeamAwayTeamFixtureDateSeason")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public @ResponseBody Fixture findByCompetitionHomeTeamAwayTeamFixtureDateSeason(@ModelAttribute FixtureModel fixtureModel) {
        return fixtureService.findByCompetitionHomeTeamAwayTeamFixtureDateSeason( fixtureModel);
    }

    @PostMapping(value="/update")
    @PreAuthorize("hasRole('ADMIN')")
    public @ResponseBody ResponseEntity<MessageResponse> update(@RequestParam Long id, @ModelAttribute FixtureModel fixtureModel){
        return fixtureService.update(id, fixtureModel);
    }

}