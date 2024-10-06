package com.softuni.superMarket.db.repositories;

import com.softuni.superMarket.db.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepository extends JpaRepository<Shop,String> {
    Shop findByName(String name);
}
