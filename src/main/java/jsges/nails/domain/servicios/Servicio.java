package jsges.nails.domain.servicios;

import jakarta.persistence.*;
import jsges.nails.domain.organizacion.Cliente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Servicio extends TipoServicio {


        @ManyToOne(cascade = CascadeType.ALL)
        private Cliente cliente;

        private Timestamp fechaRegistro;
        private Timestamp fechaRealizacion;
        private double total;
        private String denominacion;
}