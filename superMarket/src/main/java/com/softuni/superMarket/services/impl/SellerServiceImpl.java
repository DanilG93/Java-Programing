package com.softuni.superMarket.services.impl;

import com.softuni.superMarket.db.entity.Category;
import com.softuni.superMarket.db.entity.Seller;
import com.softuni.superMarket.db.entity.Shop;
import com.softuni.superMarket.db.repositories.SellerRepository;
import com.softuni.superMarket.services.SellerService;
import com.softuni.superMarket.services.ShopService;
import com.softuni.superMarket.util.ValidatorUtil;
import jakarta.validation.ConstraintViolation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

@Service
@AllArgsConstructor
public class SellerServiceImpl implements SellerService {

    private ShopService shopService;
    private SellerRepository sellerRepository;
    private ValidatorUtil validatorUtil;

    @Override
    public String addedSeller(String sellerInfo) {

        StringBuilder sb = new StringBuilder();


        String[] sellerInfoArr = sellerInfo.split(" ");
        String firstName = sellerInfoArr[0];
        String lastName = sellerInfoArr[1];
        Integer age = Integer.parseInt(sellerInfoArr[2]);
        BigDecimal salary = BigDecimal.valueOf(Double.parseDouble(sellerInfoArr[3]));
        String shopName = sellerInfoArr[4];

        Shop shop = shopService.findByName(shopName);
        if (shop == null) {
            return sb.append("The store with that name does not exist. Please insert the store first")
                    .append(System.lineSeparator()).toString().trim();
        }

        Seller sellerFirstNameAndLastName = sellerRepository.findByFirstNameAndLastName(firstName, lastName);

        if ( sellerFirstNameAndLastName != null) {
            if (sellerFirstNameAndLastName.getShop().getName().equals(shopName)) {
                return "Seller already exists!!!";
            }
        }

        Seller seller = new Seller(firstName, lastName, age, salary, shop);
        seller.setId(UUID.randomUUID().toString());


        if (validatorUtil.isValid(seller)) {
            sellerRepository.save(seller);
            return "Successfully added seller";
        }

        Set<ConstraintViolation<Seller>> violations = validatorUtil.getViolations(seller);
        violations.forEach(v -> sb.append(v.getMessage()).append(System.lineSeparator()));


        return sb.toString().trim();
    }

    @Override
    public String addedManager(String toSeller, String toManager) {
        String[] sellerInfo = toSeller.split(" ");
        Seller seller = sellerRepository.findByFirstNameAndLastName(sellerInfo[0], sellerInfo[1]);
        if (seller == null) {
            return "The seller with that firstName and lastName does not exist. Please insert seller first";
        }

        String[] managerInfo = toManager.split(" ");
        Seller manager = sellerRepository.findByFirstNameAndLastName(managerInfo[0], managerInfo[1]);

        if (manager == null) {
            return "The manager with that firstName and lastName does not exist. Please insert seller(manager) first";
        }

        seller.setManager(manager);
        sellerRepository.save(seller);

        return "Successfully added manager!";
    }
}
