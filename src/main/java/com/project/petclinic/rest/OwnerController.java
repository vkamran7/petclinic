package com.project.petclinic.rest;

import com.project.petclinic.model.Owner;
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
@RequestMapping("/api/owners")
public class OwnerController {

    private final ClinicService clinicService;

    public OwnerController(ClinicService clinicService) {
        this.clinicService = clinicService;
    }

    @PreAuthorize("hasRole(@roles.OWNER_ADMIN)")
    @GetMapping("/*/lastname/{lastName}")
    public ResponseEntity<Collection<Owner>> getOwnerList(@PathVariable("lastName") String lastName) {
        if (lastName == null) {
            lastName = "";
        }
        Collection<Owner> owners = clinicService.findOwnerByLastName(lastName);
        if (owners.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(owners, HttpStatus.OK);
    }

    @PreAuthorize("hasRole(@roles.OWNER_ADMIN)")
    @GetMapping
    public ResponseEntity<Collection<Owner>> getOwners() {
        Collection<Owner> owners = clinicService.findAllOwners();
        if (owners.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(owners, HttpStatus.OK);
    }

    @PreAuthorize("hasRole(@roles.OWNER_ADMIN)")
    @GetMapping("/{ownerId}")
    public ResponseEntity<Owner> getOwner(@PathVariable("ownerId") int ownerId) {
        Owner owner = clinicService.findOwnerById(ownerId);
        if (owner == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(owner, HttpStatus.OK);
    }

    @PreAuthorize("hasRole(@roles.OWNER_ADMIN)")
    @PostMapping
    public ResponseEntity<Owner> addOwner(@RequestBody @Valid Owner owner,
                                          BindingResult bindingResult,
                                          UriComponentsBuilder builder) {
        HttpHeaders headers = new HttpHeaders();
        if (bindingResult.hasErrors() || owner.getId() != null) {
            BindingErrorsResponse errorsResponse = new BindingErrorsResponse(owner.getId());
            errorsResponse.addAllErrors(bindingResult);
            headers.add("errors", errorsResponse.toJSON());
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }
        clinicService.saveOwner(owner);
        headers.setLocation(builder.path("/api/owners/{id}").buildAndExpand(owner.getId()).toUri());
        return new ResponseEntity<>(owner, headers, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole(@roles.OWNER_ADMIN)")
    @PutMapping("/{ownerId")
    public ResponseEntity<Owner> updateOwner(@PathVariable("ownerId") int ownerId,
                                             @RequestBody @Valid Owner owner,
                                             BindingResult bindingResult,
                                             UriComponentsBuilder builder) {
        boolean bodyIdMatchesPathId = owner.getId() == null || ownerId == owner.getId();
        if (bindingResult.hasErrors() || !bodyIdMatchesPathId) {
            BindingErrorsResponse errorsResponse = new BindingErrorsResponse(ownerId, owner.getId());
            errorsResponse.addAllErrors(bindingResult);
            HttpHeaders headers = new HttpHeaders();
            headers.add("errors", errorsResponse.toJSON());
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }
        Owner currentOwner = clinicService.findOwnerById(ownerId);
        if (currentOwner == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        currentOwner.setTelephone(owner.getTelephone());
        currentOwner.setCity(owner.getCity());
        currentOwner.setAddress(owner.getAddress());
        currentOwner.setFirstName(owner.getFirstName());
        currentOwner.setLastName(owner.getLastName());
        clinicService.saveOwner(currentOwner);
        return new ResponseEntity<>(currentOwner, HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("hasRole(@roles.OWNER_ADMIN)")
    @DeleteMapping("/{ownerId}")
    public ResponseEntity<Void> deleteOwner(@PathVariable("ownerId") int ownerId) {
        Owner owner = clinicService.findOwnerById(ownerId);
        if (owner == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        clinicService.deleteOwner(owner);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
