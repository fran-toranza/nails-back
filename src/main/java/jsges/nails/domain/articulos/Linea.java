package jsges.nails.domain.articulos;

import jakarta.persistence.*;
import jsges.nails.DTO.articulos.LineaDTO;
import jsges.nails.domain.TipoObjeto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Linea extends TipoObjeto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Lob
    private String denominacion;

    private int estado;

    @Lob
    private String observacion;

    public Linea(String nombre) {
        this.denominacion = nombre;
    }

    public Linea(LineaDTO model) {
        this.denominacion = model.denominacion;
    }
}