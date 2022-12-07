package springlogin.controllers;


import springlogin.models.CovidVariant;
import springlogin.models.CovidVariantModel;
import springlogin.payload.response.MessageResponse;
import springlogin.services.CovidVariantService;
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
@RequestMapping("/covidVariant")
public class CovidVariantController {
    public final CovidVariantService covidVariantService;

    @Autowired
    public CovidVariantController(CovidVariantService covidVariantService) {
        this.covidVariantService = covidVariantService;
    }

    // return all CovidVariants

    @GetMapping(value={"/","/list",""} )
    @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
    public @ResponseBody List<CovidVariant> list(){
        return covidVariantService.list();
    }

    // return CovidVariant by id

    @GetMapping(value="/findById")
    @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
    public @ResponseBody
    CovidVariant findById(@RequestParam("id")  Long id){
        return covidVariantService.findById(id);
    }

    // return CovidVariant by name

    @GetMapping(value="/findByName")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public @ResponseBody  CovidVariant findByName(@ModelAttribute CovidVariantModel covidVariantModel) {
        return covidVariantService.findByName(covidVariantModel);
    }

    // add new CovidVariant

    @PutMapping(value="/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<MessageResponse> add(@RequestBody CovidVariantModel covidVariantModel){
        return covidVariantService.add(covidVariantModel);
    }

    // edit/update a CovidVariant record - only if record with id exists

    @PostMapping(value="/update")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<MessageResponse> update(@RequestBody CovidVariant covidVariant) {
        return covidVariantService.update( covidVariant.getId(), covidVariant );
    }


    // delete by id

    @DeleteMapping(value="/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<MessageResponse> delete(@RequestBody CovidVariant covidVariant){
        return covidVariantService.delete(covidVariant);
    }
}
