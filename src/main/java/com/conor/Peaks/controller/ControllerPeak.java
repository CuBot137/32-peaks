package com.conor.Peaks.controller;

import com.conor.Peaks.dto.DTOId;
import com.conor.Peaks.dto.DTOPeak;
import com.conor.Peaks.service.ServicePeak;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ControllerPeak {
    @Autowired
    private ServicePeak servicePeak;

    // Takes in parameters from postman in JSON format.
    @PostMapping("add")
    public ResponseEntity<String> add (@RequestBody DTOPeak dtoPeak){
        String name = dtoPeak.getName();
        int distance = dtoPeak.getDistance();
        boolean checked = dtoPeak.isChecked();
        if(name != null && !name.equals("") && distance > 0){
            try {
                servicePeak.addToRepo(name,distance,checked);
                return ResponseEntity.status(HttpStatus.ACCEPTED).body("Object inserted into DB");
            }catch (Exception e){
                throw new RuntimeException("Exception thrown from controller: "+ e.getMessage());
            }
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Object not inserted");
        }
    }

    @PatchMapping("/true/{id}")
    public ResponseEntity<String> updateCheckedTrue(@PathVariable long id){
        if(id > 0){
            try{
                servicePeak.checkedSummit(id);
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(id+" was updated");
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Issue with id. Check if it is legit");
        }
    }

}
