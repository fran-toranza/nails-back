package jsges.nails.DTO.servicios;

import jsges.nails.domain.servicios.ItemServicio;
import jsges.nails.domain.servicios.Servicio;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ServicioDTO {

    private Integer id;
    private Integer cliente;
    private Timestamp fechaDocumento;
    private Set<ItemServicioDTO> listaItems = new HashSet<>();
    private Double total;
    private String clienteRazonSocial;
    private String denominacion;


    public ServicioDTO() {
    }

    public ServicioDTO(Servicio elemento, List<ItemServicio> list) {
        this.id = elemento.getId();
        this.cliente = elemento.getCliente().getId();
        this.clienteRazonSocial = elemento.getCliente().getRazonSocial();
        this.fechaDocumento = elemento.getFechaRealizacion();
        this.total = elemento.getTotal();
        list.forEach(model -> listaItems.add(new ItemServicioDTO(model)));
    }

    public ServicioDTO(Servicio elemento) {
        this.id = elemento.getId();
        this.cliente = elemento.getCliente().getId();
        this.clienteRazonSocial = elemento.getCliente().getRazonSocial();
        this.fechaDocumento = elemento.getFechaRealizacion();
        this.total = elemento.getTotal();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCliente() {
        return cliente;
    }

    public void setCliente(Integer cliente) {
        this.cliente = cliente;
    }

    public Timestamp getFechaDocumento() {
        return fechaDocumento;
    }

    public void setFechaDocumento(Timestamp fechaDocumento) {
        this.fechaDocumento = fechaDocumento;
    }

    public Set<ItemServicioDTO> getListaItems() {
        return listaItems;
    }

    public void setListaItems(Set<ItemServicioDTO> listaItems) {
        this.listaItems = listaItems;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getClienteRazonSocial() {
        return clienteRazonSocial;
    }

    public void setClienteRazonSocial(String clienteRazonSocial) {
        this.clienteRazonSocial = clienteRazonSocial;
    }
    
    

}