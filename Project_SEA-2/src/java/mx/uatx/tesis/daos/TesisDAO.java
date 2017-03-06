/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uatx.tesis.daos;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import mx.uatx.tesis.beans.TesisBEANS;
import mx.uatx.tesis.db.MysqlConnect;

/**
 *
 * @author tlp_0_000
 */
public class TesisDAO {

    public List<TesisBEANS> retriveLogin(String correo1, String pass1) {
        ResultSet rs = null;
        String query = "";
        List<TesisBEANS> listLogin = new ArrayList<TesisBEANS>();
        TesisBEANS usuario = null;

        MysqlConnect mysql = MysqlConnect.getDbCon();
        query = "SELECT * FROM  usuarios WHERE  correo = '" + correo1 + "' and password = '" + pass1 + "';";

        try {
            rs = mysql.query(query);

            while (rs.next()) {
                usuario = new TesisBEANS();
                usuario.setIdUsuario(rs.getInt(1));
                usuario.setNombreUsu(rs.getString(2));
                usuario.setApellidoPaterno(rs.getString(3));
                usuario.setApellidoMaterno(rs.getString(4));
                usuario.setCorreo(rs.getString(5));
                usuario.setPassword(rs.getString(6));
                usuario.setRol_idRol(rs.getInt(7));

                listLogin.add(usuario);
            }

        } catch (Exception ex) {
//            System.out.println("Error == " + ex.getMessage());
        }
        return listLogin;

    }

    public List<TesisBEANS> retriveListRoles() {
        ResultSet rs = null;
        String query = "";
        List<TesisBEANS> listRol = new ArrayList<TesisBEANS>();
        TesisBEANS roles = null;

        MysqlConnect mysql = MysqlConnect.getDbCon();
        query = "SELECT * FROM  roles";

        try {
            rs = mysql.query(query);

            while (rs.next()) {
                roles = new TesisBEANS();
                roles.setIdRoles(rs.getInt(1));
                roles.setDescripcionRol(rs.getString(2));

                listRol.add(roles);
            }

        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
        }
        return listRol;

    }

    public int createUsuario(TesisBEANS usuario) {
        ResultSet rs = null;
        String query = "";
        String campos = "";

        MysqlConnect conn = MysqlConnect.getDbCon();

        campos += "'" + usuario.getNombreUsu() + "',";
        campos += "'" + usuario.getApellidoPaterno() + "',";
        campos += "'" + usuario.getApellidoMaterno() + "',";
        campos += "'" + usuario.getCorreo() + "',";
        campos += "'" + usuario.getPassword() + "',";
        campos += "'" + usuario.getRol_idRol() + "'";

        query = "INSERT INTO USUARIOS(idUsuarios,nombres,apellidoPaterno,apellidoMaterno,correo,password,roles_idRoles) "
                + "Values (null," + campos + ")";

        try {
            conn.insert(query);
        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
        }

        return 1;
    }

    public int createClass(TesisBEANS clase) {
        ResultSet rs = null;
        String query = "";
        String campos = "";

        MysqlConnect conn = MysqlConnect.getDbCon();

        campos += "'" + clase.getUsuario_idUsuario() + "',";
        campos += "'" + clase.getNombreClass() + "'";

        query = "INSERT INTO USUARIOS(idClaseProfesor,usuario_idUsuario,nombreClase) "
                + "Values (null," + campos + ")";

        try {
            conn.insert(query);
        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
        }

        return 1;
    }

    public List<TesisBEANS> retriveListClasesProfesor() {
        ResultSet rs = null;
        String query = "";
        List<TesisBEANS> listClases = new ArrayList<TesisBEANS>();
        TesisBEANS clases = null;

        MysqlConnect mysql = MysqlConnect.getDbCon();
        query = "SELECT * FROM  claseProfesor";

        try {
            rs = mysql.query(query);

            while (rs.next()) {
                clases = new TesisBEANS();
                clases.setIdClaseProfesor(rs.getInt(1));
                clases.setUsuario_idUsuario(rs.getInt(2));
                clases.setNombreClass(rs.getString(3));

                listClases.add(clases);
            }

        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
        }
        return listClases;

    }

    public List<TesisBEANS> emailExist(String correo) {
        ResultSet rs = null;
        String query = "";
        List<TesisBEANS> listClases = new ArrayList<TesisBEANS>();
        TesisBEANS clases = null;

        MysqlConnect mysql = MysqlConnect.getDbCon();
        query = "select COUNT(idUsuarios) FROM usuarios WHERE correo like'" + correo + "';";

        try {
            rs = mysql.query(query);

            while (rs.next()) {
                clases = new TesisBEANS();
                clases.setCorreoExist(rs.getInt(1));
                listClases.add(clases);
            }

        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
        }
        return listClases;

    }
}
