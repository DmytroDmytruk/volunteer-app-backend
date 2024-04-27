package com.example.volunteerappbackend.DTOs.mappers;

import com.example.volunteerappbackend.DTOs.request.Auth.SignUpRequest;
import com.example.volunteerappbackend.Database.entities.User;
import org.modelmapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class UserMapper {

    private final ModelMapper modelMapper;
    private final Converter<String, LocalDate> toStringDate = new AbstractConverter<String, LocalDate>() {
        @Override
        protected LocalDate convert(String s) {
            return LocalDate.parse(s, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        }
    };
    Provider<LocalDate> localDateProvider = new AbstractProvider<LocalDate>() {
        @Override
        public LocalDate get() {
            return LocalDate.now();
        }
    };

    @Autowired
    public UserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.modelMapper.addConverter(toStringDate);
        this.modelMapper.getTypeMap(String.class, LocalDate.class).setProvider(localDateProvider);
    }

    public User toEntity(SignUpRequest request) {
        return modelMapper.map(request, User.class);
    }
}

