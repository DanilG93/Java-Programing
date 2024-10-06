package com.softuni.superMarket.db.repositories;

import com.softuni.superMarket.db.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends JpaRepository<Seller,String> {
        Seller findByFirstNameAndLastName(String firstName,String lastName);
}
