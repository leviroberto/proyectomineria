package SistemaLara.capa1_presentacion.util;

import java.util.regex.Pattern;
import javax.swing.JOptionPane;

public class Numero_a_Letra {

//    private final String[] UNIDADES = {"", "un ", "dos ", "tres ", "cuatro ", "cinco ", "seis ", "siete ", "ocho ", "nueve "};
//    private final String[] DECENAS = {"diez ", "once ", "doce ", "trece ", "catorce ", "quince ", "dieciseis ",
//        "diecisiete ", "dieciocho ", "diecinueve", "veinte ", "treinta ", "cuarenta ",
//        "cincuenta ", "sesenta ", "setenta ", "ochenta ", "noventa "};
//    private final String[] CENTENAS = {"", "ciento ", "doscientos ", "trecientos ", "cuatrocientos ", "quinientos ", "seiscientos ",
//        "setecientos ", "ochocientos ", "novecientos "};
    private final String[] UNIDADES = {"", " UN", " DOS", " TRES", " CUATRO", " CINCO", " SEIS", 
        " SIETE", " OCHO", " NUEVE", " UNO"};
    private final String[] DECENAS = {" DIEZ", " ONCE", " DOCE", " TRECE", " CATORCE", " QUINCE", " DIECISEIS",
        " DIECISIETE", " DIECIOCHO", " DIECINUEVE", " VEINTE", " TREINTA", " CUARENTA",
        " CINCUENTA", " SESENTA", " SETENTA", " OCHENTA", " NOVENTA"};
    private final String[] DECENAS1 = {" VEINTIUN", " VENTIDOS", " VEINTITRES", " VEINTICUATRO",
        " VEINTICINCO", " VEINTISEIS", " VEINTISIETE", " VEINTIOCHO", " VEINTINUEVE"};
    private final String[] CENTENAS = {"", " CIENTO ", " DOSCIENTOS ", " TRESCIENTOS ", " CUATROCIENTOS ",
        " QUINIENTOS", " SEISCIENTOS",
        " SETECIENTOS", " OCHOCIENTOS", " NOVECIENTOS"};

    public Numero_a_Letra() {
    }

    public String Convertir(String numero, boolean mayusculas) {
        String literal = "";
        String parte_decimal;
        //si el numero utiliza (.) en lugar de (,) -> se reemplaza
        numero = numero.replace(".", ",");
        //si el numero no tiene parte decimal, se le agrega ,00
        if (numero.indexOf(",") == -1) {
            numero = numero + ",00";
        }

        //se valida formato de entrada -> 0,00 y 999 999 999,00
        if (Pattern.matches("\\d{1,9},\\d{1,2}", numero)) {
            //se divide el numero 0000000,00 -> entero y decimal
            String Num[] = numero.split(",");
            //de da formato al numero decimal
            parte_decimal = " CON " + Num[1] + "/100";
            //se convierte el numero a literal
            if (Integer.parseInt(Num[0]) == 0) {//si el valor es cero
                literal = " CERO";
            } else if (Integer.parseInt(Num[0]) > 999999) {//si es millon
                literal = getMillones(Num[0]);
            } else if (Integer.parseInt(Num[0]) > 999) {//si es miles
                literal = getMiles(Num[0]);
            } else if (Integer.parseInt(Num[0]) > 99) {//si es centena
                literal = getCentenas(Num[0]);
            } else if (Integer.parseInt(Num[0]) > 9) {//si es decena
                literal = getDecenas(Num[0]);
            } else {//sino unidades -> 9
                literal = getUnidades(Num[0]);
            }
            //devuelve el resultado en mayusculas o minusculas
            if (mayusculas) {

                return (literal + parte_decimal).toUpperCase();
            } else {
                return (literal + parte_decimal);
            }
        } else {//error, no se puede convertir
            return literal = null;
        }
    }

    /* funciones para convertir los numeros a literales */
    private String getUnidades(String numero) {// 1 - 9
        //si tuviera algun 0 antes se lo quita -> 09 = 9 o 009=9
        String num = numero.substring(numero.length() - 1);
        return UNIDADES[Integer.parseInt(num)];
    }

    private String getDecenas(String num) {// 99                        
        int n = Integer.parseInt(num);
        if (n < 10) {//para casos como -> 01 - 09
            return getUnidades(num);
        } else if (n > 19) {//para 20...99
            String u = getUnidades(num);
            if (u.equals("")) { //para 20,30,40,50,60,70,80,90
                return DECENAS[Integer.parseInt(num.substring(0, 1)) + 8];
            } else {
                return DECENAS[Integer.parseInt(num.substring(0, 1)) + 8] + " Y" + u;
            }
        } else {//numeros entre 11 y 19
            return DECENAS[n - 10];
        }
    }

    private String getCentenas(String num) {// 999 o 099
        if (Integer.parseInt(num) > 99) {//es centena
            if (Integer.parseInt(num) == 100) {//caso especial
                return " CIEN";
            } else {
                return CENTENAS[Integer.parseInt(num.substring(0, 1))] + getDecenas(num.substring(1));
            }
        } else {//por Ej. 099 
            //se quita el 0 antes de convertir a decenas
            return getDecenas(Integer.parseInt(num) + "");
        }
    }

    private String getMiles(String numero) {// 999 999
        //obtiene las centenas
        String c = numero.substring(numero.length() - 3);
        //obtiene los miles
        String m = numero.substring(0, numero.length() - 3);
        String n = "";
        //se comprueba que miles tenga valor entero
        if (Integer.parseInt(m) > 0) {
            n = getCentenas(m);
            return n + " MIL" + getCentenas(c);
        } else {
            return "" + getCentenas(c);
        }

    }

    private String getMillones(String numero) { //000 000 000        
        //se obtiene los miles
        String miles = numero.substring(numero.length() - 6);
        //se obtiene los millones
        String millon = numero.substring(0, numero.length() - 6);
        String n = "";
        if (Integer.parseInt(millon) > 1) {
            n = getCentenas(millon) + " MILLONES";
        } else {
            n = getUnidades(millon) + " MILLON";
        }
        return n + getMiles(miles);
    }

    public String convertirNumeroALetras(String numero) {
        String resultado = "";
        String literal = "";
        String parte_decimal;
        String auxLetras = "";
        //si el numero utiliza (.) en lugar de (,) -> se reemplaza
        numero = numero.replace(".", ",");
        //si el numero no tiene parte decimal, se le agrega ,00
        if (numero.indexOf(",") == -1) {
            numero = numero + ",00";
        }

        //se valida formato de entrada -> 0,00 y 999 999 999,00
        if (Pattern.matches("\\d{1,9},\\d{1,2}", numero)) {
            //se divide el numero 0000000,00 -> entero y decimal
            String Num[] = numero.split(",");
            //de da formato al numero decimal
            parte_decimal = " CON" + Num[1] + "/100";
            //se convierte el numero a literal
            if (Integer.parseInt(Num[0]) == 0) {//si el valor es cero
                auxLetras = " CERO";
            } else {
                auxLetras = numeroEntero(Integer.valueOf(Num[0])).toString();
            }
            //devuelve el resultado en mayusculas o minusculas
            resultado = (auxLetras + parte_decimal).toUpperCase();

        }
        return resultado;
    }

    private static StringBuilder numeroEntero(int n) {
        JOptionPane.showMessageDialog(null, n);
        StringBuilder result = new StringBuilder();
        int centenas = n / 100;
        int decenas = (n % 100) / 10;
        int unidades = (n % 10);

        switch (centenas) {
            case 0:
                break;
            case 1:
                if (decenas == 0 && unidades == 0) {
                    result.append(" CIENTE");
                    return result;
                } else {
                    result.append(" CIENTO");
                }
                break;
            case 2:
                result.append(" DOSCIENTOS");
                break;
            case 3:
                result.append(" TRECIENTOS");
                break;
            case 4:
                result.append(" CUATROCIENTOS");
                break;
            case 5:
                result.append(" QUINIENTOS");
                break;
            case 6:
                result.append(" SEISCIENTOS");
                break;
            case 7:
                result.append(" SETECIENTOS");
                break;
            case 8:
                result.append(" OCHOCIENTOS");
                break;
            case 9:
                result.append(" NOVECIENTOS");
                break;
        }

        switch (decenas) {
            case 0:
                break;
            case 1:
                if (unidades == 0) {
                    result.append(" Diez");
                    return result;
                } else if (unidades == 1) {
                    result.append(" Once");
                    return result;
                } else if (unidades == 2) {
                    result.append(" Doce");
                    return result;
                } else if (unidades == 3) {
                    result.append(" Trece");
                    return result;
                } else if (unidades == 4) {
                    result.append(" Catorce");
                    return result;
                } else if (unidades == 5) {
                    result.append(" Quince");
                    return result;
                } else {
                    result.append(" Dieci");
                }
                break;
            case 2:
                if (unidades == 0) {
                    result.append(" Veinte");
                    return result;
                } else {
                    result.append(" Veinti");
                }
                break;
            case 3:
                result.append(" Treinta");
                break;
            case 4:
                result.append(" Cuarenta");
                break;
            case 5:
                result.append(" Cincuenta");
                break;
            case 6:
                result.append(" Sesenta");
                break;
            case 7:
                result.append(" Setenta");
                break;
            case 8:
                result.append(" Ochenta");
                break;
            case 9:
                result.append(" Noventa");
                break;
        }

        if (decenas > 2 && unidades > 0) {
            result.append("y ");
        }

        switch (unidades) {
            case 0:
                break;
            case 1:
                result.append(" Un");
                break;
            case 2:
                result.append(" Dos");
                break;
            case 3:
                result.append(" Tres");
                break;
            case 4:
                result.append(" Cuatro");
                break;
            case 5:
                result.append(" Cinco");
                break;
            case 6:
                result.append(" Seis");
                break;
            case 7:
                result.append(" Siete");
                break;
            case 8:
                result.append(" Ocho");
                break;
            case 9:
                result.append(" Nueve");
                break;
        }

        return result;
    }

}
