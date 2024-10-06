package com.softuni.superMarket;

import com.softuni.superMarket.services.*;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
@AllArgsConstructor
public class AppController implements CommandLineRunner {


    private TownService townService;
    private CategoryService categoryService;
    private ShopService shopService;
    private SellerService sellerService;
    private ProductService productService;

    @Override
    public void run(String... args) {

        Scanner sc = new Scanner(System.in);
        while (true) {
            String format = """
                    Hi
                    Choose option from:
                    1 - for Add Category
                    2 - for Add Town
                    3 - for Add Shop
                    4 - for Add Seller
                    5 - for Add Product
                    6 - for Set seller new manager
                    7 - for Distributing product in shops
                    8 - for Showing all sellers in Shop
                    9 - for Showing all products in Shop
                    10 - for exit""";
            System.out.println(format);


            int options = Integer.parseInt(sc.nextLine());

            switch (options) {
                case 1:
                    System.out.println("Enter category name:");
                    addedCategory(sc.nextLine());
                    break;
                case 2:
                    System.out.println("Enter town name");
                    addedTown(sc.nextLine());
                    break;
                case 3:
                    System.out.println("Enter shop details in format: name address town");
                    String shopDetails = sc.nextLine();
                    addedShop(shopDetails);
                    break;
                case 4:
                    System.out.println("Enter seller details in format: firstName lastName age salary shopName");
                    String sellerDetails = sc.nextLine();
                    addedSeller(sellerDetails);
                    break;
                case 5:
                    System.out.println("Enter product details in format: name price bestBefore(dd-MM-yyyy) category");
                    String productDetails = sc.nextLine();
                    addedProduct(productDetails);
                    break;
                case 6:
                    System.out.println("Enter seller first and last names:");
                    String firstAndLastNamesSeller = sc.nextLine();
                    System.out.println("Enter manager first and last names:");
                    String firstAndLastNamesManager = sc.nextLine();
                    addedManager(firstAndLastNamesSeller,firstAndLastNamesManager);
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 9:
                    break;
                case 10:
                    return;
            }

        }

    }

    private void addedManager(String sellerNameAndLastName, String managerNameAndLastName) {
        System.out.println(sellerService.addedManager(sellerNameAndLastName,managerNameAndLastName));
    }

    private void addedProduct(String productDetails) {
        System.out.println(productService.addedProduct(productDetails));
    }

    private void addedSeller(String sellerDetails) {
        System.out.println(sellerService.addedSeller(sellerDetails));
    }

    private void addedShop(String shopDetails) {
        System.out.println(shopService.addShop(shopDetails));
    }

    private void addedTown(String name) {
        System.out.println(townService.addTown(name));
    }

    private void addedCategory(String name) {
        String info = categoryService.addCategory(name);
        System.out.println(info);
    }
}
