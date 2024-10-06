package com.softuni.superMarket.services;

import com.softuni.superMarket.db.entity.Shop;
import org.springframework.stereotype.Service;

@Service
public interface ShopService {
    String addShop(String shopInfo);
    Shop findByName(String name);
}
