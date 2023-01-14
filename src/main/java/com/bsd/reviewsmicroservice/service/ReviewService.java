package com.bsd.reviewsmicroservice.service;

import com.bsd.reviewsmicroservice.domain.Accommodation;
import com.bsd.reviewsmicroservice.domain.User;
import com.bsd.reviewsmicroservice.repository.ReviewRepository;
import com.bsd.reviewsmicroservice.domain.Review;
import com.bsd.reviewsmicroservice.domain.dto.ReviewDto;
import com.bsd.reviewsmicroservice.factory.ReviewFactory;
import com.bsd.reviewsmicroservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    private final UserRepository userRepository;

    private final ReviewFactory reviewFactory;

//    private final SecurityService securityService;

//    private final AccommodationRepository accommodationRepository;

    public Optional<Review> addReview(ReviewDto reviewDto) {
//        User user = securityService.getLoggedInUser();
//        Accommodation accommodation = accommodationRepository.findByAccommodationId(reviewDto.getAccommodationDto().getAccommodationId())
//                                                             .orElseThrow(() -> new RuntimeException("Could not find ski resort with id: " + reviewDto.getAccommodationDto().getAccommodationId()));

        if (userRepository.existsById(reviewDto.getUserDto().getUserId())) {
            Review review = reviewFactory.toEntityIfUserExists(reviewDto);
            review.setUser(userRepository.getReferenceById(reviewDto.getUserDto().getUserId()));

            return Optional.of(reviewRepository.save(review));
        }

       return Optional.of(reviewRepository.save(reviewFactory.toEntity(reviewDto)));
    }

//    @Transactional
//    public List<Review> getReviewsByAccommodation(Long accommodationId) {
//        List<Review> reviews = reviewRepository.findAllByAccommodationAccommodationId(accommodationId);
//        reviews.sort(Collections.reverseOrder(Comparator.comparing(Review::getReviewId)));
//
//        return reviews;
//    }

    public List<Review> getReviewsByUser(Long userId) {
        List<Review> reviews = reviewRepository.findAllByUserUserId(userId);
        reviews.sort(Collections.reverseOrder(Comparator.comparing(Review::getReviewId)));

        return reviews;
    }

    public void deleteReview(Long reviewId) {
        reviewRepository.deleteById(reviewId);
    }

//    public void deleteReviewsByAccommodation(Long accommodationId) {
//        getReviewsByAccommodation(accommodationId).forEach(review -> deleteReview(review.getReviewId()));
//    }

    public void deleteReviewsByUser(Long userId) {
        getReviewsByUser(userId).forEach(review -> deleteReview(review.getReviewId()));
    }
}
