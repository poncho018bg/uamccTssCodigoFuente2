/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uatx.tesis.daos;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import mx.uatx.tesis.beans.AlumnoTemarioBeans;

import mx.uatx.tesis.db.MysqlConnect;

/**
 *
 * @author tlp_0_000
 */
public class AlumnoTemarioDAO {

    public List<AlumnoTemarioBeans> retriveListTemaAlumno(int idTemaClase) {
        ResultSet rs = null;
        String query = "";
        List<AlumnoTemarioBeans> temaList = new ArrayList<AlumnoTemarioBeans>();
        AlumnoTemarioBeans tema = null;

        MysqlConnect conn = MysqlConnect.getDbCon();
        query = "SELECT idtemaProfesor,nombre,claseProfesor_idclaseProfesor FROM temaProfesor WHERE  claseProfesor_idclaseProfesor =" + idTemaClase + ";";

        try {
            rs = conn.query(query);
            while (rs.next()) {
                tema = new AlumnoTemarioBeans();
                tema.setIdTemaProfe(rs.getInt(1));
                tema.setNombreTema(rs.getString(2));
                tema.setClaseProfe_idClaseProfe(rs.getInt(3));
                temaList.add(tema);
            }
        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
        }
        return temaList;
    }

    public List<AlumnoTemarioBeans> retriveListSUBTemaAlumno() {
        ResultSet rs = null;
        String query = "";
        List<AlumnoTemarioBeans> subTemaList = new ArrayList<AlumnoTemarioBeans>();
        AlumnoTemarioBeans subTema = null;

        MysqlConnect conn = MysqlConnect.getDbCon();
        query = "SELECT idsubtemaProfesor, nombre, temaProfesor_idtemaProfesor FROM subtemaProfesor ";

        try {
            rs = conn.query(query);
            while (rs.next()) {
                subTema = new AlumnoTemarioBeans();
                subTema.setIdSubtemaProfe(rs.getInt(1));
                subTema.setNombreSubtema(rs.getString(2));
                subTema.setTemaProfe_idTemaProfe(rs.getInt(3));
                subTemaList.add(subTema);
            }
        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
        }
        return subTemaList;
    }

}
