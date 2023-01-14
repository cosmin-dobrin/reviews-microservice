package com.bsd.reviewsmicroservice.controller;

import com.bsd.reviewsmicroservice.service.ReviewService;
import com.bsd.reviewsmicroservice.translator.ReviewTranslator;
import com.bsd.reviewsmicroservice.domain.dto.ReviewDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
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

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<?> deleteReview(@PathVariable("reviewId") Long reviewId) {
        reviewService.deleteReview(reviewId);
        return ResponseEntity.ok().build();
    }

    //    @GetMapping("/by-accommodation")
//    public ResponseEntity<List<ReviewDto>> getReviewsByAccommodation(@RequestParam Long accommodationId) {
//        return ResponseEntity.ok(
//                reviewTranslator.generateReviewDtoList(
//                        reviewService.getReviewsByAccommodation(accommodationId)));
//    }
}


//        URI uri = UriComponentsBuilder.fromHttpUrl("localhost:8080/users/" + userId)
//                                      .build()
//                                      .toUri();
//
//        HttpRequest request = HttpRequest.newBuilder()
//                                         .uri(uri)
//                                         .method("GET", HttpRequest.BodyPublishers.noBody())
//                                         .build();

//        HttpResponse<String> response = HttpClient.newHttpClient()
//                                                  .send(request, HttpResponse.BodyHandlers.ofString());
//        System.out.println(response.body());

//        JSONArray income = JsonPath.parse(response.body()).read("$.");

//        System.out.println(income);