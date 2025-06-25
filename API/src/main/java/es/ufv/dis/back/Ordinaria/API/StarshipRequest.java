package es.ufv.dis.back.Ordinaria.API;

public class StarshipRequest {
    private String ship;

    public StarshipRequest() {}

    public StarshipRequest(String ship) {
        this.ship = ship;
    }

    public String getShip() { return ship; }
    public void setShip(String ship) { this.ship = ship; }
}
