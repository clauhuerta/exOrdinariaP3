package org.vaadin.example;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.router.Route;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.*;
import java.util.List;
import java.util.Map;

@Route("")
public class MainView extends H1 {

    private final Grid<StarshipFront> grid = new Grid<>(StarshipFront.class, false);
    private final Gson gson = new Gson();
    private final HttpClient client = HttpClient.newHttpClient();

    public MainView() {
        setText("Star Wars Starships");
        configureGrid();
        add(grid);
        fetchData();
    }

    private void configureGrid() {
        grid.addColumn(StarshipFront::getName).setHeader("Name");
        grid.addColumn(StarshipFront::getModel).setHeader("Model");
        grid.addColumn(StarshipFront::getStarshipClass).setHeader("Class");
        grid.addColumn(StarshipFront::getCrew).setHeader("Crew");
        grid.addColumn(s -> s.getFilms().size()).setHeader("Films");
        grid.addComponentColumn(ship -> {
            Button b = new Button("Generar");
            b.addClickListener(e -> generatePdf(ship.getName()));
            return b;
        }).setHeader("Generar");
    }

    private void fetchData() {
        try {
            HttpRequest req = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8087/api/starships"))
                    .GET().build();
            String json = client.send(req, HttpResponse.BodyHandlers.ofString()).body();
            Type listType = new TypeToken<List<StarshipFront>>(){}.getType();
            List<StarshipFront> ships = gson.fromJson(json, listType);
            grid.setItems(ships);
        } catch (Exception e) {
            Notification.show("Error cargando datos: " + e.getMessage(), 3000, Notification.Position.MIDDLE);
        }
    }

    private void generatePdf(String name) {
        try {
            String body = gson.toJson(Map.of("ship", name));
            HttpRequest req = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8087/api/generate"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(body))
                    .build();
            client.sendAsync(req, HttpResponse.BodyHandlers.discarding())
                    .thenAccept(r -> Notification.show("PDF generado para " + name, 2000, Notification.Position.TOP_START));
        } catch (Exception e) {
            Notification.show("Error generando PDF: " + e.getMessage(), 3000, Notification.Position.MIDDLE);
        }
    }
}
