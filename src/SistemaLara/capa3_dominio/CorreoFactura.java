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
public class CorreoFactura {
    private int codigo;
    private Electronica electronico;
    private ProveedorServicio proveedorServicio; 
    private String mensaje;
    private String asunoto;

    public CorreoFactura() {
        mensaje="";
        asunoto="";
        
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Electronica getElectronico() {
        return electronico;
    }

    public void setElectronico(Electronica electronico) {
        this.electronico = electronico;
    }
    
    

    public ProveedorServicio getProveedorServicio() {
        return proveedorServicio;
    }

    public void setProveedorServicio(ProveedorServicio proveedorServicio) {
        this.proveedorServicio = proveedorServicio;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getAsunoto() {
        return asunoto;
    }

    public void setAsunoto(String asunoto) {
        this.asunoto = asunoto;
    }

  
    
    
    
}
