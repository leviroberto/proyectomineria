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
public class Electronica {

    private int id;
    private int codigoFactura;
    private String rutaxml;
    private String rutapdf;
    private String numeroFactura;

    public Electronica() {
        rutapdf="";
        rutaxml="";
        numeroFactura="";
    }

    public String getNumeroFactura() {
        return numeroFactura;
    }

    public int getCodigoFactura() {
        return codigoFactura;
    }

    public void setCodigoFactura(int codigoFactura) {
        this.codigoFactura = codigoFactura;
    }
    
    
    

    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public String getRutaxml() {
        return rutaxml;
    }

    public void setRutaxml(String rutaxml) {
        this.rutaxml = rutaxml;
    }

    public String getRutapdf() {
        return rutapdf;
    }

    public void setRutapdf(String rutapdf) {
        this.rutapdf = rutapdf;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Electronica(int id) {
        this.id = id;
    }

}
