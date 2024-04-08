package com.conor.Peaks.repo;

import com.conor.Peaks.model.ModelPeak;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoPeak extends JpaRepository<ModelPeak, Long> {
    @Modifying
    @Transactional
    @Query(value = "UPDATE model_peak SET checked = true WHERE id = :id", nativeQuery = true)
    void changeCheckedToTrue(long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE model_peak SET checked = false WHERE id = :id", nativeQuery = true)
    void changeCheckedToFalse(long id);
    @Transactional
    @Query(value = "SELECT * FROM model_peak", nativeQuery = true)
    String getAll();
}
