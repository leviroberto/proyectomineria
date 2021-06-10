/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa3_dominio;

import SistemaLara.capa1_presentacion.util.Verificador;
import java.sql.Date;

/**
 *
 * @author mineria
 */
public class TareaDiaria {
    

    private int codigo;
    private String descripcion;
    private Date fecha;
    private Empresa empresa;
    private Personal personal;
    private Estado estado;
    private String estadoTarea;
    

    public TareaDiaria() {
    }

    public String getEstadoTarea() {
        return estadoTarea;
    }

    public void setEstadoTarea(String estadoTarea) {
        this.estadoTarea = estadoTarea;
    }

    
    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }

    public TareaDiaria(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public boolean estaTareaActivaHoy() {
        java.util.Date fechaHoy = new java.util.Date();
        return Verificador.estatareaActivaHoy(fechaHoy, fecha);
    }

}
