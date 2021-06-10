/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa1_presentacion.util;

/**
 *
 * @author mineria
 */
public class NumeroToLetras {

    public static String NumeroALetras(String numero) {
        String res = null;
        try {
            numero = numero.replace(".", ",");
            //si el numero no tiene parte decimal, se le agrega ,00
            if (numero.indexOf(",") == -1) {
                numero = numero + ",00";
            }
            String Num[] = numero.split(",");
            //de da formato al numero decimal

            String dec;
            long entero = Integer.parseInt(Num[0]);
            String decimales = "";
            int tamaño = Num[1].length() - 1;
            if (tamaño == 0) {
                int numeroDecimal = Integer.parseInt(Num[1]);
                if (numeroDecimal < 10) {
                    decimales = numeroDecimal + "0";
                } else {
                    decimales = "" + numeroDecimal;
                }
            } else {
                decimales = Num[1];
            }

            dec = " CON " + decimales + "/100 ";
            String verificar = NumeroALetras((double) entero);
            if (verificar.equals("")) {
                res = null;
            } else {
                res = verificar + dec;
            }

            return res;
        } catch (Exception e) {
            DesktopNotify.showDesktopMessage("FiveCod Software", "El total no deve ser menor a cero verifique los datos", DesktopNotify.ERROR);

            return res;
        }

    }

    private static String NumeroALetras(double value) {
        String num2Text = "";
        if (value < 0) {
            return "";
        }
        value = Math.floor(value);
        if (value == 0) {
            num2Text = "CERO";
        } else if (value == 1) {
            num2Text = "UNO";
        } else if (value == 2) {
            num2Text = "DOS";
        } else if (value == 3) {
            num2Text = "TRES";
        } else if (value == 4) {
            num2Text = "CUATRO";
        } else if (value == 5) {
            num2Text = "CINCO";
        } else if (value == 6) {
            num2Text = "SEIS";
        } else if (value == 7) {
            num2Text = "SIETE";
        } else if (value == 8) {
            num2Text = "OCHO";
        } else if (value == 9) {
            num2Text = "NUEVE";
        } else if (value == 10) {
            num2Text = "DIEZ";
        } else if (value == 11) {
            num2Text = "ONCE";
        } else if (value == 12) {
            num2Text = "DOCE";
        } else if (value == 13) {
            num2Text = "TRECE";
        } else if (value == 14) {
            num2Text = "CATORCE";
        } else if (value == 15) {
            num2Text = "QUINCE";
        } else if (value < 20) {
            num2Text = "DIECI" + NumeroALetras(value - 10);
        } else if (value == 20) {
            num2Text = "VEINTE";
        } else if (value < 30) {
            num2Text = "VEINTI" + NumeroALetras(value - 20);
        } else if (value == 30) {
            num2Text = "TREINTA";
        } else if (value == 40) {
            num2Text = "CUARENTA";
        } else if (value == 50) {
            num2Text = "CINCUENTA";
        } else if (value == 60) {
            num2Text = "SESENTA";
        } else if (value == 70) {
            num2Text = "SETENTA";
        } else if (value == 80) {
            num2Text = "OCHENTA";
        } else if (value == 90) {
            num2Text = "NOVENTA";
        } else if (value < 100) {
            num2Text = NumeroALetras(Math.floor(value / 10) * 10) + " Y " + NumeroALetras(value % 10);
        } else if (value == 100) {
            num2Text = "CIEN";
        } else if (value < 200) {
            num2Text = "CIENTO " + NumeroALetras(value - 100);
        } else if ((value == 200) || (value == 300) || (value == 400) || (value == 600) || (value == 800)) {
            num2Text = NumeroALetras(Math.floor(value / 100)) + "CIENTOS";
        } else if (value == 500) {
            num2Text = "QUINIENTOS";
        } else if (value == 700) {
            num2Text = "SETECIENTOS";
        } else if (value == 900) {
            num2Text = "NOVECIENTOS";
        } else if (value < 1000) {
            num2Text = NumeroALetras(Math.floor(value / 100) * 100) + " " + NumeroALetras(value % 100);
        } else if (value == 1000) {
            num2Text = "MIL";
        } else if (value < 2000) {
            num2Text = "MIL " + NumeroALetras(value % 1000);
        } else if (value < 1000000) {
            num2Text = NumeroALetras(Math.floor(value / 1000)) + " MIL";
            if ((value % 1000) > 0) {
                num2Text = num2Text + " " + NumeroALetras(value % 1000);
            }
        } else if (value == 1000000) {
            num2Text = "UN MILLON";
        } else if (value < 2000000) {
            num2Text = "UN MILLON " + NumeroALetras(value % 1000000);
        } else if (value < 1000000000) {
            num2Text = NumeroALetras(Math.floor(value / 1000000)) + " MILLONES ";
            if ((value - Math.floor(value / 1000000) * 1000000) > 0) {
                num2Text = num2Text + " " + NumeroALetras(value - Math.floor(value / 1000000) * 1000000);
            }
        }

        return num2Text;

    }
}
