package com.softuniLab.MobiLeLeLeWebApplication.dataBase.repositories;

import com.softuniLab.MobiLeLeLeWebApplication.dataBase.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
