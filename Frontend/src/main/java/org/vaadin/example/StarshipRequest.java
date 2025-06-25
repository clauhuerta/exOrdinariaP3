package org.vaadin.example;

public class StarshipRequest {
    private String ship;

    // Constructor vacío
    public StarshipRequest() {}

    // Constructor con campo
    public StarshipRequest(String ship) {
        this.ship = ship;
    }

    // Getter y setter
    public String getShip() {
        return ship;
    }

    public void setShip(String ship) {
        this.ship = ship;
    }
}
