package es.ufv.dis.back.Ordinaria.API;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javax.annotation.PostConstruct;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class StarshipService {

    private List<Starship> all;
    private final Gson gson = new Gson();

    private final File dataFile = new File("src/main/resources/datos.json");
    private final File peticionesFile = new File("peticiones/peticiones.json");

    @Value("${pdf.output.dir}")
    private String pdfOutputDir;
    @Value("${json.requests.dir}")
    private String jsonRequestsDir;

    @PostConstruct
    public void init() throws IOException {
        // asegurar carpetas
        new File(pdfOutputDir).mkdirs();
        new File(jsonRequestsDir).mkdirs();

        // cargar datos.json
        try (Reader r = new FileReader(dataFile)) {
            Type listType = new TypeToken<List<Starship>>(){}.getType();
            all = gson.fromJson(r, listType);
        }
        // inicializar peticiones.json si no existe
        if (!peticionesFile.exists()) {
            peticionesFile.getParentFile().mkdirs();
            try (Writer w = new FileWriter(peticionesFile)) {
                gson.toJson(new ArrayList<Peticion>(), w);
            }
        }
    }

    public List<Starship> getAll() {
        return all;
    }

    public void generateReport(String shipName) throws IOException {
        // buscar nave
        Starship target = all.stream()
                .filter(s -> s.getName().equalsIgnoreCase(shipName))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No encontrada"));
        // generar PDF
        PDFManager.generatePDF(target, pdfOutputDir);
        // actualizar peticiones
        updatePeticiones(shipName);
    }

    private synchronized void updatePeticiones(String shipName) throws IOException {
        List<Peticion> list;
        try (Reader r = new FileReader(peticionesFile)) {
            Type type = new TypeToken<List<Peticion>>(){}.getType();
            list = gson.fromJson(r, type);
        }
        boolean found = false;
        for (Peticion p : list) {
            if (p.getShip().equalsIgnoreCase(shipName)) {
                p.setCount(p.getCount() + 1);
                found = true;
                break;
            }
        }
        if (!found) list.add(new Peticion(shipName, 1));
        try (Writer w = new FileWriter(peticionesFile)) {
            gson.toJson(list, w);
        }
    }
}

