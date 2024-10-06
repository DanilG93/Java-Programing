package com.softuni.superMarket.db.repositories;

import com.softuni.superMarket.db.entity.Town;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface TownRepository extends JpaRepository<Town, String> {
    Optional<Town> findByName(String name);
}
