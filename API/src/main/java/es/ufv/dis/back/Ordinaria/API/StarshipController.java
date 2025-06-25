package es.ufv.dis.back.Ordinaria.API;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.io.File;
import java.util.List;

@RestController
@RequestMapping("/api/starships")
public class StarshipController {

    @Autowired
    private StarshipService starshipService;

    @GetMapping
    public List<Starship> getAllStarships() {
        return starshipService.loadAllStarships();
    }

    @PostMapping
    public String generatePdfAndTrackRequest(@RequestBody StarshipRequest request) {
        String shipName = request.getShip();
        Starship ship = starshipService.findStarshipByName(shipName);

        if (ship == null) {
            return "Starship not found";
        }

        // Construye la ruta del PDF (naves/NOMBRE.pdf, reemplaza caracteres conflictivos)
        String safeName = ship.getName().replaceAll("[^a-zA-Z0-9]", "_");
        String dirPath = "naves";
        String filePath = dirPath + File.separator + safeName + ".pdf";

        PDFManager pdfManager = new PDFManager();
        pdfManager.generarPDF(ship, ship.getFilms() != null ? ship.getFilms().size() : 0, filePath);

        // (Aquí puedes llamar a starshipService.trackPetition(shipName) si implementas la lógica)

        return "PDF generated for: " + ship.getName();
    }
}
