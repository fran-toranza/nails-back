package jsges.nails.domain.organizacion;

import jakarta.persistence.*;
import jsges.nails.domain.TipoObjeto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Cliente extends TipoObjeto {



        @Lob
        @Column(nullable = false)
        private String razonSocial;


        @Lob
        private String letra;

        @Lob
        private String contacto;

        @Lob
        private String celular;

        @Lob
        private String mail;

        @Temporal(TemporalType.DATE)
        private Date fechaInicio;

        @Temporal(TemporalType.DATE)
        private Date fechaNacimiento;
}