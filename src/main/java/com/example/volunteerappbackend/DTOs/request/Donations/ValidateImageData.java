package com.example.volunteerappbackend.DTOs.request.Donations;

import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Base64;
import java.util.List;

@Constraint(validatedBy = ImageDataValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidateImageData {
    String message() default "Invalid image data, image must be Base64, and height amd width must be at least 100 px";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

@Component
class ImageDataValidator implements ConstraintValidator<ValidateImageData, List<String>> {
    private String message;

    @Override
    public void initialize(ValidateImageData constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
    @Override
    public boolean isValid(List<String> value, ConstraintValidatorContext context) {
        int counter = 0;
        if (value == null || value.isEmpty()) {
            return true;
        }
        for(String val : value) {
            if (!isValidBase64(val)) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("Invalid image data in the list. Problem with image: " + counter)
                        .addConstraintViolation();
                return false;
            }
            counter++;
        }
        return true;
    }

    private boolean isValidBase64(String base64Image) {
        try{
            byte[] base64Bytes = Base64.getDecoder().decode(base64Image);
            BufferedImage image = ImageIO.read(new ByteArrayInputStream(base64Bytes));
            if (image == null) {
                return false;
            }
            return image.getWidth() >= 100 && image.getHeight() >= 100;
        } catch (IllegalArgumentException | IOException e) {
            return false;
        }

    }
}


/*
* import javax.imageio.ImageIO;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

public class ImageListValidator implements ConstraintValidator<ValidateImageList, List<String>> {
    @Override
    public void initialize(ValidateImageList constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(List<String> photos, ConstraintValidatorContext context) {
        if (photos == null || photos.isEmpty()) {
            return true; // Consider empty list as valid, change if needed
        }

        for (String photo : photos) {
            if (!isValidImage(photo)) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("Invalid image data in the list")
                       .addConstraintViolation();
                return false;
            }
        }

        return true;
    }

    private boolean isValidImage(String base64Image) {
        try {
            byte[] imageBytes = Base64.getDecoder().decode(base64Image);
            BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageBytes));
            if (image == null) {
                return false;
            }

        } catch (IllegalArgumentException | IOException e) {
            return false;
        }
    }
}
* */