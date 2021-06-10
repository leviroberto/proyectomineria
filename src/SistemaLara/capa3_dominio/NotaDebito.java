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
public class NotaDebito {

    private List<NotaDebitoDetalle> listaNotaDebitoDetalle;
    private List<NotaDebitoDetalle> listaNotaDebitoEliminadas;
    private TipoNotaDebito tipoNotaDebito;
    private int codigo;
    private String NumeroFactura;
    private String NumeroNotaDebito;
    private ProveedorServicio proveedorServicio;
    private String Denominacion;
    private Date FechaEmision;
    private Date FechaEmision_Comprobante;
    private String codigoUnico;
    private String Consecion;
    private Double ValorVenta;
    private Double IGV;
    private Double Total;
    private String Moneda;
    private String Lectura;
    private Empresa empresa;
    private Estado estado;//no se pone
    private Personal personal;
    private String descripcionMotivo;
    public NotaDebito() {
        this.listaNotaDebitoDetalle = new ArrayList<>();
        this.listaNotaDebitoEliminadas = new ArrayList<>();

    }

    public List<NotaDebitoDetalle> getListaNotaDebitoDetalle() {
        return listaNotaDebitoDetalle;
    }

    public TipoNotaDebito getTipoNotaDebito() {
        return tipoNotaDebito;
    }

    public String getDescripcionMotivo() {
        return descripcionMotivo;
    }

    public void setDescripcionMotivo(String descripcionMotivo) {
        this.descripcionMotivo = descripcionMotivo;
    }

    public void setTipoNotaDebito(TipoNotaDebito tipoNotaDebito) {
        this.tipoNotaDebito = tipoNotaDebito;
    }

    public void setListaNotaDebitoDetalle(List<NotaDebitoDetalle> listaNotaDebitoDetalle) {
        this.listaNotaDebitoDetalle = listaNotaDebitoDetalle;
    }

    public List<NotaDebitoDetalle> getListaNotaDebitoEliminadas() {
        return listaNotaDebitoEliminadas;
    }

    public boolean agregarNotaDebitoDetalle(NotaDebitoDetalle notaDebitoDetalle) {
        return listaNotaDebitoDetalle.add(notaDebitoDetalle);
    }

    public void setListaNotaDebitoEliminadas(List<NotaDebitoDetalle> listaNotaDebitoEliminadas) {
        this.listaNotaDebitoEliminadas = listaNotaDebitoEliminadas;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNumeroFactura() {
        return NumeroFactura;
    }

    public void setNumeroFactura(String NumeroFactura) {
        this.NumeroFactura = NumeroFactura;
    }

    public String getNumeroNotaDebito() {
        return NumeroNotaDebito;
    }

    public void setNumeroNotaDebito(String NumeroNotaDebito) {
        this.NumeroNotaDebito = NumeroNotaDebito;
    }

    public ProveedorServicio getProveedorServicio() {
        return proveedorServicio;
    }

    public void setProveedorServicio(ProveedorServicio proveedorServicio) {
        this.proveedorServicio = proveedorServicio;
    }

    public String getDenominacion() {
        return Denominacion;
    }

    public void setDenominacion(String Denominacion) {
        this.Denominacion = Denominacion;
    }

    public Date getFechaEmision() {
        return FechaEmision;
    }

    public void setFechaEmision(Date FechaEmision) {
        this.FechaEmision = FechaEmision;
    }

    public Date getFechaEmision_Comprobante() {
        return FechaEmision_Comprobante;
    }

    public void setFechaEmision_Comprobante(Date FechaEmision_Comprobante) {
        this.FechaEmision_Comprobante = FechaEmision_Comprobante;
    }

    public String getCodigoUnico() {
        return codigoUnico;
    }

    public void setCodigoUnico(String codigoUnico) {
        this.codigoUnico = codigoUnico;
    }

    public String getConsecion() {
        return Consecion;
    }

    public void setConsecion(String Consecion) {
        this.Consecion = Consecion;
    }

    public Double getValorVenta() {
        return ValorVenta;
    }

    public void setValorVenta(Double ValorVenta) {
        this.ValorVenta = ValorVenta;
    }

    public Double getIGV() {
        return IGV;
    }

    public void setIGV(Double IGV) {
        this.IGV = IGV;
    }

    public Double getTotal() {
        return Total;
    }

    public void setTotal(Double Total) {
        this.Total = Total;
    }

    public String getMoneda() {
        return Moneda;
    }

    public void setMoneda(String Moneda) {
        this.Moneda = Moneda;
    }

    public String getLectura() {
        return Lectura;
    }

    public void setLectura(String Lectura) {
        this.Lectura = Lectura;
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

    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }

    public NotaDebitoDetalle getNotaDebitoDetalle(Reintegro reintegro) {
        NotaDebitoDetalle notaDebitoDetalle = null;

        for (NotaDebitoDetalle debitoDetalle : listaNotaDebitoDetalle) {
            if (debitoDetalle.getReintegro().getCodigo() == reintegro.getCodigo()) {
                notaDebitoDetalle = debitoDetalle;
            }
        }
        return notaDebitoDetalle;

    }

    public boolean modificarNotaDebitoDetalle(NotaDebitoDetalle notaDebitoDetalle) {
        boolean estado = false;
        int numeroAfectado = obtenerIndiceNotaDebitoDetalle(notaDebitoDetalle.getReintegro());
        if (numeroAfectado != -1) {
            listaNotaDebitoDetalle.get(numeroAfectado).setCantidad(notaDebitoDetalle.getCantidad());
            listaNotaDebitoDetalle.get(numeroAfectado).setDescripcion(notaDebitoDetalle.getDescripcion());
            listaNotaDebitoDetalle.get(numeroAfectado).setValorVenta(notaDebitoDetalle.getValorVenta());
            listaNotaDebitoDetalle.get(numeroAfectado).setReintegro(notaDebitoDetalle.getReintegro());
            listaNotaDebitoDetalle.get(numeroAfectado).setPrecioUnitario(notaDebitoDetalle.getPrecioUnitario());
            listaNotaDebitoDetalle.get(numeroAfectado).setUnidad(notaDebitoDetalle.getUnidad());
            estado = true;
        }
        return estado;
    }

    public int obtenerIndiceNotaDebitoDetalle(Reintegro reintegro) {
        int valor = -1;
        int contador = 0;
        for (NotaDebitoDetalle notaDebitoDetalle : listaNotaDebitoDetalle) {
            if (notaDebitoDetalle.getReintegro().getCodigo() == reintegro.getCodigo()) {
                valor = contador;
            }
            contador++;
        }
        return valor;
    }

    public NotaDebitoDetalle buscarNotaDebitoDetalle(int codigoReintegro) {
        NotaDebitoDetalle notaDebitoDetalle = null;
        for (NotaDebitoDetalle debitoDetalle : listaNotaDebitoDetalle) {
            if (debitoDetalle.getReintegro().getCodigo() == codigoReintegro) {
                notaDebitoDetalle = debitoDetalle;
            }
        }

        return notaDebitoDetalle;
    }

    public boolean eliminarNotaDebitoDetalle(NotaDebitoDetalle notaDebitoDetalle) {
        int numeroAfectado = obtenerIndiceNotaDebitoDetalle(notaDebitoDetalle.getReintegro());
        if (numeroAfectado != -1) {
            listaNotaDebitoDetalle.remove(numeroAfectado);
            listaNotaDebitoEliminadas.add(notaDebitoDetalle);
            return true;
        } else {
            return false;
        }
    }

    public boolean existeNotaDebitoDetalle(NotaDebitoDetalle fac) {
        boolean estado = false;
        for (NotaDebitoDetalle facturaDetalle : listaNotaDebitoDetalle) {
            if (fac.getReintegro().getCodigo() == facturaDetalle.getReintegro().getCodigo()) {
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
        String a = "0";
        double b = 0;
        try {
            for (NotaDebitoDetalle notaDebitoDetalle : listaNotaDebitoDetalle) {

                b = b + notaDebitoDetalle.getValorVenta();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
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
