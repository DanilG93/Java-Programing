package com.softuniLab.MobiLeLeLeWebApplication.dataBase.repositories;

import com.softuniLab.MobiLeLeLeWebApplication.dataBase.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

}
