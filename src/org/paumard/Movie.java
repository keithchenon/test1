package org.paumard;

import org.paumard.model.Actor;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author José Paumard
 */
public class Movie {
    private String title ;
    private int releaseYear ;
    
    private Set<org.paumard.model.Actor> actors = new HashSet<>() ;
    
    public Movie(String title, int releaseYear) {
        this.title = title;
        this.releaseYear = releaseYear;
    }
    
    public String title() {
        return this.title ;
    }
    
    public int releaseYear() {
        return this.releaseYear ;
    }
    
    public void addActor(org.paumard.model.Actor actor) {
        this.actors.add(actor) ;
    }
    
    public Set<Actor> actors() {
        return this.actors ;
    }
    
    @Override
    public String toString() {
        return "Movie{" + "title=" + title + ", releaseYear=" + releaseYear + ", actors=" + actors + '}';
    }
}
