package jsges.nails.DTO.Organizacion;

import jsges.nails.DTO.TipoObjetoDTO;
import jsges.nails.domain.organizacion.Cliente;
import lombok.Data;

import java.util.Date;

@Data
public class ClienteDTO  extends TipoObjetoDTO {

    private String razonSocial;
    private String letra;
    private String contacto;
    private String celular;
    private String mail;
    private Date fechaInicio;
    private Date fechaNacimiento;

    public ClienteDTO(Cliente model) {
        this.setId(model.getId());
        this.razonSocial = model.getRazonSocial();
        this.letra = model.getLetra();
        this.contacto = model.getContacto();
        this.celular = model.getCelular();
        this.mail = model.getMail();
        this.fechaInicio = model.getFechaInicio();
        this.fechaNacimiento = model.getFechaNacimiento();
    }

    public ClienteDTO() {
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
}