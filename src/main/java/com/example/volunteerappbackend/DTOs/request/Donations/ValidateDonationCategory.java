package com.example.volunteerappbackend.DTOs.request.Donations;

import com.example.volunteerappbackend.Database.repos.DonationCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = DonationCategoryValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidateDonationCategory {
    String message() default "Invalid donation category, it doesn`t exist in db";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

@Component
class DonationCategoryValidator implements ConstraintValidator<ValidateDonationCategory, String> {
    @Autowired
    private DonationCategoryRepository donationCategoryRepository;

    @Override
    public void initialize(ValidateDonationCategory constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return donationCategoryRepository.existsByCategoryName(value);
    }
}
