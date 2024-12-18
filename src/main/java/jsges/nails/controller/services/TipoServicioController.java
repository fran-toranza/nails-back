package jsges.nails.controller.services;

import jsges.nails.DTO.servicios.TipoServicioDTO;
import jsges.nails.service.servicios.ITipoServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="${path_mapping}")
@CrossOrigin(value="${path_cross}")
public class TipoServicioController {

    @Autowired
    private ITipoServicioService modelService;

    @GetMapping("/tiposServicios")
    public ResponseEntity<List<TipoServicioDTO>> getAll() {
        return ResponseEntity.ok(modelService.listar());
    }

    @GetMapping("/tiposServiciosPageQuery")
    public ResponseEntity<Page<TipoServicioDTO>> getItems(@RequestParam(defaultValue = "") String consulta,
                                                          @RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "${max_page}") int size) {
        return ResponseEntity.ok(modelService.listarPaginado(consulta, PageRequest.of(page, size)));
    }

    @PostMapping("/tiposServicios")
    public ResponseEntity<TipoServicioDTO> agregar(@RequestBody TipoServicioDTO model) {
        return ResponseEntity.ok(modelService.guardar(model));
    }

    @DeleteMapping("/tipoServicioEliminar/{id}")
    public ResponseEntity<TipoServicioDTO> eliminar(@PathVariable Integer id){
        return ResponseEntity.ok(modelService.eliminar(id));
    }

    @GetMapping("/tiposServicios/{id}")
    public ResponseEntity<TipoServicioDTO> getPorId(@PathVariable Integer id){
        return ResponseEntity.ok(modelService.buscarPorId(id));
    }

    @PutMapping("/tiposServicios/{id}")
    public ResponseEntity<TipoServicioDTO> actualizar(@PathVariable Integer id,
                                                      @RequestBody TipoServicioDTO modelRecibido) {
        modelRecibido.setId(id);
        return ResponseEntity.ok(modelService.actualizar(modelRecibido));
    }
}
