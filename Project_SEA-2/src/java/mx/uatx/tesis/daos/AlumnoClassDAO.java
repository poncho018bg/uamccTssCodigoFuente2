/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uatx.tesis.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.uatx.tesis.beans.AlumnoClassBeans;
//import mx.uatx.tesis.beans.IndexBeans;
import mx.uatx.tesis.db.MysqlConnect;

/**
 *
 * @author tlp_0_000
 */
public class AlumnoClassDAO {

    public List<AlumnoClassBeans> retriveListProfesores() {
        ResultSet rs = null;
        String query = "";
        List<AlumnoClassBeans> listProf = new ArrayList<AlumnoClassBeans>();
        AlumnoClassBeans profesor = null;
        MysqlConnect mysql = MysqlConnect.getDbCon();
        query = "SELECT idUsuarios, nombres FROM  usuarios WHERE roles_idRoles = 2";
        try {
            rs = mysql.query(query);
            while (rs.next()) {
                profesor = new AlumnoClassBeans();
                profesor.setIdUsuario(rs.getInt(1));
                profesor.setNombreUsu(rs.getString(2));

                listProf.add(profesor);
            }

        } catch (Exception ex) {
            // System.out.println(ex.getMessage());
        }
        return listProf;
    }

    public List<AlumnoClassBeans> retriveListClases(int idProfesor) {
        ResultSet rs = null;
        String query = "";
        List<AlumnoClassBeans> listClases = new ArrayList<AlumnoClassBeans>();
        AlumnoClassBeans clases = null;

        MysqlConnect mysql = MysqlConnect.getDbCon();

        query = "SELECT idclaseProfesor,usuarios_idusuarios,nombreClase FROM  claseProfesor where usuarios_idusuarios =" + idProfesor + ";";

        try {
            rs = mysql.query(query);

            while (rs.next()) {
                clases = new AlumnoClassBeans();
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

    public List<AlumnoClassBeans> retriveListClasesID(int idUsuario) {
        ResultSet rs = null;
        String query = "";
        List<AlumnoClassBeans> listClases = new ArrayList<AlumnoClassBeans>();
        AlumnoClassBeans clases = null;

        MysqlConnect mysql = MysqlConnect.getDbCon();

        query = "select calum.idclaseAlumno, calum.usuarios_idusuarios, calum.claseProfesor_idclaseProfesor, "
                + "cprof.idclaseProfesor, cprof.nombreClase "
                + "FROM clasealumno calum, claseprofesor cprof "
                + "WHERE cprof.idclaseProfesor = calum.claseProfesor_idclaseProfesor "
                + "AND calum.usuarios_idusuarios=" + idUsuario + ";";

        try {
            rs = mysql.query(query);

            while (rs.next()) {
                clases = new AlumnoClassBeans();
                clases.setIdClaseAlumnoTAB(rs.getInt(1));
                clases.setUsuario_idUsuarioTAB(rs.getInt(2));
                clases.setClaseProfesor_idClaseProfesorTAB(rs.getInt(3));
                clases.setIdClaseProfesorTAB(rs.getInt(4));
                clases.setNombreClassTAB(rs.getString(5));
                listClases.add(clases);
            }

        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
        }
        return listClases;
    }

    public void createClasePro(AlumnoClassBeans clase, int idUsuario) {
        ResultSet rs = null;
        String query = "";
        String campos = "";

        MysqlConnect conn = MysqlConnect.getDbCon();

        campos += "'" + clase.getClasePro_idClaseProClaseAlum() + "'";

        query = "INSERT INTO claseAlumno(idclaseAlumno, usuarios_idUsuarios,claseProfesor_idClaseProfesor)"
                + "VALUES(null," + idUsuario + "," + campos + ");";

        try {
            conn.insert(query);
        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
        }

        
    }

    public boolean retriveListClaseAlumnoEliminar(int idClase) {
        ResultSet rs = null;
        String query = "";
        boolean rest = false;

        MysqlConnect conn = MysqlConnect.getDbCon();
        query = "DELETE FROM claseAlumno WHERE idClaseAlumno= " + idClase + ";";

        try {
            conn.insert(query);
            rest = true;
        } catch (SQLException ex) {
            Logger.getLogger(ClassProDAO.class.getName()).log(Level.SEVERE, null, ex);
            rest = false;
        }

        return rest;
    }

    public List<AlumnoClassBeans> retriveCalseExist(AlumnoClassBeans clase, int idUsuario) {
        ResultSet rs = null;
        String query = "";
        List<AlumnoClassBeans> listClaseAlum = new ArrayList<AlumnoClassBeans>();
        AlumnoClassBeans claseAlum = null;
        MysqlConnect mysql = MysqlConnect.getDbCon();
        query = "SELECT COUNT(idClaseAlumno) "
                + " FROM  claseAlumno "
                + " WHERE usuarios_idUsuarios =" + idUsuario
                + " AND claseProfesor_idclaseProfesor=" + clase.getClasePro_idClaseProClaseAlum() + " ;";

        try {
            rs = mysql.query(query);
            while (rs.next()) {
                claseAlum = new AlumnoClassBeans();
                claseAlum.setTotalClases(rs.getInt(1));

                listClaseAlum.add(claseAlum);
            }

        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
        }
        return listClaseAlum;
    }

    public List<AlumnoClassBeans> listarClases(int idClaseAlumno) {
        ResultSet rs = null;
        String query = "";
        List<AlumnoClassBeans> listClaseAlum = new ArrayList<AlumnoClassBeans>();
        AlumnoClassBeans claseAlum = null;
        MysqlConnect mysql = MysqlConnect.getDbCon();
        query = " Select idclaseAlumno, usuarios_idUsuarios, claseProfesor_idClaseProfesor "
                + " from clasealumno "
                + " where idClaseAlumno=" + idClaseAlumno + ";";

        try {
            rs = mysql.query(query);
            while (rs.next()) {
                claseAlum = new AlumnoClassBeans();
                claseAlum.setIdclaseUp(rs.getInt(1));
                claseAlum.setIdusuarioUp(rs.getInt(2));
                claseAlum.setIdClaseProfUp(rs.getInt(3));

                listClaseAlum.add(claseAlum);
            }

        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
        }
        return listClaseAlum;
    }

}
