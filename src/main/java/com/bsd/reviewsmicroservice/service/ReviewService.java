package com.bsd.reviewsmicroservice.service;

import com.bsd.reviewsmicroservice.repository.ReviewRepository;
import com.bsd.reviewsmicroservice.domain.Review;
import com.bsd.reviewsmicroservice.domain.dto.ReviewDto;
import com.bsd.reviewsmicroservice.factory.ReviewFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewFactory reviewFactory;

    public Optional<Review> addReview(ReviewDto reviewDto) {
        return Optional.of(reviewRepository.save(reviewFactory.toEntity(reviewDto)));
    }

    @Transactional
    public List<Review> getReviewsByAccommodation(Long accommodationId) {
        List<Review> reviews = reviewRepository.findAllByAccommodationId(accommodationId);
        reviews.sort(Collections.reverseOrder(Comparator.comparing(Review::getReviewId)));

        return reviews;
    }

    @Transactional
    public List<Review> getReviewsByUser(Long userId) {
        List<Review> reviews = reviewRepository.findAllByUserId(userId);
        reviews.sort(Collections.reverseOrder(Comparator.comparing(Review::getReviewId)));

        return reviews;
    }
    @Transactional
    public List<Review> getReviewsByUserAndAccommodation(Long userId, Long accommodationId) {
        List<Review> reviews = reviewRepository.findAllByUserIdAndAccommodationId(userId, accommodationId);
        reviews.sort(Collections.reverseOrder(Comparator.comparing(Review::getReviewId)));

        return reviews;
    }

    public void deleteReview(Long reviewId) {
        reviewRepository.deleteById(reviewId);
    }

    public void deleteReviewsByAccommodation(Long accommodationId) {
        getReviewsByAccommodation(accommodationId).forEach(review -> deleteReview(review.getReviewId()));
    }

    public void deleteReviewsByUser(Long userId) {
        getReviewsByUser(userId).forEach(review -> deleteReview(review.getReviewId()));
    }

    public void deleteReviewsByUserAndAccommodation(Long userId, Long accommodationId) {
        getReviewsByUserAndAccommodation(userId, accommodationId).forEach(review -> deleteReview(review.getReviewId()));
    }
}
