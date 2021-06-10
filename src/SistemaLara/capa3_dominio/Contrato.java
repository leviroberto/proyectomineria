/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa3_dominio;

import SistemaLara.capa1_presentacion.util.Fechas;
import SistemaLara.capa1_presentacion.util.Verificador;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author FiveCod Software
 */
public class Contrato {
     SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    private int codigo;
    private Personal personal;
    private Date fechaIninicioContrato;
    private Date fechaTerminoContrato;
    private int totalDiasPagar;
    private Double sueldo;
    private Estado estado;
    private Empresa empresa;
    private List<Date> listaFechasPagos;

    public Contrato() {
        listaFechasPagos = new ArrayList<>();
    }

    public void agregarFecha(Date fecha) {
        listaFechasPagos.add(fecha);
    }

    public List<Date> getListaFechasPagos() {
        return listaFechasPagos;
    }

    public void setListaFechasPagos(List<Date> listaFechasPagos) {
        this.listaFechasPagos = listaFechasPagos;
    }

    public Empresa getEmpresa() {
        return empresa;
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

    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }

    public Date getFechaIninicioContrato() {
        return fechaIninicioContrato;
    }

    public void setFechaIninicioContrato(Date fechaIninicioContrato) {
        this.fechaIninicioContrato = fechaIninicioContrato;
    }

    public Date getFechaTerminoContrato() {
        return fechaTerminoContrato;
    }

    public void setFechaTerminoContrato(Date fechaTerminoContrato) {
        this.fechaTerminoContrato = fechaTerminoContrato;
    }

    public int getTotalDiasPagar() {
        return totalDiasPagar;
    }

    public void setTotalDiasPagar(int totalDiasPagar) {
        this.totalDiasPagar = totalDiasPagar;
    }

    public Double getSueldo() {
        return sueldo;
    }

    public void setSueldo(Double sueldo) {
        this.sueldo = sueldo;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public boolean tieneContrato() {
        return estado.getCodigo() == 1;
    }

    ///reglas de negocio
    public boolean esCorrectoDiasPagar() {
        return totalDiasPagar > 0 && totalDiasPagar <= 31;
    }

    public boolean esCorrectaFechaFinalContrato() {
        return fechaIninicioContrato.before(fechaTerminoContrato) || fechaIninicioContrato.equals(fechaTerminoContrato);
    }

    @Override
    public String toString() {
        return "Contrato{" + codigo + " personal=" + personal.getApellidos() + ", fechaIninicioContrato=" + fechaIninicioContrato + ", fechaTerminoContrato=" + fechaTerminoContrato + ", totalDiasPagar=" + totalDiasPagar + ", sueldo=" + sueldo + ", estado=" + estado.getCodigo() + ", empresa=" + empresa.getNombreComercial() + '}';
    }

    public boolean estaContratoDisponible() {
        java.util.Date date = new java.util.Date();
        boolean estado = false;
        if (date.equals(fechaTerminoContrato)) {
            estado = false;
        } else if (date.after(fechaTerminoContrato)) {
            estado = false;
        } else {
            estado = true;
        }
        return estado;
    }

    public void obtenerFechasPagos() {
        ArrayList<Date> listaFechas = new ArrayList<Date>();
        listaFechasPagos.clear();
        Date fechas = fechaIninicioContrato;
        agregarFecha(fechaIninicioContrato);
        boolean estado = true;
        while (estado) {
            if (fechas.before(fechaTerminoContrato)) {
                fechas = new java.sql.Date(Fechas.sumarDiasAFecha(fechas, totalDiasPagar).getTime());
                agregarFecha(fechas);
                estado = true;
            } else {
                estado = false;
            }

        }

    }

    public Date obtenerUltimaFechaPago() {
//        Date fechaSelleccionada = null;
//        String fechaActual = Fechas.obtenerFechaActual();
//        for (Date date : obtenerFechasPagos()) {
//            if (Fechas.formatearFechas(date).equals(fechaActual)) {
//                fechaSelleccionada = date;
//            }
//        }
//        return fechaSelleccionada;
        return null;
    }

    public int obtenerFechaPagoProximoIndice() {
        java.util.Date fechaActual = new java.util.Date();
        Date axiliar = null;
        int numero = 0;
        Date fechaSeleccionada = null;
        int contador = 0;
        for (Date fecha : listaFechasPagos) {
            if (fechaActual.after(fecha)) {
                numero = contador;
            }
            contador++;
            axiliar = fecha;
        }
        return numero;
    }

    public boolean estaEnTiempoContrato() {
        java.util.Date fechaHoy = new java.util.Date();
        return Verificador.estaEnTiempoContrato(fechaHoy, fechaIninicioContrato, fechaTerminoContrato);
    }

    public boolean estaDePagoPersonal() {
        boolean estado = false;
        try {

            obtenerFechasPagos();
            String formatomes = "MM";
            String formatodia = "dd";
            String formatoanio = "yyyy";

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat dateFormatMes = new SimpleDateFormat(formatomes);
            SimpleDateFormat dateFormatDia = new SimpleDateFormat(formatodia);
            SimpleDateFormat dateFormatAnio = new SimpleDateFormat(formatoanio);

            String messistema = dateFormatMes.format(new java.util.Date());
            int diasistema = Integer.parseInt(dateFormatDia.format(new java.util.Date()));
            int aniosistema = Integer.parseInt(dateFormatAnio.format(new java.util.Date()));
            String numeroDiaActualizado="";
            numeroDiaActualizado=""+diasistema;
            if(diasistema<10){
                numeroDiaActualizado=0+""+diasistema;
            }
            String fechaSeleccionad = aniosistema + "-" + messistema + "-" + numeroDiaActualizado;

       
            for (Date fecha : listaFechasPagos) {
                String fechaObtenida = df.format(fecha);
                if (fechaObtenida.equals(fechaSeleccionad)) {
                    estado = true;
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return estado;
    }

}
