package com.conor.Peaks.controller;

import com.conor.Peaks.dto.DTOId;
import com.conor.Peaks.dto.DTOPeak;
import com.conor.Peaks.model.ModelPeak;
import com.conor.Peaks.service.ServicePeak;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PutMapping("/true")
    public ResponseEntity<String> updateCheckedTrue(@RequestBody DTOId dtoId){
        long id = dtoId.getId();
        if(id > 0){
            try{
                servicePeak.checkedSummitTrue(id);
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(id+" was updated");
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Issue with id. Check if it is legit");
        }
    }

    @PutMapping("/false")
    public ResponseEntity<String> updateCheckedFalse(@RequestBody DTOId dtoId){
        long id = dtoId.getId();
        if(id > 0){
            try{
                servicePeak.checkedSummitFalse(id);
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(id+" was updated");
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Issue with id. Check if it is legit");
        }
    }

    @GetMapping("/all")
    public List<ModelPeak> disAll(){
        try {
            List<ModelPeak> peaks = servicePeak.displayAll();
            return peaks;
        }catch (Exception e){
            throw new RuntimeException("Error displaying all: "+ e.getMessage());
        }
    }

}
