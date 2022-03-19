package com.jwt.services;

import com.jwt.enums.MessageTypes;
import com.jwt.exceptions.MyMessageResponse;
import com.jwt.models.Firstname;
import com.jwt.models.FirstnameModel;
import com.jwt.payload.response.MessageResponse;
import com.jwt.repositories.FirstnameRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FirstnameService {
    private static final Logger logger = LoggerFactory.getLogger(FirstnameService.class);
    private final FirstnameRepository firstnameRepository;

    @Autowired
    public FirstnameService(FirstnameRepository firstnameRepository) {
        this.firstnameRepository = firstnameRepository;

    }

    public  List<Firstname> list(){
        return firstnameRepository.findAll();
    }

    // return Firstname by id

    public Firstname findById( @RequestParam("id") Long id){
        Optional<Firstname> firstname = firstnameRepository.findById(id);
        if(firstname.isEmpty()) logger.warn("Firstname not found with is: "+id);
        return firstname.orElse(new Firstname());
    }

    // return Firstname by firstname

    public  Firstname findByFirstname( @ModelAttribute FirstnameModel firstnameModel) {
        Optional<Firstname> firstname =firstnameRepository.findByFirstname(firstnameModel.getFirstname());
        if(firstname.isEmpty()) logger.warn(String.format("Firstname : %s not found", firstnameModel.getFirstname()));
        return firstname.orElse(new Firstname());
    }

    // return irish firstname given the english firstname

    public  Firstname findIrishFirstname(@ModelAttribute FirstnameModel firstnameModel) {
        Optional<Firstname> firstname = firstnameRepository.findByFirstname(firstnameModel.getFirstname());
        if(firstname.isEmpty()) logger.warn(String.format("English Firstname : %s not found", firstnameModel.getFirstname()));
        return firstname.orElse(new Firstname());
    }

    // return english Firstname(s) given the irish firstname

    public List<Firstname> findEnglishFirstname( @ModelAttribute FirstnameModel firstnameModel) {
        Optional<List<Firstname>> firstnames = firstnameRepository.findByFirstnameIrish(firstnameModel.getFirstnameIrish());
        if(firstnames.isEmpty()) logger.warn(String.format("Irish Firstname : %s not found", firstnameModel.getFirstname()));
        return firstnames.orElse(new ArrayList<>());
    }

    // add new firstname

    public  ResponseEntity<MessageResponse> add(@ModelAttribute FirstnameModel firstnameModel){

        if(firstnameRepository.existsByFirstname(firstnameModel.getFirstname()))
            return ResponseEntity.ok(new MyMessageResponse("Error: Firstname already exists", MessageTypes.WARN));

        firstnameRepository.save(firstnameModel.translateModelToFirstname());
        return ResponseEntity.ok(new MyMessageResponse("new Firstname added", MessageTypes.INFO));
    }

    // delete by id

    public ResponseEntity<MessageResponse> deleteById(@RequestParam("id") Long id){

        Optional<Firstname> firstname = firstnameRepository.findById(id);
        if(firstname.isEmpty())
            return ResponseEntity.ok(new MyMessageResponse("Error: Cannot delete firstname with id: " + id, MessageTypes.WARN));

        firstnameRepository.deleteById(id);
        return ResponseEntity.ok(new MyMessageResponse("Firstname deleted with id: " + id, MessageTypes.INFO));
    }

    // edit/update a firstname record - only if record with id exists

    public ResponseEntity<MessageResponse> update(Long id, FirstnameModel firstnameModel){
        // check if exists first
        // then update

        if(!firstnameRepository.existsById(id))
            return ResponseEntity.ok(new MyMessageResponse("Error: Firstname with Id: ["+id+"] -> does not exist - cannot update record", MessageTypes.WARN));

        firstnameRepository.save(firstnameModel.translateModelToFirstname(id));
        return ResponseEntity.ok(new MyMessageResponse("Firstname record updated", MessageTypes.INFO));
    }

}