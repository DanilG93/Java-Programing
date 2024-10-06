package com.softuni.superMarket.services.impl;

import com.softuni.superMarket.db.entity.Category;
import com.softuni.superMarket.db.entity.Product;
import com.softuni.superMarket.db.repositories.ProductRepository;
import com.softuni.superMarket.services.CategoryService;
import com.softuni.superMarket.services.ProductService;
import com.softuni.superMarket.util.ValidatorUtil;
import jakarta.validation.ConstraintViolation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private CategoryService categoryService;
    private ProductRepository productRepository;
    private ValidatorUtil validatorUtil;

    @Override
    public String addedProduct(String productInfo) {

        StringBuilder sb = new StringBuilder();

        String[] productInfoArr = productInfo.split(" ");
        String nameProduct = productInfoArr[0];
        BigDecimal price = BigDecimal.valueOf(Double.parseDouble(productInfoArr[1]));
        String[] dataText = productInfoArr[2].split("-");
        LocalDate bestBefore = LocalDate.of(Integer.parseInt(dataText[2])
                , Integer.parseInt(dataText[1])
                , Integer.parseInt(dataText[0]));

        String categoryName = productInfoArr[3];
        Category categoryByName = categoryService.findByName(categoryName);
        if (categoryByName == null) {
            categoryService.addCategory(categoryName);
            categoryByName = categoryService.findByName(categoryName);
        }

        Product product = new Product(bestBefore, " ", nameProduct, price, categoryByName, new ArrayList<>());
        product.setId(UUID.randomUUID().toString());

        if (validatorUtil.isValid(product)) {
            productRepository.save(product);
            return "Successfully added product!";
        }

        Set<ConstraintViolation<Product>> violations = validatorUtil.getViolations(product);
        violations.forEach(v -> sb.append(v.getMessage()).append(System.lineSeparator()));


        return sb.toString().trim();
    }
}
