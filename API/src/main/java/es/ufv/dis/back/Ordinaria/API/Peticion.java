package es.ufv.dis.back.Ordinaria.API;

public class Peticion {
    private String ship;
    private int count;

    public Peticion() {}

    public Peticion(String s, int c) {
        this.ship = s;
        this.count = c;
    }

    public String getShip() { return ship; }
    public void setShip(String s) { this.ship = s; }

    public int getCount() { return count; }
    public void setCount(int c) { this.count = c; }
}
