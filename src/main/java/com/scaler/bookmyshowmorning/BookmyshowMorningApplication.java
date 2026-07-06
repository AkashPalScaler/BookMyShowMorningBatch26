package com.scaler.bookmyshowmorning;

import com.scaler.bookmyshowmorning.models.Language;
import com.scaler.bookmyshowmorning.models.Movie;
import com.scaler.bookmyshowmorning.models.User;
import com.scaler.bookmyshowmorning.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class BookmyshowMorningApplication implements CommandLineRunner {

    @Autowired // Gets injected from the registry
    MovieRepository movieRepository;

    public static void main(String[] args) {
        SpringApplication.run(BookmyshowMorningApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Movie movie = new Movie();
        movie.setId(2L);
        movie.setName("Obsession");
        movie.setGenre("Thriller");
        List<String> actors = List.of("Curry Baker","Amitabh");
        movie.setActors(actors);
        movie.setLanguages(List.of(Language.TAMIL, Language.HINDI));

        movieRepository.save(movie);

    }
}
//Test