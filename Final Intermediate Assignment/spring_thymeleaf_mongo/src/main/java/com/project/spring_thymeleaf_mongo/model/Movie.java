package com.project.spring_thymeleaf_mongo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(collection = "movie")
public class Movie {

    @Id
    private Long id;
    private String name;
    private String genre;
    private Long age;
    private Double Score;


}
