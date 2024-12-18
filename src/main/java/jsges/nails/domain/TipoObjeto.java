package jsges.nails.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;


@MappedSuperclass
@Data
public class TipoObjeto implements Serializable {

    public static final int ESTADO_ACTIVO = 0;
    public static final int ESTADO_ELIMINADO = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tipo_objeto_id_seq")
    @SequenceGenerator(name = "tipo_objeto_id_seq", sequenceName = "tipo_objeto_id_seq", allocationSize = 1)
    private Integer id;

    private int codigo; 

    @Column(length = 255)
    @Size(max = 255, message = "La denominación no puede exceder los 255 caracteres.")
    private String denominacion;

    @Column(length = 500)
    @Size(max = 500, message = "El detalle no puede exceder los 500 caracteres.")
    private String detalle;

    private int estado = ESTADO_ACTIVO;

    public void markAsEliminado() {
        this.estado = 1;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        TipoObjeto other = (TipoObjeto) obj;
        return Objects.equals(id, other.id);
    }
}
