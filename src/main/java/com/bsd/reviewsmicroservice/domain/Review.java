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

//    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
//    @JoinColumn(name = "accommodation_id", foreignKey = @ForeignKey(name = "FK_accommodation_resort"))
//    private Accommodation accommodation;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "FK_review_user"))
    private User user;

    public Review() {
    }

    public Review(int stars, String comment, LocalDate timestamp, User user) {
        this.stars = stars;
        this.comment = comment;
        this.timestamp = timestamp;
//        this.accommodation = accommodation;
        this.user = user;
    }
}
