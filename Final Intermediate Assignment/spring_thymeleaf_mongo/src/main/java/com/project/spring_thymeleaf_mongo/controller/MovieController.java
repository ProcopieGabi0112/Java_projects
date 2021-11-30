package com.project.spring_thymeleaf_mongo.controller;

import com.project.spring_thymeleaf_mongo.model.Movie;
import com.project.spring_thymeleaf_mongo.repository.MovieRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.spring5.context.webflux.IReactiveDataDriverContextVariable;
import org.thymeleaf.spring5.context.webflux.ReactiveDataDriverContextVariable;
import reactor.core.publisher.Mono;
import java.util.Optional;

@Controller
@Slf4j
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;


    @GetMapping("/")
    public String getAllMovies(final Model model) {
        IReactiveDataDriverContextVariable reactiveDataDriverContextVariable
                = new ReactiveDataDriverContextVariable(movieRepository.findAll());
        model.addAttribute("movies", reactiveDataDriverContextVariable);
        return "allMovies";
    }

    @GetMapping(path = {"/edit", "/edit/{id}"})
    public String createUpdateMovieById(Model model, @PathVariable Optional<Long> id) {
        if (id.isPresent()) {
            Mono<Movie> movie = movieRepository.findById(id.get());
            log.info(" $$$$$ UPDATED by ID Movie :: {}", movie);
            model.addAttribute("movie", movie);
        } else {
            model.addAttribute("movie", new Movie());
        }
        return "addMovie";
    }



    @GetMapping("/delete/{id}")
    public String deleteMovie(final Model model, @PathVariable Long id) {
        log.info("::::: DELETE MOVIE ID ::::: {}", id);
        movieRepository.deleteById(id).subscribe();
        return "redirect:/";
    }
    @PostMapping("/create")
    public String createMovie(Movie movie) {
        log.info("::::: CREATE::::: {}", movie);
        movieRepository.save(movie).subscribe();
        return "redirect:/";
    }

}


