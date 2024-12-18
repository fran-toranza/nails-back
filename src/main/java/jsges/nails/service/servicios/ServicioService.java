package jsges.nails.service.servicios;

import jsges.nails.DTO.servicios.ItemServicioDTO;
import jsges.nails.DTO.servicios.ServicioDTO;
import jsges.nails.DTO.servicios.TipoServicioDTO;
import jsges.nails.domain.organizacion.Cliente;
import jsges.nails.domain.servicios.ItemServicio;
import jsges.nails.domain.servicios.Servicio;
import jsges.nails.domain.servicios.TipoServicio;
import jsges.nails.excepcion.RecursoNoEncontradoExcepcion;
import jsges.nails.repository.organizacion.ClienteRepository;
import jsges.nails.repository.servicios.ServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ServicioService implements IServicioService {

    @Autowired
    private ServicioRepository servicioRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ITipoServicioService tipoServicioService;

    @Autowired
    private IItemServicioService itemServicioService;

    @Override
    public List<ServicioDTO> listar() {
        return servicioRepository.buscarNoEliminados().stream()
                .map(this::entityToDTOCompleto)
                .toList();
    }

    @Override
    public ServicioDTO buscarPorId(Integer id) {
        Servicio servicio = servicioRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoExcepcion("No se encontró el servicio con id: " + id));
        return entityToDTOCompleto(servicio);
    }

    @Override
    public ServicioDTO guardar(ServicioDTO dto) {
        // Convertir DTO a Entidad
        Servicio servicio = dtoToEntity(dto);
        // Guardar servicio
        servicio = servicioRepository.save(servicio);
        
        // Guardar items
        if (dto.getListaItems() != null) {
            for (ItemServicioDTO itemDTO : dto.getListaItems()) {
                guardarItemServicio(servicio, itemDTO);
            }
        }

        // Retornar DTO completo (incluyendo items)
        return entityToDTOCompleto(servicioRepository.findById(servicio.getId()).get());
    }

    @Override
    public ServicioDTO actualizar(Integer id, ServicioDTO dto) {
        servicioRepository.findById(id).orElseThrow(() ->
                new RecursoNoEncontradoExcepcion("No se encontró el servicio con id: " + id));
        dto.setId(id);
        Servicio servicio = dtoToEntity(dto);
        servicio = servicioRepository.save(servicio);

        if (dto.getListaItems() != null) {
            List<ItemServicio> itemsAnteriores = itemServicioService.buscarPorServicio(servicio.getId());
            for (ItemServicio itemAnt : itemsAnteriores) {
                itemServicioService.eliminar(itemAnt.getId());
            }

            for (ItemServicioDTO itemDTO : dto.getListaItems()) {
                guardarItemServicio(servicio, itemDTO);
            }
        }

        return entityToDTOCompleto(servicioRepository.findById(servicio.getId()).get());
    }

    @Override
    public ServicioDTO eliminar(Integer id) {
        Servicio servicio = servicioRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoExcepcion("No se encontró el servicio con id: " + id));
        servicio.markAsEliminado();
        servicioRepository.save(servicio);
        return entityToDTOCompleto(servicio);
    }

    @Override
    public List<ServicioDTO> listar(String consulta) {
        return servicioRepository.buscarNoEliminados(consulta).stream().map(this::entityToDTOCompleto).toList();
    }

    @Override
    public Page<ServicioDTO> listarPaginado(String consulta, Pageable pageable) {
        List<ServicioDTO> servicios = listar(consulta);
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<ServicioDTO> list;

        if (servicios.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, servicios.size());
            list = servicios.subList(startItem, toIndex);
        }

        return new PageImpl<>(list, PageRequest.of(currentPage, pageSize), servicios.size());
    }

    // ---------------------- Métodos Auxiliares ----------------------

    private Servicio dtoToEntity(ServicioDTO dto) {
        Servicio servicio = new Servicio();
        servicio.setId(dto.getId());
        servicio.setCodigo(dto.getCodigo());
        servicio.setDenominacion(dto.getDenominacion());
        servicio.setEstado(dto.getEstado());
        servicio.setDetalle(dto.getDetalle());

        // **Obtener la entidad Cliente gestionada desde la base de datos**
        Cliente cliente = clienteRepository.findById(dto.getCliente())
                .orElseThrow(() -> new RecursoNoEncontradoExcepcion("No se encontró el cliente con id: " + dto.getCliente()));
        servicio.setCliente(cliente);

        // Fechas y total
        servicio.setFechaRegistro(dto.getFechaDocumento());
        servicio.setFechaRealizacion(dto.getFechaDocumento());
        servicio.setTotal(dto.getTotal() != null ? dto.getTotal() : 0.0);

        return servicio;
    }

    private ServicioDTO entityToDTOCompleto(Servicio servicio) {
        // Obtener items del servicio
        List<ItemServicio> items = itemServicioService.buscarPorServicio(servicio.getId());
        return new ServicioDTO(servicio, items);
    }

    private void guardarItemServicio(Servicio servicio, ItemServicioDTO itemDTO) {
        TipoServicioDTO tipoServicioDTO = tipoServicioService.buscarPorId(itemDTO.getTipoServicioId());
        TipoServicio tipoServicio = tipoServicioDtoToEntity(tipoServicioDTO);

        ItemServicio item = new ItemServicio(servicio, tipoServicio, itemDTO.getPrecio(), itemDTO.getObservacion());
        itemServicioService.guardar(item);
    }

    private TipoServicio tipoServicioDtoToEntity(TipoServicioDTO dto) {
        TipoServicio tipo = new TipoServicio();
        tipo.setId(dto.getId());
        tipo.setCodigo(dto.getCodigo());
        tipo.setDenominacion(dto.getDenominacion());
        tipo.setEstado(dto.getEstado());
        tipo.setDetalle(dto.getDetalle());
        return tipo;
    }
}
