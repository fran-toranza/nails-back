package jsges.nails.service.articulos;

import jsges.nails.DTO.articulos.LineaDTO;
import jsges.nails.domain.articulos.Linea;
import jsges.nails.excepcion.RecursoNoEncontradoExcepcion;
import jsges.nails.repository.articulos.LineaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class LineaService implements ILineaService {

    @Autowired
    private LineaRepository modelRepository;

    @Override
    public List<LineaDTO> getAll() {
        List<Linea> list = modelRepository.buscarNoEliminados();
        return list.stream().map(LineaDTO::new).toList();
    }

    @Override
    public Page<LineaDTO> getItems(String consulta, Pageable pageable) {
        List<Linea> listado = modelRepository.buscarNoEliminados(consulta);
        List<LineaDTO> listadoDTO = listado.stream().map(LineaDTO::new).toList();
        return paginateList(listadoDTO, pageable);
    }

    @Override
    public LineaDTO agregar(LineaDTO model) {
        List<Linea> list = modelRepository.buscarExacto(model.getDenominacion());
        if (!list.isEmpty()) {
            // Podrías lanzar una excepción personalizada o simplemente hacer otra lógica
            throw new IllegalArgumentException("La línea ya existe.");
        }
        Linea nuevaLinea = new Linea(model);
        nuevaLinea = modelRepository.save(nuevaLinea);
        return new LineaDTO(nuevaLinea);
    }

    @Override
    public LineaDTO eliminar(Integer id) {
        Linea model = modelRepository.findById(id).orElse(null);
        if (model == null) {
            throw new RecursoNoEncontradoExcepcion("El id recibido no existe: " + id);
        }
        model.markAsEliminado();
        model = modelRepository.save(model);
        return new LineaDTO(model);
    }

    @Override
    public LineaDTO getPorId(Integer id) {
        Linea linea = modelRepository.findById(id).orElse(null);
        if (linea == null) {
            throw new RecursoNoEncontradoExcepcion("No se encontró el id: " + id);
        }
        return new LineaDTO(linea);
    }

    @Override
    public LineaDTO actualizar(Integer id, LineaDTO modelRecibido) {
        Linea model = modelRepository.findById(id).orElse(null);
        if (model == null) {
            throw new RecursoNoEncontradoExcepcion("El id recibido no existe: " + id);
        }
        model.setDenominacion(modelRecibido.getDenominacion());
        model = modelRepository.save(model);
        return new LineaDTO(model);
    }

    // Método auxiliar para paginar listas en memoria
    private Page<LineaDTO> paginateList(List<LineaDTO> lineas, Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;

        List<LineaDTO> list;
        if (lineas.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, lineas.size());
            list = lineas.subList(startItem, toIndex);
        }

        return new PageImpl<>(list, PageRequest.of(currentPage, pageSize), lineas.size());
    }

    @Override
    public Linea getLineaEntityById(Integer id) {
        Linea linea = modelRepository.findById(id).orElse(null);
        if (linea == null) {
            // Puedes lanzar una excepción si lo deseas.
            throw new RecursoNoEncontradoExcepcion("No se encontró la línea con id: " + id);
        }
        return linea;
    }
}
