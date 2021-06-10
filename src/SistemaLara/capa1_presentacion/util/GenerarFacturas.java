/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa1_presentacion.util;

/**
 *
 * @author MINERAMIRAFLORES
 */
public class GenerarFacturas {

    private int dato;
    private final int cont = 1;
    private String num = "";

    public GenerarFacturas() {
    }

    public void generar(int dato) {
        this.dato = dato;
        if ((this.dato >= 10000000) || (this.dato < 100000000)) {
            int can = cont + this.dato;
            num = "" + can;
        }
        if ((this.dato >= 1000000) || (this.dato < 10000000)) {
            int can = cont + this.dato;
            num = "0" + can;
        }
        if ((this.dato >= 100000) || (this.dato < 1000000)) {
            int can = cont + this.dato;
            num = "00" + can;
        }
        if ((this.dato >= 10000) || (this.dato < 100000)) {
            int can = cont + this.dato;
            num = "000" + can;
        }
        if ((this.dato >= 1000) || (this.dato < 10000)) {
            int can = cont + this.dato;
            num = "0000" + can;
        }
        if ((this.dato >= 100) || (this.dato < 1000)) {
            int can = cont + this.dato;
            num = "00000" + can;
        }
        if ((this.dato >= 9) || (this.dato < 100)) {
            int can = cont + this.dato;
            num = "000000" + can;
        }
        if (this.dato < 9) {
            int can = cont + this.dato;
            num = "0000000" + can;
        }
    }

    public String correlativo() {
        return this.num;
    }

}
