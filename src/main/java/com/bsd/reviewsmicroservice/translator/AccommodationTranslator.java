package com.bsd.reviewsmicroservice.translator;

import com.bsd.reviewsmicroservice.domain.Accommodation;
import com.bsd.reviewsmicroservice.domain.User;
import com.bsd.reviewsmicroservice.domain.dto.AccommodationDto;
import com.bsd.reviewsmicroservice.domain.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AccommodationTranslator {

    public AccommodationDto generateAccommodationDto(Accommodation accommodation) {
        AccommodationDto accommodationDto = new AccommodationDto();
        accommodationDto.setAccommodationId(accommodation.getAccommodationId());
        return accommodationDto;
    }

    public List<AccommodationDto> generateAccommodationDtoList(List<Accommodation> accommodations) {
        return accommodations.stream().map(this::generateAccommodationDto).collect(Collectors.toList());
    }
}
