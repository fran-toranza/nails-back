package jsges.nails.DTO.articulos;

import jsges.nails.DTO.TipoObjetoDTO;
import jsges.nails.domain.articulos.ArticuloVenta;

public class ArticuloVentaDTO extends TipoObjetoDTO {

    private Integer linea;

    public ArticuloVentaDTO(ArticuloVenta model) {
        this.setId(model.getId());
        this.setCodigo(model.getCodigo());
        this.setDenominacion(model.getDenominacion());
        this.setEstado(model.getEstado());
        this.setDetalle(model.getDetalle());
        this.linea = model.getLinea().getId();
    }

    public ArticuloVentaDTO() {
    }

    public Integer getLinea() {
        return linea;
    }

    public void setLinea(Integer linea) {
        this.linea = linea;
    }
}
