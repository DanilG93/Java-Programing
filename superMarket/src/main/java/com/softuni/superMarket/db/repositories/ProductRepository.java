package com.softuni.superMarket.db.repositories;

import com.softuni.superMarket.db.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,String> {

}
