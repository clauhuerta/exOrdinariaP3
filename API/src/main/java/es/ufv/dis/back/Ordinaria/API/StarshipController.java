package es.ufv.dis.back.Ordinaria.API;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class StarshipController {

    private final StarshipService svc = new StarshipService();

    @GetMapping("/starships")
    public List<Starship> getAll() {
        return svc.getAll();
    }

    @PostMapping("/generate")
    public ResponseEntity<?> generatePdf(@RequestBody Map<String,String> body) {
        try {
            svc.generateReport(body.get("ship"));
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("error", e.getMessage()));
        }
    }
}
