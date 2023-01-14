package com.bsd.reviewsmicroservice.factory;

import com.bsd.reviewsmicroservice.domain.Review;
import com.bsd.reviewsmicroservice.domain.dto.ReviewDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReviewFactory {

    private final AccommodationFactory accommodationFactory;
    private final UserFactory userFactory;

    public Review toBaseEntity(ReviewDto reviewDto) {
        Review review = new Review();

        review.setReviewId(reviewDto.getReviewId());
        review.setStars(reviewDto.getStars());
        review.setComment(reviewDto.getComment());
        review.setTimestamp(reviewDto.getTimestamp());

        return review;
    }

    public Review toEntity(ReviewDto reviewDto) {
        Review review = new Review();

        review.setReviewId(reviewDto.getReviewId());
        review.setStars(reviewDto.getStars());
        review.setComment(reviewDto.getComment());
        review.setTimestamp(reviewDto.getTimestamp());
        review.setAccommodation(accommodationFactory.toEntity(reviewDto.getAccommodationDto()));
        review.setUser(userFactory.toEntity(reviewDto.getUserDto()));

        return review;
    }

    public Review toEntityIfUserExists(ReviewDto reviewDto) {
        Review review = new Review();

        review.setReviewId(reviewDto.getReviewId());
        review.setStars(reviewDto.getStars());
        review.setComment(reviewDto.getComment());
        review.setTimestamp(reviewDto.getTimestamp());
        review.setAccommodation(accommodationFactory.toEntity(reviewDto.getAccommodationDto()));

        return review;
    }

    public Review toEntityIfAccommodationExists(ReviewDto reviewDto) {
        Review review = new Review();

        review.setReviewId(reviewDto.getReviewId());
        review.setStars(reviewDto.getStars());
        review.setComment(reviewDto.getComment());
        review.setTimestamp(reviewDto.getTimestamp());
        review.setUser(userFactory.toEntity(reviewDto.getUserDto()));

        return review;
    }
}
