package com.scaler.bookmyshowmorning.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Movie extends  BaseModel {
    private String name;
    private String genre;
    private int runTimeInMins;
    @ElementCollection // Primitive type lists
    private List<String> actors;

    @Enumerated(EnumType.STRING)
    @ElementCollection
    private List<Language> languages;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getRunTimeInMins() {
        return runTimeInMins;
    }

    public void setRunTimeInMins(int runTimeInMins) {
        this.runTimeInMins = runTimeInMins;
    }

    public List<String> getActors() {
        return actors;
    }

    public void setActors(List<String> actors) {
        this.actors = actors;
    }

    public List<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(List<Language> languages) {
        this.languages = languages;
    }
}

    // movie_actor : id | movie_id | [actor1, actor2, actor2]