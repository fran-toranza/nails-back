package jsges.nails.DTO.servicios;

import jsges.nails.domain.servicios.ItemServicio;
import lombok.Data;

@Data
public class ItemServicioDTO {
    private Integer id;
    private String tipoServicio;
    private Integer tipoServicioId;
    private Double precio;
    private String observaciones;

    public ItemServicioDTO(ItemServicio model) {
        this.observaciones = model.getObservacion();
        this.precio = model.getPrecio();
        this.tipoServicio = model.getTipoServicio().getDenominacion();
        this.tipoServicioId = model.getTipoServicio().getId();
        this.id = model.getId();
    }

    public ItemServicioDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(String tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public Integer getTipoServicioId() {
        return tipoServicioId;
    }

    public void setTipoServicioId(Integer tipoServicioId) {
        this.tipoServicioId = tipoServicioId;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}