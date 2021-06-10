/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa3_dominio;

import static SistemaLara.capa1_presentacion.FormGestionarLiquidacion.redondearDecimales;
import SistemaLara.capa1_presentacion.util.Verificador;
import java.sql.Date;

/**
 *
 * @author MINERAMIRAFLORES
 */
public class Liquidacion {

    private int codigo;
    private Procedencia procedencia;
    private Date fecha;
    private ClienteEntrante clienteEntrante;
    private String lote;
    private String tmh;
    private String h2o;
    private String tms;
    private String leyau;
    private String leyag;
    private String inter;
    private String rec;
    private String escalador;
    private String maquilla;
    private String factor;
    private String conscn;
    private String stms;
    private String totalus;
    private String reintegro;
    private String facturado;
    private Estado estado;
    private Personal personal;
    private String estadoLiquidacion;
    private Empresa empresa;
    private int ValorizacionId;
    private String proteccion;

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public int getValorizacionId() {
        return ValorizacionId;
    }

    public void setValorizacionId(int ValorizacionId) {
        this.ValorizacionId = ValorizacionId;
    }

    public String getEscalador() {
        return escalador;
    }

    public void setEscalador(String escalador) {
        this.escalador = escalador;
    }

    public String getEstadoLiquidacion() {
        return estadoLiquidacion;
    }

    public String getConscn() {
        return conscn;
    }

    public void setConscn(String conscn) {
        this.conscn = conscn;
    }

    public void setEstadoLiquidacion(String estadoLiquidacion) {
        this.estadoLiquidacion = estadoLiquidacion;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Procedencia getProcedencia() {
        return procedencia;
    }

    public void setProcedencia(Procedencia procedencia) {
        this.procedencia = procedencia;
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

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public String getTmh() {
        return tmh;
    }

    public void setTmh(String tmh) {
        this.tmh = tmh;
    }

    public String getH2o() {
        return h2o;
    }

    public void setH2o(String h2o) {
        this.h2o = h2o;
    }

    public String getTms() {
        return tms;
    }

    public void setTms(String tms) {
        this.tms = tms;
    }

    public String getLeyau() {
        return leyau;
    }

    public void setLeyau(String leyau) {
        this.leyau = leyau;
    }

    public String getLeyag() {
        return leyag;
    }

    public void setLeyag(String leyag) {
        this.leyag = leyag;
    }

    public String getInter() {
        return inter;
    }

    public void setInter(String inter) {
        this.inter = inter;
    }

    public String getRec() {
        return rec;
    }

    public void setRec(String rec) {
        this.rec = rec;
    }

    public String getMaquilla() {
        return maquilla;
    }

    public void setMaquilla(String maquilla) {
        this.maquilla = maquilla;
    }

    public String getFactor() {
        return factor;
    }

    public void setFactor(String factor) {
        this.factor = factor;
    }

    public String getStms() {
        return stms;
    }

    public void setStms(String stms) {
        this.stms = stms;
    }

    public String getTotalus() {
        return totalus;
    }

    public void setTotalus(String totalus) {
        this.totalus = totalus;
    }

    public String getReintegro() {
        return reintegro;
    }

    public void setReintegro(String reintegro) {
        this.reintegro = reintegro;
    }

    public String getFacturado() {
        return facturado;
    }

    public void setFacturado(String facturado) {
        this.facturado = facturado;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }

    public String getProteccion() {
        return proteccion;
    }

    public void setProteccion(String proteccion) {
        this.proteccion = proteccion;
    }

    
    
    
    //REGLA DE NEGOCIO
    public Double calcularTotalTms() {
        Double totalTms = 0.0;
        Double convertidoTmh = Double.parseDouble(tmh);
        totalTms = +(convertidoTmh - (convertidoTmh * (Double.parseDouble(h2o) / 100)));
        return totalTms;
    }

    public Double calcularTotal() {
        Double total$TMS = 0.0;
        double leyau = Double.parseDouble(this.leyau);
        double inter = Double.parseDouble(this.inter);
        double rec = Double.parseDouble(this.rec);
        double maquilla = Double.parseDouble(this.maquilla);
        double factor = Double.parseDouble(this.factor);
        double conscon = Double.parseDouble(conscn);
        double total = +(((((leyau * (rec / 100))) * (inter - 60)) - maquilla - conscon) * factor);
        total$TMS = redondearDecimales(total, 2);
        return total$TMS;
    }

    public Double calcularTotalUs() {
        Double totalUs = 0.0;
        double totalu = +calcularTotalTms() * calcularTotal();
        totalUs = redondearDecimales(totalu, 2);
        return totalUs;
    }

}
