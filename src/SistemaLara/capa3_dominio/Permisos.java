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
public class Permisos {

    private int codigo;
    private String descripcion;
    private Estado estado;
    private Empresa empreasa;

    public Permisos() {
    }

    public Permisos(int codigo, String descripcion, Estado estado, Empresa empreasa) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.estado = estado;
        this.empreasa = empreasa;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Empresa getEmpreasa() {
        return empreasa;
    }

    public void setEmpreasa(Empresa empreasa) {
        this.empreasa = empreasa;
    }

}
