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
public class Valorizacion {

    private int codigo;
    private ClienteEntrante clienteEntrante;
    private String tmh;
    private String costoTransporteTrujilloSoles;
    private String costoTransporteNascaSoles;
    private String totalAnalisis;
    private String totalUS;
    private String totalPorcentaje;
    private String adelantos;
    private String otrosGastos;
    private String totalGastos;
    private String totalPagar;
    private String valorizacionEstado;
    private Date fecha;
    private String cambio;
    private String costoTransporteTrujilloDolar;
    private String costoTransporteNascaDolar;
    private String tarifaAnalisis;
    private String cLotes;
    private String tarifaPorcentaje;
    private Date FechaId;
    private String toneladas;
    private String DescuentoSoles;
    private String policia;


    public int getCodigo() {
        return codigo;
    }


    
    

    public Date getFechaId() {
        return FechaId;
    }

    public void setFechaId(Date FechaId) {
        this.FechaId = FechaId;
    }

    
    
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public ClienteEntrante getClienteEntrante() {
        return clienteEntrante;
    }

    public void setClienteEntrante(ClienteEntrante clienteEntrante) {
        this.clienteEntrante = clienteEntrante;
    }

    public String getTmh() {
        return tmh;
    }

    public void setTmh(String tmh) {
        this.tmh = tmh;
    }

    public String getCostoTransporteTrujilloSoles() {
        return costoTransporteTrujilloSoles;
    }

    public void setCostoTransporteTrujilloSoles(String costoTransporteTrujilloSoles) {
        this.costoTransporteTrujilloSoles = costoTransporteTrujilloSoles;
    }

    public String getCostoTransporteNascaSoles() {
        return costoTransporteNascaSoles;
    }

    public void setCostoTransporteNascaSoles(String costoTransporteNascaSoles) {
        this.costoTransporteNascaSoles = costoTransporteNascaSoles;
    }

    public String getTotalAnalisis() {
        return totalAnalisis;
    }

    public void setTotalAnalisis(String totalAnalisis) {
        this.totalAnalisis = totalAnalisis;
    }

    public String getTotalUS() {
        return totalUS;
    }

    public void setTotalUS(String totalUS) {
        this.totalUS = totalUS;
    }

    public String getTotalPorcentaje() {
        return totalPorcentaje;
    }

    public void setTotalPorcentaje(String totalPorcentaje) {
        this.totalPorcentaje = totalPorcentaje;
    }

    public String getAdelantos() {
        return adelantos;
    }

    public void setAdelantos(String adelantos) {
        this.adelantos = adelantos;
    }

    public String getOtrosGastos() {
        return otrosGastos;
    }

    public void setOtrosGastos(String otrosGastos) {
        this.otrosGastos = otrosGastos;
    }

    public String getTotalGastos() {
        return totalGastos;
    }

    public void setTotalGastos(String totalGastos) {
        this.totalGastos = totalGastos;
    }

    public String getTotalPagar() {
        return totalPagar;
    }

    public void setTotalPagar(String totalPagar) {
        this.totalPagar = totalPagar;
    }

    public String getCostoTransporteTrujilloDolar() {
        return costoTransporteTrujilloDolar;
    }

    public void setCostoTransporteTrujilloDolar(String costoTransporteTrujilloDolar) {
        this.costoTransporteTrujilloDolar = costoTransporteTrujilloDolar;
    }

    public String getCostoTransporteNascaDolar() {
        return costoTransporteNascaDolar;
    }

    public void setCostoTransporteNascaDolar(String costoTransporteNascaDolar) {
        this.costoTransporteNascaDolar = costoTransporteNascaDolar;
    }

    public String getTarifaAnalisis() {
        return tarifaAnalisis;
    }

    public void setTarifaAnalisis(String tarifaAnalisis) {
        this.tarifaAnalisis = tarifaAnalisis;
    }

    public String getcLotes() {
        return cLotes;
    }

    public void setcLotes(String cLotes) {
        this.cLotes = cLotes;
    }

    public String getTarifaPorcentaje() {
        return tarifaPorcentaje;
    }

    public void setTarifaPorcentaje(String tarifaPorcentaje) {
        this.tarifaPorcentaje = tarifaPorcentaje;
    }

    public String getToneladas() {
        return toneladas;
    }

    public void setToneladas(String toneladas) {
        this.toneladas = toneladas;
    }

    public String getDescuentoSoles() {
        return DescuentoSoles;
    }

    public void setDescuentoSoles(String DescuentoSoles) {
        this.DescuentoSoles = DescuentoSoles;
    }

    public String getPolicia() {
        return policia;
    }

    public void setPolicia(String policia) {
        this.policia = policia;
    }


    public String getValorizacionEstado() {
        return valorizacionEstado;
    }

    public void setValorizacionEstado(String valorizacionEstado) {
        this.valorizacionEstado = valorizacionEstado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getCambio() {
        return cambio;
    }

    public void setCambio(String cambio) {
        this.cambio = cambio;
    }

    @Override
    public String toString() {
        return "Valorizacion{" + "codigo=" + codigo + ", clienteEntrante=" + clienteEntrante.getApellidos() + ", tmh=" + tmh + ", costoTransporteTrujilloSoles=" + costoTransporteTrujilloSoles + ", costoTransporteNascaSoles=" + costoTransporteNascaSoles + ", totalAnalisis=" + totalAnalisis + ", totalUS=" + totalUS + ", totalPorcentaje=" + totalPorcentaje + ", adelantos=" + adelantos + ", otrosGastos=" + otrosGastos + ", totalGastos=" + totalGastos + ", totalPagar=" + totalPagar + ", valorizacionEstado=" + valorizacionEstado + ", fecha=" + fecha + ", cambio=" + cambio + ", costoTransporteTrujilloDolar=" + costoTransporteTrujilloDolar + ", costoTransporteNascaDolar=" + costoTransporteNascaDolar + ", tarifaAnalisis=" + tarifaAnalisis + ", cLotes=" + cLotes + ", tarifaPorcentaje=" + tarifaPorcentaje + ", FechaId=" + FechaId + ", toneladas=" + toneladas + ", DescuentoSoles=" + DescuentoSoles + ", policia=" + policia + '}';
    }
    
    
    

}
