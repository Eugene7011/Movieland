package com.podzirei.movieland.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name_russian", nullable = false)
    private String nameRussian;

    @Column(name = "name_native", nullable = false)
    private String nameNative;

    @Column(name = "year_of_release", nullable = false)
    private Date releasedDate;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "rating", nullable = false)
    private Double rating;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "picture_path", nullable = false)
    private String picturePath;

    @Column(name = "votes", nullable = false)
    private int votes;
}
