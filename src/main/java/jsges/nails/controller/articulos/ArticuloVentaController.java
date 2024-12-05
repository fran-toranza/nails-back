package jsges.nails.controller.articulos;

import jsges.nails.DTO.articulos.ArticuloVentaDTO;
import jsges.nails.domain.articulos.ArticuloVenta;
import jsges.nails.excepcion.RecursoNoEncontradoExcepcion;
import jsges.nails.service.articulos.IArticuloVentaService;
import jsges.nails.service.articulos.ILineaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping(value="${path_mapping}")
@CrossOrigin(value="${path_cross}")
public class ArticuloVentaController {
    private static final Logger logger = LoggerFactory.getLogger(ArticuloVentaController.class);
    @Autowired
    private IArticuloVentaService  modelService;

    @Autowired
    private ILineaService lineaService;

    public ArticuloVentaController() {

    }

    @GetMapping({"/articulos"})
public List<ArticuloVenta> getAll() {
    logger.info("traer todos los articulos");
    return modelService.listar();
}

@GetMapping({"/articulosPageQuery"})
public ResponseEntity<Page<ArticuloVentaDTO>> getItems(@RequestParam(defaultValue = "") String consulta,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "${max_page}") int size) {
    ResponseEntity<List<ArticuloVentaDTO>> listadoDTO = (ResponseEntity<List<ArticuloVentaDTO>>) modelService.listar(consulta);

    return modelService.buscarPagina(PageRequest.of(page, size), listadoDTO.getBody());
}


    @PostMapping("/articulos")
    public ArticuloVenta agregar(@RequestBody ArticuloVentaDTO model){
        logger.info("entra" );

        Integer idLinea = model.getLinea();

        ArticuloVenta newModel =  new ArticuloVenta();
        newModel.setDenominacion(model.getDenominacion());
        newModel.setLinea(lineaService.buscarPorId(idLinea));

        ArticuloVenta modelSave= modelService.guardar(newModel);
        return modelSave;
    }


    @DeleteMapping("/articuloEliminar/{id}")
    public ResponseEntity<ArticuloVenta> eliminar(@PathVariable Integer id){
        ArticuloVenta model = modelService.buscarPorId(id);
        if (model == null){
            throw new RecursoNoEncontradoExcepcion("El id recibido no existe: " + id);
        }

        model.markAsDeleted();
        modelService.guardar(model);
        return ResponseEntity.ok(model);
    }

    @GetMapping("/articulos/{id}")
    public ResponseEntity<ArticuloVentaDTO> getPorId(@PathVariable Integer id){
        ArticuloVenta articuloVenta = modelService.buscarPorId(id);
        if(articuloVenta == null){
            throw new RecursoNoEncontradoExcepcion("No se encontro el id: " + id);
        }
        ArticuloVentaDTO model = new ArticuloVentaDTO(articuloVenta);
        return ResponseEntity.ok(model);
    }

    @PutMapping("/articulos/{id}")
    public ResponseEntity<ArticuloVenta> actualizar(@PathVariable Integer id,
                                                    @RequestBody ArticuloVentaDTO modelRecibido){
        logger.info("articulo " +modelRecibido);
        ArticuloVenta model = modelService.buscarPorId(id);
        logger.info("articulo " +model);
        if (model == null){
            throw new RecursoNoEncontradoExcepcion("El id recibido no existe: " + id);
        }
        logger.info("articulo " +model);
        model.setDenominacion(modelRecibido.getDenominacion());
        model.setLinea(lineaService.buscarPorId(modelRecibido.getLinea()));
        modelService.guardar(model);
        return ResponseEntity.ok(model);
    }

}

