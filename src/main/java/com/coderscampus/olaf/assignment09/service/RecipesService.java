package com.coderscampus.olaf.assignment09.service;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderscampus.olaf.assignment09.dto.RecipeDto;

@Service
public class RecipesService {
	
	@Autowired
	private RecipesBook recipesBook;

	public List<RecipeDto> readRecipes() throws IOException {
		
		Reader importedList = new FileReader("recipes.txt");

		Iterable<CSVRecord> csvRecipes = CSVFormat.DEFAULT.withFirstRecordAsHeader()
												  		  .withDelimiter(',')
												  		  .withEscape('\\')
												  		  .withIgnoreSurroundingSpaces()
												  		  .parse(importedList);

		for (CSVRecord recipe : csvRecipes) {
			Integer cookingMinutes = Integer.parseInt(recipe.get("Cooking Minutes"));
			Boolean dairyFree = Boolean.parseBoolean(recipe.get("Dairy Free"));
			Boolean glutenFree = Boolean.parseBoolean(recipe.get("Gluten Free"));
			String instructions = recipe.get("Instructions");
			Double preparationMinutes = Double.parseDouble(recipe.get("Preparation Minutes"));
			Double pricePerServing = Double.parseDouble(recipe.get("Price Per Serving"));
			Integer readyInMinutes = Integer.parseInt(recipe.get("Ready In Minutes"));
			Integer servings = Integer.parseInt(recipe.get("Servings"));
			Double spoonacularScore = Double.parseDouble(recipe.get("Spoonacular Score"));
			String title = recipe.get("Title");
			Boolean vegan = Boolean.parseBoolean(recipe.get("Vegan"));
			Boolean vegetarian = Boolean.parseBoolean(recipe.get("Vegetarian"));

			recipesBook.getRecipes().add(new RecipeDto(cookingMinutes, dairyFree, glutenFree, instructions, preparationMinutes,
					pricePerServing, readyInMinutes, servings, spoonacularScore, title, vegan, vegetarian));
			
		}

		return recipesBook.getRecipes();
	}

	public List<RecipeDto> filterRecipes(String filter) throws IOException {
		
		if (recipesBook.getRecipes().isEmpty()) {
			readRecipes();
		}
		
		switch (filter) {
		case "glutenFree": 
			return recipesBook.getRecipes()
							 .stream()
							 .filter(RecipeDto::getGlutenFree)
							 .collect(Collectors.toList());
		
		case "vegan":
			return recipesBook.getRecipes()
							 .stream()
							 .filter(RecipeDto::getVegan)
							 .collect(Collectors.toList());
		
		case "veganGlutenFree":
			return recipesBook.getRecipes()
							 .stream()
							 .filter(RecipeDto::getVegan)
							 .filter(RecipeDto::getGlutenFree)
							 .collect(Collectors.toList());
		
		case "vegetarian":
			return recipesBook.getRecipes()
							 .stream()
							 .filter(RecipeDto::getVegetarian)
							 .collect(Collectors.toList());
			
		case "all":
			return recipesBook.getRecipes();
			
		default: return null;
		
		}		
		
	}

}
