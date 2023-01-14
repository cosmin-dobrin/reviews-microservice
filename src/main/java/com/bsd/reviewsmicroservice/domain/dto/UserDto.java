package com.bsd.reviewsmicroservice.domain.dto;

import com.bsd.reviewsmicroservice.domain.Review;
import com.bsd.reviewsmicroservice.domain.enums.RoleType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties
public class UserDto {

    public Long userId;

    public String firstName;

    public String lastName;

    public String email;

    public RoleType roleType;
}
