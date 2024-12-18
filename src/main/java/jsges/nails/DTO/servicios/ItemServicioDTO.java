package jsges.nails.DTO.servicios;

import jsges.nails.domain.servicios.ItemServicio;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ItemServicioDTO extends TipoServicioDTO {

    private String observacion;
    private String tipoServicio;
    private Integer tipoServicioId;
    private Double precio;

    public ItemServicioDTO(ItemServicio item) {
        // Campos de TipoObjeto
        this.setId(item.getId());
        this.setCodigo(item.getCodigo());
        this.setDenominacion(item.getDenominacion());
        this.setEstado(item.getEstado());
        this.setDetalle(item.getDetalle());

        // Campos específicos de ItemServicio
        this.observacion = item.getObservacion();
        this.tipoServicio = item.getTipoServicio().getDenominacion();
        this.tipoServicioId = item.getTipoServicio().getId();
        this.precio = item.getPrecio();
    }

    public ItemServicioDTO() {
    }
}
