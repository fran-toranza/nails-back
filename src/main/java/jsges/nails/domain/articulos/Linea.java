package jsges.nails.domain.articulos;

import jakarta.persistence.Entity;
import jsges.nails.DTO.articulos.LineaDTO;
import jsges.nails.domain.TipoObjeto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Linea extends TipoObjeto {

    public Linea() {
        super();
    }

    public Linea(String nombre) {
        this.setDenominacion(nombre);
    }

    public Linea(LineaDTO model) {
        this.setDenominacion(model.getDenominacion());
    }
}
