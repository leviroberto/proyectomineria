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
public class Empresa {

    private int codigo;
    private String departamento;
    private String direccion;
    private String nombreComercial;
    private String distrito;
    private String nombreLegal;
    private String nroDocumento;
    private String telefono;
    private String tipoDocumento;
    private String ubigeo;
    private String urbanizacion;
    private String provincia;
    private String rutaPFX;
    private String clavePFX;
    private String rutaXML;
    private Estado estado;
    private String vision;
    private String mision;
    private Date fechaRegistro;
    private String rutaImagen;
    
    private String empresabd;

    public Empresa() {
    }

    public String getEmpresabd() {
        return empresabd;
    }

    public void setEmpresabd(String empresabd) {
        this.empresabd = empresabd;
    }

    
    
    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    
    
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getNombreLegal() {
        return nombreLegal;
    }

    public void setNombreLegal(String nombreLegal) {
        this.nombreLegal = nombreLegal;
    }

    public String getNroDocumento() {
        return nroDocumento;
    }

    public void setNroDocumento(String nroDocumento) {
        this.nroDocumento = nroDocumento;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getUbigeo() {
        return ubigeo;
    }

    public void setUbigeo(String ubigeo) {
        this.ubigeo = ubigeo;
    }

    public String getUrbanizacion() {
        return urbanizacion;
    }

    public void setUrbanizacion(String urbanizacion) {
        this.urbanizacion = urbanizacion;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getRutaPFX() {
        return rutaPFX;
    }

    public void setRutaPFX(String rutaPFX) {
        this.rutaPFX = rutaPFX;
    }

    public String getClavePFX() {
        return clavePFX;
    }

    public void setClavePFX(String clavePFX) {
        this.clavePFX = clavePFX;
    }

    public String getRutaXML() {
        return rutaXML;
    }

    public void setRutaXML(String rutaXML) {
        this.rutaXML = rutaXML;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getVision() {
        return vision;
    }

    public void setVision(String vision) {
        this.vision = vision;
    }

    public String getMision() {
        return mision;
    }

    public void setMision(String mision) {
        this.mision = mision;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    @Override
    public String toString() {
        return "Empresa{" + "codigo=" + codigo + ", departamento=" + departamento + ", direccion=" + direccion + ", nombreComercial=" + nombreComercial + ", distrito=" + distrito + ", nombreLegal=" + nombreLegal + ", nroDocumento=" + nroDocumento + '}';
    }

}
