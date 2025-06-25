package es.ufv.dis.back.Ordinaria.API;


import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Starship {
    private String name;
    private String model;
    @SerializedName("cost_in_credits")
    private String costInCredits;
    private String crew;
    @SerializedName("cargo_capacity")
    private String cargoCapacity;
    private String consumables;
    @SerializedName("hyperdrive_rating")
    private String hyperdriveRating;
    @SerializedName("starship_class")
    private String starshipClass;
    private List<String> pilots;
    private List<String> films;

    // Getters y setters
    public String getName() { return name; }
    public void setName(String n) { this.name = n; }
    public String getModel() { return model; }
    public void setModel(String m) { this.model = m; }
    public String getCostInCredits() { return costInCredits; }
    public void setCostInCredits(String c) { this.costInCredits = c; }
    public String getCrew() { return crew; }
    public void setCrew(String c) { this.crew = c; }
    public String getCargoCapacity() { return cargoCapacity; }
    public void setCargoCapacity(String c) { this.cargoCapacity = c; }
    public String getConsumables() { return consumables; }
    public void setConsumables(String c) { this.consumables = c; }
    public String getHyperdriveRating() { return hyperdriveRating; }
    public void setHyperdriveRating(String h) { this.hyperdriveRating = h; }
    public String getStarshipClass() { return starshipClass; }
    public void setStarshipClass(String s) { this.starshipClass = s; }
    public List<String> getPilots() { return pilots; }
    public void setPilots(List<String> p) { this.pilots = p; }
    public List<String> getFilms() { return films; }
    public void setFilms(List<String> f) { this.films = f; }
}
