package com.herd.API.models;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CowRepository extends JpaRepository<Cow, String> {

    @Query("SELECT s FROM Cow s WHERE s.cowNumber = ?1")
    Optional<Cow> findCowByCowNumber(int cowNumber);

    @Query("SELECT s FROM Cow s WHERE s.collarId = ?1")
    Optional<Cow> findCowByCollarId(int collarId);
}
