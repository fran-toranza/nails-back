package jsges.nails.controller.services;

import jsges.nails.DTO.servicios.ServicioDTO;
import jsges.nails.service.servicios.IServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="${path_mapping}")
@CrossOrigin(value="${path_cross}")
public class ServicioController {

    @Autowired
    private IServicioService modelService;

    @GetMapping("/servicios")
    public ResponseEntity<List<ServicioDTO>> getAll() {
        return ResponseEntity.ok(modelService.listar());
    }

    @GetMapping("/servicio/{id}")
    public ResponseEntity<ServicioDTO> getPorId(@PathVariable Integer id){
        return ResponseEntity.ok(modelService.buscarPorId(id));
    }

    @GetMapping("/serviciosPageQuery")
    public ResponseEntity<Page<ServicioDTO>> getItems(@RequestParam(defaultValue = "") String consulta,
                                                      @RequestParam(defaultValue = "0") int page,
                                                      @RequestParam(defaultValue = "${max_page}") int size) {
        return ResponseEntity.ok(modelService.listarPaginado(consulta, PageRequest.of(page, size)));
    }

    @PostMapping("/servicios")
    public ResponseEntity<ServicioDTO> agregar(@RequestBody ServicioDTO model){
        return ResponseEntity.ok(modelService.guardar(model));
    }

    @PutMapping("/servicios/{id}")
    public ResponseEntity<ServicioDTO> actualizar(@PathVariable Integer id,
                                                  @RequestBody ServicioDTO dto){
        return ResponseEntity.ok(modelService.actualizar(id, dto));
    }

    @DeleteMapping("/servicio/{id}")
    public ResponseEntity<ServicioDTO> eliminar(@PathVariable Integer id) {
        return ResponseEntity.ok(modelService.eliminar(id));
    }
}
