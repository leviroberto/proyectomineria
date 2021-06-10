
package SistemaLara.capa3_dominio;

import SistemaLara.capa1_presentacion.util.DesktopNotify;



import java.sql.Date;


/**
 *
 * @author MINERAMIRAFLORES
 */
public class PagoTransporte {

    private int codigo;
    private ProveedorServicio proveedorServicio;
    private Date fecha;
    private String nroFactura;
    private Double peso;
    private Double total;
    private Double detraccion;
    private String descontar;
    private Double adelanto;
    private Double importe;
    private String nroOperacion;
    private String rutaBaucher;
    private Date fechaPago;
    private Estado estado;
    private TipoCliente tipoCliente;
    private Personal personal;
    private Double precio;
    private String estadoPagoTransporte;
    private Empresa empresa;

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public String getRutaBaucher() {
        return rutaBaucher;
    }

    public void setRutaBaucher(String rutaBaucher) {
        this.rutaBaucher = rutaBaucher;
    }

    public PagoTransporte() {

    }

    public Double getPrecio() {
        return precio;
    }

    public String getEstadoPagoTransporte() {
        return estadoPagoTransporte;
    }

    public void setEstadoPagoTransporte(String estadoPagoTransporte) {
        this.estadoPagoTransporte = estadoPagoTransporte;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getNroFactura() {
        return nroFactura;
    }

    public void setNroFactura(String nroFactura) {
        this.nroFactura = nroFactura;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getDetraccion() {
        return detraccion;
    }

    public void setDetraccion(Double detraccion) {
        this.detraccion = detraccion;
    }

    public String getDescontar() {
        return descontar;
    }

    public void setDescontar(String descontar) {
        this.descontar = descontar;
    }

    public Double getAdelanto() {
        return adelanto;
    }

    public void setAdelanto(Double adelanto) {
        this.adelanto = adelanto;
    }

    public Double getImporte() {
        return importe;
    }

    public void setImporte(Double importe) {
        this.importe = importe;
    }

    public String getNroOperacion() {
        return nroOperacion;
    }

    public void setNroOperacion(String nroOperacion) {
        this.nroOperacion = nroOperacion;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public TipoCliente getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(TipoCliente tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    @Override
    public String toString() {
        return "PagoTransporte{" + "codigo=" + codigo + ", proveedorServicio=" + proveedorServicio.getDireccion() + ", fecha=" + fecha + ", nroFactura=" + nroFactura + ", peso=" + peso + ", total=" + total + ", detraccion=" + detraccion + ", descontar=" + descontar + ", adelanto=" + adelanto + ", importe=" + importe + ", nroOperacion=" + nroOperacion + ", fechaPago=" + fechaPago + " tipoCliente=" + tipoCliente.getCodigo() + ", personal=" + personal.getApellidos() + ", precio=" + precio + '}';
    }

    //REGLAS DE NEGOCIO
    public Double calcularPrecioPorTonelada() {
        Double precioTonelada = 0.0;
        precioTonelada = (peso * precio);
        return precioTonelada;

    }

    public Double calcularImporte() {
        return calcularPrecioPorTonelada() - calcularAdelanto();
    }

    public Double calcularDetraccion() {
        Double detraccion = 0.0;
        Double precioPorTonelada = calcularPrecioPorTonelada();
        detraccion = ((precioPorTonelada * 4) / 100);
        return detraccion;
    }

    public Double calcularDescuentoDestraccion() {
        Double descuentoDetraccion = 0.0;
        if (descontar.equals("Si")) {
            descuentoDetraccion = (calcularPrecioPorTonelada() - calcularDetraccion()) - calcularAdelanto();
        } else {
            descuentoDetraccion = calcularImporte();
        }
        return descuentoDetraccion;
    }

    public Double calcularAdelanto() {
        if (adelanto <= -1) {
            adelanto = 0.0;
        }
        return adelanto;
    }

    public boolean hayParaDescuentoDetraccion() {
        return descontar == "Si";
    }

    public boolean esCorrectoFechaPago() {
        boolean resultado = false;
        if (fechaPago != null) {
            int operacion = fechaPago.compareTo(fecha);
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
            int operacion = fecha.compareTo(fechaPago);
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
