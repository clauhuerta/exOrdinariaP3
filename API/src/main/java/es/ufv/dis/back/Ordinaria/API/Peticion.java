package es.ufv.dis.back.Ordinaria.API;

public class Peticion {
    private String name;
    private int count;

    public Peticion() {}

    public Peticion(String name, int count) {
        this.name = name;
        this.count = count;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getCount() { return count; }
    public void setCount(int count) { this.count = count; }
}
