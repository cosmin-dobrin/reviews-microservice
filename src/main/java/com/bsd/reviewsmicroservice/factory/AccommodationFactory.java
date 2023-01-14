package com.bsd.reviewsmicroservice.factory;

import com.bsd.reviewsmicroservice.domain.Accommodation;
import com.bsd.reviewsmicroservice.domain.User;
import com.bsd.reviewsmicroservice.domain.dto.AccommodationDto;
import com.bsd.reviewsmicroservice.domain.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccommodationFactory {

    public Accommodation toEntity(AccommodationDto accommodationDto) {
        Accommodation accommodation = new Accommodation();
        accommodation.setAccommodationId(accommodationDto.accommodationId);
        return accommodation;
    }
}
