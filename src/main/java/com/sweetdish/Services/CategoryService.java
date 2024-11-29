package com.sweetdish.Services;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sweetdish.Dto.CategoriesDto;
import com.sweetdish.GlobalErrors.CommonError;
import com.sweetdish.Modulers.Categories;
import com.sweetdish.Repositories.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository cateRepo;
	

    /* creating category to add items in category table (e.g. drinks,sweets,main course)  */
	public CategoriesDto createCategory(CategoriesDto dto) {
		
		 if (dto.getName() == null || dto.getName().isEmpty()) {
	    			    throw new CommonError("Category name cannot be empty");
	        }
		 
		 if (cateRepo.existsByName(dto.getName())) {
			    throw new CommonError("Category with this name already exists");
	        }
		
		Categories category=new Categories();
		category.setName(dto.getName());
		Categories data=cateRepo.save(category);
		
		CategoriesDto result=new CategoriesDto(data.getId(),data.getName(),data.getCreatedAt(),data.getUpdatedAt());
		return result;
	}
	
	
	//get all categories
	public List<CategoriesDto> AllCategories(){
		List<CategoriesDto>result=cateRepo.findAll().stream().map(data->
		new CategoriesDto(data.getId(),data.getName(),data.getCreatedAt(),data.getUpdatedAt())
		).collect(Collectors.toList());
		
	    if (result.isEmpty()) {
		    throw new CommonError("The categories list is empty");
	    }
		return result;
	}
	
	
	//update category
		public String updateCategory(Long id, CategoriesDto dto) {
		    // Validate if ID is null or empty
		    if (id == null) {
		        throw new CommonError("Category ID cannot be empty");
		    }

		    // Validate if name is null or empty
		    if (dto == null || dto.getName() == null || dto.getName().isEmpty()) {
		        throw new CommonError("Category name cannot be empty");
		    }

		    // Check if category with the provided ID exists
		    Categories existingCategory = cateRepo.findById(id)
		            .orElseThrow(() -> new CommonError("Category not found with ID: " + id));

		    // Check if another category with the same name already exists (unique constraint)
		    if (cateRepo.existsByName(dto.getName()) && !existingCategory.getName().equalsIgnoreCase(dto.getName())) {
		        throw new CommonError("Category with this name already exists: " + dto.getName());
		    }

		    // Update the existing category details
		    existingCategory.setName(dto.getName());
		    existingCategory.setUpdatedAt(LocalDateTime.now());

		    // Save the updated category to the database
		    cateRepo.save(existingCategory);

		    return "Category updated successfully!";
		}

	

}
