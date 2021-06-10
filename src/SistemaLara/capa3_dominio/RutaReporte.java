/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa3_dominio;

/**
 *
 * @author mineria
 */
public class RutaReporte {

    private int codigo;
    private String adelantoCliente;
    private String adelantoProveedor;
    private String cheque;
    private String factura;
    private String liquidacion;
    private String pagoTransporte;
    private String valorizacion;
    private String notaDebito;
        private String notaCredito;

    public String getNotaCredito() {
        return notaCredito;
    }

    public void setNotaCredito(String notaCredito) {
        this.notaCredito = notaCredito;
    }

        

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getAdelantoCliente() {
        return adelantoCliente;
    }

    public void setAdelantoCliente(String adelantoCliente) {
        this.adelantoCliente = adelantoCliente;
    }

    public String getAdelantoProveedor() {
        return adelantoProveedor;
    }

    public void setAdelantoProveedor(String adelantoProveedor) {
        this.adelantoProveedor = adelantoProveedor;
    }

    public String getCheque() {
        return cheque;
    }

    public void setCheque(String cheque) {
        this.cheque = cheque;
    }

    public String getFactura() {
        return factura;
    }

    public void setFactura(String factura) {
        this.factura = factura;
    }

    public String getLiquidacion() {
        return liquidacion;
    }

    public void setLiquidacion(String liquidacion) {
        this.liquidacion = liquidacion;
    }

    public String getPagoTransporte() {
        return pagoTransporte;
    }

    public void setPagoTransporte(String pagoTransporte) {
        this.pagoTransporte = pagoTransporte;
    }

    public String getValorizacion() {
        return valorizacion;
    }

    public void setValorizacion(String valorizacion) {
        this.valorizacion = valorizacion;
    }

    public String getNotaDebito() {
        return notaDebito;
    }

    public void setNotaDebito(String notaDebito) {
        this.notaDebito = notaDebito;
    }

}
