package com.softuni.superMarket.services;

import org.springframework.stereotype.Service;

@Service
public interface SellerService {

    String addedSeller(String sellerInfo);
    String addedManager(String toSeller,String manager);
}
