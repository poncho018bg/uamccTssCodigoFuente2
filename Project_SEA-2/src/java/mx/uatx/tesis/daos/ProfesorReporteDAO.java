/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uatx.tesis.daos;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import mx.uatx.tesis.beans.ProfesorReporteBeans;

import mx.uatx.tesis.db.MysqlConnect;

/**
 *
 * @author tlp_0_000
 */
public class ProfesorReporteDAO {

    public List<ProfesorReporteBeans> retriveListEjercicioPDF(int idEjercicio) {
        ResultSet rs = null;
        String query = "";
        List<ProfesorReporteBeans> alumnosInscritos = new ArrayList<ProfesorReporteBeans>();
        ProfesorReporteBeans inscritos = null;

        MysqlConnect conn = MysqlConnect.getDbCon();
        query = " SELECT ep.nombre AS \"Ejercicio\", ea.fechaInicio_a, ea.fechaFinal_a,  ep.tiempoTotal, ea.tiempoTotal_a,ep.intentosTotal, ea.intentosTotal_a,  ee.estado, sp.nombre AS \"Subtema\", tp.nombre AS \"Tema\", cp.nombreClase AS \"Clase\", usu.nombres AS \"Nombre Usuario\", usu.apellidoPaterno AS \"Apellido\" "
                + " FROM ejercicioalumno ea,  ejercicioprofesor ep, ejercicioestado ee , subtemaprofesor sp, temaprofesor tp, claseprofesor cp, usuarios usu "
                + " WHERE ea.ejercicioprofesor_idejercicioProfesor = " + idEjercicio
                + " AND ea.ejercicioProfesor_idejercicioProfesor = ep.idejercicioProfesor "
                + " AND ea.idejercicioAlumno = ee.ejercicioAlumno_idejercicioAlumno "
                + " AND ep.subtemaProfesor_idsubtemaProfesor = sp.idsubtemaProfesor "
                + " AND sp.temaProfesor_idtemaProfesor=tp.idtemaProfesor "
                + " AND tp.claseProfesor_idclaseProfesor=cp.idclaseProfesor "
                + " AND ea.usuarios_idusuarios=usu.idusuarios ;";

        try {
            rs = conn.query(query);
            while (rs.next()) {
                inscritos = new ProfesorReporteBeans();
                inscritos.setNombreEjercicio(rs.getString(1));
                inscritos.setFechaInicio(rs.getString(2));
                inscritos.setFechaFin(rs.getString(3));
                inscritos.setMinutosp(rs.getString(4));
                inscritos.setMinutos(rs.getString(5));
                inscritos.setNum_Intentosp(rs.getString(6));
                inscritos.setNum_Intentos(rs.getString(7));
                inscritos.setEstado(rs.getString(8));
                inscritos.setSubtema(rs.getString(9));
                inscritos.setTema(rs.getString(10));
                inscritos.setClase(rs.getString(11));
                inscritos.setNombreAlumno(rs.getString(12));
                inscritos.setPaternoAlumno(rs.getString(13));
                alumnosInscritos.add(inscritos);

            }
        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
        }
        return alumnosInscritos;
    }

    public List<ProfesorReporteBeans> retriveListTemasPDF(int idClase, int tema) {
        ResultSet rs = null;
        String query = "";
        List<ProfesorReporteBeans> alumnosInscritos = new ArrayList<ProfesorReporteBeans>();
        ProfesorReporteBeans inscritos = null;

        MysqlConnect conn = MysqlConnect.getDbCon();
        query = "  SELECT ep.nombre AS \"Ejercicio\", ea.fechaInicio_a, ea.fechaFinal_a, ep.tiempoTotal,"
                + " ea.tiempoTotal_a,ep.intentosTotal, ea.intentosTotal_a,  ee.estado, sp.nombre AS \"Subtema\", "
                + " tp.nombre AS \"Tema\", cp.nombreClase AS \"Clase\", usu.nombres AS \"Nombre Usuario\", usu.apellidoPaterno AS \"Apellido\" "
                + " FROM ejercicioalumno ea,  ejercicioprofesor ep, ejercicioEstado ee, subtemaprofesor sp, temaprofesor tp, claseprofesor cp, usuarios usu "
                + " WHERE cp.idclaseProfesor= " + idClase
                + " AND sp.temaProfesor_idtemaProfesor= " + tema
                + " AND ea.ejercicioProfesor_idejercicioProfesor = ep.idejercicioProfesor "
                + " AND ea.idejercicioAlumno = ee.ejercicioAlumno_idejercicioAlumno "
                + " AND ep.subtemaProfesor_idsubtemaProfesor = sp.idsubtemaProfesor "
                + " AND sp.temaProfesor_idtemaProfesor=tp.idtemaProfesor "
                + " AND tp.claseProfesor_idclaseProfesor=cp.idclaseProfesor "
                + " AND ea.usuarios_idusuarios=usu.idusuarios;";

        try {
            rs = conn.query(query);
            while (rs.next()) {
                inscritos = new ProfesorReporteBeans();
                inscritos.setNombreEjercicio(rs.getString(1));
                inscritos.setFechaInicio(rs.getString(2));
                inscritos.setFechaFin(rs.getString(3));
                inscritos.setMinutosp(rs.getString(4));
                inscritos.setMinutos(rs.getString(5));
                inscritos.setNum_Intentosp(rs.getString(6));
                inscritos.setNum_Intentos(rs.getString(7));
                inscritos.setEstado(rs.getString(8));
                inscritos.setSubtema(rs.getString(9));
                inscritos.setTema(rs.getString(10));
                inscritos.setClase(rs.getString(11));
                inscritos.setNombreAlumno(rs.getString(12));
                inscritos.setPaternoAlumno(rs.getString(13));
                alumnosInscritos.add(inscritos);

            }
        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
        }
        return alumnosInscritos;
    }

    public List<ProfesorReporteBeans> retriveListAlumnosPDF(int idClase) {
        ResultSet rs = null;
        String query = "";
        List<ProfesorReporteBeans> alumnosInscritos = new ArrayList<ProfesorReporteBeans>();
        ProfesorReporteBeans inscritos = null;

        MysqlConnect conn = MysqlConnect.getDbCon();
        query = "SELECT ep.nombre AS \"EJERCICIO\", ea.fechaInicio_a, ea.fechaFinal_a, ep.tiempoTotal, ea.tiempoTotal_a,ep.intentosTotal, ea.intentosTotal_a,  ee.estado, sp.nombre AS \"SUBTEMA\", tp.nombre AS \"TEMA\", cp.nombreClase AS \"CLASE\", usu.nombres AS \"USUARIO\", usu.apellidoPaterno AS \"APELLIDO\" "
                + " FROM ejercicioalumno ea,  ejercicioprofesor ep, ejercicioEstado ee, subtemaprofesor sp, temaprofesor tp, claseprofesor cp, usuarios usu, clasealumno ca "
                + " WHERE cp.idclaseProfesor=" + idClase
                + " AND ea.claseAlumno_idclaseAlumno =ca.idclaseAlumno "
                + " AND ea.ejercicioProfesor_idejercicioProfesor = ep.idejercicioProfesor "
                + " AND ea.idejercicioAlumno = ee.ejercicioAlumno_idejercicioAlumno "
                + " AND ep.subtemaProfesor_idsubtemaProfesor = sp.idsubtemaProfesor "
                + " AND sp.temaProfesor_idtemaProfesor=tp.idtemaProfesor "
                + " AND tp.claseProfesor_idclaseProfesor=cp.idclaseProfesor "
                + " AND ea.usuarios_idusuarios=usu.idusuarios; ";

        try {
            rs = conn.query(query);
            while (rs.next()) {
                inscritos = new ProfesorReporteBeans();
                inscritos.setNombreEjercicio(rs.getString(1));
                inscritos.setFechaInicio(rs.getString(2));
                inscritos.setFechaFin(rs.getString(3));
                inscritos.setMinutosp(rs.getString(4));
                inscritos.setMinutos(rs.getString(5));
                inscritos.setNum_Intentosp(rs.getString(6));
                inscritos.setNum_Intentos(rs.getString(7));
                inscritos.setEstado(rs.getString(8));
                inscritos.setSubtema(rs.getString(9));
                inscritos.setTema(rs.getString(10));
                inscritos.setClase(rs.getString(11));
                inscritos.setNombreAlumno(rs.getString(12));
                inscritos.setPaternoAlumno(rs.getString(13));
                alumnosInscritos.add(inscritos);

            }
        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
        }
        return alumnosInscritos;
    }

    public List<ProfesorReporteBeans> retriveListSubtemasPDF(int idClase, int idSubtema) {
        ResultSet rs = null;
        String query = "";
        List<ProfesorReporteBeans> alumnosInscritos = new ArrayList<ProfesorReporteBeans>();
        ProfesorReporteBeans inscritos = null;

        MysqlConnect conn = MysqlConnect.getDbCon();
        query = "  SELECT ep.nombre, ea.fechaInicio_a, ea.fechaFinal_a, ep.tiempoTotal, ea.tiempoTotal_a,ep.intentosTotal, ea.intentosTotal_a,  ee.estado, sp.nombre, tp.nombre, cp.nombreClase, usu.nombres, usu.apellidoPaterno "
                + " FROM ejercicioalumno ea,  ejercicioprofesor ep, ejercicioEstado ee, subtemaprofesor sp, temaprofesor tp, claseprofesor cp, usuarios usu "
                + " WHERE cp.idclaseProfesor= " + idClase
                + " AND ep.subtemaProfesor_idsubtemaProfesor= " + idSubtema
                + " AND ea.ejercicioProfesor_idejercicioProfesor = ep.idejercicioProfesor "
                + " AND ea.idejercicioAlumno = ee.ejercicioAlumno_idejercicioAlumno "
                + " AND ep.subtemaProfesor_idsubtemaProfesor = sp.idsubtemaProfesor "
                + " AND sp.temaProfesor_idtemaProfesor=tp.idtemaProfesor "
                + " AND tp.claseProfesor_idclaseProfesor=cp.idclaseProfesor "
                + " AND ea.usuarios_idusuarios=usu.idusuarios;";

        try {
            rs = conn.query(query);
            while (rs.next()) {
                inscritos = new ProfesorReporteBeans();
                inscritos.setNombreEjercicio(rs.getString(1));
                inscritos.setFechaInicio(rs.getString(2));
                inscritos.setFechaFin(rs.getString(3));
                inscritos.setMinutosp(rs.getString(4));
                inscritos.setMinutos(rs.getString(5));
                inscritos.setNum_Intentosp(rs.getString(6));
                inscritos.setNum_Intentos(rs.getString(7));
                inscritos.setEstado(rs.getString(8));
                inscritos.setSubtema(rs.getString(9));
                inscritos.setTema(rs.getString(10));
                inscritos.setClase(rs.getString(11));
                inscritos.setNombreAlumno(rs.getString(12));
                inscritos.setPaternoAlumno(rs.getString(13));
                alumnosInscritos.add(inscritos);

            }
        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
        }
        return alumnosInscritos;
    }

    public List<ProfesorReporteBeans> retriveListAlumnosPDF(int idClase, int idUsuario) {
        ResultSet rs = null;
        String query = "";
        List<ProfesorReporteBeans> alumnosInscritos = new ArrayList<ProfesorReporteBeans>();
        ProfesorReporteBeans inscritos = null;

        MysqlConnect conn = MysqlConnect.getDbCon();
        query = "   SELECT ep.nombre AS \"Ejercicio\", ea.fechaInicio_a, ea.fechaFinal_a, ep.tiempoTotal, ea.tiempoTotal_a,ep.intentosTotal, ea.intentosTotal_a,  "
                + " ee.estado, sp.nombre AS \"Subtema\", tp.nombre AS \"Tema\", cp.nombreClase AS \"Clase\", usu.nombres AS \"Nombre Usuario\", usu.apellidoPaterno AS \"Apellido\" "
                + " FROM ejercicioalumno ea,  ejercicioprofesor ep, ejercicioEstado ee, subtemaprofesor sp, temaprofesor tp, claseprofesor cp, usuarios usu "
                + " WHERE ea.claseAlumno_idclaseAlumno =" + idClase
                + " AND  ea.usuarios_idusuarios =" + idUsuario
                + " AND ea.ejercicioProfesor_idejercicioProfesor = ep.idejercicioProfesor "
                + " AND ea.idejercicioAlumno = ee.ejercicioAlumno_idejercicioAlumno "
                + " AND ep.subtemaProfesor_idsubtemaProfesor = sp.idsubtemaProfesor "
                + " AND sp.temaProfesor_idtemaProfesor=tp.idtemaProfesor "
                + " AND tp.claseProfesor_idclaseProfesor=cp.idclaseProfesor "
                + " AND ea.usuarios_idusuarios=usu.idusuarios ;";

        try {
            rs = conn.query(query);
            while (rs.next()) {
                inscritos = new ProfesorReporteBeans();
                inscritos.setNombreEjercicio(rs.getString(1));
                inscritos.setFechaInicio(rs.getString(2));
                inscritos.setFechaFin(rs.getString(3));
                inscritos.setMinutosp(rs.getString(4));
                inscritos.setMinutos(rs.getString(5));
                inscritos.setNum_Intentosp(rs.getString(6));
                inscritos.setNum_Intentos(rs.getString(7));
                inscritos.setEstado(rs.getString(8));
                inscritos.setSubtema(rs.getString(9));
                inscritos.setTema(rs.getString(10));
                inscritos.setClase(rs.getString(11));
                inscritos.setNombreAlumno(rs.getString(12));
                inscritos.setPaternoAlumno(rs.getString(13));
                alumnosInscritos.add(inscritos);

            }
        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
        }
        return alumnosInscritos;
    }

    public List<ProfesorReporteBeans> retriveEjercicioPDF(int idClase) {
        ResultSet rs = null;
        String query = "";
        List<ProfesorReporteBeans> alumnosInscritos = new ArrayList<ProfesorReporteBeans>();
        ProfesorReporteBeans inscritos = null;

        MysqlConnect conn = MysqlConnect.getDbCon();
        query = "  SELECT ep.idejercicioProfesor, ep.nombre "
                + " FROM ejercicioprofesor ep, subtemaprofesor sp, temaprofesor tp "
                + " WHERE sp.idsubtemaProfesor = ep.subtemaprofesor_idsubtemaProfesor "
                + " AND sp.temaprofesor_idTemaProfesor = tp.idtemaprofesor "
                + " AND tp.claseprofesor_idClaseProfesor =" + idClase;

        try {
            rs = conn.query(query);
            while (rs.next()) {
                inscritos = new ProfesorReporteBeans();
                inscritos.setIdEjercicio(rs.getInt(1));
                inscritos.setEjercicioNom(rs.getString(2));
                alumnosInscritos.add(inscritos);
            }
        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
        }
        return alumnosInscritos;
    }

    public List<ProfesorReporteBeans> retriveTemaPDF(int idUsuario, int idClase) {

        ResultSet rs = null;
        String query = "";
        List<ProfesorReporteBeans> alumnosInscritos = new ArrayList<ProfesorReporteBeans>();
        ProfesorReporteBeans inscritos = null;

        MysqlConnect conn = MysqlConnect.getDbCon();
        query = " SELECT tp.idtemaProfesor, tp.nombre "
                + " FROM temaprofesor tp, claseProfesor cp "
                + " WHERE tp.claseprofesor_idClaseprofesor = cp.idclaseProfesor "
                + " AND cp.usuarios_idUsuarios= " + idUsuario
                + " AND cp.idclaseProfesor=" + idClase + ";";

        try {
            rs = conn.query(query);
            while (rs.next()) {
                inscritos = new ProfesorReporteBeans();
                inscritos.setIdTema(rs.getInt(1));
                inscritos.setNombreTema(rs.getString(2));
                alumnosInscritos.add(inscritos);
            }
        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
        }
        return alumnosInscritos;
    }

    public List<ProfesorReporteBeans> retriveAlumnoPDF(int idClase) {
        ResultSet rs = null;
        String query = "";
        List<ProfesorReporteBeans> alumnosInscritos = new ArrayList<ProfesorReporteBeans>();
        ProfesorReporteBeans inscritos = null;

        MysqlConnect conn = MysqlConnect.getDbCon();
        query = " SELECT usu.idusuarios, usu.nombres, usu.apellidoPaterno, usu.apellidoMaterno  "
                + " FROM usuarios usu, clasealumno ca "
                + " WHERE usu.idusuarios = ca.usuarios_idusuarios "
                + " AND ca.claseProfesor_idclaseProfesor=" + idClase;

        try {
            rs = conn.query(query);
            while (rs.next()) {
                inscritos = new ProfesorReporteBeans();
                inscritos.setIdAlumno(rs.getInt(1));
                inscritos.setNomAlumno(rs.getString(2));
                inscritos.setApellidoPaterno(rs.getString(3));
                inscritos.setApellidoMaterno(rs.getString(4));
                alumnosInscritos.add(inscritos);
            }
        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
        }
        return alumnosInscritos;
    }

    public List<ProfesorReporteBeans> retrivePROFESOR(int idProfesor) {
        ResultSet rs = null;
        String query = "";
        List<ProfesorReporteBeans> alumnosInscritos = new ArrayList<ProfesorReporteBeans>();
        ProfesorReporteBeans inscritos = null;

        MysqlConnect conn = MysqlConnect.getDbCon();
        query = " select CONCAT (  nombres,apellidoPaterno,apellidoMaterno)\n"
                + "from usuarios "
                + "WHERE idUsuarios =" + idProfesor;

        try {
            rs = conn.query(query);
            while (rs.next()) {
                inscritos = new ProfesorReporteBeans();
                inscritos.setNombreProfesor(rs.getString(1));
                alumnosInscritos.add(inscritos);
            }
        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
        }
        return alumnosInscritos;
    }

    public List<ProfesorReporteBeans> retriveSubtemaPDF(int idClase) {
        ResultSet rs = null;
        String query = "";
        List<ProfesorReporteBeans> alumnosInscritos = new ArrayList<ProfesorReporteBeans>();
        ProfesorReporteBeans inscritos = null;

        MysqlConnect conn = MysqlConnect.getDbCon();
        query = "  SELECT sp.idSubtemaProfesor, sp.nombre "
                + " FROM subtemaProfesor sp, temaprofesor tp "
                + " WHERE temaProfesor_idtemaProfesor = idtemaProfesor "
                + " AND claseProfesor_idClaseProfesor =" + idClase;

        try {
            rs = conn.query(query);
            while (rs.next()) {
                inscritos = new ProfesorReporteBeans();
                inscritos.setIdSubtema(rs.getInt(1));
                inscritos.setNombreSubtemas(rs.getString(2));
                alumnosInscritos.add(inscritos);
            }
        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
        }
        return alumnosInscritos;
    }

    public List<ProfesorReporteBeans> totalClasePDF(int idClase) {
        ResultSet rs = null;
        String query = "";
        List<ProfesorReporteBeans> alumnosInscritos = new ArrayList<ProfesorReporteBeans>();
        ProfesorReporteBeans inscritos = null;

        MysqlConnect conn = MysqlConnect.getDbCon();
        query = " SELECT COUNT(DISTINCT ea.idejercicioAlumno) "
                + " FROM ejercicioprofesor ep, subtemaProfesor sp, temaprofesor tp, claseprofesor cp, ejercicioalumno ea "
                + " WHERE cp.idclaseProfesor=" + idClase
                + " AND cp.idclaseProfesor=tp.claseProfesor_idclaseProfesor "
                + " AND tp.idtemaProfesor=sp.temaProfesor_idtemaProfesor "
                + " AND sp.idsubtemaProfesor=ep.subtemaProfesor_idsubtemaProfesor"
                + " AND ep.idejercicioProfesor= ea.ejercicioProfesor_idejercicioProfesor ;";

        try {
            rs = conn.query(query);
            while (rs.next()) {
                inscritos = new ProfesorReporteBeans();
                inscritos.setTotalEjercicios(rs.getString(1));
                alumnosInscritos.add(inscritos);
            }
        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
        }
        return alumnosInscritos;
    }

    public List<ProfesorReporteBeans> totalClasePassPDF(int idClase) {
        ResultSet rs = null;
        String query = "";
        List<ProfesorReporteBeans> alumnosInscritos = new ArrayList<ProfesorReporteBeans>();
        ProfesorReporteBeans inscritos = null;

        MysqlConnect conn = MysqlConnect.getDbCon();
        query = " SELECT  COUNT(DISTINCT ea.idejercicioAlumno)"
                + " FROM ejercicioprofesor ep, ejercicioAlumno ea,subtemaProfesor sp, temaprofesor tp, claseprofesor cp, ejercicioEstado ee,  clasealumno ca "
                + " WHERE cp.idclaseProfesor=" + idClase
                + " AND cp.idclaseProfesor=ca.claseProfesor_idclaseProfesor "
                + " AND cp.idclaseProfesor=tp.claseProfesor_idclaseProfesor "
                + " AND tp.idtemaProfesor=sp.temaProfesor_idtemaProfesor "
                + " AND sp.idsubtemaProfesor=ep.subtemaProfesor_idsubtemaProfesor "
                + " AND ep.idejercicioProfesor=ea.ejercicioProfesor_idejercicioProfesor "
                + " AND ea.idejercicioAlumno= ee.ejercicioAlumno_idejercicioAlumno "
                + " AND ee.estado LIKE 'OK' ";

        try {
            rs = conn.query(query);
            while (rs.next()) {
                inscritos = new ProfesorReporteBeans();
                inscritos.setTotalEjerciciosPass(rs.getString(1));
                alumnosInscritos.add(inscritos);
            }
        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
        }
        return alumnosInscritos;
    }

    public List<ProfesorReporteBeans> totalTemas(int idTema) {
        ResultSet rs = null;
        String query = "";
        List<ProfesorReporteBeans> alumnosInscritos = new ArrayList<ProfesorReporteBeans>();
        ProfesorReporteBeans inscritos = null;

        MysqlConnect conn = MysqlConnect.getDbCon();
        query = " SELECT  COUNT(*) "
                + " FROM ejercicioprofesor ep, subtemaProfesor sp, temaprofesor tp, ejercicioalumno ea "
                + " WHERE tp.idtemaProfesor= " + idTema
                + " AND tp.idtemaProfesor=sp.temaProfesor_idtemaProfesor "
                + " AND sp.idsubtemaProfesor=ep.subtemaProfesor_idsubtemaProfesor "
                + " AND  ep.idejercicioProfesor= ea.ejercicioProfesor_idejercicioProfesor ";

        try {
            rs = conn.query(query);
            while (rs.next()) {
                inscritos = new ProfesorReporteBeans();
                inscritos.setTotalEjercicios(rs.getString(1));
                alumnosInscritos.add(inscritos);
            }
        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
        }
        return alumnosInscritos;
    }

    public List<ProfesorReporteBeans> totalTemasPass(int idTema) {
        ResultSet rs = null;
        String query = "";
        List<ProfesorReporteBeans> alumnosInscritos = new ArrayList<ProfesorReporteBeans>();
        ProfesorReporteBeans inscritos = null;

        MysqlConnect conn = MysqlConnect.getDbCon();
        query = " SELECT  COUNT(*)"
                + " FROM ejercicioprofesor ep, ejercicioAlumno ea,subtemaProfesor sp, temaprofesor tp, claseprofesor cp, ejercicioEstado ee,  clasealumno ca "
                + " WHERE tp.idtemaProfesor=" + idTema
                + " AND ca.claseProfesor_idclaseProfesor = cp.idclaseProfesor "
                + " AND cp.idclaseProfesor=tp.claseProfesor_idclaseProfesor "
                + " AND tp.idtemaProfesor=sp.temaProfesor_idtemaProfesor "
                + " AND sp.idsubtemaProfesor=ep.subtemaProfesor_idsubtemaProfesor "
                + " AND ep.idejercicioProfesor=ea.ejercicioProfesor_idejercicioProfesor "
                + " AND ea.idejercicioAlumno= ee.ejercicioAlumno_idejercicioAlumno "
                + " AND ea.usuarios_idusuarios=ca.usuarios_idusuarios "
                + " AND ee.estado LIKE 'OK' "
                + " AND ep.tiempoTotal >= ea.tiempoTotal_a "
                + " AND ep.intentosTotal >= ea.intentosTotal_a; ";

        try {
            rs = conn.query(query);
            while (rs.next()) {
                inscritos = new ProfesorReporteBeans();
                inscritos.setTotalEjerciciosPass(rs.getString(1));
                alumnosInscritos.add(inscritos);
            }
        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
        }
        return alumnosInscritos;
    }

    public List<ProfesorReporteBeans> SubtemasTotal(int idSubtema) {
        ResultSet rs = null;
        String query = "";
        List<ProfesorReporteBeans> alumnosInscritos = new ArrayList<ProfesorReporteBeans>();
        ProfesorReporteBeans inscritos = null;

        MysqlConnect conn = MysqlConnect.getDbCon();
        query = " SELECT count(*) "
                + " FROM   ejercicioprofesor ep, subtemaprofesor sp, ejercicioalumno ea "
                + " WHERE sp.idsubtemaProfesor=" + idSubtema
                + " AND sp.idsubtemaProfesor=ep.subtemaProfesor_idsubtemaProfesor "
                + " AND ea.ejercicioProfesor_idejercicioProfesor=ep.idejercicioProfesor;";

        try {
            rs = conn.query(query);
            while (rs.next()) {
                inscritos = new ProfesorReporteBeans();
                inscritos.setTotalEjercicios(rs.getString(1));
                alumnosInscritos.add(inscritos);
            }
        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
        }
        return alumnosInscritos;
    }

    public List<ProfesorReporteBeans> SubtemasTotalPass(int idSubtema) {
        ResultSet rs = null;
        String query = "";
        List<ProfesorReporteBeans> alumnosInscritos = new ArrayList<ProfesorReporteBeans>();
        ProfesorReporteBeans inscritos = null;

        MysqlConnect conn = MysqlConnect.getDbCon();
        query = "SELECT count(*) "
                + " FROM   ejercicioprofesor ep, subtemaprofesor sp, ejercicioalumno ea, ejercicioestado ee "
                + " WHERE ep.subtemaProfesor_idsubtemaProfesor=" + idSubtema
                + " AND sp.idsubtemaProfesor= ep.subtemaProfesor_idsubtemaProfesor "
                + " AND ep.idejercicioProfesor = ea.ejercicioProfesor_idejercicioProfesor"
                + " AND ea.idejercicioAlumno = ee.ejercicioAlumno_idejercicioAlumno "
                + " AND ee.estado LIKE 'OK';";

        try {
            rs = conn.query(query);
            while (rs.next()) {
                inscritos = new ProfesorReporteBeans();
                inscritos.setTotalEjerciciosPass(rs.getString(1));
                alumnosInscritos.add(inscritos);
            }
        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
        }
        return alumnosInscritos;
    }

    public List<ProfesorReporteBeans> ejercicioTotal(int idEjercicio) {
        ResultSet rs = null;
        String query = "";
        List<ProfesorReporteBeans> alumnosInscritos = new ArrayList<ProfesorReporteBeans>();
        ProfesorReporteBeans inscritos = null;

        MysqlConnect conn = MysqlConnect.getDbCon();
        query = " SELECT count(*) "
                + " FROM ejercicioprofesor ep, ejercicioalumno ea "
                + " WHERE ep.idejercicioProfesor = " + idEjercicio
                + " AND ep.idejercicioProfesor=ea.ejercicioProfesor_idejercicioProfesor; ";

        try {
            rs = conn.query(query);
            while (rs.next()) {
                inscritos = new ProfesorReporteBeans();
                inscritos.setTotalEjercicios(rs.getString(1));
                alumnosInscritos.add(inscritos);
            }
        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
        }
        return alumnosInscritos;
    }

    public List<ProfesorReporteBeans> ejercicioTotalPass(int idEjercicio) {
        ResultSet rs = null;
        String query = "";
        List<ProfesorReporteBeans> alumnosInscritos = new ArrayList<ProfesorReporteBeans>();
        ProfesorReporteBeans inscritos = null;

        MysqlConnect conn = MysqlConnect.getDbCon();
        query = "SELECT count(*)"
                + " FROM ejercicioprofesor ep, ejercicioalumno ea, ejercicioestado ee "
                + " WHERE ep.idejercicioProfesor=" + idEjercicio
                + " AND ep.idejercicioProfesor=ea.ejercicioProfesor_idejercicioProfesor "
                + " AND ee.ejercicioAlumno_idejercicioAlumno= ea.idejercicioAlumno "
                + " AND ee.estado LIKE 'OK';";

        try {
            rs = conn.query(query);
            while (rs.next()) {
                inscritos = new ProfesorReporteBeans();
                inscritos.setTotalEjerciciosPass(rs.getString(1));
                alumnosInscritos.add(inscritos);
            }
        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
        }
        return alumnosInscritos;
    }

    public List<ProfesorReporteBeans> alumnoTotal(int idAlumno) {
        ResultSet rs = null;
        String query = "";
        List<ProfesorReporteBeans> alumnosInscritos = new ArrayList<ProfesorReporteBeans>();
        ProfesorReporteBeans inscritos = null;

        MysqlConnect conn = MysqlConnect.getDbCon();
        query = " SELECT count(*) "
                + " FROM   ejercicioalumno ea "
                + " WHERE ea.usuarios_idusuarios =" + idAlumno;

        try {
            rs = conn.query(query);
            while (rs.next()) {
                inscritos = new ProfesorReporteBeans();
                inscritos.setTotalEjercicios(rs.getString(1));
                alumnosInscritos.add(inscritos);
            }
        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
        }
        return alumnosInscritos;
    }

    public List<ProfesorReporteBeans> alumnoTotalPass(int idAlumno) {
        ResultSet rs = null;
        String query = "";
        List<ProfesorReporteBeans> alumnosInscritos = new ArrayList<ProfesorReporteBeans>();
        ProfesorReporteBeans inscritos = null;

        MysqlConnect conn = MysqlConnect.getDbCon();
        query = " SELECT count(*) "
                + " FROM ejercicioalumno ea, ejercicioestado ee "
                + " WHERE ea.usuarios_idusuarios = " + idAlumno
                + " AND ee.ejercicioAlumno_idejercicioAlumno= ea.idejercicioAlumno "
                + " AND ee.estado LIKE 'OK'";

        try {
            rs = conn.query(query);
            while (rs.next()) {
                inscritos = new ProfesorReporteBeans();
                inscritos.setTotalEjerciciosPass(rs.getString(1));
                alumnosInscritos.add(inscritos);
            }
        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
        }
        return alumnosInscritos;
    }

    public List<ProfesorReporteBeans> totalEjerciciosXClasePDF(int idClase, int idUsuario) {
        ResultSet rs = null;
        String query = "";
        List<ProfesorReporteBeans> alumnosInscritos = new ArrayList<ProfesorReporteBeans>();
        ProfesorReporteBeans inscritos = null;

        MysqlConnect conn = MysqlConnect.getDbCon();
        query = " SELECT COUNT( ea.usuarios_idusuarios) "
                + " FROM ejercicioprofesor ep, subtemaProfesor sp, temaprofesor tp, claseprofesor cp, clasealumno ca, ejercicioalumno ea "
                + " WHERE cp.idclaseProfesor= " + idClase
                + " AND ea.usuarios_idusuarios= " + idUsuario
                + " AND ca.claseProfesor_idclaseProfesor = cp.idclaseProfesor "
                + " AND cp.idclaseProfesor=tp.claseProfesor_idclaseProfesor "
                + " AND tp.idtemaProfesor=sp.temaProfesor_idtemaProfesor "
                + " AND sp.idsubtemaProfesor=ep.subtemaProfesor_idsubtemaProfesor "
                + " AND ep.idejercicioProfesor=ea.ejercicioProfesor_idejercicioProfesor "
                + " AND ea.usuarios_idusuarios=ca.usuarios_idusuarios ";

        try {
            rs = conn.query(query);
            while (rs.next()) {
                inscritos = new ProfesorReporteBeans();
                inscritos.setTotalEjercicios(rs.getString(1));
                alumnosInscritos.add(inscritos);
            }
        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
        }
        return alumnosInscritos;
    }

    public List<ProfesorReporteBeans> totalEjerciciosXClasePassPDF(int idClase, int idUsuario) {
        ResultSet rs = null;
        String query = "";
        List<ProfesorReporteBeans> alumnosInscritos = new ArrayList<ProfesorReporteBeans>();
        ProfesorReporteBeans inscritos = null;

        MysqlConnect conn = MysqlConnect.getDbCon();
        query = " SELECT COUNT( ea.usuarios_idusuarios) "
                + " FROM ejercicioprofesor ep, subtemaProfesor sp, temaprofesor tp, claseprofesor cp, clasealumno ca, ejercicioalumno ea, ejercicioestado ee "
                + " WHERE cp.idclaseProfesor= " + idClase
                + " AND ea.usuarios_idusuarios= " + idUsuario
                + " AND ca.claseProfesor_idclaseProfesor = cp.idclaseProfesor "
                + " AND cp.idclaseProfesor=tp.claseProfesor_idclaseProfesor "
                + " AND tp.idtemaProfesor=sp.temaProfesor_idtemaProfesor "
                + " AND sp.idsubtemaProfesor=ep.subtemaProfesor_idsubtemaProfesor "
                + " AND ep.idejercicioProfesor=ea.ejercicioProfesor_idejercicioProfesor "
                + " AND ea.idejercicioAlumno=ee.ejercicioAlumno_idejercicioAlumno "
                + " AND ea.usuarios_idusuarios=ca.usuarios_idusuarios"
                + " AND ee.estado LIKE 'OK';";

        try {
            rs = conn.query(query);
            while (rs.next()) {
                inscritos = new ProfesorReporteBeans();
                inscritos.setTotalEjerciciosPass(rs.getString(1));
                alumnosInscritos.add(inscritos);
            }
        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
        }
        return alumnosInscritos;
    }

    public List<ProfesorReporteBeans> retriveClasePDF(int idClase, int idUsuario) {
        ResultSet rs = null;
        String query = "";
        List<ProfesorReporteBeans> alumnosInscritos = new ArrayList<ProfesorReporteBeans>();
        ProfesorReporteBeans inscritos = null;

        MysqlConnect conn = MysqlConnect.getDbCon();
        query = " SELECT ep.nombre AS \"EJERCICIO\", ea.fechaInicio_a, ea.fechaFinal_a, ep.tiempoTotal, ea.tiempoTotal_a, ep.intentosTotal, ea.intentosTotal_a, ee.estado, "
                + " sp.nombre AS \"SUBTEMA\", tp.nombre AS \"TEMA\", cp.nombreClase AS \"CLASE\", usu.nombres AS \"USUARIO\", usu.apellidoPaterno AS \"APELLIDO\" "
                + " FROM ejercicioalumno ea,  ejercicioprofesor ep, ejercicioEstado ee, subtemaprofesor sp, temaprofesor tp, claseprofesor cp, usuarios usu, clasealumno ca  "
                + " WHERE cp.idclaseProfesor= " + idClase
                + " AND ea.usuarios_idusuarios= " + idUsuario
                + " AND cp.idclaseProfesor= tp.claseProfesor_idclaseProfesor "
                + " AND tp.idtemaProfesor=sp.temaProfesor_idtemaProfesor "
                + " AND sp.idsubtemaProfesor= ep.subtemaProfesor_idsubtemaProfesor "
                + " AND ep.idejercicioProfesor= ea.ejercicioProfesor_idejercicioProfesor "
                + " AND ea.idejercicioAlumno = ee.ejercicioAlumno_idejercicioAlumno "
                + " AND ea.usuarios_idusuarios=usu.idusuarios "
                + " AND usu.idusuarios=ca.usuarios_idusuarios "
                + " AND ep.idejercicioProfesor= ea.ejercicioProfesor_idejercicioProfesor "
                + " AND cp.idclaseProfesor = ca.idclaseAlumno ";

        try {
            rs = conn.query(query);
            while (rs.next()) {
                inscritos = new ProfesorReporteBeans();
                inscritos.setNombreEjercicio(rs.getString(1));
                inscritos.setFechaInicio(rs.getString(2));
                inscritos.setFechaFin(rs.getString(3));
                inscritos.setMinutosp(rs.getString(4));
                inscritos.setMinutos(rs.getString(5));
                inscritos.setNum_Intentosp(rs.getString(6));
                inscritos.setNum_Intentos(rs.getString(7));
                inscritos.setEstado(rs.getString(8));
                inscritos.setSubtema(rs.getString(9));
                inscritos.setTema(rs.getString(10));
                inscritos.setClase(rs.getString(11));
                inscritos.setNombreAlumno(rs.getString(12));
                inscritos.setPaternoAlumno(rs.getString(13));

                alumnosInscritos.add(inscritos);

            }
        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
        }
        return alumnosInscritos;
    }

    public List<ProfesorReporteBeans> retriveTemaList(int idClase) {
        ResultSet rs = null;
        String query = "";
        List<ProfesorReporteBeans> alumnosInscritos = new ArrayList<ProfesorReporteBeans>();
        ProfesorReporteBeans inscritos = null;

        MysqlConnect conn = MysqlConnect.getDbCon();
        query = " SELECT tp.idtemaProfesor, tp.nombre "
                + " FROM temaprofesor tp, claseProfesor cp "
                + " WHERE cp.idclaseProfesor=" + idClase
                + " AND tp.claseprofesor_idClaseprofesor = cp.idclaseProfesor";

        try {
            rs = conn.query(query);
            while (rs.next()) {
                inscritos = new ProfesorReporteBeans();
                inscritos.setIdTema(rs.getInt(1));
                inscritos.setNombreTema(rs.getString(2));
                alumnosInscritos.add(inscritos);
            }
        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
        }
        return alumnosInscritos;
    }

    public List<ProfesorReporteBeans> retriveTemaAlumnoPDF(int idTema, int idUsuario) {
        ResultSet rs = null;
        String query = "";
        List<ProfesorReporteBeans> alumnosInscritos = new ArrayList<ProfesorReporteBeans>();
        ProfesorReporteBeans inscritos = null;

        MysqlConnect conn = MysqlConnect.getDbCon();
        query = " SELECT ep.nombre AS \"EJERCICIO\", ea.fechaInicio_a, ea.fechaFinal_a, ep.tiempoTotal, ea.tiempoTotal_a, ep.intentosTotal, ea.intentosTotal_a, ee.estado, "
                + " sp.nombre AS \"SUBTEMA\", tp.nombre AS \"TEMA\", cp.nombreClase AS \"CLASE\", usu.nombres AS \"USUARIO\", usu.apellidoPaterno AS \"APELLIDO\" "
                + " FROM ejercicioalumno ea,  ejercicioprofesor ep, ejercicioEstado ee, subtemaprofesor sp, temaprofesor tp, claseprofesor cp, usuarios usu, clasealumno ca "
                + " WHERE tp.idtemaProfesor=" + idTema
                + " AND ea.usuarios_idusuarios= " + idUsuario
                + " AND cp.idclaseProfesor= tp.claseProfesor_idclaseProfesor "
                + " AND tp.idtemaProfesor=sp.temaProfesor_idtemaProfesor "
                + " AND sp.idsubtemaProfesor= ep.subtemaProfesor_idsubtemaProfesor "
                + " AND ep.idejercicioProfesor= ea.ejercicioProfesor_idejercicioProfesor "
                + " AND ea.idejercicioAlumno = ee.ejercicioAlumno_idejercicioAlumno "
                + " AND ea.usuarios_idusuarios=usu.idusuarios "
                + " AND usu.idusuarios=ca.usuarios_idusuarios "
                + " AND ca.claseProfesor_idclaseProfesor = cp.idclaseProfesor; ";

        try {
            rs = conn.query(query);
            while (rs.next()) {
                inscritos = new ProfesorReporteBeans();
                inscritos.setNombreEjercicio(rs.getString(1));
                inscritos.setFechaInicio(rs.getString(2));
                inscritos.setFechaFin(rs.getString(3));
                inscritos.setMinutosp(rs.getString(4));
                inscritos.setMinutos(rs.getString(5));
                inscritos.setNum_Intentosp(rs.getString(6));
                inscritos.setNum_Intentos(rs.getString(7));
                inscritos.setEstado(rs.getString(8));
                inscritos.setSubtema(rs.getString(9));
                inscritos.setTema(rs.getString(10));
                inscritos.setClase(rs.getString(11));
                inscritos.setNombreAlumno(rs.getString(12));
                inscritos.setPaternoAlumno(rs.getString(13));

                alumnosInscritos.add(inscritos);

            }
        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
        }
        return alumnosInscritos;
    }

    public List<ProfesorReporteBeans> totalEjerciciosXTemaPDF(int idTema, int idUsuario) {
        ResultSet rs = null;
        String query = "";
        List<ProfesorReporteBeans> alumnosInscritos = new ArrayList<ProfesorReporteBeans>();
        ProfesorReporteBeans inscritos = null;

        MysqlConnect conn = MysqlConnect.getDbCon();
        query = " SELECT   COUNT(*) "
                + " FROM temaprofesor tp, subtemaprofesor sp, ejercicioprofesor ep, ejercicioalumno ea "
                + " WHERE  tp.idtemaProfesor = " + idTema
                + " AND ea.usuarios_idusuarios= " + idUsuario
                + " AND tp.idtemaProfesor=sp.temaProfesor_idtemaProfesor "
                + " AND sp.idsubtemaProfesor=ep.subtemaProfesor_idsubtemaProfesor "
                + " AND ep.idejercicioProfesor=ea.ejercicioProfesor_idejercicioProfesor;";

        try {
            rs = conn.query(query);
            while (rs.next()) {
                inscritos = new ProfesorReporteBeans();
                inscritos.setTotalEjercicios(rs.getString(1));
                alumnosInscritos.add(inscritos);
            }
        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
        }
        return alumnosInscritos;
    }

    public List<ProfesorReporteBeans> totalEjerciciosXTemaPassPDF(int idTema, int idUsuario) {
        ResultSet rs = null;
        String query = "";
        List<ProfesorReporteBeans> alumnosInscritos = new ArrayList<ProfesorReporteBeans>();
        ProfesorReporteBeans inscritos = null;

        MysqlConnect conn = MysqlConnect.getDbCon();
        query = " SELECT COUNT(*) "
                + " FROM temaprofesor tp, subtemaprofesor sp, ejercicioprofesor ep, ejercicioalumno ea, ejercicioestado ee "
                + " WHERE  tp.idtemaProfesor = " + idTema
                + " AND ea.usuarios_idusuarios= " + idUsuario
                + " AND tp.idtemaProfesor=sp.temaProfesor_idtemaProfesor "
                + " AND sp.idsubtemaProfesor=ep.subtemaProfesor_idsubtemaProfesor "
                + " AND ep.idejercicioProfesor=ea.ejercicioProfesor_idejercicioProfesor "
                + " AND ee.ejercicioAlumno_idejercicioAlumno = ea.idejercicioAlumno "
                + " AND ee.estado LIKE 'OK';";

        try {
            rs = conn.query(query);
            while (rs.next()) {
                inscritos = new ProfesorReporteBeans();
                inscritos.setTotalEjerciciosPass(rs.getString(1));
                alumnosInscritos.add(inscritos);
            }
        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
        }
        return alumnosInscritos;
    }

    public List<ProfesorReporteBeans> retriveSubtemaTemaAlumnoPDF(int idSubtema, int idUsuario) {
        ResultSet rs = null;
        String query = "";
        List<ProfesorReporteBeans> alumnosInscritos = new ArrayList<ProfesorReporteBeans>();
        ProfesorReporteBeans inscritos = null;

        MysqlConnect conn = MysqlConnect.getDbCon();
        query = " SELECT ep.nombre AS \"EJERCICIO\", ea.fechaInicio_a, ea.fechaFinal_a, ep.tiempoTotal, ea.tiempoTotal_a, ep.intentosTotal, ea.intentosTotal_a, ee.estado, "
                + " sp.nombre AS \"SUBTEMA\", tp.nombre AS \"TEMA\", cp.nombreClase AS \"CLASE\", usu.nombres AS \"USUARIO\", usu.apellidoPaterno AS \"APELLIDO\" "
                + " FROM ejercicioalumno ea,  ejercicioprofesor ep, ejercicioEstado ee, subtemaprofesor sp, temaprofesor tp, claseprofesor cp, usuarios usu, clasealumno ca  "
                + " WHERE sp.idsubtemaProfesor=" + idSubtema
                + " AND ea.usuarios_idusuarios= " + idUsuario
                + " AND cp.idclaseProfesor=tp.claseProfesor_idclaseProfesor"
                + " AND tp.idtemaProfesor=sp.temaProfesor_idtemaProfesor"
                + " AND sp.idsubtemaProfesor= ep.subtemaProfesor_idsubtemaProfesor "
                + " AND ep.idejercicioProfesor= ea.ejercicioProfesor_idejercicioProfesor "
                + " AND ea.idejercicioAlumno = ee.ejercicioAlumno_idejercicioAlumno "
                + " AND ea.usuarios_idusuarios=usu.idusuarios "
                + " AND usu.idusuarios=ca.usuarios_idusuarios"
                + " AND ca.claseProfesor_idclaseProfesor = cp.idclaseProfesor;";

        try {
            rs = conn.query(query);
            while (rs.next()) {
                inscritos = new ProfesorReporteBeans();
                inscritos.setNombreEjercicio(rs.getString(1));
                inscritos.setFechaInicio(rs.getString(2));
                inscritos.setFechaFin(rs.getString(3));
                inscritos.setMinutosp(rs.getString(4));
                inscritos.setMinutos(rs.getString(5));
                inscritos.setNum_Intentosp(rs.getString(6));
                inscritos.setNum_Intentos(rs.getString(7));
                inscritos.setEstado(rs.getString(8));
                inscritos.setSubtema(rs.getString(9));
                inscritos.setTema(rs.getString(10));
                inscritos.setClase(rs.getString(11));
                inscritos.setNombreAlumno(rs.getString(12));
                inscritos.setPaternoAlumno(rs.getString(13));

                alumnosInscritos.add(inscritos);

            }
        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
        }
        return alumnosInscritos;
    }

    public List<ProfesorReporteBeans> totalEjerciciosXSubtemaTemaPDF(int idSubtema, int idUsuario) {
        ResultSet rs = null;
        String query = "";
        List<ProfesorReporteBeans> alumnosInscritos = new ArrayList<ProfesorReporteBeans>();
        ProfesorReporteBeans inscritos = null;

        MysqlConnect conn = MysqlConnect.getDbCon();
        query = " SELECT   COUNT(DISTINCT ea.idejercicioAlumno) "
                + " FROM temaprofesor tp, subtemaprofesor sp, ejercicioprofesor ep, ejercicioalumno ea "
                + " WHERE  sp.idsubtemaProfesor = " + idSubtema
                + " AND ea.usuarios_idusuarios= " + idUsuario
                + " AND sp.idsubtemaProfesor=ep.subtemaProfesor_idsubtemaProfesor "
                + " AND ep.idejercicioProfesor=ea.ejercicioProfesor_idejercicioProfesor";

        try {
            rs = conn.query(query);
            while (rs.next()) {
                inscritos = new ProfesorReporteBeans();
                inscritos.setTotalEjercicios(rs.getString(1));
                alumnosInscritos.add(inscritos);
            }
        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
        }
        return alumnosInscritos;
    }

    public List<ProfesorReporteBeans> totalEjerciciosXSubtemaTemaPassPDF(int idSubtema, int idUsuario) {
        ResultSet rs = null;
        String query = "";
        List<ProfesorReporteBeans> alumnosInscritos = new ArrayList<ProfesorReporteBeans>();
        ProfesorReporteBeans inscritos = null;

        MysqlConnect conn = MysqlConnect.getDbCon();
        query = " SELECT COUNT(DISTINCT ea.idejercicioAlumno) "
                + " FROM temaprofesor tp, subtemaprofesor sp, ejercicioprofesor ep, ejercicioalumno ea, ejercicioestado ee "
                + " WHERE  sp.idsubtemaProfesor = " + idSubtema
                + " AND ea.usuarios_idusuarios= " + idUsuario
                + " AND sp.idsubtemaProfesor=ep.subtemaProfesor_idsubtemaProfesor "
                + " AND ep.idejercicioProfesor=ea.ejercicioProfesor_idejercicioProfesor "
                + " AND ee.ejercicioAlumno_idejercicioAlumno = ea.idejercicioAlumno "
                + " AND ee.estado LIKE 'OK';";

        try {
            rs = conn.query(query);
            while (rs.next()) {
                inscritos = new ProfesorReporteBeans();
                inscritos.setTotalEjerciciosPass(rs.getString(1));
                alumnosInscritos.add(inscritos);
            }
        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
        }
        return alumnosInscritos;
    }
}
