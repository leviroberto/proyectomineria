/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa3_dominio;

import java.sql.Date;

/**
 *
 * @author MINERAMIRAFLORES
 */
public class Adelanto {

    private int codigo;
    private Double cantidad;
    private String moneda;
    private String descripcion;
    private Date fechaPago;
    private ClienteEntrante clienteEntrante;
    private ProveedorServicio proveedorServicio;
    private Personal personal;
    private String adelantoEstado;
    private Estado estado;
    private Valorizacion valorizacion;
    private Empresa empresa;

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Valorizacion getValorizacion() {
        return valorizacion;
    }

    public void setValorizacion(Valorizacion valorizacion) {
        this.valorizacion = valorizacion;
    }

    public Adelanto() {
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public ClienteEntrante getClienteEntrante() {
        return clienteEntrante;
    }

    public void setClienteEntrante(ClienteEntrante clienteEntrante) {
        this.clienteEntrante = clienteEntrante;
    }

    public ProveedorServicio getProveedorServicio() {
        return proveedorServicio;
    }

    public void setProveedorServicio(ProveedorServicio proveedorServicio) {
        this.proveedorServicio = proveedorServicio;
    }

    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public String getAdelantoEstado() {
        return adelantoEstado;
    }

    public void setAdelantoEstado(String adelanteEstado) {
        this.adelantoEstado = adelanteEstado;
    }

}
