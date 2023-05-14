package com.example.lunch_voted_system.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;


import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "lunches")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Lunch extends BaseEntity implements Serializable {


    public Lunch(Integer id, String restaurantName, String starter, String mainCourse,
                 String salad, String dessert, String drinks, Integer votes) {
        this(restaurantName, starter, mainCourse, salad, dessert, drinks, votes);
        this.id = id;
    }

    @Column(name = "restaurant_name", nullable = false, unique = true)
    @NotEmpty
    @Size(max = 128)
    private String restaurantName;

    @Column(name = "starter")
    @Size(max = 128)
    private String starter;

    @Column(name = "main_course")
    @Size(max = 128)
    private String mainCourse;

    @Column(name = "salad")
    @Size(max = 128)
    private String salad;

    @Column(name = "dessert")
    @Size(max = 128)
    private String dessert;


    @Column(name = "drinks")
    @Size(max = 128)
    private String drinks;

    @Column(name = "votes", nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer votes;



}