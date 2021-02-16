package com.coderscampus.olaf.assignment09.web;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coderscampus.olaf.assignment09.dto.RecipeDto;
import com.coderscampus.olaf.assignment09.service.RecipesService;

@RestController
public class RecipesController {
	
	@Autowired
	private RecipesService recipesService;	
	
	@GetMapping("/gluten-free")
	public List<RecipeDto> filterGlutenFree () throws IOException {
		 return recipesService.filterRecipes("glutenFree");
	}

	@GetMapping("/vegan")
	public List<RecipeDto> filterVegan () throws IOException {
		 return recipesService.filterRecipes("vegan");
	}

	@GetMapping("/vegan-and-gluten-free")
	public List<RecipeDto> filterVeganGlutenFree () throws IOException {
		 return recipesService.filterRecipes("veganGlutenFree");
	}

	@GetMapping("/vegetarian")
	public List<RecipeDto> filterVegetarian () throws IOException {
		 return recipesService.filterRecipes("vegetarian");
	}

	@GetMapping("/all-recipes")
	public List<RecipeDto> filterAllRecipes () throws IOException {
		 return recipesService.filterRecipes("all");
	}
	
	@GetMapping("")
	public String root (){
		 return ("Nothing to be found here... \"secret\" URLs required!");
	}
}
