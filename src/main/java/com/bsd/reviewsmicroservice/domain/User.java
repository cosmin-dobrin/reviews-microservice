package com.bsd.reviewsmicroservice.domain;

import com.bsd.reviewsmicroservice.domain.dto.UserDto;
import com.bsd.reviewsmicroservice.domain.enums.RoleType;
import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "user_id")
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_id_seq_name")
//    @SequenceGenerator(name = "users_id_seq_name", sequenceName = "users_id_seq", allocationSize = 1)
    private Long userId;

    @NotNull
    @Column(name = "first_name")
    private String firstName;

    @NotNull
    @Column(name = "last_name")
    private String lastName;

    @NotNull
    @Column(name = "email")
    private String email;

    @NotNull
    @Column(name = "Role")
    private RoleType roleType;

    @OneToMany(mappedBy = "user")
    private List<Review> review;

    public User() {
    }

    public User updateFields(UserDto userDto) {
        this.setEmail(userDto.getEmail());
        this.setFirstName(userDto.getFirstName());
        this.setLastName(userDto.getLastName());

        return this;
    }
}
