package com.bsd.reviewsmicroservice.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "accommodations")
public class Accommodation {

    @Id
    @Column(name = "accommodation_id")
    private Long accommodationId;
}
