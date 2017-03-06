/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uatx.tesis.daos;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import mx.uatx.tesis.beans.GraficaProfesorBeans;
import mx.uatx.tesis.db.MysqlConnect;

/**
 *
 * @author tlp_0_000
 */
public class GraficaProfesorDAO {

    public List<GraficaProfesorBeans> retriveListAlumnosInscritos(int idclase) {
        ResultSet rs = null;
        String query = "";
        List<GraficaProfesorBeans> alumnosInscritos = new ArrayList<GraficaProfesorBeans>();
        GraficaProfesorBeans inscritos = null;

        MysqlConnect conn = MysqlConnect.getDbCon();
        query = "SELECT ca.idclasealumno, ca.usuarios_idusuarios, ca.claseprofesor_idclaseprofesor, us.idusuarios, us.nombres, us.apellidoPaterno, us.apellidoMaterno FROM clasealumno ca, usuarios us WHERE ca.claseprofesor_idclaseprofesor =" + idclase + " AND ca.usuarios_idusuarios = us.idusuarios" + ";";

        try {
            rs = conn.query(query);
            while (rs.next()) {
                inscritos = new GraficaProfesorBeans();
                inscritos.setIdClaseAlumno_ca(rs.getInt(1));
                inscritos.setUsuarios_idusuarios_ca(rs.getInt(2));
                inscritos.setClaseProfesor_idClaseProfesor_ca(rs.getInt(3));
                inscritos.setIdUsuario_ca(rs.getInt(4));
                inscritos.setNombres_ca(rs.getString(5));
                inscritos.setPaterno_ca(rs.getString(6));
                inscritos.setMaterno_ca(rs.getString(7));

                alumnosInscritos.add(inscritos);

            }
        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
        }
        return alumnosInscritos;
    }

    public List<GraficaProfesorBeans> retriveListGraficaProfesor1(int idclase) {
        ResultSet rs = null;
        String query = "";
        List<GraficaProfesorBeans> alumnosInscritos = new ArrayList<GraficaProfesorBeans>();
        GraficaProfesorBeans inscritos = null;

        MysqlConnect conn = MysqlConnect.getDbCon();
        query = "select  COUNT(cp.usuarios_idusuarios) "
                + " from claseprofesor cp, clasealumno ca, ejercicioalumno ea, ejercicioestado ee "
                + " WHERE cp.idclaseProfesor= " + idclase
                + " AND cp.idclaseProfesor= ca.claseProfesor_idclaseProfesor  "
                + " AND ca.idclaseAlumno= ea.claseAlumno_idclaseAlumno "
                + " AND ea.idejercicioAlumno= ee.ejercicioAlumno_idejercicioAlumno  "
                + " AND ee.estado like 'OK'";

        try {
            rs = conn.query(query);
            while (rs.next()) {
                inscritos = new GraficaProfesorBeans();
                inscritos.setCa_idclasealumno(rs.getInt(1));

                alumnosInscritos.add(inscritos);

            }
        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
        }
        return alumnosInscritos;
    }

    public List<GraficaProfesorBeans> cuentaInstcritosGraficaProfesor(int idclase) {
        ResultSet rs = null;
        String query = "";
        List<GraficaProfesorBeans> alumnosInscritos = new ArrayList<GraficaProfesorBeans>();
        GraficaProfesorBeans inscritos = null;

        MysqlConnect conn = MysqlConnect.getDbCon();
        query = "select  COUNT(cp.usuarios_idusuarios) "
                + " from claseprofesor cp, clasealumno ca, ejercicioalumno ea, ejercicioestado ee "
                + " WHERE cp.idclaseProfesor= " + idclase
                + " AND cp.idclaseProfesor= ca.claseProfesor_idclaseProfesor  "
                + " AND ca.idclaseAlumno= ea.claseAlumno_idclaseAlumno "
                + " AND ea.idejercicioAlumno= ee.ejercicioAlumno_idejercicioAlumno ;";

        try {
            rs = conn.query(query);
            while (rs.next()) {
                inscritos = new GraficaProfesorBeans();
                inscritos.setCa_usuarios_idusuariosT(rs.getInt(1));

                alumnosInscritos.add(inscritos);

            }
        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
        }
        return alumnosInscritos;
    }

}
