package com.myrecipedairy.recipecrud.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.myrecipedairy.recipecrud.Entity.Recipe;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

	List<Recipe> findByAccesstype(String accesstype);
}