package jsges.nails.controller.articulos;

import jsges.nails.DTO.articulos.LineaDTO;
import jsges.nails.service.articulos.ILineaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "${path_mapping}")
@CrossOrigin(value = "${path_cross}")
public class LineaController {

    @Autowired
    private ILineaService modelService;

    @Value("${page_max}")
    private int pageMax;

    @GetMapping("/lineas")
    public ResponseEntity<List<LineaDTO>> getAll() {
        return ResponseEntity.ok(modelService.getAll());
    }

    @GetMapping("/lineasPageQuery")
    public ResponseEntity<Page<LineaDTO>> getItems(@RequestParam(defaultValue = "") String consulta,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "${page_max}") int size) {
        return ResponseEntity.ok(modelService.getItems(consulta, PageRequest.of(page, size)));
    }

    @PostMapping("/linea")
    public ResponseEntity<LineaDTO> agregar(@RequestBody LineaDTO model) {
        return ResponseEntity.ok(modelService.agregar(model));
    }

    @DeleteMapping("/lineaEliminar/{id}")
    public ResponseEntity<LineaDTO> eliminar(@PathVariable Integer id) {
        return ResponseEntity.ok(modelService.eliminar(id));
    }

    @GetMapping("/linea/{id}")
    public ResponseEntity<LineaDTO> getPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(modelService.getPorId(id));
    }

    @PutMapping("/linea/{id}")
    public ResponseEntity<LineaDTO> actualizar(@PathVariable Integer id,
            @RequestBody LineaDTO modelRecibido) {
        return ResponseEntity.ok(modelService.actualizar(id, modelRecibido));
    }
}
