package com.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.demo.dao.MealPlanningDao;
import com.demo.models.MealPlanning;
import com.demo.models.User;


@Service
public class MealPlanningServiceImpl implements MealPlanningService {

	@Autowired
	 private MealPlanningDao mdao;

	@Override
	public List<MealPlanning> getAllMealPlannings() {
		return mdao.findAll();
	}

	@Override
	public Boolean addNewMealPlanning(MealPlanning mp) {
		try {
	        mdao.save(mp);
	        return true;
	    } catch (Exception e){
	        return false;
	    }
	}

	@Override
	public MealPlanning getMealPlanningById(int mid) {
		Optional<MealPlanning> op=mdao.findById(mid);
		 if(op.isPresent()) {
			 return op.get();
		 }
		 return null;
	}

	@Override
	public Boolean updateMealPlanningById(MealPlanning mp) {
		Optional<MealPlanning> op=mdao.findById(mp.getMealId());
		if(op.isPresent()) {
			MealPlanning mp1=op.get();
			mp1.setMealTime(mp.getMealTime());
			mp1.setTotalCalories(mp.getTotalCalories());
			mp1.setQuantity(mp.getQuantity());
			mp1.setUserMeals(mp.getUserMeals());
			mp1.setUserMeals(mp.getUserMeals());
			if(mdao.save(mp1)!=null) {
				return true;
			};
		}
		return false;
	}

	@Override
	public Boolean deleteMealPlanningById(int mpid) {
		if (mdao.existsById(mpid)) {
			mdao.deleteById(mpid);
			return true;
		}
		return false;	
	}
	
	public Set<User> getUsersByMealPlanning(int mealPlanningId) {
	    MealPlanning mealPlanning = mdao.findById(mealPlanningId).orElse(null);
	    if (mealPlanning != null) {
	        return mealPlanning.getUserMeals();
	    } else {
	        return null;
	    }
	}	
}