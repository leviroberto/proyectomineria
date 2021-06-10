/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa3_dominio;

import java.sql.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author FiveCod Software
 */
public class AdelantoPersonal {

    private int codigo;
    private Contrato contrato;
    private Date fecha;
    private String motivo;
    private double montoAdelanto;
    private Estado estado;
    private Empresa empresa;

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public AdelantoPersonal() {
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public double getMontoAdelanto() {
        return montoAdelanto;
    }

    public void setMontoAdelanto(double montoAdelanto) {
        this.montoAdelanto = montoAdelanto;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    //reglas de negocio
    public double calcularSueldoActual() {
        return contrato.getSueldo() - montoAdelanto;
    }

    public double calcularSueldoActual(Double monto) {
        return contrato.getSueldo() - monto;
    }

    public boolean verificarMonto(Double monto) {
        return monto <= contrato.getSueldo();
    }

    @Override
    public String toString() {
        return "Adelanto{" + "codigo=" + codigo + ", contrato=" + contrato + ", fecha=" + fecha + ", motivo=" + motivo + ", montoAdelanto=" + montoAdelanto + ", estado=" + estado + '}';
    }

}
