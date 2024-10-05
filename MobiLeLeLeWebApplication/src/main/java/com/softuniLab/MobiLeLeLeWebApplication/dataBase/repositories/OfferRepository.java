package com.softuniLab.MobiLeLeLeWebApplication.dataBase.repositories;

import com.softuniLab.MobiLeLeLeWebApplication.dataBase.entities.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferRepository extends JpaRepository<Offer,Long> {
}
