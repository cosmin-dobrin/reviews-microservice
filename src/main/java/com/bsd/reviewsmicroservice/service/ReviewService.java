package com.bsd.reviewsmicroservice.service;

import com.bsd.reviewsmicroservice.repository.AccommodationRepository;
import com.bsd.reviewsmicroservice.repository.ReviewRepository;
import com.bsd.reviewsmicroservice.domain.Review;
import com.bsd.reviewsmicroservice.domain.dto.ReviewDto;
import com.bsd.reviewsmicroservice.factory.ReviewFactory;
import com.bsd.reviewsmicroservice.repository.UserRepository;
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
    private final UserRepository userRepository;
    private final AccommodationRepository accommodationRepository;
    private final ReviewFactory reviewFactory;

    public Optional<Review> addReview(ReviewDto reviewDto) {

        if (checkIfAccommodationExists(reviewDto) && checkIfUserExists(reviewDto)) {
            Review review = reviewFactory.toBaseEntity(reviewDto);
            review.setUser(userRepository.getReferenceById(reviewDto.getUserDto().getUserId()));
            review.setAccommodation(accommodationRepository.getReferenceById(reviewDto.getAccommodationDto().getAccommodationId()));
            return Optional.of(reviewRepository.save(review));
        }

        if (checkIfAccommodationExists(reviewDto)) {
            Review review = reviewFactory.toEntityIfAccommodationExists(reviewDto);
            review.setAccommodation(accommodationRepository.getReferenceById(getAccommodationId(reviewDto)));
            return Optional.of(reviewRepository.save(review));
        }

        if (checkIfUserExists(reviewDto)) {
            Review review = reviewFactory.toEntityIfUserExists(reviewDto);
            review.setUser(userRepository.getReferenceById(getUserId(reviewDto)));
            return Optional.of(reviewRepository.save(review));
        }

       return Optional.of(reviewRepository.save(reviewFactory.toEntity(reviewDto)));
    }

    @Transactional
    public List<Review> getReviewsByAccommodation(Long accommodationId) {
        List<Review> reviews = reviewRepository.findAllByAccommodationAccommodationId(accommodationId);
        reviews.sort(Collections.reverseOrder(Comparator.comparing(Review::getReviewId)));

        return reviews;
    }

    @Transactional
    public List<Review> getReviewsByUser(Long userId) {
        List<Review> reviews = reviewRepository.findAllByUserUserId(userId);
        reviews.sort(Collections.reverseOrder(Comparator.comparing(Review::getReviewId)));

        return reviews;
    }
    @Transactional
    public List<Review> getReviewsByUserAndAccommodation(Long userId, Long accommodationId) {
        List<Review> reviews = reviewRepository.findAllByUserUserIdAndAccommodationAccommodationId(userId, accommodationId);
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


    private Long getAccommodationId(ReviewDto reviewDto) {
        return reviewDto.getAccommodationDto().getAccommodationId();
    }

    private Long getUserId(ReviewDto reviewDto) {
        return reviewDto.getUserDto().getUserId();
    }

    private boolean checkIfUserExists(ReviewDto reviewDto) {
        return userRepository.existsById(getUserId(reviewDto));
    }

    private boolean checkIfAccommodationExists(ReviewDto reviewDto) {
        return accommodationRepository.existsById(getAccommodationId(reviewDto));
    }
}
