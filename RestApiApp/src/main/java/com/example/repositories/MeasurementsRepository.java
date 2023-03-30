package com.example.repositories;

import com.example.models.Measurements;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasurementsRepository extends JpaRepository<Measurements, Integer> {

    long countByRainingTrue();
}
