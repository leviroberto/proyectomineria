/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa3_dominio;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author FiveCod Software
 */
public class PagoPersonal {

    private int codigo;
    private Contrato contrato;
    private Date fechaPago;
    private Double montoPagado;
    private Estado estado;
    private Empresa empresa;

    private List<Adelanto> listaAdelantos;

    public PagoPersonal() {
        listaAdelantos = new ArrayList<>();
    }

    public List<Adelanto> getListaAdelantos() {
        return listaAdelantos;
    }

    public void setListaAdelantos(List<Adelanto> listaAdelantos) {
        this.listaAdelantos = listaAdelantos;
    }
    
    
    

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public Double getMontoPagado() {
        return montoPagado;
    }

    public void setMontoPagado(Double montoPagado) {
        this.montoPagado = montoPagado;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public boolean yaEstaPagadoFecha() {
        boolean estaPagado = false;
//        String fechaActual = Fechas.obtenerFechaActual();
//        if (fechaActual.equals(Fechas.formatearFechas(fechaPago))) {
//            if (estado.equals(ESTADO_ACTIVO)) {
//                estaPagado = true;
//            }
//        }

        return estaPagado;
    }

}
