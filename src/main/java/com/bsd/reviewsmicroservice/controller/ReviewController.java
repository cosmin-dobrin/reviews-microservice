package com.bsd.reviewsmicroservice.controller;

import com.bsd.reviewsmicroservice.service.ReviewService;
import com.bsd.reviewsmicroservice.translator.ReviewTranslator;
import com.bsd.reviewsmicroservice.domain.dto.ReviewDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    private final ReviewTranslator reviewTranslator;

    @PostMapping
    public ResponseEntity<ReviewDto> addReview(@RequestBody ReviewDto reviewDto) {
        return reviewService.addReview(reviewDto)
                            .map(review -> ResponseEntity.ok(reviewTranslator.generateReviewDto(review)))
                            .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    @GetMapping("/by-user")
    public ResponseEntity<List<ReviewDto>> getReviewsByUser(@RequestParam Long userId) {
        return ResponseEntity.ok(
                reviewTranslator.generateReviewDtoList(
                        reviewService.getReviewsByUser(userId)));
    }

    @GetMapping("/by-accommodation")
    public ResponseEntity<List<ReviewDto>> getReviewsByAccommodation(@RequestParam Long accommodationId) {
        return ResponseEntity.ok(
                reviewTranslator.generateReviewDtoList(
                        reviewService.getReviewsByAccommodation(accommodationId)));
    }

    @GetMapping("/by-user-accommodation")
    public ResponseEntity<List<ReviewDto>> getReviewsByUserAndAccommodation(@RequestParam Long userId,
                                                                            @RequestParam Long accommodationId) {
        return ResponseEntity.ok(
                reviewTranslator.generateReviewDtoList(
                        reviewService.getReviewsByUserAndAccommodation(userId, accommodationId)));
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<?> deleteReview(@PathVariable Long reviewId) {
        reviewService.deleteReview(reviewId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<?> deleteReviewsByUserAndAccommodation(@RequestParam Long userId,
                                                                 @RequestParam Long accommodationId) {
        reviewService.deleteReviewsByUserAndAccommodation(userId, accommodationId);
        return ResponseEntity.ok().build();
    }
}
