package jsges.nails.service.articulos;

import jsges.nails.domain.articulos.Linea;

import jsges.nails.DTO.articulos.LineaDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ILineaService {
    List<LineaDTO> getAll();
    Page<LineaDTO> getItems(String consulta, Pageable pageable);
    LineaDTO agregar(LineaDTO model);
    LineaDTO eliminar(Integer id);
    LineaDTO getPorId(Integer id);
    LineaDTO actualizar(Integer id, LineaDTO modelRecibido);
    Linea getLineaEntityById(Integer id);
}
