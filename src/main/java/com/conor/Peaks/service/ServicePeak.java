package com.conor.Peaks.service;

import com.conor.Peaks.model.ModelPeak;
import com.conor.Peaks.repo.RepoPeak;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public void checkedSummitTrue(Long id){
        try {
            repoPeak.changeCheckedToTrue(id);
        }
        catch (Exception e){
            throw new RuntimeException("Changing checked to true has failed: "+e.getMessage());
        }
    }

    public void checkedSummitFalse(Long id){
        try {
            repoPeak.changeCheckedToFalse(id);
        }
        catch (Exception e){
            throw new RuntimeException("Changing checked to true has failed: "+e.getMessage());
        }
    }

    public List<ModelPeak> displayAll(){
        return repoPeak.findAll();
    }
}
