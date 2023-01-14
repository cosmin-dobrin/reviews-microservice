package com.bsd.reviewsmicroservice.domain;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "accommodations")
public class Accommodation {

    @Id
    @Column(name = "accommodation_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "accommodations_id_seq_name")
    @SequenceGenerator(name = "accommodations_id_seq_name", sequenceName = "accommodations_id_seq", allocationSize = 1)
    private Long accommodationId;

    @NotNull
    @Column(name = "name")
    private String name;

    @Column(name = "description", columnDefinition="TEXT")
    private String description;

    @NotNull
    @Column(name = "phone")
    private String phone;

    @NotNull
    @Column(name = "number_of_booked_slots")
    private Long numberOfBookedSlots;

    @NotNull
    @Column(name = "total_number_of_slots")
    private Long totalNumberOfSlots;

//    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    @JoinColumn(name = "photo_id", foreignKey = @ForeignKey(name = "FK_shelters_photos"))
//    Photo photo;

//    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
//    @JoinColumn(name = "city_id", foreignKey = @ForeignKey(name = "FK_shelter_city"))
//    private City city;

    public Accommodation updateFields(Accommodation accommodation) {
        this.name = accommodation.getName();
        this.description = accommodation.getDescription();
        this.phone = accommodation.getPhone();
        this.numberOfBookedSlots = accommodation.getNumberOfBookedSlots();
        this.totalNumberOfSlots = accommodation.getTotalNumberOfSlots();
//        this.photo = accommodation.getPhoto();

        return this;
    }
}
