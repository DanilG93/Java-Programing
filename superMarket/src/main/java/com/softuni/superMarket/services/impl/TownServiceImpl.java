package com.softuni.superMarket.services.impl;

import com.softuni.superMarket.db.entity.Town;
import com.softuni.superMarket.db.repositories.TownRepository;
import com.softuni.superMarket.services.TownService;
import com.softuni.superMarket.util.ValidatorUtil;
import jakarta.validation.ConstraintViolation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TownServiceImpl implements TownService {

    private TownRepository townRepository;
    private ValidatorUtil validatorUtil;

    @Override
    public String addTown(String name) {
        StringBuilder sb = new StringBuilder();

        if (name.trim().isEmpty()) {
            return sb.append("Name cannot be empty!").append(System.lineSeparator()).toString().trim();
        }

        Town town = new Town(name);
        town.setId(UUID.randomUUID().toString());

        if (validatorUtil.isValid(town)) {
            sb.append("Successfully added town!").append(System.lineSeparator());
            townRepository.save(town);
        } else {
            Set<ConstraintViolation<Town>> violations = validatorUtil.getViolations(town);
            violations.forEach(v -> sb.append(v.getMessage()).append(System.lineSeparator()));
        }

        return sb.toString().trim();
    }


    public List<Town> findAll() {
        return townRepository.findAll();
    }

    @Override
    public Town findByName(String name) {
        return townRepository.findByName(name).stream().findFirst().orElse(null);
    }
}
