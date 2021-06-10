/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa3_dominio;

import SistemaLara.capa1_presentacion.util.DesktopNotify;
import java.sql.Date;

/**
 *
 * @author MINERAMIRAFLORES
 */
public class ChequeProveedor {

    private int codigo;
    private ProveedorServicio proveedorServicio;
    private String entregadoA;
    private String concepto;
    private Double monto;
    private String moneda;
    private String fechaPago;
    private String fechaEmision;
    private String lectura;
    private Empresa empresa;
    private Personal personal;

    public ChequeProveedor() {
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

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public ProveedorServicio getProveedorServicio() {
        return proveedorServicio;
    }

    public void setProveedorServicio(ProveedorServicio proveedorServicio) {
        this.proveedorServicio = proveedorServicio;
    }

    public String getEntregadoA() {
        return entregadoA;
    }

    public void setEntregadoA(String entregadoA) {
        this.entregadoA = entregadoA;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(String fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public String getLectura() {
        return lectura;
    }

    public void setLectura(String lectura) {
        this.lectura = lectura;
    }

    @Override
    public String toString() {
        return "Cheque{" + "codigo=" + codigo + ", proveedorServicio=" + proveedorServicio.getDireccion() + ", entregadoA=" + entregadoA + ", concepto=" + concepto + ", monto=" + monto + ", moneda=" + moneda + ", fechaPago=" + fechaPago + ", fechaEmision=" + fechaEmision + ", lectura=" + lectura + '}';
    }

    public boolean esCorrectoFechaPago() {
        boolean resultado = false;
        if (fechaPago != null) {
            int operacion = fechaPago.compareTo(fechaEmision);
            if (operacion < 0) {//ES IGUAL LAS DOS FECHAS
                resultado = false;
                DesktopNotify.showDesktopMessage("FiveCod Software", "La fecha de pago es menor a la fecha de emisión", 7);

            } else if (operacion == 0) {
                resultado = true;
            } else {
                resultado = true;
            }
        } else {
            resultado = true;
        }

        return resultado;
    }

    public boolean esCorrectoFechaEmision() {
        boolean resultado = false;
        if (fechaPago != null) {
            int operacion = fechaEmision.compareTo(fechaPago);
            if (operacion < 0) {//ES IGUAL LAS DOS FECHAS
                resultado = true;

            } else if (operacion == 0) {
                resultado = true;
            } else {
                resultado = false;
                DesktopNotify.showDesktopMessage("FiveCod Software", "La fecha de emision es mayor a fecha de pago", 7);

            }
        } else {
            resultado = true;
        }

        return resultado;
    }

}
