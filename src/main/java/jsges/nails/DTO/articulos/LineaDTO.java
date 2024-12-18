package jsges.nails.DTO.articulos;

import jsges.nails.DTO.TipoObjetoDTO;
import jsges.nails.domain.articulos.Linea;

public class LineaDTO extends TipoObjetoDTO {

    public LineaDTO() {
        super();
    }

    public LineaDTO(Linea linea) {
        this.setId(linea.getId());
        this.setCodigo(linea.getCodigo());
        this.setDenominacion(linea.getDenominacion());
        this.setEstado(linea.getEstado());
        this.setDetalle(linea.getDetalle());
    }
}
