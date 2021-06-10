/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa1_presentacion.util;

import FiveCodMaterilalDesignComboBox.MaterialComboBox;
import static SistemaLara.capa1_presentacion.FormRegistroFactura.facturaSeleccionado;
import static SistemaLara.capa1_presentacion.FormRegistroNotaDeDebito.notaDebitoSeleccionado;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import org.jdesktop.swingx.JXSearchField;
import rojeru_san.RSMPassView;
import rojeru_san.RSMTextFull;
import rojeru_san.componentes.RSDateChooser;
import rojerusan.RSComboMetro;

/**
 *
 * @author FiveCod Software
 */
public class Verificador {

    private static int x, y;

    public static String devolverRedondeoConCero(String numeros) {
        String numero = "" + numeros;

        String literal = "";
        String parte_decimal;
        //si el numero utiliza (.) en lugar de (,) -> se reemplaza
        numero = numero.replace(".", ",");
        //si el numero no tiene parte decimal, se le agrega ,00
        if (numero.indexOf(",") == -1) {
            numero = numero + ",00";
        }
        String toConvertidoDecimal = "";
        String Num[] = numero.split(",");
        int tamaño = Num[1].length() - 1;
        if (tamaño == 0) {
            int numeroDecimal = Integer.parseInt(Num[1]);
            if (numeroDecimal < 10) {
                toConvertidoDecimal = numeroDecimal + "0";
            } else {
                toConvertidoDecimal = "" + numeroDecimal;
            }
        } else {
            toConvertidoDecimal = Num[1];
        }

        String numeroConvertidor = Num[0] + "." + toConvertidoDecimal;

        return numeroConvertidor;
    }

    public static Boolean estaVacioTexto(RSMTextFull texto) {
        if (texto.getText().equals("")) {
            return true;
        } else {
            return false;
        }
    }

    public static Boolean estaVacioTexto(JTextArea texto) {
        if (texto.getText().equals("")) {
            return true;
        } else {
            return false;
        }
    }

    public static Boolean estaVacioTexto(FCMaterialTextField texto) {
        if (texto.getText().equals("")) {
            return true;
        } else {
            return false;
        }
    }

    public static Boolean estaVacioTexto(JXSearchField texto) {
        if (texto.getText().equals("")) {
            return true;
        } else {
            return false;
        }
    }

    public static Boolean estaVacio(RSMPassView texto) {
        if (texto.getText().equals("")) {
            return true;
        } else {
            return false;
        }
    }

    public static Boolean estaVacioTextoFecha(RSDateChooser texto) {
        if (texto.getDatoFecha() == null) {
            return true;
        } else {
            return false;
        }
    }
//nos permite decirle que cuente una cantidad de letras dentro del objeto

    public static int verificarContadorNumeros(RSMTextFull texto, JLabel label, String cadena, int cantidad) {
        int contador = 0;
        if (texto.getText().length() < cantidad) {
            label.setText("Numero " + cadena + " incorrecto");
            label.requestFocus();
            pintarColor(label);
            contador = 0;
        } else {
            label.setText(cadena + " Correcto");
            quitarColor(label);
            contador = 1;
        }
        return contador;
    }

    public static int verificarContadorNumeros(FCMaterialTextField texto, JLabel label, String cadena, int cantidad) {
        int contador = 0;
        if (texto.getText().length() < cantidad) {
            label.setText("Numero " + cadena + " Incorrecto");
            label.requestFocus();
            pintarColor(label);
            contador = 0;
        } else {
            label.setText(cadena + " Correcto");
            quitarColor(label);
            contador = 1;
        }
        return contador;
    }

    public static int verificarCantidadLetras(RSMTextFull texto, JLabel label, String cadena, int cantidad) {
        int contador = 0;
        if (texto.getText().length() < cantidad) {
            label.setText(cadena + " Correcto");
            quitarColor(label);
            contador = 1;
        } else {
            label.setText("Hay mucho texto en " + cadena);
            label.requestFocus();
            pintarColor(label);
            contador = 0;
        }
        return contador;
    }

    public static int verificarCantidadLetras(FCMaterialTextField texto, int cantidad) {
        int contador = 0;
        if (texto.getText().length() < cantidad) {
            texto.setAccent(Color.RED);
            texto.setCaretColor(Color.RED);
            texto.requestFocus();
            contador = 0;
        } else {
            texto.setAccent(Color.GREEN);
            texto.setCaretColor(Color.GREEN);
            contador = 1;
        }
        return contador;
    }

    public static int verificadorCampos(RSMTextFull texto, JLabel label, String cadena) {
        int contador = 0;
        if (Verificador.estaVacioTexto(texto)) {
            label.setText("Falta " + cadena);
            label.requestFocus();
            pintarColor(label);
            contador = 0;
        } else {
            label.setText(cadena + " Correcto");
            quitarColor(label);
            contador = 1;
        }
        return contador;
    }

    public static int verificadorCampos(FCMaterialTextField texto, JLabel label, String cadena) {
        int contador = 0;
        if (Verificador.estaVacioTexto(texto)) {
            label.setText("Falta " + cadena);
            label.requestFocus();
            pintarColor(label);
            contador = 0;
        } else {
            label.setText(cadena + " Correcto");
            quitarColor(label);
            contador = 1;
        }
        return contador;
    }

    public static int verificadorCampos(FCMaterialTextField texto) {
        int contador = 0;
        if (Verificador.estaVacioTexto(texto)) {
            texto.setAccent(Color.RED);
            texto.setCaretColor(Color.RED);
            texto.requestFocus();

            contador = 0;
        } else {
            texto.setAccent(Color.GREEN);
            texto.setCaretColor(Color.GREEN);

            contador = 1;
        }
        return contador;
    }

    public static int verificadorCampos(JTextArea texto) {
        int contador = 0;
        if (Verificador.estaVacioTexto(texto)) {
            texto.setCaretColor(Color.RED);
            texto.requestFocus();
            contador = 0;
        } else {
            texto.setCaretColor(Color.GREEN);
            contador = 1;
        }
        return contador;
    }

    public static int verificadorCampos(JXSearchField texto, JLabel label, String cadena) {
        int contador = 0;
        if (Verificador.estaVacioTexto(texto)) {
            label.setText("Falta " + cadena);
            label.requestFocus();
            pintarColor(label);
            contador = 0;
        } else {
            label.setText(cadena + " Correcto");
            quitarColor(label);
            contador = 1;
        }
        return contador;
    }

    public static int verificadorContraseña(RSMPassView texto, JLabel label, String cadena) {
        int contador = 0;
        if (Verificador.estaVacio(texto)) {
            label.setText("Falta " + cadena);
            label.requestFocus();
            pintarColor(label);
            contador = 0;
        } else {
            label.setText(cadena + " Correcto");
            quitarColor(label);
            contador = 1;
        }
        return contador;
    }

    public static int verificadorCamposFechas(RSDateChooser fecha, JLabel label, String cadena) {
        int contador = 0;
        if (estaVacioTextoFecha(fecha)) {
            label.setText("Falta " + cadena);
            label.requestFocus();
            pintarColor(label);
            contador = 0;
        } else {
            label.setText(cadena + " Correcto");
            quitarColor(label);
            contador = 1;
        }
        return contador;
    }

    public static int verificadorCamposFechas(RSDateChooser fecha) {
        int contador = 0;
        if (estaVacioTextoFecha(fecha)) {
            fecha.requestFocus();
            fecha.setForeground(Color.RED);
            DesktopNotify.showDesktopMessage("FiveCod Software", "Error con las fechas verifique ", 7);

            contador = 0;
        } else {
            fecha.setForeground(Color.GREEN);
            contador = 1;
        }
        return contador;
    }

    public static int verificarCombobox(RSComboMetro combobox, JLabel label, String cadena) {
        int contador = 0;
        if (combobox.getSelectedIndex() == 0) {
            label.setText("Falta " + cadena);
            label.requestFocus();
            pintarColor(label);
            contador = 0;
        } else {
            label.setText(cadena + " Correcto");
            quitarColor(label);
            contador = 1;
        }
        return contador;
    }

    public static int verificarCombobox(MaterialComboBox combobox, JLabel label, String cadena) {
        int contador = 0;
        if (combobox.getSelectedIndex() == -1) {
            label.setText("Falta " + cadena);
            label.requestFocus();
            pintarColor(label);
            contador = 0;
        } else {
            label.setText(cadena + " Correcto");
            quitarColor(label);
            contador = 1;
        }
        return contador;
    }

    public static int verificarComboboxSexo(MaterialComboBox combobox, JLabel label, String cadena) {
        int contador = 0;
        if (combobox.getSelectedIndex() == 0) {
            label.setText("Falta " + cadena);
            label.requestFocus();
            pintarColor(label);
            contador = 0;
        } else {
            label.setText(cadena + " Correcto");
            quitarColor(label);
            contador = 1;
        }
        return contador;
    }

    public static int verificarCombobox(JComboBox combobox, JLabel label, String cadena) {
        int contador = 0;
        if (combobox.getSelectedIndex() == 0) {
            label.setText("Falta " + cadena);
            label.requestFocus();
            pintarColor(label);
            contador = 0;
        } else {
            label.setText(cadena + " Correcto");
            quitarColor(label);
            contador = 1;
        }
        return contador;
    }

    public static void ponerMayusculaPrimeraLetra(RSMTextFull texto) {
        try {
            if (texto.getText().length() > 0) {
                String[] arreglo = texto.getText().split(" ");
                String cadenaContenada = "";
                for (int i = 0; i < arreglo.length; i++) {
                    String Axtexto = arreglo[i];
                    if (Axtexto.length() > 0) {
                        cadenaContenada = cadenaContenada + " " + Axtexto.substring(0, 1).toUpperCase() + "" + Axtexto.substring(1, Axtexto.length()).toLowerCase();

                    }
                }
                texto.setText(cadenaContenada);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

    public static void pintarColor(JLabel label) {
        label.setForeground(new Color(219, 0, 0));
    }

    public static void pintarColor(JLabel label, String texto) {
        label.setForeground(new Color(219, 0, 0));
        label.setText("Error " + texto);
    }

    public static void quitarColor(JLabel label) {
        label.setForeground(new Color(0, 200, 81));
    }

    public static void MousePressed(MouseEvent evt) {
        x = evt.getX();
        y = evt.getY();
    }

    public static void MouseDragged(MouseEvent evt, JDialog dialog) {
        dialog.setLocation(dialog.getLocation().x + evt.getX() - x, dialog.getLocation().y + evt.getY() - y);
    }

    public static void MouseDraggedFrame(MouseEvent evt, JFrame dialog) {
        dialog.setLocation(dialog.getLocation().x + evt.getX() - x, dialog.getLocation().y + evt.getY() - y);
    }

    public static void PresionarEnter(KeyEvent evt, RSMTextFull texto) {
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            texto.requestFocus();
        }
    }

    public static int obtenerNumeroMes(String mes) {
        int numero = 0;
        switch (mes) {
            case "ENERO":
                numero = 1;
                break;
            case "FEBRERO":
                numero = 2;
                break;
            case "MARZO":
                numero = 3;
                break;
            case "ABRIL":
                numero = 4;
                break;
            case "MAYO":
                numero = 5;
                break;
            case "JUNIO":
                numero = 6;
                break;
            case "JULIO":
                numero = 7;
                break;
            case "AGOSTO":
                numero = 8;
                break;
            case "SEPTIEMBRE":
                numero = 9;
                break;
            case "OCTUBRE":
                numero = 10;
                break;
            case "NOVIEMBRE":
                numero = 11;
                break;
            case "DICIEMBRE":
                numero = 12;
                break;

        }
        return numero;
    }

    public static String obtenerNombreMes(int mes) {
        String nombreMes = "";
        switch (mes) {
            case 1:
                nombreMes = "ENERO";
                break;
            case 2:
                nombreMes = "FEBRERO";
                break;
            case 3:
                nombreMes = "MARZO";
                break;
            case 4:
                nombreMes = "ABRIL";
                break;
            case 5:
                nombreMes = "MAYO";
                break;
            case 6:
                nombreMes = "JUNIO";
                break;
            case 7:
                nombreMes = "JULIO";
                break;
            case 8:
                nombreMes = "AGOSTO";
                break;
            case 9:
                nombreMes = "SEPTIEMBRE";
                break;
            case 10:
                nombreMes = "OCTUBRE";
                break;
            case 11:
                nombreMes = "NOVIEMBRE";
                break;
            case 12:
                nombreMes = "DICIEMBRE";
                break;

        }
        return nombreMes;
    }

    public static boolean verificarCumpleaños(Date fechaSistema, Date fechaTrabajador) {
//        String formatoAnio = "yyyy";
        String formatomes = "MM";
        String formatodia = "DD";
//        SimpleDateFormat dateFormatAnio = new SimpleDateFormat(formatoAnio);
        SimpleDateFormat dateFormatMes = new SimpleDateFormat(formatomes);
        SimpleDateFormat dateFormatDia = new SimpleDateFormat(formatodia);

//        int aniosistema = Integer.parseInt(dateFormatAnio.format(fechaSistema));
        int messistema = Integer.parseInt(dateFormatMes.format(fechaSistema));
        int diasistema = Integer.parseInt(dateFormatDia.format(fechaSistema));

//        int anioTrabajador = Integer.parseInt(dateFormatAnio.format(fechaTrabajador));
        int mesTrabajador = Integer.parseInt(dateFormatMes.format(fechaTrabajador));
        int diaTrabajador = Integer.parseInt(dateFormatDia.format(fechaTrabajador));

        return mesTrabajador == messistema && diaTrabajador == diasistema;

    }

    public static boolean estatareaActivaHoy(Date fechaSistema, Date fechaTareaDiaria) {
//        String formatoAnio = "yyyy";
        boolean estado = false;
        try {
            String formato = "yyyy-MM-dd";

//        SimpleDateFormat dateFormatAnio = new SimpleDateFormat(formatoAnio);
            SimpleDateFormat format = new SimpleDateFormat(formato);

//        int aniosistema = Integer.parseInt(dateFormatAnio.format(fechaSistema));
            Date fecha = format.parse(format.format(fechaSistema));

            if (fecha.compareTo(fechaTareaDiaria) == 0) {
                estado = true;
            }

        } catch (Exception e) {
        }

        return estado;

    }

    public static boolean estaEnTiempoContrato(Date fechaSistema, Date fechaInicioContrato, Date fechaFinTarea) {
//        String formatoAnio = "yyyy";
        boolean estado = false;
        try {
            String formato = "yyyy-MM-dd";

//        SimpleDateFormat dateFormatAnio = new SimpleDateFormat(formatoAnio);
            SimpleDateFormat format = new SimpleDateFormat(formato);

//        int aniosistema = Integer.parseInt(dateFormatAnio.format(fechaSistema));
            Date fecha = format.parse(format.format(fechaSistema));
            int fecha1 = fecha.compareTo(fechaInicioContrato);
            int fecha2 = fecha.compareTo(fechaFinTarea);
            if (fecha1 == 1 || fecha1 == 0) {
                if (fecha2 == -1) {
                    estado = true;
                }

            }

        } catch (Exception e) {
        }

        return estado;

    }

//    public static int calcularEdad(Date fechaNacimiento) {
//        java.util.Date fecha = new java.util.Date();
//        LocalDate fechaHoy = LocalDate.now();
//        return fechaHoy.getYear() - fechaNacimiento.getYear();
//    }
    public static int calcularEdad(Date fechaNacimiento) {
        Calendar today = Calendar.getInstance();
        String formatoAnio = "yyyy";
        SimpleDateFormat dateFormatAnio = new SimpleDateFormat(formatoAnio);
        int anio = Integer.parseInt(dateFormatAnio.format(fechaNacimiento));
        int diffYear = today.get(Calendar.YEAR) - anio;
        return diffYear;
    }

    public static String[] llenarComboRegion(RSComboMetro comboMetro, String region) {
        String[] AMAZONAS = {"Chachapoyas", "Bagua", "Condorcanqui", "Luya", "Rodríguez", "Utcubamba"};
        String[] ANCASH = {"Huaraz", "Aija", "Antonio Raymond", "Asunción", "Bolognesi", "Carhuaz", "Carlos Fermín Fitzcarrald", "Casma", "Corongo",
            "Huari", "Huarmey", "Huaylas", "Mariscal Luzuriaga", "Ocros", "Pallasca", "Pomabamba", "Recuay", "Santa", "Sihuas", "Yungay"};
        String[] APURIMAC = {"Abancay", "Andahuaylas", "Antabamba", "Aymaraes", "Cotabambas", "Chicheros", "Grau"};
        String[] AREQUIPA = {"Arequipa", "Camaná", "Caraveli", "Castilla", "Caylloma", "Condesuyos", "Islay", "La Unión"};
        String[] AYACUCHO = {"Huamanga", "Cangallo", "Huancasancos", "Huanta", "La Mar", "Lucanas", "Parinacochas", "Páucar del Sara Sara", "Sucre", "Víctor Fajardo", "Vilcashuaman"};
        String[] CAJAMARCA = {"Cajamarca", "Cajabamba", "Celendín", "Chota", "Contumazá", "Cutervo", "Hualgayoc", "Jaén", "San Ignacio", "San Marcos", "San Miguel", "San Pablo", "Santa Cruz"};
        String[] CALLAO = {"Callao"};
        String[] CUSCO = {"Cusco", "Acomayo", "Anta", "Calca", "Canas", "Canchis", "Chumbivilcas", "Espinar", "La Convención", "Paruro", "Paucartambo", "Quispicanchi", "Urubamba"};
        String[] HUANCAVELICA = {"Huancavelica ", "Acobamba", "Angaraes", "Castrovirreyna", "Churcampa", "Huaytará", "Tayacaja"};
        String[] HUÁNUCO = {"Huanuco", "Ambo", "Dos de Mayo", "Huacaybamba", "Huamalíes", "Leoncio Prado", "Marañón", "Pachitea", "Puerto Inca", "Lauricocha", "Yarowilca"};
        String[] ICA = {"Ica", "Chincha", "Nazca", "Palpa", "Pisco"};
        String[] JUNIN = {"Huancayo", "Concepción", "Chanchamayo", "Jauja", "Junín", "Satipo", "Tarma", "Yauli", "Chupaca"};
        String[] LALIBERTAD = {"Trujillo", "Ascope", "Bolívar", "Chepén", "Julcán", "Otuzco", "Pacasmayo", "Pataz", "Sanchez Carrión", "Santiago de Chuco", "Gran Chimú", "Virú"};
        String[] LAMBAYEQUE = {"Chiclayo", "Ferreñafe", "Lambayeque"};
        String[] LIMA = {"Lima", "Barranca", "Cajatambo", "Canta", "Cañete", "Huaral", "Huarochirí", "Huaura", "Oyón", "Yauyos"};
        String[] LORETO = {"Maynas", "Alto Amazonas", "Loreto", "Mariscal Ramón Castilla", "Requena", "Ucayali", "Datem de Marañón", "Putumayo"};
        String[] MADREDEDIOS = {"Tambopata", "Manu", "Tahuamanu"};
        String[] MOQUEGUA = {"Mariscal Nieto", "General Sánchez Cerro", "Ilo"};
        String[] PASCO = {"Pasco", "Daniel Alcides Carrión", "Oxapampa"};
        String[] PIURA = {"Piura", "Ayabaca", "Huancabamba", "Morropón", "Paita", "Sullana", "Talara", "Secure"};
        String[] PUNO = {"Puno", "Azángaro", "Carabaya", "Chucuito", "El Collao", "Huacané", "Lampa", "Melgar", "Moho", "San Antonio de Putiña", "San Román", "Sandía", "Yunguyo"};
        String[] SANMARTIN = {"Moyobamba", "Bellavista", "El Dorado", "Huallaga", "Lamas", "Mariscal Cáceres", "Picota", "Rioja", "San Martín", "Tocache"};
        String[] TACNA = {"Tacna", "Candarave", "Jorge Basadre", "Tarata"};
        String[] TUMBES = {"Tumbes", "Contralmirante Villar", "Zarumilla"};
        String[] UCAYALI = {"Coronel Portillo", "Atalaya", "Padre Abad", "Purús"};
        String[] devolver = new String[25];
        switch (region) {
            case "AMAZONAS":
                devolver = AMAZONAS;
                break;
            case "ANCASH":
                devolver = ANCASH;
                break;
            case "APURIMAC":
                devolver = APURIMAC;
                break;
            case "AREQUIPA":
                devolver = AREQUIPA;
                break;
            case "AYACUCHO":
                devolver = AYACUCHO;
                break;
            case "CAJAMARCA":
                devolver = CAJAMARCA;
                break;
            case "CALLAO":
                devolver = CALLAO;
                break;
            case "CUSCO":
                devolver = CUSCO;
                break;
            case "HUANCAVELICA":
                devolver = HUANCAVELICA;
                break;
            case "HUÁNUCO":
                devolver = HUÁNUCO;
                break;
            case "ICA":
                devolver = ICA;
                break;
            case "c":
                devolver = JUNIN;
                break;
            case "LA LIBERTAD":
                devolver = LALIBERTAD;
                break;
            case "LAMBAYEQUE":
                devolver = LAMBAYEQUE;
                break;
            case "LIMA":
                devolver = LIMA;
                break;
            case "LORETO":
                devolver = LORETO;
                break;
            case "MADRE DE DIOS":
                devolver = MADREDEDIOS;
                break;
            case "MOQUEGUA":
                devolver = MOQUEGUA;
                break;
            case "PASCO":
                devolver = PASCO;
                break;
            case "PIURA":
                devolver = PIURA;
                break;
            case "PUNO":
                devolver = PUNO;
                break;
            case "SAN MARTIN":
                devolver = SANMARTIN;
                break;
            case "TACNA":
                devolver = TACNA;
                break;
            case "TUMBES":
                devolver = TUMBES;
                break;
            case "UCAYALI":
                devolver = UCAYALI;
                break;

        }
        return devolver;

    }

    public static String[] llenarComboProvincia(RSComboMetro comboMetro, String region) {
        String[] Chachapoyas = {"Sonche"};
        String[] Asunción = {"Sonche"};
        String[] devolver = new String[24];
        switch (region) {
            case "Chachapoyas":
                devolver = Chachapoyas;
                break;
            case "Asunción":
                devolver = Asunción;
                break;
        }
        return devolver;

    }

    public static String convertiToLetter(java.lang.String numero) {
        String convertidor = null;
        try {
            org.tempuri.Service1 service = new org.tempuri.Service1();
            org.tempuri.IService1 port = service.getBasicHttpBindingIService1();
            convertidor = port.convertiToLetter(numero);
            return convertidor;
        } catch (Exception e) {
            DesktopNotify.showDesktopMessage("FiveCod Software", "Error al consumir el webservice. Activar el servidor o comicarse con soporte ", DesktopNotify.ERROR);

            return convertidor;
        }

    }

    public static Date sumarDiasFecha(Date fecha, int dias) {
        if (dias == 0) {
            return fecha;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        calendar.add(Calendar.DAY_OF_YEAR, dias);
        return calendar.getTime();

    }

}
