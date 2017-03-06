/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uatx.tesis.daos;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import mx.uatx.tesis.beans.AvanceProfesorBeans;
import mx.uatx.tesis.db.MysqlConnect;

/**
 *
 * @author tlp_0_000
 */
public class AvanceProfesorDAO {

    public List<AvanceProfesorBeans> DetallesGrafica1(int idUsuario, int idClase) {
        ResultSet rs = null;
        String query = "";
        List<AvanceProfesorBeans> detallesEjercicio = new ArrayList<AvanceProfesorBeans>();
        AvanceProfesorBeans detalles = null;

        MysqlConnect conn = MysqlConnect.getDbCon();
        query = "SELECT idejercicioalumno, ejercicioprofesor_idejercicioprofesor, usuarios_idusuarios, claseAlumno_idclaseAlumno, fechaInicio_a, fechaFinal_a, tiempoTotal_a, intentosTotal_a  FROM ejercicioAlumno WHERE usuarios_idusuarios = " + idUsuario + " AND claseAlumno_idclaseAlumno = " + idClase + " ;";

        try {
            rs = conn.query(query);
            while (rs.next()) {
                detalles = new AvanceProfesorBeans();
                detalles.setIdeejrcicioAlumno_ea(rs.getInt(1));
                detalles.setIdejercicioProfesor_ea(rs.getInt(2));
                detalles.setIdusuarios_ea(rs.getInt(3));
                detalles.setIdclaseAlumno_ea(rs.getInt(4));
                detalles.setFechaInicio_ea(rs.getString(5));
                detalles.setFechaFinal_ea(rs.getString(6));
                detalles.setTiempoTotal_ea(rs.getInt(7));
                detalles.setIntentosTotal_ea(rs.getInt(8));

                detallesEjercicio.add(detalles);

            }
        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
        }
        return detallesEjercicio;
    }

    public List<AvanceProfesorBeans> nombreEjercicio(int idEjercicio) {

        ResultSet rs = null;
        String query = "";
        List<AvanceProfesorBeans> nombreEjercicio = new ArrayList<AvanceProfesorBeans>();
        AvanceProfesorBeans nombre = null;

        MysqlConnect conn = MysqlConnect.getDbCon();
        query = "SELECT idejercicioprofesor, nombre  FROM ejercicioProfesor WHERE idejercicioprofesor = " + idEjercicio + ";";

        try {
            rs = conn.query(query);
            while (rs.next()) {
                nombre = new AvanceProfesorBeans();

                nombre.setIdejercicioProfesor_ep(rs.getInt(1));
                nombre.setNombre_ep(rs.getString(2));

                nombreEjercicio.add(nombre);

            }
        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
        }

        return nombreEjercicio;

    }

}
