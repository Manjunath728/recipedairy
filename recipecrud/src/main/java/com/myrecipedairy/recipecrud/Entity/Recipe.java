package com.myrecipedairy.recipecrud.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "recipe")
public class Recipe {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@Pattern(regexp = "^[a-zA-Z ]+$", message = "Recipe name must contain only text (no numbers)")
    @NotBlank(message = "recipename should not be blank")
    @NotNull(message = "recipename should not be null")
    @Column(name = "recipename", nullable = false)
    private String recipename;
	
    @NotBlank(message = "category should not be blank")
    @NotNull(message = "category should not be null")
    @Column(name = "category")
    private String category;
	@Pattern(regexp = "^(public|private)$", message = "Access type must be either 'public' or 'private'")
    @NotBlank(message = "accesstype should not be blank")
    @NotNull(message = "accesstype should not be null")
    @Column(name = "accesstype", nullable = false)
    private String accesstype;

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRecipename() {
        return recipename;
    }

    public void setRecipename(String recipename) {
        this.recipename = recipename;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAccesstype() {
        return accesstype;
    }

    public void setAccesstype(String accesstype) {
        this.accesstype = accesstype;
    }
}
