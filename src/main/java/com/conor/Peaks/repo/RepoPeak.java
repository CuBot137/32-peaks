package com.conor.Peaks.repo;

import com.conor.Peaks.model.ModelPeak;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepoPeak extends JpaRepository<ModelPeak, Long> {
    @Transactional
    @Query(value = "UPDATE model_peak SET checked = true WHERE id = :id", nativeQuery = true)
    void changeCheckedToTrue(long id);

    @Query(value = "UPDATE model_peak SET checked = false WHERE id = :id", nativeQuery = true)
    Optional<ModelPeak> changeCheckedToFalse(long id);
}
