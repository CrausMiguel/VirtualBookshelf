package com.api.repositories;

import com.api.models.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface GenderRepository extends JpaRepository<Gender, UUID> {
    @Query(value = "SELECT * FROM gender g WHERE g.description = ?1", nativeQuery = true)
    Optional<Gender> getGenderByDescription(String description);
}
