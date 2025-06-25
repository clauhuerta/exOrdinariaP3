package org.vaadin.example;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Route("")
public class MainView extends VerticalLayout {

    private Grid<Starship> grid = new Grid<>(Starship.class, false);
    private Gson gson = new Gson();
    private final String BACKEND_URL = "http://localhost:8085/api/starships";

    public MainView() {
        add(new Div()); // Para separar visualmente

        // Configura columnas del Grid
        grid.addColumn(Starship::getName).setHeader("Name");
        grid.addColumn(Starship::getModel).setHeader("Model");
        grid.addColumn(Starship::getCost_in_credits).setHeader("Cost in Credits");
        grid.addColumn(Starship::getCrew).setHeader("Crew");
        grid.addColumn(Starship::getCargo_capacity).setHeader("Cargo Capacity");
        grid.addColumn(Starship::getConsumables).setHeader("Consumables");
        grid.addColumn(Starship::getHyperdrive_rating).setHeader("Hyperdrive Rating");
        grid.addColumn(Starship::getStarship_class).setHeader("Starship Class");
        grid.addColumn(s -> s.getPilots() != null ? s.getPilots().size() : 0).setHeader("Pilots");
        grid.addColumn(s -> s.getFilms() != null ? s.getFilms().size() : 0).setHeader("Films");

        // Columna con el botón "Generar"
        grid.addComponentColumn(starship -> {
            Button btn = new Button("Generar");
            btn.addClickListener(e -> generarPdf(starship.getName()));
            return btn;
        }).setHeader("Acciones");

        add(grid);
        setSizeFull();

        // Cargar datos al iniciar
        cargarNaves();
    }

    private void cargarNaves() {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BACKEND_URL))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            Type listType = new TypeToken<List<Starship>>() {}.getType();
            List<Starship> starships = gson.fromJson(response.body(), listType);
            grid.setItems(starships);
        } catch (Exception e) {
            Notification.show("Error al cargar las naves: " + e.getMessage(), 5000, Notification.Position.MIDDLE);
        }
    }

    private void generarPdf(String nombreNave) {
        try {
            StarshipRequest req = new StarshipRequest(nombreNave);
            String json = gson.toJson(req);

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BACKEND_URL))
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .header("Content-Type", "application/json")
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            Notification.show(response.body(), 4000, Notification.Position.BOTTOM_START);
        } catch (Exception e) {
            Notification.show("Error al generar PDF: " + e.getMessage(), 5000, Notification.Position.MIDDLE);
        }
    }
}
