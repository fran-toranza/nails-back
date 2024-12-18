package jsges.nails.DTO.servicios;

import jsges.nails.DTO.TipoObjetoDTO;
import jsges.nails.domain.servicios.Servicio;
import jsges.nails.domain.servicios.ItemServicio;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class ServicioDTO extends TipoObjetoDTO {

    private Integer cliente; // ID del cliente
    private String clienteNombre;
    private Timestamp fechaDocumento;
    private Double total;
    private Set<ItemServicioDTO> listaItems = new HashSet<>();

    public ServicioDTO(Servicio elemento) {
        // Campos de TipoObjeto
        this.setId(elemento.getId());
        this.setCodigo(elemento.getCodigo());
        this.setDenominacion(elemento.getDenominacion());
        this.setEstado(elemento.getEstado());
        this.setDetalle(elemento.getDetalle());

        // Campos específicos de Servicio
        this.cliente = elemento.getCliente().getId();
        this.clienteNombre = elemento.getCliente().getNombre();
        this.fechaDocumento = elemento.getFechaRealizacion();
        this.total = elemento.getTotal();
    }

    public ServicioDTO(Servicio servicio, List<ItemServicio> items) {
        // Asigna los campos base de TipoObjeto
        this.setId(servicio.getId());
        this.setCodigo(servicio.getCodigo());
        this.setDenominacion(servicio.getDenominacion());
        this.setEstado(servicio.getEstado());
        this.setDetalle(servicio.getDetalle());

        // Campos específicos
        this.setCliente(servicio.getCliente().getId());
        this.setClienteNombre(servicio.getCliente().getNombre());
        this.setFechaDocumento(servicio.getFechaRealizacion());
        this.setTotal(servicio.getTotal());

        // Convertir items a ItemServicioDTO
        Set<ItemServicioDTO> listaItemsDTO = new HashSet<>();
        for (ItemServicio item : items) {
            listaItemsDTO.add(new ItemServicioDTO(item));
        }
        this.setListaItems(listaItemsDTO);
    }

}
