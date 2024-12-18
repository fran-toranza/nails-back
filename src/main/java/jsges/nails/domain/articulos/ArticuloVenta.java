package jsges.nails.domain.articulos;

import jakarta.persistence.*;
import jsges.nails.domain.TipoObjeto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class ArticuloVenta extends TipoObjeto {

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "linea_id", nullable = false)
    private Linea linea;

    public ArticuloVenta() {
        super();
    }
}
