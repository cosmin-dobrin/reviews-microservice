package com.bsd.reviewsmicroservice.repository;

import com.bsd.reviewsmicroservice.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findAllByAccommodationId(Long accommodationId);

    List<Review> findAllByUserId(Long userId);

    List<Review> findAllByUserIdAndAccommodationId(Long userId, Long accommodationId);
}
