/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa3_dominio;

import SistemaLara.capa1_presentacion.util.Verificador;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MINERAMIRAFLORES
 */
public class Personal {

    private int codigo;
    private String nombres;
    private String apellidos;
    private String dni;
    private String telefono;
    private String email;
    private String direccion;
    private String sexo;
    private Date fechaNacimiento;
    private Double sueldo;
    private Date fechaPago;
    private Date fechaIngreso;
    private Estado estado;
    private int totalDiasPagar;
    private String password;
    private TipoPersonal tipoPersonal;
    private Empresa empresa;
    

    private RutaReporte rutaReporte;
    private String estadoPersonalNotificacion;

    private List<PermisosDetallePersonal> listaDetallePermisos;

    public Personal() {
        listaDetallePermisos = new ArrayList<>();
    }

   
    
    

    public List<PermisosDetallePersonal> getListaDetallePermisos() {
        return listaDetallePermisos;
    }

    public void setListaDetallePermisos(List<PermisosDetallePersonal> listaDetallePermisos) {
        this.listaDetallePermisos = listaDetallePermisos;
    }
    
    

    public Empresa getEmpresa() {
        return empresa;
    }

    public RutaReporte getRutaReporte() {
        return rutaReporte;
    }

    public void setRutaReporte(RutaReporte rutaReporte) {
        this.rutaReporte = rutaReporte;
    }

    public String getEstadoPersonalNotificacion() {
        return estadoPersonalNotificacion;
    }

    public void setEstadoPersonalNotificacion(String estadoPersonalNotificacion) {
        this.estadoPersonalNotificacion = estadoPersonalNotificacion;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public TipoPersonal getTipoPersonal() {
        return tipoPersonal;
    }

    public void setTipoPersonal(TipoPersonal tipoPersonal) {
        this.tipoPersonal = tipoPersonal;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Double getSueldo() {
        return sueldo;
    }

    public void setSueldo(Double sueldo) {
        this.sueldo = sueldo;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public int getTotalDiasPagar() {
        return totalDiasPagar;
    }

    public void setTotalDiasPagar(int totalDiasPagar) {
        this.totalDiasPagar = totalDiasPagar;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "Personal{" + "codigo=" + codigo + ", nombres=" + nombres + ", apellidos=" + apellidos + ", dni=" + dni + ", telefono=" + telefono + ", email=" + email + ", direccion=" + direccion + ", sexo=" + sexo + ", fechaNacimiento=" + fechaNacimiento + ", sueldo=" + sueldo + ", fechaPago=" + fechaPago + ", fechaIngreso=" + fechaIngreso + ", estado=" + estado + ", totalDiasPagar=" + totalDiasPagar + ", password=" + password + ", tipoPersonal=" + tipoPersonal + '}';
    }
    //METODOS GENERADOS

    public String getGenerarNombre() {
        return apellidos + " " + nombres;
    }

    public boolean estaDeCumpleaños() {
        return Verificador.verificarCumpleaños(new java.util.Date(), new java.util.Date(fechaNacimiento.getTime()));
    }

    public int calcularEdad() {
        return Verificador.calcularEdad(new java.util.Date(fechaNacimiento.getTime()));
    }

}
