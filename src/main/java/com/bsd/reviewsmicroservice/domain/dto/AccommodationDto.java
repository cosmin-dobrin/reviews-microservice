package com.bsd.reviewsmicroservice.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties
public class AccommodationDto {

    public Long accommodationId;
}
