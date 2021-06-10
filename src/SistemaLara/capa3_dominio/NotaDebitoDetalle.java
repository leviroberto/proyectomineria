/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa3_dominio;

/**
 *
 * @author MINERAMIRAFLORES
 */
public class NotaDebitoDetalle {

    private int codigo;
    private String cantidad;
    private String unidad;
    private String descripcion;
    private Double precioUnitario;
    private Double valorVenta;
    private NotaDebito notaDebito;
    private Estado estado;
    private Personal personal;
    private Reintegro reintegro;
    private String NotaDebitoEstado;

    public Double getValorVenta() {
        return valorVenta;
    }

    public void setValorVenta(Double valorVenta) {
        this.valorVenta = valorVenta;
    }

    public NotaDebito getNotaDebito() {
        return notaDebito;
    }

    public void setNotaDebito(NotaDebito notaDebito) {
        this.notaDebito = notaDebito;
    }

    public Reintegro getReintegro() {
        return reintegro;
    }

    public void setReintegro(Reintegro reintegro) {
        this.reintegro = reintegro;
    }

    public String getNotaDebitoEstado() {
        return NotaDebitoEstado;
    }

    public void setNotaDebitoEstado(String NotaDebitoEstado) {
        this.NotaDebitoEstado = NotaDebitoEstado;
    }

 
    public NotaDebitoDetalle() {
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }


  



    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

  


    
}
