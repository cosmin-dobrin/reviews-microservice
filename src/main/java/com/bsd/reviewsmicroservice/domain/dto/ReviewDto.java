package com.bsd.reviewsmicroservice.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonIgnoreProperties
public class ReviewDto {

    public Long reviewId;

    public int stars;

    public String comment;

    public LocalDate timestamp;

    public Long accommodationId;

    public Long userId;
}
