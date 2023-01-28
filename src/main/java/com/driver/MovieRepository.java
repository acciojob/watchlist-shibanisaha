package com.driver;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@Repository
public class MovieRepository {
    HashMap<String, Movie> movieDB = new HashMap<>();
    HashMap<String, Director> directorDB = new HashMap<>();
    HashMap<String, List<String>> movieDirectorPairDB = new HashMap<>();

    public void addMovie(Movie movie){
        movieDB.put(movie.getName(), movie);
    }

    public void addDirector(Director director){
        directorDB.put(director.getName(), director);
    }

    public void  addMovieDirectorPair(String movie, String director){
        if(movieDirectorPairDB.containsKey(director)){
            movieDirectorPairDB.get(director).add(movie);
        }else{
            List<String> temp = new ArrayList<>();
            temp.add(movie);
            movieDirectorPairDB.put(director, temp);
        }
    }

    public Movie getMovieByName(String name){
       return movieDB.get(name);
    }

    public Director getDirectorByName(String name){
        return directorDB.get(name);
    }

    public List<String> getMoviesByDirectorName(String name){
        return movieDirectorPairDB.get(name);
    }

    public List<String>  findAllMovies(){
        return new ArrayList<>(movieDB.keySet());
    }

    public void deleteDirectorByName(String name){
        for(String s: movieDirectorPairDB.get(name)){
            if(movieDB.containsKey(s))
            movieDB.remove(s);
        }
        directorDB.remove(name);
    }

    public void deleteAllDirectors(){
        HashSet<String> movieSet = new HashSet<>();

        for(String director: movieDirectorPairDB.keySet()){
            for(String movie: movieDirectorPairDB.get(director)){
                movieSet.add(movie);
            }
        }

        for(String movie: movieSet){
            if(movieDB.containsKey(movie))
                movieDB.remove(movie);
        }

    }


}
