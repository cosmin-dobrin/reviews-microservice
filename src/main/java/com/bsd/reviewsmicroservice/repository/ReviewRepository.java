package com.bsd.reviewsmicroservice.repository;

import com.bsd.reviewsmicroservice.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findAllByAccommodationAccommodationId(Long accommodationId);

    List<Review> findAllByUserUserId(Long accommodationId);
}
