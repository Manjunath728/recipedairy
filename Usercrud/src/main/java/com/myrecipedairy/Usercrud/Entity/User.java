package com.myrecipedairy.Usercrud.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@Pattern(regexp = "^[a-zA-Z ]+$", message = "Recipe name must contain only text (no numbers)")
    @NotBlank(message = "name should not be blank")
    @NotNull(message = "name should not be null")
    @Column(name = "name", nullable = false)
    private String name;

    @NotBlank(message = "Email should not be blank")
    @NotNull(message = "Email should not be null")
    @Email(regexp = ".+@.+\\..+", message = "Email should be valid")
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
