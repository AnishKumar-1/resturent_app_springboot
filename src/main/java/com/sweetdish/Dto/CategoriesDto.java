package com.sweetdish.Dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoriesDto {

	private Long id;
	private String name;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
