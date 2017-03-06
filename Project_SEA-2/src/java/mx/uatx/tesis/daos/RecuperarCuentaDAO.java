/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uatx.tesis.daos;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import mx.uatx.tesis.beans.RecuperarCuentaBeans;

import mx.uatx.tesis.db.MysqlConnect;

/**
 *
 * @author tlp_0_000
 */
public class RecuperarCuentaDAO {

    public List<RecuperarCuentaBeans> retriveLogin(String correo1) {
        ResultSet rs = null;
        String query = "";
        List<RecuperarCuentaBeans> listLogin = new ArrayList<RecuperarCuentaBeans>();
        RecuperarCuentaBeans usuario = null;

        MysqlConnect mysql = MysqlConnect.getDbCon();
        query = "SELECT * FROM  usuarios WHERE  correo = '" + correo1 + "';";
        try {
            rs = mysql.query(query);

            while (rs.next()) {
                usuario = new RecuperarCuentaBeans();
                usuario.setIdUsuario(rs.getInt(1));
                usuario.setNombre(rs.getString(2));
                usuario.setParterno(rs.getString(3));
                usuario.setMaterno(rs.getString(4));
                usuario.setCorreo(rs.getString(5));
                usuario.setPassword(rs.getString(6));
                usuario.setRol(rs.getInt(7));

                listLogin.add(usuario);
            }

        } catch (Exception ex) {
//            System.out.println("Error == " + ex.getMessage());
        }
        return listLogin;

    }

    public boolean retriveLoginTrue(String correo1) {
        boolean salida;
        ResultSet rs = null;
        String query = "";
        List<RecuperarCuentaBeans> listLogin = new ArrayList<RecuperarCuentaBeans>();
        RecuperarCuentaBeans usuario = null;

        MysqlConnect mysql = MysqlConnect.getDbCon();
        query = "SELECT * FROM  usuarios WHERE  correo = '" + correo1 + "';";
        try {
            rs = mysql.query(query);

            while (rs.next()) {
                usuario = new RecuperarCuentaBeans();

                usuario.setCorreo(rs.getString(1));
                usuario.setPassword(rs.getString(2));

                listLogin.add(usuario);
            }
            salida = true;
        } catch (Exception ex) {
//            System.out.println("Error == " + ex.getMessage());
            salida = false;
        }
        return salida;

    }

}
