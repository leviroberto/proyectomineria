/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa3_dominio;

import static SistemaLara.capa1_presentacion.FormRegistroNotaDeDebito.redondearDecimales;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;


public class NotaCredito {

    private List<NotaCreditoDetalle> listaNotaCreditoDetalle;
    private List<NotaCreditoDetalle> listaNotaCreditoEliminadas;
    private int codigo;
    private String numeroNotaCreadito;
    private String numeroFactura;
    private ProveedorServicio proveedorServicio;
    private String denominacion;
    private Date fechaEmision;
    private Date fechaEmisionComprobante;
    private Double subtotal;
    private Double igv;
    private Double total;
    private String moneda;
    private String lectura;
    private Empresa empresa;
    private Estado estado;//no se pone
    private Personal personal;
    //variables auxiliares
    private int contadorItem = 0;
    private TipoNotaCD tipoNotaCD;
    private String descripcionMotivo;

    public NotaCredito() {
        this.listaNotaCreditoDetalle = new ArrayList<>();
        this.listaNotaCreditoEliminadas = new ArrayList<>();

    }

    public TipoNotaCD getTipoNotaCD() {
        return tipoNotaCD;
    }

    public void setTipoNotaCD(TipoNotaCD tipoNotaCD) {
        this.tipoNotaCD = tipoNotaCD;
    }

    public String getDescripcionMotivo() {
        return descripcionMotivo;
    }

    public void setDescripcionMotivo(String descripcionMotivo) {
        this.descripcionMotivo = descripcionMotivo;
    }

    
    
    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public Date getFechaEmisionComprobante() {
        return fechaEmisionComprobante;
    }

    public void setFechaEmisionComprobante(Date fechaEmisionComprobante) {
        this.fechaEmisionComprobante = fechaEmisionComprobante;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public List<NotaCreditoDetalle> getListaNotaCreditoDetalle() {
        return listaNotaCreditoDetalle;
    }

    public void setListaNotaCreditoDetalle(List<NotaCreditoDetalle> listaNotaCreditoDetalle) {
        this.listaNotaCreditoDetalle = listaNotaCreditoDetalle;
    }

    public List<NotaCreditoDetalle> getListaNotaCreditoEliminadas() {
        return listaNotaCreditoEliminadas;
    }

    public void setListaNotaCreditoEliminadas(List<NotaCreditoDetalle> listaNotaCreditoEliminadas) {
        this.listaNotaCreditoEliminadas = listaNotaCreditoEliminadas;
    }

    private int obtenerUltimoCodigoDetalle() {
        int ultimoCodigo = 0;
        if (listaNotaCreditoDetalle.size() > 0) {
            ultimoCodigo = listaNotaCreditoDetalle.get(0).getCodigo();
            for (NotaCreditoDetalle a : listaNotaCreditoDetalle) {
                int codigoObtenido = a.getCodigo();
                if (codigoObtenido > ultimoCodigo) {
                    ultimoCodigo = a.getCodigo();
                }
            }
        }

        return ultimoCodigo;
    }

    public boolean agregarNotaCreditoDetalle(NotaCreditoDetalle notaCreditoDetalle) {
        int codigo = obtenerUltimoCodigoDetalle();
        codigo++;
        notaCreditoDetalle.setCodigo(codigo);
        return listaNotaCreditoDetalle.add(notaCreditoDetalle);
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

    public String getNumeroNotaCreadito() {
        return numeroNotaCreadito;
    }

    public void setNumeroNotaCreadito(String numeroNotaCreadito) {
        this.numeroNotaCreadito = numeroNotaCreadito;
    }

    public String getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public int getContadorItem() {
        return contadorItem;
    }

    public void setContadorItem(int contadorItem) {
        this.contadorItem = contadorItem;
    }

    public ProveedorServicio getProveedorServicio() {
        return proveedorServicio;
    }

    public void setProveedorServicio(ProveedorServicio proveedorServicio) {
        this.proveedorServicio = proveedorServicio;
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

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getLectura() {
        return lectura;
    }

    public void setLectura(String lectura) {
        this.lectura = lectura;
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

    public NotaCreditoDetalle getNotaCreditoDetalle(NotaDebito notaDebito) {
        NotaCreditoDetalle creditoDetalle = null;

        for (NotaCreditoDetalle notaCreditoDetalle : listaNotaCreditoDetalle) {
            if (notaCreditoDetalle.getNotaCredito().getCodigo() == notaDebito.getCodigo()) {
                creditoDetalle = notaCreditoDetalle;
            }
        }
        return creditoDetalle;

    }

    public boolean modificarNotaCreditoDetalle(NotaCreditoDetalle notaDebitoDetalle) {
        boolean estado = false;
        int numeroAfectado = obtenerIndiceFactruraDetalle(notaDebitoDetalle);
        if (numeroAfectado != -1) {
            listaNotaCreditoDetalle.get(numeroAfectado).setValorVenta(notaDebitoDetalle.getValorVenta());
            listaNotaCreditoDetalle.get(numeroAfectado).setDescripcion(notaDebitoDetalle.getDescripcion());
            listaNotaCreditoDetalle.get(numeroAfectado).setNotaCredito(notaDebitoDetalle.getNotaCredito());
            listaNotaCreditoDetalle.get(numeroAfectado).setPersonal(notaDebitoDetalle.getPersonal());
            estado = true;
        }
        return estado;
    }
//

    public int obtenerIndiceFactruraDetalle(NotaCreditoDetalle notaDebitoDetalle) {
        int valor = -1;
        int contador = 0;
        for (NotaCreditoDetalle facturaDetalle : listaNotaCreditoDetalle) {
            if (facturaDetalle.getCodigo() == notaDebitoDetalle.getCodigo()) {
                valor = contador;
            }
            contador++;
        }
        return valor;
    }

    public NotaCreditoDetalle buscarFacturaDetalle(int codigoNotaDebito) {
        NotaCreditoDetalle creditoDetalle = null;

        for (NotaCreditoDetalle notaCreditoDetalle : listaNotaCreditoDetalle) {
            if (notaCreditoDetalle.getCodigo() == codigoNotaDebito) {
                creditoDetalle = notaCreditoDetalle;
            }
        }

        return creditoDetalle;
    }

    public boolean eliminarNotaCreditoDetalle(NotaCreditoDetalle notaCreditoDetalle) {
        int numeroAfectado = obtenerIndiceFactruraDetalle(notaCreditoDetalle);
        if (numeroAfectado != -1) {
            listaNotaCreditoDetalle.remove(numeroAfectado);
           
            listaNotaCreditoEliminadas.add(notaCreditoDetalle);
            return true;
        } else {
            return false;
        }
    }

    public boolean existeDetalleNotaCredito(NotaCreditoDetalle fac) {
        fac.setCodigo(contadorItem++);
        boolean estado = false;
        for (NotaCreditoDetalle creditoDetalle : listaNotaCreditoDetalle) {
            if (fac.getNotaCredito().getCodigo() == creditoDetalle.getNotaCredito().getCodigo()) {
                estado = true;
                break;
            }
        }
        return estado;
    }

    public Double calcularValor() {
        Double totalSi = 0.0;
        Double totalNo = 0.0;
        Double suma = 0.0;
        String a = "";
        double b = 0;
        for (NotaCreditoDetalle notaCreditoDetalle : listaNotaCreditoDetalle) {

            b = b + notaCreditoDetalle.getValorVenta();
        }

        return redondearDecimales(b, 2);
    }

    public Double calcularIGV() {
        Double valor = calcularValor();
        Double igv = 0.0;
        igv = valor * 0.18;
        return redondearDecimales(igv, 2);
    }

    public Double calcularTotal() {
        Double igv = calcularIGV();
        Double valor = calcularValor();
        Double totals = 0.0;
        totals = valor + igv;
        return redondearDecimales(totals, 2);
    }

}
