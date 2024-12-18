package jsges.nails.DTO.Organizacion;

import jsges.nails.DTO.TipoObjetoDTO;
import jsges.nails.domain.organizacion.Cliente;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
public class ClienteDTO extends TipoObjetoDTO {

    private String razonSocial;
    private String nombre;
    private String celular;
    private String mail;
    private Date fechaInicio;
    private Date fechaNacimiento;

    public ClienteDTO() {
    }

    public ClienteDTO(Cliente cliente) {
        this.setId(cliente.getId());
        this.setCodigo(cliente.getCodigo());
        this.setDenominacion(cliente.getDenominacion());
        this.setEstado(cliente.getEstado());
        this.setDetalle(cliente.getDetalle());

        this.razonSocial = cliente.getRazonSocial();
        this.nombre = cliente.getNombre();
        this.celular = cliente.getCelular();
        this.mail = cliente.getMail();
        this.fechaInicio = cliente.getFechaInicio();
        this.fechaNacimiento = cliente.getFechaNacimiento();
    }
}
