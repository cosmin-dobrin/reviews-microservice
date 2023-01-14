package com.bsd.reviewsmicroservice.translator;

import com.bsd.reviewsmicroservice.domain.Review;
import com.bsd.reviewsmicroservice.domain.dto.ReviewDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ReviewTranslator {

//    private final SkiResortTranslator skiResortTranslator;

    private final UserTranslator userTranslator;

    public ReviewDto generateReviewDto(Review review) {
        ReviewDto reviewDto = new ReviewDto();

        reviewDto.setReviewId(review.getReviewId());
        reviewDto.setStars(review.getStars());
        reviewDto.setComment(review.getComment());
        reviewDto.setTimestamp(review.getTimestamp());
//        reviewDto.setSkiResortDto(skiResortTranslator.generateSkiResortDto(review.getSkiResort()));
        reviewDto.setUserDto(userTranslator.generateUserDto(review.getUser()));

        return reviewDto;
    }

    public List<ReviewDto> generateReviewDtoList(List<Review> reviews) {
        return reviews.stream()
                      .map(this::generateReviewDto)
                      .collect(Collectors.toList());
    }
}
