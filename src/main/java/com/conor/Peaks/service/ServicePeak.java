package com.conor.Peaks.service;

import com.conor.Peaks.model.ModelPeak;
import com.conor.Peaks.repo.RepoPeak;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicePeak {
    @Autowired
    private RepoPeak repoPeak;
    public void addToRepo(String name, int distance, boolean checked){
        try{
            ModelPeak modelPeak = new ModelPeak();
            modelPeak.setName(name);
            modelPeak.setDistance(distance);
            modelPeak.setChecked(false);
            repoPeak.save(modelPeak);
        } catch (Exception e) {
            throw new RuntimeException("Error while adding to repo: "+e.getMessage());
        }
    }

    public void checkedSummit(Long id){
        try {
            repoPeak.changeCheckedToTrue(id);
        }
        catch (Exception e){
            throw new RuntimeException("Changing checked to true has failed: "+e.getMessage());
        }
    }
}
