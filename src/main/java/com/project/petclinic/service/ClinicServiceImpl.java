package com.project.petclinic.service;

import com.project.petclinic.model.*;
import com.project.petclinic.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
public class ClinicServiceImpl implements ClinicService {

    private final PetRepository petRepository;
    private final VetRepository vetRepository;
    private final OwnerRepository ownerRepository;
    private final VisitRepository visitRepository;
    private final SpecialtyRepository specialtyRepository;
    private final PetTypeRepository petTypeRepository;

    @Autowired
    public ClinicServiceImpl(PetRepository petRepository,
                             VetRepository vetRepository,
                             OwnerRepository ownerRepository,
                             VisitRepository visitRepository,
                             SpecialtyRepository specialtyRepository,
                             PetTypeRepository petTypeRepository) {
        this.petRepository = petRepository;
        this.vetRepository = vetRepository;
        this.ownerRepository = ownerRepository;
        this.visitRepository = visitRepository;
        this.specialtyRepository = specialtyRepository;
        this.petTypeRepository = petTypeRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Pet findPetById(int id) throws DataAccessException {
        Pet pet;
        try {
            pet = petRepository.findById(id);
        } catch (ObjectRetrievalFailureException | EmptyResultDataAccessException ex) {
            ex.printStackTrace();
            return null;
        }
        return pet;
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Pet> findAllPets() throws DataAccessException {
        return petRepository.findAll();
    }

    @Override
    @Transactional
    public void savePet(Pet pet) throws DataAccessException {
        petRepository.save(pet);
    }

    @Override
    @Transactional
    public void deletePet(Pet pet) throws DataAccessException {
        petRepository.delete(pet);
    }



    @Override
    @Transactional(readOnly = true)
    public Collection<Visit> findVisitsByPetId(int petId) throws DataAccessException {
        return visitRepository.findByPetId(petId);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Visit> findAllVisits() throws DataAccessException {
        return visitRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Visit findVisitById(int id) throws DataAccessException {
        Visit visit;
        try {
            visit = visitRepository.findById(id);
        } catch (ObjectRetrievalFailureException | EmptyResultDataAccessException ex) {
            ex.printStackTrace();
            return null;
        }
        return visit;
    }

    @Override
    @Transactional
    public void saveVisit(Visit visit) throws DataAccessException {
        visitRepository.save(visit);
    }

    @Override
    @Transactional
    public void deleteVisit(Visit visit) throws DataAccessException {
        visitRepository.delete(visit);
    }

    @Override
    @Transactional(readOnly = true)
    public Vet findVetById(int id) throws DataAccessException {
        Vet vet;
        try {
            vet = vetRepository.findById(id);
        } catch (ObjectRetrievalFailureException | EmptyResultDataAccessException ex) {
            ex.printStackTrace();
            return null;
        }
        return vet;
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "vets")
    public Collection<Vet> findVets() throws DataAccessException {
        return vetRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Vet> findAllVets() throws DataAccessException {
        return vetRepository.findAll();
    }

    @Override
    @Transactional
    public void saveVet(Vet vet) throws DataAccessException {
        vetRepository.save(vet);
    }

    @Override
    @Transactional
    public void deleteVet(Vet vet) throws DataAccessException {
        vetRepository.delete(vet);
    }

    @Override
    @Transactional(readOnly = true)
    public Owner findOwnerById(int id) throws DataAccessException {
        Owner owner;
        try {
            owner = ownerRepository.findById(id);
        } catch (ObjectRetrievalFailureException | EmptyResultDataAccessException ex) {
            ex.printStackTrace();
            return null;
        }
        return owner;
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Owner> findAllOwners() throws DataAccessException {
        return ownerRepository.findAll();
    }

    @Override
    @Transactional
    public void saveOwner(Owner owner) throws DataAccessException {
        ownerRepository.save(owner);
    }

    @Override
    @Transactional
    public void deleteOwner(Owner owner) throws DataAccessException {
        ownerRepository.delete(owner);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Owner> findOwnerByLastName(String lastName) throws DataAccessException {
        return ownerRepository.findByLastName(lastName);
    }

    @Override
    @Transactional(readOnly = true)
    public PetType findPetTypeById(int id) throws DataAccessException {
        PetType type;
        try {
            type = petTypeRepository.findById(id);
        } catch (ObjectRetrievalFailureException | EmptyResultDataAccessException ex) {
            ex.printStackTrace();
            return null;
        }
        return type;
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<PetType> findAllPetTypes() throws DataAccessException {
        return petTypeRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<PetType> findPetTypes() throws DataAccessException {
        return petRepository.findPetTypes();
    }

    @Override
    @Transactional
    public void savePetType(PetType type) throws DataAccessException {
        petTypeRepository.save(type);
    }

    @Override
    @Transactional
    public void deletePetType(PetType type) throws DataAccessException {
        petTypeRepository.delete(type);
    }

    @Override
    @Transactional(readOnly = true)
    public Specialty findSpecialtyById(int id) throws DataAccessException {
        Specialty specialty;
        try {
            specialty = specialtyRepository.findById(id);
        } catch (ObjectRetrievalFailureException | EmptyResultDataAccessException ex) {
            ex.printStackTrace();
            return null;
        }
        return specialty;
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Specialty> findAllSpecialties() throws DataAccessException {
        return specialtyRepository.findAll();
    }

    @Override
    @Transactional
    public void saveSpecialty(Specialty specialty) throws DataAccessException {
        specialtyRepository.save(specialty);
    }

    @Override
    @Transactional
    public void deleteSpecialty(Specialty specialty) throws DataAccessException {
        specialtyRepository.delete(specialty);
    }
}
