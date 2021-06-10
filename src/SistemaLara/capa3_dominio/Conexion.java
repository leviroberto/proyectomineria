/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa3_dominio;

import java.sql.*;
import javax.swing.*;

public class Conexion {

    Connection conect = null;

    public Connection Conexion() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
//   conect = DriverManager.getConnection("jdbc:mysql://192.168.1.15/bdproyecto","root","");
            conect = DriverManager.getConnection("jdbc:mysql://192.168.1.52/bdsistemalara", "root", "");

        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error " + e);
        }
        return conect;
    }

}
