package jsges.nails.DTO.servicios;

import jsges.nails.DTO.TipoObjetoDTO;
import jsges.nails.domain.servicios.TipoServicio;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TipoServicioDTO extends TipoObjetoDTO {

    public TipoServicioDTO() {
    }

    public TipoServicioDTO(TipoServicio model) {
        this.setId(model.getId());
        this.setCodigo(model.getCodigo());
        this.setDenominacion(model.getDenominacion());
        this.setEstado(model.getEstado());
        this.setDetalle(model.getDetalle());
    }
}
