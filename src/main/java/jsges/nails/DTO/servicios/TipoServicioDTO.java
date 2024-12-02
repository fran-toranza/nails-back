package jsges.nails.DTO.servicios;

import jsges.nails.DTO.TipoObjetoDTO;

public class TipoServicioDTO extends TipoObjetoDTO {

    private Integer id;
    private String denominacion;

    public TipoServicioDTO() {
    }

    public TipoServicioDTO(Integer id, String denominacion) {
        this.id = id;
        this.denominacion = denominacion;
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