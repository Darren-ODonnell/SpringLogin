package com.jwt.controllers;

import com.jwt.models.Position;
import com.jwt.models.Teamsheet;
import com.jwt.models.TeamsheetModel;
import com.jwt.payload.response.MessageResponse;
import com.jwt.services.TeamsheetService;
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
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping({"/teamsheet","/teamsheets",""})
public class TeamsheetController {
    public final TeamsheetService teamsheetService;

    @Autowired
    public TeamsheetController(TeamsheetService teamsheetService) {
        this.teamsheetService = teamsheetService;
    }

    // return all Teamsheets

    @GetMapping(value={"/","/list",""} )
    @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
    public @ResponseBody List<Teamsheet> list(){

        return teamsheetService.list();
    }

    // return Teamsheet by id

    @GetMapping(value="/findById")
    @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
    public @ResponseBody Teamsheet findById(@RequestParam("id")  Long id){
        return teamsheetService.findById(id);
    }

    // add new Teamsheet

    @PutMapping(value="/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<MessageResponse> add(@RequestBody TeamsheetModel teamsheetModel){
        return teamsheetService.add(teamsheetModel);
    }

    // edit/update a Teamsheet record - only if record with id exists

    @PostMapping(value="/update")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<MessageResponse> update(@RequestBody Teamsheet teamsheet) {
        return teamsheetService.update( teamsheet.getId(), teamsheet);
    }

    // delete by id

    @DeleteMapping(value="/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<MessageResponse> delete(@RequestBody Teamsheet teamsheet){
        return teamsheetService.delete(teamsheet);
    }
}