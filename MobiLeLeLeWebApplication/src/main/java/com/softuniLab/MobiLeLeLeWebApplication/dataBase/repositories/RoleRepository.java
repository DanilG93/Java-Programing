package com.softuniLab.MobiLeLeLeWebApplication.dataBase.repositories;

import com.softuniLab.MobiLeLeLeWebApplication.dataBase.entities.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<UserRole,Long> {

}
