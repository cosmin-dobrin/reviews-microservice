package com.bsd.reviewsmicroservice.repository;

import com.bsd.reviewsmicroservice.domain.Accommodation;
import com.bsd.reviewsmicroservice.domain.Review;
import com.bsd.reviewsmicroservice.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccommodationRepository extends JpaRepository<Accommodation, Long> {

    List<Accommodation> findAllByAccommodationId(Long accommodationId);
}
