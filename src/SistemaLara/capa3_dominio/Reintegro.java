/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa3_dominio;

import java.sql.Date;

/**
 *
 * @author mineria
 */
public class Reintegro {

    private int codigo;
    private Date fecha;
    private ClienteEntrante clienteEntrante;
    private Procedencia procedencia;
    private String lote;
    private Double tmh;
    private Double tms;
    private Double h2o;
    private Double leyau;
    private Double leyag;
    private Double inter;
    private Double rec;
    private Double maquilla;
    private Double factor;
    private Double conscon;
    private Double escalador;
    private Double Stms;
    private Double totalUs;
    private Estado estado;
    private Liquidacion liquidacion;
    private Double reintrego;
    private String pago;

    public Reintegro() {
    }

    public Double getTms() {
        return tms;
    }

    public void setTms(Double tms) {
        this.tms = tms;
    }

    
    
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public ClienteEntrante getClienteEntrante() {
        return clienteEntrante;
    }

    public void setClienteEntrante(ClienteEntrante clienteEntrante) {
        this.clienteEntrante = clienteEntrante;
    }

    public Procedencia getProcedencia() {
        return procedencia;
    }

    public void setProcedencia(Procedencia procedencia) {
        this.procedencia = procedencia;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public Double getTmh() {
        return tmh;
    }

    public void setTmh(Double tmh) {
        this.tmh = tmh;
    }

    public Double getH2o() {
        return h2o;
    }

    public void setH2o(Double h2o) {
        this.h2o = h2o;
    }

    public Double getLeyau() {
        return leyau;
    }

    public void setLeyau(Double leyau) {
        this.leyau = leyau;
    }

    public Double getLeyag() {
        return leyag;
    }

    public void setLeyag(Double leyag) {
        this.leyag = leyag;
    }

    

    public Double getInter() {
        return inter;
    }

    public void setInter(Double inter) {
        this.inter = inter;
    }

    public Double getRec() {
        return rec;
    }

    public void setRec(Double rec) {
        this.rec = rec;
    }

    public Double getMaquilla() {
        return maquilla;
    }

    public void setMaquilla(Double maquilla) {
        this.maquilla = maquilla;
    }

    public Double getFactor() {
        return factor;
    }

    public void setFactor(Double factor) {
        this.factor = factor;
    }

    public Double getConscon() {
        return conscon;
    }

    public void setConscon(Double conscon) {
        this.conscon = conscon;
    }

    public Double getEscalador() {
        return escalador;
    }

    public void setEscalador(Double escalador) {
        this.escalador = escalador;
    }

    public Double getStms() {
        return Stms;
    }

    public void setStms(Double Stms) {
        this.Stms = Stms;
    }

    public Double getTotalUs() {
        return totalUs;
    }

    public void setTotalUs(Double totalUs) {
        this.totalUs = totalUs;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Liquidacion getLiquidacion() {
        return liquidacion;
    }

    public void setLiquidacion(Liquidacion liquidacion) {
        this.liquidacion = liquidacion;
    }

    public Double getReintrego() {
        return reintrego;
    }

    public void setReintrego(Double reintrego) {
        this.reintrego = reintrego;
    }

    public String getPago() {
        return pago;
    }

    public void setPago(String pago) {
        this.pago = pago;
    }

  
  
    
    

}
