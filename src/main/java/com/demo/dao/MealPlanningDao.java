package com.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.demo.models.MealPlanning;

@Repository
public interface MealPlanningDao extends JpaRepository<MealPlanning, Integer> {

}