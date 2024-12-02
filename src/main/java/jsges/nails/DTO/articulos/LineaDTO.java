package jsges.nails.DTO.articulos;

import jsges.nails.DTO.TipoObjetoDTO;
import jsges.nails.domain.articulos.Linea;

public class LineaDTO extends TipoObjetoDTO {

    private Integer id;
    private String denominacion;

    public LineaDTO() {
        super();
    }

    public LineaDTO(Linea linea) {
        this.id = linea.getId();
        this.denominacion = linea.getDenominacion();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }
}