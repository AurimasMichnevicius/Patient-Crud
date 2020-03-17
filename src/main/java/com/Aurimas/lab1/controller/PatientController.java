package com.Aurimas.lab1.controller;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.Aurimas.lab1.model.Patient;
import com.Aurimas.lab1.repository.PatientRepository;
import com.Aurimas.lab1.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/api/v1")
public class PatientController {
    @Autowired
    private PatientRepository patientRepository;

    @GetMapping("/patients")
    public List <Patient> getAllPatients(){
        return patientRepository.findAll();
    }

    //get
    @GetMapping("/patients/{id}")
    public ResponseEntity <Patient> getPatientById(@PathVariable(value = "id") Long patientId) throws ResourceNotFoundException{
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found for this id :: " + patientId));
        return ResponseEntity.ok().body(patient);
    }

    //create
    @PostMapping("/patients")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity <Patient> createPatient(@Valid @RequestBody Patient patient) {
        try{
            final Patient savePatient = patientRepository.save(patient);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(savePatient.getId())
                    .toUri();
            return ResponseEntity.created(location).body(savePatient);
        }
        catch (DataAccessException ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong data structure", ex);
        }
        
    }

    //update
    @PutMapping("/patients/{id}")
    public ResponseEntity <Patient> updatePatient(@PathVariable(value = "id") Long patientId,
                                          @Valid @RequestBody Patient PatientDetails) throws ResourceNotFoundException{
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(()-> new ResourceNotFoundException("Patient not found for this id :: " + patientId));
        try{
            patient.setname(PatientDetails.getName());
            patient.setsurname(PatientDetails.getSurname());
            patient.setpersonalCode(PatientDetails.getPersonalCode());
			patient.setCondition(PatientDetails.getCondition());
            final Patient updatedPatient = patientRepository.save(patient);
            return ResponseEntity.status(HttpStatus.OK).body(updatedPatient);
        }
        catch(Exception ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong data structure", ex);
        }
    }

    //delete
    @DeleteMapping("/patients/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public Map <String, Boolean> deletePatient(@PathVariable(value = "id") Long patientId) throws ResourceNotFoundException{
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(()-> new ResourceNotFoundException("Patient not found for this id :: " + patientId));

        patientRepository.delete(patient);
        Map <String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
