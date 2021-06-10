/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa3_dominio;

import static SistemaLara.capa1_presentacion.FormRegistroFactura.redondearDecimales;
import SistemaLara.capa1_presentacion.util.Verificador;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author MINERAMIRAFLORES
 */
public class Factura {

    private List<FacturaDetalle> listaFacturaDetalle;
    private List<FacturaDetalle> listaFacturaEliminadas;

    private int codigo;
    private String nroFactura;
    private ProveedorServicio proveedorServicio;
    private Date fecha;
    private String guian;
    private String direccion;
    private Double valorVenta;
    private Double igv;
    private Double total;
    private String descripcion;
    private String moneda;
    private String codigoUnico;
    private String rnc;
    private String lectura;
    private String estadoFactura;//no se pone
    private Date FechaPago;
    private Empresa empresa;
    private Estado estado;//no se pone
    private Personal personal;

    public Factura() {
        this.listaFacturaDetalle = new ArrayList<>();
        this.listaFacturaEliminadas = new ArrayList<>();

    }

    public List<FacturaDetalle> getListaFacturaEliminadas() {
        return listaFacturaEliminadas;
    }

    public void setListaFacturaEliminadas(List<FacturaDetalle> listaFacturaEliminadas) {
        this.listaFacturaEliminadas = listaFacturaEliminadas;
    }

    public List<FacturaDetalle> getListaFacturaDetalle() {
        return listaFacturaDetalle;
    }

    public void setListaFacturaDetalle(List<FacturaDetalle> listaFacturaDetalle) {
        this.listaFacturaDetalle = listaFacturaDetalle;
    }

    public boolean agregarFacturaDetalle(FacturaDetalle facturaDetalle) {
        return listaFacturaDetalle.add(facturaDetalle);
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

    public String getNroFactura() {
        return nroFactura;
    }

    public void setNroFactura(String nroFactura) {
        this.nroFactura = nroFactura;
    }

    public ProveedorServicio getProveedorServicio() {
        return proveedorServicio;
    }

    public void setProveedorServicio(ProveedorServicio proveedorServicio) {
        this.proveedorServicio = proveedorServicio;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getGuian() {
        return guian;
    }

    public void setGuian(String guian) {
        this.guian = guian;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Double getValorVenta() {
        return valorVenta;
    }

    public void setValorVenta(Double valorVenta) {
        this.valorVenta = valorVenta;
    }

    public Double getIgv() {
        return igv;
    }

    public void setIgv(Double igv) {
        this.igv = igv;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getCodigoUnico() {
        return codigoUnico;
    }

    public void setCodigoUnico(String codigoUnico) {
        this.codigoUnico = codigoUnico;
    }

    public String getRnc() {
        return rnc;
    }

    public void setRnc(String rnc) {
        this.rnc = rnc;
    }

    public String getLectura() {
        return lectura;
    }

    public void setLectura(String lectura) {
        this.lectura = lectura;
    }

    public String getEstadoFactura() {
        return estadoFactura;
    }

    public void setEstadoFactura(String estadoFactura) {
        this.estadoFactura = estadoFactura;
    }

    public Date getFechaPago() {
        return FechaPago;
    }

    public void setFechaPago(Date FechaPago) {
        this.FechaPago = FechaPago;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public FacturaDetalle getFacturaDetalle(Liquidacion liquidacion) {
        FacturaDetalle facturaDetalle = null;

        for (FacturaDetalle facturaDetalle1 : listaFacturaDetalle) {
            if (facturaDetalle1.getLiquidacion().getCodigo() == liquidacion.getCodigo()) {
                facturaDetalle = facturaDetalle1;
            }
        }
        return facturaDetalle;

    }

    public boolean modificarFacturaDetalle(FacturaDetalle facturaDetalle) {
        boolean estado = false;
        int numeroAfectado = obtenerIndiceFactruraDetalle(facturaDetalle.getLiquidacion());
        if (numeroAfectado != -1) {

            listaFacturaDetalle.get(numeroAfectado).setCantidad(facturaDetalle.getCantidad());
            listaFacturaDetalle.get(numeroAfectado).setDescripcion(facturaDetalle.getDescripcion());
            listaFacturaDetalle.get(numeroAfectado).setImporte(facturaDetalle.getImporte());
            listaFacturaDetalle.get(numeroAfectado).setLiquidacion(facturaDetalle.getLiquidacion());
            listaFacturaDetalle.get(numeroAfectado).setPrecioUnitario(facturaDetalle.getPrecioUnitario());
            listaFacturaDetalle.get(numeroAfectado).setUnidad(facturaDetalle.getUnidad());
            listaFacturaDetalle.get(numeroAfectado).setAdelanto(facturaDetalle.getAdelanto());
            estado = true;
        }
        return estado;
    }

    public int obtenerIndiceFactruraDetalle(Liquidacion liquidacion) {
        int valor = -1;
        int contador = 0;
        for (FacturaDetalle facturaDetalle : listaFacturaDetalle) {
            if (facturaDetalle.getLiquidacion().getCodigo() == liquidacion.getCodigo()) {
                valor = contador;
            }
            contador++;
        }
        return valor;
    }

    public FacturaDetalle buscarFacturaDetalle(int codigoLiquidacion) {
        FacturaDetalle facturaDetalle = null;

        for (FacturaDetalle facturaDetalle1 : listaFacturaDetalle) {
            if (facturaDetalle1.getLiquidacion().getCodigo() == codigoLiquidacion) {
                facturaDetalle = facturaDetalle1;
            }
        }

        return facturaDetalle;
    }

    public boolean eliminarFacturaDetalle(FacturaDetalle facturaDetalle) {
        int numeroAfectado = obtenerIndiceFactruraDetalle(facturaDetalle.getLiquidacion());
        if (numeroAfectado != -1) {
            listaFacturaDetalle.remove(numeroAfectado);
            listaFacturaEliminadas.add(facturaDetalle);
            return true;
        } else {
            return false;
        }
    }

    public boolean existeDetalleFactura(FacturaDetalle fac) {
        boolean estado = false;
        for (FacturaDetalle facturaDetalle : listaFacturaDetalle) {
            if (fac.getLiquidacion().getCodigo() == facturaDetalle.getLiquidacion().getCodigo()) {
                estado = true;
                break;
            }
        }
        return estado;
    }
    public Double calcularValor() {
        Double totalSi = 0.00;
        Double totalNo = 0.00;
        Double suma = 0.00;
        String a = "";
        double b = 0.00;
        for (FacturaDetalle facturaDetalle : listaFacturaDetalle) {
            if (facturaDetalle.getAdelanto().equals("No")) {
                a = facturaDetalle.getImporte();
                b = Double.valueOf(a);
                totalNo = totalNo + b;
            } else if (facturaDetalle.getAdelanto().equals("Si")) {
                a = facturaDetalle.getImporte();
                b = Double.valueOf(a);
                totalSi = totalSi + b;
            }
        }
        suma = totalNo - totalSi;
        return redondearDecimales(suma, 2);
    }

    public Double calcularIGV() {
        Double valor = calcularValor();
        Double igv = 0.00;
        igv = valor * 0.18;
        return redondearDecimales(igv, 2);
    }

    

    public Double calcularTotal() {
        Double igv = calcularIGV();
        Double valor = calcularValor();
        Double totals = 0.00;
        totals = valor + igv;
     
        return redondearDecimales(totals, 2);
    }

}
