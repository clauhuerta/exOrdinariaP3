package org.vaadin.example;


import com.google.gson.annotations.SerializedName;
import java.util.List;

public class StarshipFront {
    private String name;
    private String model;
    @SerializedName("starship_class")
    private String starshipClass;
    private String crew;
    private List<String> films;

    // Getters y setters
    public String getName() { return name; }
    public void setName(String n) { this.name = n; }
    public String getModel() { return model; }
    public void setModel(String m) { this.model = m; }
    public String getStarshipClass() { return starshipClass; }
    public void setStarshipClass(String s) { this.starshipClass = s; }
    public String getCrew() { return crew; }
    public void setCrew(String c) { this.crew = c; }
    public List<String> getFilms() { return films; }
    public void setFilms(List<String> f) { this.films = f; }
}
