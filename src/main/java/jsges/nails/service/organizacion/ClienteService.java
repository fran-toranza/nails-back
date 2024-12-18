package jsges.nails.service.organizacion;

import jsges.nails.DTO.Organizacion.ClienteDTO;
import jsges.nails.domain.organizacion.Cliente;
import jsges.nails.excepcion.RecursoNoEncontradoExcepcion;
import jsges.nails.repository.organizacion.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ClienteService implements IClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<ClienteDTO> listar() {
        return clienteRepository.buscarNoEliminados().stream().map(ClienteDTO::new).toList();
    }

    @Override
    public ClienteDTO buscarPorId(Integer id) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(
                () -> new RecursoNoEncontradoExcepcion("El id recibido no existe: " + id));
        return new ClienteDTO(cliente);
    }

    @Override
    public ClienteDTO guardar(ClienteDTO clienteDTO) {
        Cliente cliente = dtoToEntity(clienteDTO);
        cliente = clienteRepository.save(cliente);
        return new ClienteDTO(cliente);
    }

    @Override
    public ClienteDTO eliminar(Integer id) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(
                () -> new RecursoNoEncontradoExcepcion("El id recibido no existe: " + id));
        cliente.markAsEliminado();
        cliente = clienteRepository.save(cliente);
        return new ClienteDTO(cliente);
    }

    @Override
    public ClienteDTO actualizar(ClienteDTO clienteDTO) {
        // Verificamos que el cliente exista antes de actualizar
        clienteRepository.findById(clienteDTO.getId()).orElseThrow(
                () -> new RecursoNoEncontradoExcepcion("El id recibido no existe: " + clienteDTO.getId()));

        Cliente cliente = dtoToEntity(clienteDTO);
        cliente = clienteRepository.save(cliente);
        return new ClienteDTO(cliente);
    }

    @Override
    public List<ClienteDTO> listar(String consulta) {
        return clienteRepository.buscarNoEliminados(consulta).stream().map(ClienteDTO::new).toList();
    }

    @Override
    public Page<ClienteDTO> listarPaginado(String consulta, Pageable pageable) {
        List<ClienteDTO> clientes = listar(consulta);
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<ClienteDTO> list;

        if (clientes.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, clientes.size());
            list = clientes.subList(startItem, toIndex);
        }

        return new PageImpl<>(list, PageRequest.of(currentPage, pageSize), clientes.size());
    }

    private Cliente dtoToEntity(ClienteDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setId(dto.getId());
        cliente.setCodigo(dto.getCodigo());
        cliente.setDenominacion(dto.getDenominacion());
        cliente.setEstado(dto.getEstado());
        cliente.setDetalle(dto.getDetalle());

        cliente.setRazonSocial(dto.getRazonSocial());
        cliente.setNombre(dto.getNombre());
        cliente.setCelular(dto.getCelular());
        cliente.setMail(dto.getMail());
        cliente.setFechaInicio(dto.getFechaInicio());
        cliente.setFechaNacimiento(dto.getFechaNacimiento());

        return cliente;
    }
}
