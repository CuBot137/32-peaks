package com.conor.Peaks.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ModelPeak {
    @Id
    @GeneratedValue
    @Column
    private long id;
    @Column
    private String name;
    @Column
    private int distance;
    @Column
    private boolean checked;
}
