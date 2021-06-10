/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa3_dominio;

/**
 *
 * @author XGamer
 */
public class Cambio {
    
    private int codigo;
    private String dolar;
    private String tarifaa;
    private String tarifa;
    private String trans1;
    private String trans2;
    private String poli;
    private String tonelada;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDolar() {
        return dolar;
    }

    public void setDolar(String dolar) {
        this.dolar = dolar;
    }

    public String getTarifaa() {
        return tarifaa;
    }

    public void setTarifaa(String tarifaa) {
        this.tarifaa = tarifaa;
    }

    public String getTarifa() {
        return tarifa;
    }

    public void setTarifa(String tarifa) {
        this.tarifa = tarifa;
    }

    public String getTrans1() {
        return trans1;
    }

    public void setTrans1(String trans1) {
        this.trans1 = trans1;
    }

    public String getTrans2() {
        return trans2;
    }

    public void setTrans2(String trans2) {
        this.trans2 = trans2;
    }

    public String getPoli() {
        return poli;
    }

    public void setPoli(String poli) {
        this.poli = poli;
    }

    public String getTonelada() {
        return tonelada;
    }

    public void setTonelada(String tonelada) {
        this.tonelada = tonelada;
    }

    @Override
    public String toString() {
        return "Cambio{" + "codigo=" + codigo + ", dolar=" + dolar + ", tarifaa=" + tarifaa + ", tarifa=" + tarifa + ", trans1=" + trans1 + ", trans2=" + trans2 + ", poli=" + poli + ", tonelada=" + tonelada + '}';
    }
    
    
    
    
    
}
