package jsges.nails.DTO.articulos;

import jsges.nails.DTO.TipoObjetoDTO;
import jsges.nails.domain.articulos.Linea;

public class LineaDTO extends TipoObjetoDTO {


    public LineaDTO() {
        super();
    }

    public LineaDTO(Linea linea) {
        this.setId(linea.getId());
        this.setDenominacion(linea.getDenominacion());
    }
}