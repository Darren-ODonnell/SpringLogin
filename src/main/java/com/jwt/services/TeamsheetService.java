package com.jwt.services;

import com.jwt.enums.MessageTypes;
import com.jwt.exceptions.MyMessageResponse;
import com.jwt.models.Teamsheet;
import com.jwt.models.TeamsheetModel;
import com.jwt.payload.response.MessageResponse;
import com.jwt.repositories.TeamsheetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamsheetService {

    TeamsheetRepository teamsheetRepository;

    @Autowired
    public TeamsheetService(TeamsheetRepository teamsheetRepository) {
        this.teamsheetRepository = teamsheetRepository;
    }

    // return all Teamsheets

    public List<Teamsheet> list(){
        List<Teamsheet> teamsheets = teamsheetRepository.findAll();
        if(teamsheets.isEmpty()) new MyMessageResponse("Error: No Teamsheets listed", MessageTypes.WARN);
        return teamsheets;
    }

    // return Teamsheet by id

    public Teamsheet findById( Long id){
        Optional<Teamsheet> teamsheet = teamsheetRepository.findById(id);
        if(teamsheet.isEmpty())
            new MyMessageResponse(String.format("Teamsheet id: %d not found", id), MessageTypes.ERROR);
        return teamsheet.orElse(new Teamsheet());
    }


    // add new Teamsheet

    public ResponseEntity<MessageResponse> add(TeamsheetModel teamsheetModel){

        if(teamsheetRepository.existsByFixtureId(teamsheetModel.getFixture().getId()))
            return ResponseEntity.ok(new MyMessageResponse("Error: Teamsheet already exists", MessageTypes.WARN));

        teamsheetRepository.save(teamsheetModel.translateModelToTeamsheet());
        return ResponseEntity.ok(new MyMessageResponse("new Teamsheet added", MessageTypes.INFO));
    }

    // delete by id

    public ResponseEntity<MessageResponse> delete(Teamsheet teamsheet){
        Long id = teamsheet.getId();
        if(!teamsheetRepository.existsById(id))
            return ResponseEntity.ok(new MyMessageResponse("Error: Cannot delete Teamsheet with id: "+id, MessageTypes.WARN));

        teamsheetRepository.deleteById(id);
        return ResponseEntity.ok(new MyMessageResponse("Teamsheet deleted with id: " + id, MessageTypes.INFO));
    }

    // edit/update a Teamsheet record - only if record with id exists

    public ResponseEntity<MessageResponse> update(Long id, Teamsheet teamsheet){

        // check if exists first
        // then update

        if(!teamsheetRepository.existsById(id))
            return ResponseEntity.ok(new MyMessageResponse("Error: Id does not exist ["+id+"] -> cannot update record", MessageTypes.WARN));

        teamsheetRepository.save(teamsheet);
        return ResponseEntity.ok(new MyMessageResponse("Teamsheet record updated", MessageTypes.INFO));
    }


}