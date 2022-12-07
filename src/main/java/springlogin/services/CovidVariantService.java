package springlogin.services;


import springlogin.enums.MessageTypes;
import springlogin.exceptions.MyMessageResponse;
import springlogin.models.CovidVariant;
import springlogin.models.CovidVariantModel;
import springlogin.payload.response.MessageResponse;
import springlogin.repositories.CovidVariantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CovidVariantService {

    CovidVariantRepository covidVariantRepository;

    @Autowired
    public CovidVariantService(CovidVariantRepository covidVariantRepository) {
        this.covidVariantRepository = covidVariantRepository;
    }

    // return all CovidVariants

    public List<CovidVariant> list(){
        List<CovidVariant> covidVariants = covidVariantRepository.findAll();
        if(covidVariants.isEmpty()) new MyMessageResponse("Error: No CovidVariants listed", MessageTypes.WARN);
        return covidVariants;
    }

    // return CovidVariant by id

    public CovidVariant findById( Long id){
        Optional<CovidVariant> covidVariant = covidVariantRepository.findById(id);
        if(covidVariant.isEmpty())
            new MyMessageResponse(String.format("CovidVariant id: %d not found", id), MessageTypes.ERROR);
        return covidVariant.orElse(new CovidVariant());
    }

    // return CovidVariant by name

    public CovidVariant findByName( CovidVariantModel covidVariantModel) {
        Optional<CovidVariant> covidVariant = covidVariantRepository.findByName(covidVariantModel.getName());
        if(covidVariant.isEmpty()) new MyMessageResponse(String.format("CovidVariant name: %s not found", covidVariantModel.getName()), MessageTypes.INFO);
        return covidVariant.orElse(new CovidVariant());
    }

    // add new CovidVariant

    public ResponseEntity<MessageResponse> add(CovidVariantModel covidVariantModel){

        if(covidVariantRepository.existsByName(covidVariantModel.getName()))
            return ResponseEntity.ok(new MyMessageResponse("Error: CovidVariant already exists", MessageTypes.WARN));

        covidVariantRepository.save(covidVariantModel.translateModelToCovidVariant());
        return ResponseEntity.ok(new MyMessageResponse("new CovidVariant added", MessageTypes.INFO));
    }

    // delete by id

    public ResponseEntity<MessageResponse> delete( CovidVariant covidVariant){
        Long id = covidVariant.getId();
        if(!covidVariantRepository.existsById(id))
            return ResponseEntity.ok(new MyMessageResponse("Error: Cannot delete CovidVariant with id: "+id, MessageTypes.WARN));

        covidVariantRepository.deleteById(id);
        return ResponseEntity.ok(new MyMessageResponse("CovidVariant deleted with id: " + id, MessageTypes.INFO));
    }

    // edit/update a CovidVariant record - only if record with id exists

    public ResponseEntity<MessageResponse> update(Long id, CovidVariant covidVariant){

        // check if exists first
        // then update

        if(!covidVariantRepository.existsById(id))
            return ResponseEntity.ok(new MyMessageResponse("Error: Id does not exist ["+id+"] -> cannot update record", MessageTypes.WARN));

        covidVariantRepository.save(covidVariant);
        return ResponseEntity.ok(new MyMessageResponse("CovidVariant record updated", MessageTypes.INFO));
    }


}
