package jsges.nails.service.servicios;

import jsges.nails.DTO.servicios.ServicioDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IServicioService {
    List<ServicioDTO> listar();
    ServicioDTO buscarPorId(Integer id);
    ServicioDTO guardar(ServicioDTO dto);
    ServicioDTO actualizar(Integer id, ServicioDTO dto);
    ServicioDTO eliminar(Integer id);

    List<ServicioDTO> listar(String consulta);
    Page<ServicioDTO> listarPaginado(String consulta, Pageable pageable);
}
