package jsges.nails.service.articulos;

import jsges.nails.DTO.articulos.LineaDTO;
import jsges.nails.domain.articulos.Linea;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ILineaService {

    List<Linea> listar();

    Linea buscarPorId(Integer id);

    Linea guardar(Linea model);

    void eliminar(Linea model);

    List<Linea> listar(String consulta);

    Page<LineaDTO> findPaginated(Pageable pageable,List<LineaDTO> lineas);


    List<Linea> buscar(String consulta);

    Linea newModel(LineaDTO model);
}
