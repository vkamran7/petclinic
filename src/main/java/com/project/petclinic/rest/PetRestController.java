package com.project.petclinic.rest;

import com.project.petclinic.model.Pet;
import com.project.petclinic.model.PetType;
import com.project.petclinic.service.ClinicService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@CrossOrigin(exposedHeaders = "errors, content-type")
@RequestMapping("/api/pets")
public class PetRestController {

    private final ClinicService clinicService;

    public PetRestController(ClinicService clinicService) {
        this.clinicService = clinicService;
    }

    @PreAuthorize("hasRole(@roles.OWNER_ADMIN)")
    @GetMapping("/{petId}")
    public ResponseEntity<Pet> getPet(@PathVariable("petId") int petId) {
        Pet pet = clinicService.findPetById(petId);
        if (pet == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(pet, HttpStatus.OK);
    }

    @PreAuthorize("hasRole(@roles.OWNER_ADMIN)")
    @GetMapping
    public ResponseEntity<Collection<Pet>> getPets() {
        Collection<Pet> pets = clinicService.findAllPets();
        if (pets.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(pets, HttpStatus.OK);
    }

    @PreAuthorize("hasRole(@roles.OWNER_ADMIN)")
    @GetMapping("/pettypes")
    public ResponseEntity<Collection<PetType>> getPetTypes() {
        return new ResponseEntity<>(clinicService.findPetTypes(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole(@roles.OWNER_ADMIN)")
    @PostMapping
    public ResponseEntity<Pet> addPet(@RequestBody @Valid Pet pet,
                                      BindingResult bindingResult,
                                      UriComponentsBuilder builder) {
        BindingErrorsResponse errorsResponse = new BindingErrorsResponse();
        HttpHeaders headers = new HttpHeaders();

        if (bindingResult.hasErrors() || pet == null) {
            errorsResponse.addAllErrors(bindingResult);
            headers.add("errors", errorsResponse.toJSON());
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }
        clinicService.savePet(pet);
        headers.setLocation(builder.path("/api/pets/{id}").buildAndExpand(pet.getId()).toUri());
        return new ResponseEntity<>(pet, headers, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole(@roles.OWNER_ADMIN)")
    @PutMapping("/{petId}")
    public ResponseEntity<Pet> updatePet(@PathVariable("petId") int petId,
                                         @RequestBody @Valid Pet pet,
                                         BindingResult bindingResult) {
        BindingErrorsResponse errorsResponse = new BindingErrorsResponse();
        HttpHeaders headers = new HttpHeaders();

        if (bindingResult.hasErrors() || pet == null) {
            errorsResponse.addAllErrors(bindingResult);
            headers.add("errors", errorsResponse.toJSON());
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }
        Pet currentPet = clinicService.findPetById(petId);

        if (currentPet == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        currentPet.setBirthDate(pet.getBirthDate());
        currentPet.setName(pet.getName());
        currentPet.setType(pet.getType());
        currentPet.setOwner(pet.getOwner());
        clinicService.savePet(currentPet);
        return new ResponseEntity<>(currentPet, HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("hasRole(@roles.OWNER_ADMIN)")
    @DeleteMapping("/{petId}")
    public ResponseEntity<Void> deletePet(@PathVariable("petId") int petId) {
        Pet pet = clinicService.findPetById(petId);
        if (pet == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        clinicService.deletePet(pet);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
