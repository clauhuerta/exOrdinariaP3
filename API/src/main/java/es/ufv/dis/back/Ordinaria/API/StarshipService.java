package es.ufv.dis.back.Ordinaria.API;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.FileNotFoundException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StarshipService {

    // Lee todas las naves desde data.json en resources
    public List<Starship> loadAllStarships() {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("data.json")) {
            if (is == null) {
                throw new FileNotFoundException("No se encontró data.json en el classpath");
            }
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<Starship>>() {}.getType();
            List<Starship> ships = gson.fromJson(new InputStreamReader(is), listType);
            return ships != null ? ships : new ArrayList<>();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    // Busca una nave por su nombre (case-insensitive)
    public Starship findStarshipByName(String name) {
        List<Starship> ships = loadAllStarships();
        Optional<Starship> found = ships.stream()
                .filter(s -> s.getName().equalsIgnoreCase(name))
                .findFirst();
        return found.orElse(null);
    }

    // Método para registrar la petición (pendiente de implementar si quieres)
    public void trackPetition(String shipName) {
        // Implementa aquí la lógica para guardar el nombre de la nave y el contador en el JSON de peticiones
    }
}
