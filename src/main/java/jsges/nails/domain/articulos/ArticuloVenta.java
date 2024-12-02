package jsges.nails.domain.articulos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "linea")
public class ArticuloVenta {

        // Constante para representar el estado eliminado
        public static final int ESTADO_ELIMINADO = 1;

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        @Column(nullable = false, length = 255)
        @Size(max = 255, message = "La denominación no puede exceder los 255 caracteres.")
        private String denominacion;

        @Column(nullable = false)
        @Min(0)
        @Max(1)
        @ColumnDefault("0")
        private int estado;

        @Column(length = 500)
        @Size(max = 500, message = "La observación no puede exceder los 500 caracteres.")
        private String observacion;

        @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
        @JoinColumn(name = "linea_id", nullable = false)
        private Linea linea;

        // Método para marcar como eliminado
        public void markAsDeleted() {
                this.estado = ESTADO_ELIMINADO;
        }
}
