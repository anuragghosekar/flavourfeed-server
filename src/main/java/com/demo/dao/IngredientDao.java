package com.demo.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.demo.models.Ingredient;

@Repository
public interface IngredientDao extends JpaRepository<Ingredient,Integer> {
	
	@Query("SELECT DISTINCT i FROM Ingredient i")
	List<Ingredient> findAllDistinctIngredientNames();
}