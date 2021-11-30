package com.project.spring_thymeleaf_mongo.controller;

import com.project.spring_thymeleaf_mongo.model.Movie;
import com.project.spring_thymeleaf_mongo.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class Rest_Controller {

    @Autowired
    private MovieRepository repository;

    @PostMapping("/addMovie")
    public String saveMovie(@RequestBody Movie movie){

        repository.save(movie).subscribe();
        return "Added movie with id: " +movie.getId();
    }

    @GetMapping("/findAllMovies")
    public Flux<Movie> getMovies(){
        return repository.findAll();
    }

    @GetMapping("/findAllMovies/{id}")
    public Mono<Movie> getMovie(@PathVariable Long id){
        return repository.findById(id);
    }
    @DeleteMapping("/delete/{id}")
    public String deleteMovie(@PathVariable Long id){
        repository.deleteById(id).subscribe();
        return "movie deleted with id: " + id;
    }

}
/*
*  POST -> http://localhost:8080/addMovie
*  {    "id": 1,
        "name": "Titanic",
        "genre": "Romance/Drama",
        "age": 1997,
        "score": 7.0
    }

    {
        "id": 2,
        "name": "Vertigo",
        "genre": "Thriller",
        "age": 1970,
        "score": 8.0
    }

    {
        "id": 3,
        "name": "Star Wars",
        "genre": "Sci-Fi",
        "age": 1980,
        "score": 5.0
    }
*
*   GET -> http://localhost:8080/findAllMovies
*
*[
    {
        "id": 1,
        "name": "Titanic",
        "genre": "Romance/Drama",
        "age": 1997,
        "score": 7.0
    },
    {
        "id": 2,
        "name": "Vertigo",
        "genre": "Thriller",
        "age": 1970,
        "score": 8.0
    },
    {
        "id": 3,
        "name": "Star Wars",
        "genre": "Sci-Fi",
        "age": 1980,
        "score": 5.0
    }
]
*  GET -> http://localhost:8080/findAllMovies/2
*
*   {
       "id": 2,
       "name": "Vertigo",
       "genre": "Thriller",
       "age": 1970,
       "score": 8.0
     }
*
*  DELETE ->  http://localhost:8080/delete/2
*
*  GET -> http://localhost:8080/findAllMovies
*
*  [
    {
        "id": 1,
        "name": "Titanic",
        "genre": "Romance/Drama",
        "age": 1997,
        "score": 7.0
    },
    {
        "id": 3,
        "name": "Star Wars",
        "genre": "Sci-Fi",
        "age": 1980,
        "score": 5.0
    }
]
*
*
*
* */


