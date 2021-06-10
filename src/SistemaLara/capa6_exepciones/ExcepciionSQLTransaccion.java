/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa6_exepciones;


public class ExcepciionSQLTransaccion extends Exception {
    
    private final String MENSAJE_DE_USUARIO = "No se pudo realizar la operación.\n"
                + "Intente en otro momento o consulte con el administrador.";  

    public ExcepciionSQLTransaccion(Throwable err) {
        super(err);
    }    

    @Override
    public String getMessage() {
        return MENSAJE_DE_USUARIO;
    }
    
}
