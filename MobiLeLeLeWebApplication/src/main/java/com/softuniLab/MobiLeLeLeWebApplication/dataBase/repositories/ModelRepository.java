package com.softuniLab.MobiLeLeLeWebApplication.dataBase.repositories;

import com.softuniLab.MobiLeLeLeWebApplication.dataBase.entities.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelRepository extends JpaRepository<Model,Long> {
}
