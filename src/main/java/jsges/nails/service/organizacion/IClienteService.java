package jsges.nails.service.organizacion;

import jsges.nails.DTO.Organizacion.ClienteDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IClienteService {
    List<ClienteDTO> listar();
    ClienteDTO buscarPorId(Integer id);
    ClienteDTO guardar(ClienteDTO clienteDTO);
    ClienteDTO eliminar(Integer id);
    ClienteDTO actualizar(ClienteDTO clienteDTO);

    List<ClienteDTO> listar(String consulta);
    Page<ClienteDTO> listarPaginado(String consulta, Pageable pageable);
}
