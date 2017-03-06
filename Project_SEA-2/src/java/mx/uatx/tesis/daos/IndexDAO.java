/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uatx.tesis.daos;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import mx.uatx.tesis.beans.IndexBeans;

import mx.uatx.tesis.db.MysqlConnect;

/**
 *
 * @author tlp_0_000
 */
public class IndexDAO {

    public List<IndexBeans> retriveLogin(String correo1, String pass1) {
        ResultSet rs = null;
        String query = "";
        List<IndexBeans> listLogin = new ArrayList<IndexBeans>();
        IndexBeans usuario = null;

        MysqlConnect mysql = MysqlConnect.getDbCon();
        query = "SELECT * FROM  usuarios WHERE  correo = '" + correo1 + "' and password = '" + pass1 + "';";
        try {
            rs = mysql.query(query);

            while (rs.next()) {
                usuario = new IndexBeans();
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

    public List<IndexBeans> retriveListRoles() {
        ResultSet rs = null;
        String query = "";
        List<IndexBeans> listRol = new ArrayList<IndexBeans>();
        IndexBeans roles = null;
        MysqlConnect mysql = MysqlConnect.getDbCon();
        query = "SELECT * FROM  roles";
        try {
            rs = mysql.query(query);
            while (rs.next()) {
                roles = new IndexBeans();
                roles.setIdRoles(rs.getInt(1));
                roles.setDescripcionRol(rs.getString(2));
                listRol.add(roles);
            }

        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
        }
        return listRol;
    }

    public int createUsuario(IndexBeans usuario) {
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

    //Metodo para sacar el ID y subir a session
    public List<IndexBeans> retriveLoginSession(String correo1, String pass1) {
        ResultSet rs = null;
        String query = "";
        List<IndexBeans> listLogin2 = new ArrayList<IndexBeans>();
        IndexBeans usuario2 = null;

        MysqlConnect mysql = MysqlConnect.getDbCon();
        query = "SELECT * FROM  usuarios WHERE  correo = '" + correo1 + "' and password = '" + pass1 + "';";

        try {
            rs = mysql.query(query);

            while (rs.next()) {
                usuario2 = new IndexBeans();
                usuario2.setIdUsuario(rs.getInt(1));
                usuario2.setNombreUsu(rs.getString(2));
                usuario2.setApellidoPaterno(rs.getString(3));
                usuario2.setApellidoMaterno(rs.getString(4));
                usuario2.setCorreo(rs.getString(5));
                usuario2.setPassword(rs.getString(6));
                usuario2.setRol_idRol(rs.getInt(7));

                listLogin2.add(usuario2);
            }

        } catch (Exception ex) {
//            System.out.println("Error == " + ex.getMessage());
        }
        return listLogin2;

    }
}
