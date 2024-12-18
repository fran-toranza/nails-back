package jsges.nails.domain.organizacion;

import jsges.nails.domain.TipoObjeto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "cliente")
@Data
@EqualsAndHashCode(callSuper = true)
public class Cliente extends TipoObjeto {

    @Column(name = "razon_social", nullable = false)
    @Size(min = 2, max = 20, message = "La razón social debe tener entre 2 y 20 caracteres.")
    private String razonSocial;

    @Column(name = "nombre", nullable = false)
    @Size(min = 2, max = 20, message = "El nombre debe tener entre 2 y 20 caracteres.")
    private String nombre;

    @Pattern(regexp = "^[0-9]{9,15}$", message = "El número de teléfono debe tener entre 9 y 15 dígitos")
    @Column(nullable = false, unique = true)
    private String celular;

    @Column(name = "mail", nullable = false)
    @Pattern(
        regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
        message = "El formato del correo es inválido."
    )
    private String mail;

    @Column(name = "fecha_inicio", nullable = false)
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaInicio;

    @Column(name = "fecha_nacimiento", nullable = false)
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaNacimiento;
}
