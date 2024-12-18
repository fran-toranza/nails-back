package jsges.nails.domain.servicios;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("ItemServicio")
public class ItemServicio extends TipoServicio {

    @Column(columnDefinition = "TEXT")
    private String observacion;

    @ManyToOne
    @JoinColumn(name = "tipo_servicio_id", nullable = false)
    private TipoServicio tipoServicio;

    @ManyToOne
    @JoinColumn(name = "servicio_id", nullable = false)
    private Servicio servicio;

    private Double precio;

    public ItemServicio(Servicio servicio, TipoServicio tipoServicio, Double precio, String observacion) {
        this.servicio = servicio;
        this.tipoServicio = tipoServicio;
        this.precio = precio;
        this.observacion = observacion;
    }
}
