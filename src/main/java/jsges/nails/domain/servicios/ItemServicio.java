package jsges.nails.domain.servicios;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import java.util.Objects;

@Entity
@Data
@AllArgsConstructor
@ToString
public class ItemServicio extends TipoServicio {

    @Column(columnDefinition = "TEXT")
    private String observacion;

    @ManyToOne(cascade = CascadeType.ALL)
    private TipoServicio tipoServicio;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Servicio servicio;

    private Double precio;


    public void asEliminado() {
        this.setEstado(1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemServicio that = (ItemServicio) o;
        return this.getEstado() == that.getEstado() && Objects.equals(this.getId(), that.getId()) && Objects.equals(observacion, that.observacion) && Objects.equals(tipoServicio, that.tipoServicio) && Objects.equals(servicio, that.servicio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId(), this.getEstado(), observacion, tipoServicio, servicio);
    }

    public ItemServicio() {

    }

    public ItemServicio(Servicio servicio ,TipoServicio tipo, Double precio,String observacion) {
        this.servicio = servicio;
        this.tipoServicio = tipo;
        this.precio = precio;
        this.observacion=observacion;
    }
}
