package com.coderscampus.olaf.assignment09.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.coderscampus.olaf.assignment09.dto.RecipeDto;

@Repository
public class RecipesBook {
	
	private List<RecipeDto> recipes = new ArrayList<>(100);

	public List<RecipeDto> getRecipes() {
		return recipes;
	}

	public void setRecipes(List<RecipeDto> recipes) {
		this.recipes = recipes;
	}

}
