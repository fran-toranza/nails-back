package jsges.nails.service.servicios;

import jsges.nails.DTO.servicios.TipoServicioDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ITipoServicioService {

    List<TipoServicioDTO> listar();
    TipoServicioDTO buscarPorId(Integer id);
    TipoServicioDTO guardar(TipoServicioDTO model);
    TipoServicioDTO eliminar(Integer id);
    TipoServicioDTO actualizar(TipoServicioDTO dto);

    List<TipoServicioDTO> listar(String consulta);
    Page<TipoServicioDTO> listarPaginado(String consulta, Pageable pageable);

    List<TipoServicioDTO> buscar(String consulta);
}
