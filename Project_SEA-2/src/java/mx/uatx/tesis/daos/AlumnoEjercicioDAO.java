/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uatx.tesis.daos;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import mx.uatx.tesis.beans.AlumnoEjercicioBeans;
import mx.uatx.tesis.db.MysqlConnect;

/**
 *
 * @author tlp_0_000
 */
public class AlumnoEjercicioDAO {

    public List<AlumnoEjercicioBeans> retriveListEjerToAlumnos(int idSubtema) {
        ResultSet rs = null;
        String query = "";
        List<AlumnoEjercicioBeans> subtemaList = new ArrayList<AlumnoEjercicioBeans>();
        AlumnoEjercicioBeans subtema = null;

        MysqlConnect conn = MysqlConnect.getDbCon();
        query = "SELECT idejercicioProfesor,subtemaProfesor_idsubtemaProfesor, nombre "
                + "FROM ejercicioProfesor "
                + "WHERE  subtemaProfesor_idsubtemaProfesor =" + idSubtema + ";";
       
        try {
            rs = conn.query(query);
            while (rs.next()) {
                subtema = new AlumnoEjercicioBeans();

                subtema.setIdejercicioProfesor_A(rs.getInt(1));
                subtema.setSubtemaProfesor_idsubtemaProfesor_A(rs.getInt(2));
                subtema.setNombre_A(rs.getString(3));

                subtemaList.add(subtema);
            }
        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
        }
        return subtemaList;
    }

}
