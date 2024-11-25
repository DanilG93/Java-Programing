package com.bonappetit.repo;

import com.bonappetit.model.entity.CategoryName;
import com.bonappetit.model.entity.Recipe;
import com.bonappetit.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {


    List<Recipe> findAllByCategoryName(CategoryName categoryName);

//    @Query("SELECT r FROM Recipe r " +
//            "JOIN r.favoriteRecipes f " +
//            "WHERE r.addedBy.id = :addedById " +
//            "AND f.id = :favoriteRecipeId")
//
//    List<Recipe> findAllBYFavoriteRecipes(@Param("addedById") Long addedById,
//                                                    @Param("favoriteRecipeId") Long favoriteRecipeId);


}
