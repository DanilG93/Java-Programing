package com.softuni.superMarket.services;

import com.softuni.superMarket.db.entity.Town;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TownService {

    String addTown(String name);
    List<Town> findAll();
    Town findByName(String name);
}
