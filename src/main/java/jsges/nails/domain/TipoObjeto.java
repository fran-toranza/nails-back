package jsges.nails.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class TipoObjeto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tipo_objeto_id_seq")
    @SequenceGenerator(name = "tipo_objeto_id_seq", sequenceName = "tipo_objeto_id_seq", allocationSize = 1)
    private Integer id;

    private int codigo;

    @Lob
    private String denominacion;

    private int estado;

    @Lob
    private String detalle;

    public void markAsEliminado() {
        this.estado = 1;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        TipoObjeto other = (TipoObjeto) obj;
        return Objects.equals(id, other.id);
    }
}