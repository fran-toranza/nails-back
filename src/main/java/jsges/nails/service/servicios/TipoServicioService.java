package jsges.nails.service.servicios;

import jsges.nails.DTO.servicios.TipoServicioDTO;
import jsges.nails.domain.servicios.TipoServicio;
import jsges.nails.excepcion.RecursoNoEncontradoExcepcion;
import jsges.nails.repository.servicios.TipoServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class TipoServicioService implements ITipoServicioService {

    @Autowired
    private TipoServicioRepository modelRepository;

    @Override
    public List<TipoServicioDTO> listar() {
        return modelRepository.buscarNoEliminados().stream().map(TipoServicioDTO::new).toList();
    }

    @Override
    public TipoServicioDTO buscarPorId(Integer id) {
        TipoServicio tipo = modelRepository.findById(id).orElseThrow(
                () -> new RecursoNoEncontradoExcepcion("El id recibido no existe: " + id));
        return new TipoServicioDTO(tipo);
    }

    @Override
    public TipoServicioDTO guardar(TipoServicioDTO dto) {
        TipoServicio tipo = dtoToEntity(dto);
        tipo = modelRepository.save(tipo);
        return new TipoServicioDTO(tipo);
    }

    @Override
    public TipoServicioDTO eliminar(Integer id) {
        TipoServicio tipo = modelRepository.findById(id).orElseThrow(
                () -> new RecursoNoEncontradoExcepcion("El id recibido no existe: " + id));
        tipo.markAsEliminado();
        tipo = modelRepository.save(tipo);
        return new TipoServicioDTO(tipo);
    }

    @Override
    public TipoServicioDTO actualizar(TipoServicioDTO dto) {
        modelRepository.findById(dto.getId()).orElseThrow(
                () -> new RecursoNoEncontradoExcepcion("El id recibido no existe: " + dto.getId()));
        TipoServicio tipo = dtoToEntity(dto);
        tipo = modelRepository.save(tipo);
        return new TipoServicioDTO(tipo);
    }

    @Override
    public List<TipoServicioDTO> listar(String consulta) {
        return modelRepository.buscarNoEliminados(consulta).stream().map(TipoServicioDTO::new).toList();
    }

    @Override
    public Page<TipoServicioDTO> listarPaginado(String consulta, Pageable pageable) {
        List<TipoServicioDTO> tipos = listar(consulta);
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<TipoServicioDTO> list;

        if (tipos.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, tipos.size());
            list = tipos.subList(startItem, toIndex);
        }

        return new PageImpl<>(list, PageRequest.of(currentPage, pageSize), tipos.size());
    }

    @Override
    public List<TipoServicioDTO> buscar(String consulta) {
        return modelRepository.buscarExacto(consulta).stream().map(TipoServicioDTO::new).toList();
    }

    private TipoServicio dtoToEntity(TipoServicioDTO dto) {
        TipoServicio tipo = new TipoServicio();
        tipo.setId(dto.getId());
        tipo.setCodigo(dto.getCodigo());
        tipo.setDenominacion(dto.getDenominacion());
        tipo.setEstado(dto.getEstado());
        tipo.setDetalle(dto.getDetalle());
        return tipo;
    }
}
