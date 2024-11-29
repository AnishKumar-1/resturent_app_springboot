package com.sweetdish.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sweetdish.Dto.CategoriesDto;
import com.sweetdish.Services.CategoryService;

@RestController
public class CategoryController {

	
	@Autowired
	private CategoryService service;
	
	@PostMapping("/category")
	public ResponseEntity<CategoriesDto> createCategory(@RequestBody CategoriesDto category){
		return new ResponseEntity<>(service.createCategory(category),HttpStatus.CREATED);
	}
	
	   // Endpoint to fetch all categories
    @GetMapping("/category")
    public ResponseEntity<List<CategoriesDto>> getAllCategories() {
        List<CategoriesDto> categories = service.AllCategories();
        if (categories.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
        }
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }
    
    @PutMapping("category/{id}")
    public ResponseEntity<String> updateCategory(
            @PathVariable Long id,
            @RequestBody CategoriesDto categoryDto) {

        // Call the service method to update the category
        String response = service.updateCategory(id, categoryDto);

        // Return a success response
        return ResponseEntity.ok(response);
    }
	
}
