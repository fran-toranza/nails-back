package jsges.nails.service.articulos;

import jsges.nails.DTO.articulos.ArticuloVentaDTO;
import jsges.nails.domain.articulos.ArticuloVenta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IArticuloVentaService {


    public ResponseEntity<List<ArticuloVentaDTO>> listarNoEliminados(String consulta);

    public ResponseEntity<Page<ArticuloVentaDTO>> listar(Pageable pageable);

    public ResponseEntity<ArticuloVentaDTO> buscar(Integer id);

    public ResponseEntity<ArticuloVentaDTO> guardar(ArticuloVentaDTO model);

    public ResponseEntity<ArticuloVentaDTO> eliminar(int id);

    public ResponseEntity<Page<ArticuloVentaDTO>> buscarPagina(Pageable pageable, List<ArticuloVentaDTO> listado) ;
    public  ResponseEntity<ArticuloVentaDTO> actualizar(ArticuloVentaDTO model, Integer id) ; }
