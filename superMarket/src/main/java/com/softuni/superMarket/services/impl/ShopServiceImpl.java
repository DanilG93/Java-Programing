package com.softuni.superMarket.services.impl;

import com.softuni.superMarket.db.entity.Category;
import com.softuni.superMarket.db.entity.Shop;
import com.softuni.superMarket.db.entity.Town;
import com.softuni.superMarket.db.repositories.ShopRepository;
import com.softuni.superMarket.services.ShopService;
import com.softuni.superMarket.services.TownService;
import com.softuni.superMarket.util.ValidatorUtil;
import jakarta.validation.ConstraintViolation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ShopServiceImpl implements ShopService {
    private TownService townService;
    private ShopRepository shopRepository;
    private ValidatorUtil validatorUtil;

    @Override
    public String addShop(String shopInfo) {

        StringBuilder sb = new StringBuilder();

        String[] shopInfoArr = shopInfo.split(" ");
        String name = shopInfoArr[0];
        String address = shopInfoArr[1];
        String town_name = shopInfoArr[2];
        if (townService.findByName(town_name) == null) {
            townService.addTown(town_name);
        }

        Town townByName = townService.findByName(town_name);


        Shop shop = new Shop(address, name, townByName, new ArrayList<>());
        shop.setId(UUID.randomUUID().toString());

        if (validatorUtil.isValid(shop)) {
            sb.append("Successfully added shop!").append(System.lineSeparator());
            shopRepository.save(shop);
        } else {
            Set<ConstraintViolation<Shop>> violations = validatorUtil.getViolations(shop);
            violations.forEach(v -> sb.append(v.getMessage()).append(System.lineSeparator()));
        }

        return sb.toString().trim();
    }

    @Override
    public Shop findByName(String name) {
        return shopRepository.findByName(name);
    }
}
