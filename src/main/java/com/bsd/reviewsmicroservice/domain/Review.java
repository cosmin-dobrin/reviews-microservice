package com.bsd.reviewsmicroservice.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @Column(name = "review_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reviews_id_seq_name")
    @SequenceGenerator(name = "reviews_id_seq_name", sequenceName = "reviews_id_seq", allocationSize = 1)
    private Long reviewId;

    @Column(name = "stars")
    private int stars;

    @Column(name = "comment", columnDefinition="TEXT")
    private String comment;

    @Column(name = "timestamp")
    private LocalDate timestamp;

    @Column(name = "accommodation_id")
    private Long accommodationId;

    @Column(name = "user_id")
    private Long userId;

    public Review() {
    }

    public Review(int stars, String comment, LocalDate timestamp, Long accommodationId, Long userId) {
        this.stars = stars;
        this.comment = comment;
        this.timestamp = timestamp;
        this.accommodationId = accommodationId;
        this.userId = userId;
    }
}
