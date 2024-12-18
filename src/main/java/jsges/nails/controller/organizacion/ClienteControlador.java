package jsges.nails.controller.organizacion;

import jsges.nails.DTO.Organizacion.ClienteDTO;
import jsges.nails.service.organizacion.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="${path_mapping}")
@CrossOrigin(value="${path_cross}")
public class ClienteControlador {

    @Autowired
    private IClienteService clienteServicio;

    @GetMapping("/clientes")
    public ResponseEntity<List<ClienteDTO>> getAll() {
        return ResponseEntity.ok(clienteServicio.listar());
    }

    @GetMapping("/clientesPageQuery")
    public ResponseEntity<Page<ClienteDTO>> getItems(@RequestParam(defaultValue = "") String consulta,
                                                     @RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "${max_page}") int size) {
        return ResponseEntity.ok(clienteServicio.listarPaginado(consulta, PageRequest.of(page, size)));
    }

    @PostMapping("/clientes")
    public ResponseEntity<ClienteDTO> agregar(@RequestBody ClienteDTO clienteDTO){
        return ResponseEntity.ok(clienteServicio.guardar(clienteDTO));
    }

    @DeleteMapping("/cliente/{id}")
    public ResponseEntity<ClienteDTO> eliminar(@PathVariable Integer id){
        return ResponseEntity.ok(clienteServicio.eliminar(id));
    }

    @GetMapping("/cliente/{id}")
    public ResponseEntity<ClienteDTO> getPorId(@PathVariable Integer id){
        return ResponseEntity.ok(clienteServicio.buscarPorId(id));
    }

    @PutMapping("/clientes/{id}")
    public ResponseEntity<ClienteDTO> actualizar(@PathVariable Integer id,
                                                 @RequestBody ClienteDTO modelRecibido){
        modelRecibido.setId(id);
        return ResponseEntity.ok(clienteServicio.actualizar(modelRecibido));
    }
}
