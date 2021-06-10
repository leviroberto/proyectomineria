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
public class GastosExtras {

    private int codigo;
    private String descripcion;
    private Double monto;
    private ClienteEntrante clienteEntrante;
    private Concepto concepto;
    private String moneda;
    private Estado estado;
    private Personal personal;
    private Date fecha;

    public GastosExtras() {
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public ClienteEntrante getClienteEntrante() {
        return clienteEntrante;
    }

    public void setClienteEntrante(ClienteEntrante clienteEntrante) {
        this.clienteEntrante = clienteEntrante;
    }

    public Concepto getConcepto() {
        return concepto;
    }

    public void setConcepto(Concepto concepto) {
        this.concepto = concepto;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "GastosExtras{" + "codigo=" + codigo + ", descripcion=" + descripcion + ", monto=" + monto + ", clienteEntrante=" + clienteEntrante + ", concepto=" + concepto.getCodigo() + ", moneda=" + moneda + ", estado=" + estado + ", personal=" + personal.getCodigo() + ", fecha=" + +'}';
    }

}
