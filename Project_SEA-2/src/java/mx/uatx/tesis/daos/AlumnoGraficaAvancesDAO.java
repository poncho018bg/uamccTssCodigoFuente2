/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uatx.tesis.daos;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import mx.uatx.tesis.beans.AlumnoGraficaAvancesBeans;
import mx.uatx.tesis.db.MysqlConnect;

/**
 *
 * @author tlp_0_000
 */
public class AlumnoGraficaAvancesDAO {

    public List<AlumnoGraficaAvancesBeans> retriveTableAlumnos1(int idclase, int idUsuario) {
        ResultSet rs = null;
        String query = "";
        List<AlumnoGraficaAvancesBeans> alumnosInscritos = new ArrayList<AlumnoGraficaAvancesBeans>();
        AlumnoGraficaAvancesBeans inscritos = null;

        MysqlConnect conn = MysqlConnect.getDbCon();
        query = "SELECT idejercicioAlumno, ejercicioProfesor_idejercicioProfesor, usuarios_idusuarios, claseAlumno_idclaseAlumno, fechaInicio_a, fechaFinal_a, tiempoTotal_a, intentosTotal_a "
                + "FROM ejercicioalumno WHERE usuarios_idusuarios = " + idUsuario + " AND  claseAlumno_idclaseAlumno = " + idclase + " ;";

        try {
            rs = conn.query(query);
            while (rs.next()) {
                inscritos = new AlumnoGraficaAvancesBeans();
                inscritos.setG1_idejercicioAlumno(rs.getInt(1));
                inscritos.setG1_ejercicioProfesor_idejercicioProfesor(rs.getInt(2));
                inscritos.setG1_usuarios_idusuarios(rs.getInt(3));
                inscritos.setG1_claseAlumno_idclaseAlumno(rs.getInt(4));
                inscritos.setG1_fechaInicio_a(rs.getString(5));
                inscritos.setG1_fechaFinal_a(rs.getString(6));
                inscritos.setG1_tiempoTotal_a(rs.getInt(7));
                inscritos.setG1_intentosTotal_a(rs.getInt(8));

                alumnosInscritos.add(inscritos);

            }
        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
        }
        return alumnosInscritos;
    }

    public List<AlumnoGraficaAvancesBeans> retriveTableAlumnos2(int idEjercicio) {
        ResultSet rs = null;
        String query = "";
        List<AlumnoGraficaAvancesBeans> alumnosInscritos = new ArrayList<AlumnoGraficaAvancesBeans>();
        AlumnoGraficaAvancesBeans inscritos = null;

        MysqlConnect conn = MysqlConnect.getDbCon();
        query = "SELECT idejercicioProfesor, nombre, tiempoTotal, intentosTotal FROM ejercicioprofesor WHERE idejercicioProfesor = " + idEjercicio + ";";

        try {
            rs = conn.query(query);
            while (rs.next()) {
                inscritos = new AlumnoGraficaAvancesBeans();
                inscritos.setG2_idejercicioProfesor(rs.getInt(1));
                inscritos.setG2_nombre(rs.getString(2));
                inscritos.setG2_tiempoTotal(rs.getInt(3));
                inscritos.setG2_intentosTotal(rs.getInt(4));

                alumnosInscritos.add(inscritos);

            }
        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
        }
        return alumnosInscritos;
    }

    public List<AlumnoGraficaAvancesBeans> retriveClasePDF(int idClase, int idUsuario) {
        ResultSet rs = null;
        String query = "";
        List<AlumnoGraficaAvancesBeans> alumnosInscritos = new ArrayList<AlumnoGraficaAvancesBeans>();
        AlumnoGraficaAvancesBeans inscritos = null;

        MysqlConnect conn = MysqlConnect.getDbCon();
        query = " SELECT ep.nombre AS \"EJERCICIO\", ea.fechaInicio_a, ea.fechaFinal_a, ep.tiempoTotal, ea.tiempoTotal_a, ep.intentosTotal, ea.intentosTotal_a, ee.estado, "
                + " sp.nombre AS \"SUBTEMA\", tp.nombre AS \"TEMA\", cp.nombreClase AS \"CLASE\", usu.nombres AS \"USUARIO\", usu.apellidoPaterno AS \"APELLIDO\" "
                + " FROM ejercicioalumno ea,  ejercicioprofesor ep, ejercicioEstado ee, subtemaprofesor sp, temaprofesor tp, claseprofesor cp, usuarios usu, clasealumno ca "
                + " WHERE ea.claseAlumno_idclaseAlumno = " + idClase
                + " AND usu.idusuarios = " + idUsuario
                + " AND ca.idclaseAlumno= ea.claseAlumno_idclaseAlumno "
                + " AND ea.ejercicioProfesor_idejercicioProfesor=ep.idejercicioProfesor "
                + " AND sp.idsubtemaProfesor=ep.subtemaProfesor_idsubtemaProfesor "
                + " AND tp.idtemaProfesor= sp.temaProfesor_idtemaProfesor "
                + " AND cp.idclaseProfesor= tp.claseProfesor_idclaseProfesor "
                + " AND ea.idejercicioAlumno= ee.ejercicioAlumno_idejercicioAlumno "
                + " AND usu.idusuarios= ea.usuarios_idusuarios";

        try {
            rs = conn.query(query);
            while (rs.next()) {
                inscritos = new AlumnoGraficaAvancesBeans();
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

    public List<AlumnoGraficaAvancesBeans> retriveTemaPDF(int idClase) {
        ResultSet rs = null;
        String query = "";
        List<AlumnoGraficaAvancesBeans> alumnosInscritos = new ArrayList<AlumnoGraficaAvancesBeans>();
        AlumnoGraficaAvancesBeans inscritos = null;

        MysqlConnect conn = MysqlConnect.getDbCon();
        query = " SELECT tp.idtemaProfesor, tp.nombre "
                + " FROM temaprofesor tp, claseProfesor cp, clasealumno ca "
                + " WHERE ca.idclasealumno = " + idClase
                + " AND tp.claseprofesor_idClaseprofesor = cp.idclaseProfesor "
                + " AND ca.claseProfesor_idclaseProfesor =cp.idclaseProfesor";

        try {
            rs = conn.query(query);
            while (rs.next()) {
                inscritos = new AlumnoGraficaAvancesBeans();
                inscritos.setIdTema(rs.getInt(1));
                inscritos.setNombreTema(rs.getString(2));
                alumnosInscritos.add(inscritos);
            }
        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
        }
        return alumnosInscritos;
    }

    public List<AlumnoGraficaAvancesBeans> retriveSubtemaPDF(int idClase) {
        ResultSet rs = null;
        String query = "";
        List<AlumnoGraficaAvancesBeans> alumnosInscritos = new ArrayList<AlumnoGraficaAvancesBeans>();
        AlumnoGraficaAvancesBeans inscritos = null;

        MysqlConnect conn = MysqlConnect.getDbCon();
        query = " SELECT sp.idSubtemaProfesor, sp.nombre "
                + " FROM subtemaProfesor sp, temaprofesor tp, clasealumno ca, claseprofesor cp "
                + " WHERE ca.idclasealumno = " + idClase
                + " AND sp.temaProfesor_idtemaProfesor = tp.idtemaProfesor "
                + " AND ca.claseProfesor_idclaseProfesor =cp.idclaseProfesor "
                + " AND tp.claseprofesor_idClaseprofesor = cp.idclaseProfesor;";

        try {
            rs = conn.query(query);
            while (rs.next()) {
                inscritos = new AlumnoGraficaAvancesBeans();
                inscritos.setIdSubtema(rs.getInt(1));
                inscritos.setNombreSubtemas(rs.getString(2));
                alumnosInscritos.add(inscritos);
            }
        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
        }
        return alumnosInscritos;
    }

    public List<AlumnoGraficaAvancesBeans> retriveEjercicioPDF(int idClase) {
        ResultSet rs = null;
        String query = "";
        List<AlumnoGraficaAvancesBeans> alumnosInscritos = new ArrayList<AlumnoGraficaAvancesBeans>();
        AlumnoGraficaAvancesBeans inscritos = null;

        MysqlConnect conn = MysqlConnect.getDbCon();
        query = "  SELECT ep.idejercicioProfesor, ep.nombre "
                + " FROM ejercicioprofesor ep, subtemaprofesor sp, temaprofesor tp "
                + " WHERE sp.idsubtemaProfesor = ep.subtemaprofesor_idsubtemaProfesor "
                + " AND sp.temaprofesor_idTemaProfesor = tp.idtemaprofesor "
                + " AND tp.claseprofesor_idClaseProfesor =" + idClase;

        try {
            rs = conn.query(query);
            while (rs.next()) {
                inscritos = new AlumnoGraficaAvancesBeans();
                inscritos.setIdEjercicio(rs.getInt(1));
                inscritos.setEjercicioNom(rs.getString(2));
                alumnosInscritos.add(inscritos);
            }
        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
        }
        return alumnosInscritos;
    }

    public List<AlumnoGraficaAvancesBeans> retriveListTemasPDF(int idUsuario, int tema) {
        ResultSet rs = null;
        String query = "";
        List<AlumnoGraficaAvancesBeans> alumnosInscritos = new ArrayList<AlumnoGraficaAvancesBeans>();
        AlumnoGraficaAvancesBeans inscritos = null;

        MysqlConnect conn = MysqlConnect.getDbCon();
        query = "SELECT ep.nombre AS \"Ejercicio\", ea.fechaInicio_a, ea.fechaFinal_a, "
                + " ep.tiempoTotal, ea.tiempoTotal_a,ep.intentosTotal, ea.intentosTotal_a, "
                + " ee.estado, sp.nombre AS \"Subtema\", tp.nombre AS \"Tema\", "
                + "cp.nombreClase AS \"Clase\", usu.nombres AS \"Nombre Usuario\", usu.apellidoPaterno AS \"Apellido\""
                + " FROM ejercicioalumno ea,  ejercicioprofesor ep, ejercicioEstado ee, subtemaprofesor sp, temaprofesor tp, claseprofesor cp, usuarios usu "
                + " WHERE ea.usuarios_idusuarios=" + idUsuario
                + " AND tp.idtemaProfesor=" + tema
                + " AND ea.ejercicioProfesor_idejercicioProfesor = ep.idejercicioProfesor "
                + " AND ea.idejercicioAlumno = ee.ejercicioAlumno_idejercicioAlumno "
                + " AND ep.subtemaProfesor_idsubtemaProfesor = sp.idsubtemaProfesor "
                + " AND sp.temaProfesor_idtemaProfesor=tp.idtemaProfesor "
                + " AND tp.claseProfesor_idclaseProfesor=cp.idclaseProfesor "
                + " AND ea.usuarios_idusuarios=usu.idusuarios;";

        try {
            rs = conn.query(query);
            while (rs.next()) {
                inscritos = new AlumnoGraficaAvancesBeans();

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

    public List<AlumnoGraficaAvancesBeans> retriveListSubtemasPDF(int idUsuario, int idSubtema) {
        ResultSet rs = null;
        String query = "";
        List<AlumnoGraficaAvancesBeans> alumnosInscritos = new ArrayList<AlumnoGraficaAvancesBeans>();
        AlumnoGraficaAvancesBeans inscritos = null;

        MysqlConnect conn = MysqlConnect.getDbCon();
        query = " SELECT ep.nombre AS \"Ejercicio\", ea.fechaInicio_a, ea.fechaFinal_a, "
                + " ep.tiempoTotal, ea.tiempoTotal_a,ep.intentosTotal, ea.intentosTotal_a, "
                + " ee.estado, sp.nombre AS \"Subtema\", tp.nombre AS \"Tema\", "
                + " cp.nombreClase AS \"Clase\", usu.nombres AS \"Nombre Usuario\", usu.apellidoPaterno AS \"Apellido\" "
                + " FROM ejercicioalumno ea,  ejercicioprofesor ep, ejercicioEstado ee, subtemaprofesor sp, temaprofesor tp, claseprofesor cp, usuarios usu "
                + " WHERE ea.usuarios_idusuarios= " + idUsuario
                + " AND sp.idsubtemaProfesor= " + idSubtema
                + " AND ea.ejercicioProfesor_idejercicioProfesor = ep.idejercicioProfesor "
                + " AND ea.idejercicioAlumno = ee.ejercicioAlumno_idejercicioAlumno "
                + " AND ep.subtemaProfesor_idsubtemaProfesor = sp.idsubtemaProfesor "
                + " AND sp.temaProfesor_idtemaProfesor=tp.idtemaProfesor "
                + " AND tp.claseProfesor_idclaseProfesor=cp.idclaseProfesor "
                + " AND ea.usuarios_idusuarios=usu.idusuarios; ";
//        System.out.println(query);

        try {
            rs = conn.query(query);
            while (rs.next()) {
                inscritos = new AlumnoGraficaAvancesBeans();
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

    public List<AlumnoGraficaAvancesBeans> retriveListEjercicioPDF(int idEjercicio, int idUsuario) {
        ResultSet rs = null;
        String query = "";
        List<AlumnoGraficaAvancesBeans> alumnosInscritos = new ArrayList<AlumnoGraficaAvancesBeans>();
        AlumnoGraficaAvancesBeans inscritos = null;

        MysqlConnect conn = MysqlConnect.getDbCon();
        query = " SELECT ep.nombre AS \"Ejercicio\", ea.fechaInicio_a, ea.fechaFinal_a, ea.tiempoTotal_a, ea.intentosTotal_a, cpa.estatus, sp.nombre AS \"Subtema\", tp.nombre AS \"Tema\", cp.nombreClase AS \"Clase\", usu.nombres AS \"Nombre Usuario\", usu.apellidoPaterno AS \"Apellido\" "
                + " FROM ejercicioalumno ea,  ejercicioprofesor ep, casopruebaalumno cpa, subtemaprofesor sp, temaprofesor tp, claseprofesor cp, usuarios usu "
                + " WHERE ea.ejercicioprofesor_idejercicioProfesor =" + idEjercicio
                + " AND  ea.usuarios_idusuarios=" + idUsuario
                + " AND ea.ejercicioProfesor_idejercicioProfesor = ep.idejercicioProfesor "
                + " AND ea.usuarios_idusuarios= cpa.usuarios_idusuarios "
                + " AND ea.idejercicioAlumno = cpa.ejercicioAlumno_idejercicioAlumno "
                + " AND ep.subtemaProfesor_idsubtemaProfesor = sp.idsubtemaProfesor "
                + " AND sp.temaProfesor_idtemaProfesor=tp.idtemaProfesor "
                + " AND tp.claseProfesor_idclaseProfesor=cp.idclaseProfesor "
                + " AND ea.usuarios_idusuarios=usu.idusuarios ;";

        try {
            rs = conn.query(query);
            while (rs.next()) {
                inscritos = new AlumnoGraficaAvancesBeans();
                inscritos.setNombreEjercicio(rs.getString(1));
                inscritos.setFechaInicio(rs.getString(2));
                inscritos.setFechaFin(rs.getString(3));
                inscritos.setMinutos(rs.getString(4));
                inscritos.setNum_Intentos(rs.getString(5));
                inscritos.setEstado(rs.getString(6));
                inscritos.setSubtema(rs.getString(7));
                inscritos.setTema(rs.getString(8));
                inscritos.setClase(rs.getString((9)));
                inscritos.setNombreAlumno(rs.getString(10));
                inscritos.setPaternoAlumno(rs.getString(11));
                alumnosInscritos.add(inscritos);

            }
        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
        }
        return alumnosInscritos;
    }

    public List<AlumnoGraficaAvancesBeans> totalEjerciciosPDF(int idClase) {
        ResultSet rs = null;
        String query = "";
        List<AlumnoGraficaAvancesBeans> alumnosInscritos = new ArrayList<AlumnoGraficaAvancesBeans>();
        AlumnoGraficaAvancesBeans inscritos = null;

        MysqlConnect conn = MysqlConnect.getDbCon();
        query = " SELECT COUNT(ep.idejercicioProfesor) FROM ejercicioprofesor ep, subtemaProfesor sp, temaprofesor tp, claseprofesor cp, clasealumno ca "
                + " WHERE ca.idclaseAlumno=" + idClase
                + " AND ca.claseProfesor_idclaseProfesor = cp.idclaseProfesor "
                + " AND cp.idclaseProfesor=tp.claseProfesor_idclaseProfesor "
                + " AND tp.idtemaProfesor=sp.temaProfesor_idtemaProfesor "
                + " AND sp.idsubtemaProfesor=ep.subtemaProfesor_idsubtemaProfesor;";

        try {
            rs = conn.query(query);
            while (rs.next()) {
                inscritos = new AlumnoGraficaAvancesBeans();
                inscritos.setTotalEjercicio(rs.getString(1));
                alumnosInscritos.add(inscritos);
            }
        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
        }
        return alumnosInscritos;
    }

    public List<AlumnoGraficaAvancesBeans> totalEjerciciosPassPDF(int idClase, int idUsuario) {
        ResultSet rs = null;
        String query = "";
        List<AlumnoGraficaAvancesBeans> alumnosInscritos = new ArrayList<AlumnoGraficaAvancesBeans>();
        AlumnoGraficaAvancesBeans inscritos = null;

        MysqlConnect conn = MysqlConnect.getDbCon();
        query = " SELECT COUNT( ep.idejercicioProfesor) "
                + " FROM ejercicioprofesor ep, ejercicioAlumno ea,subtemaProfesor sp, temaprofesor tp, claseprofesor cp, ejercicioEstado ee,  clasealumno ca "
                + " WHERE ca.idclaseAlumno=" + idClase
                + " AND ea.usuarios_idusuarios =" + idUsuario
                + " AND ca.claseProfesor_idclaseProfesor = cp.idclaseProfesor "
                + " AND cp.idclaseProfesor=tp.claseProfesor_idclaseProfesor "
                + " AND tp.idtemaProfesor=sp.temaProfesor_idtemaProfesor "
                + " AND sp.idsubtemaProfesor=ep.subtemaProfesor_idsubtemaProfesor "
                + " AND ep.idejercicioProfesor=ea.ejercicioProfesor_idejercicioProfesor "
                + " AND ea.idejercicioAlumno= ee.ejercicioAlumno_idejercicioAlumno "
                + " AND ee.estado LIKE 'OK' "
                + " AND ep.tiempoTotal >= ea.tiempoTotal_a "
                + " AND ep.intentosTotal >= ea.intentosTotal_a;";

        try {
            rs = conn.query(query);
            while (rs.next()) {
                inscritos = new AlumnoGraficaAvancesBeans();
                inscritos.setTotalPass(rs.getString(1));
                alumnosInscritos.add(inscritos);
            }
        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
        }
        return alumnosInscritos;
    }

    public List<AlumnoGraficaAvancesBeans> nombreProfesorPDF(int idClase) {
        ResultSet rs = null;
        String query = "";
        List<AlumnoGraficaAvancesBeans> alumnosInscritos = new ArrayList<AlumnoGraficaAvancesBeans>();
        AlumnoGraficaAvancesBeans inscritos = null;

        MysqlConnect conn = MysqlConnect.getDbCon();
        query = " SELECT usu.nombres, usu.apellidoPaterno, usu.apellidoMaterno "
                + " FROM claseProfesor cp, clasealumno ca, usuarios usu "
                + " WHERE ca.idclaseAlumno=" + idClase
                + " AND ca.claseProfesor_idclaseProfesor= cp.idclaseProfesor "
                + " AND cp.usuarios_idusuarios=usu.idusuarios";

        try {
            rs = conn.query(query);
            while (rs.next()) {
                inscritos = new AlumnoGraficaAvancesBeans();
                inscritos.setNombreProfesor(rs.getString(1));
                inscritos.setPaternoProfesor(rs.getString(2));
                inscritos.setMaternoProfesor(rs.getString(3));
                alumnosInscritos.add(inscritos);
            }
        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
        }
        return alumnosInscritos;
    }

    public List<AlumnoGraficaAvancesBeans> totalTemas(int idTema) {
        ResultSet rs = null;
        String query = "";
        List<AlumnoGraficaAvancesBeans> alumnosInscritos = new ArrayList<AlumnoGraficaAvancesBeans>();
        AlumnoGraficaAvancesBeans inscritos = null;

        MysqlConnect conn = MysqlConnect.getDbCon();
        query = "SELECT count(*) FROM ejercicioprofesor ep, subtemaProfesor sp, temaprofesor tp, claseprofesor cp, usuarios usu "
                + " WHERE tp.idtemaProfesor=" + idTema
                + " AND cp.usuarios_idusuarios= usu.idusuarios "
                + " AND cp.idclaseProfesor=tp.claseProfesor_idclaseProfesor "
                + " AND tp.idtemaProfesor=sp.temaProfesor_idtemaProfesor "
                + " AND sp.idsubtemaProfesor=ep.subtemaProfesor_idsubtemaProfesor;";

        try {
            rs = conn.query(query);
            while (rs.next()) {
                inscritos = new AlumnoGraficaAvancesBeans();
                inscritos.setTotalPass(rs.getString(1));
                alumnosInscritos.add(inscritos);
            }
        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
        }
        return alumnosInscritos;
    }

    public List<AlumnoGraficaAvancesBeans> totalTemasPass(int idUsuario, int idClase, int idTema) {
        ResultSet rs = null;
        String query = "";
        List<AlumnoGraficaAvancesBeans> alumnosInscritos = new ArrayList<AlumnoGraficaAvancesBeans>();
        AlumnoGraficaAvancesBeans inscritos = null;

        MysqlConnect conn = MysqlConnect.getDbCon();
        query = "SELECT count(*) "
                + " FROM ejercicioprofesor ep, ejercicioAlumno ea,subtemaProfesor sp, temaprofesor tp, claseprofesor cp, ejercicioEstado ee,  clasealumno ca\n"
                + " WHERE tp.idtemaProfesor=" + idTema
                + " AND ea.usuarios_idusuarios =" + idUsuario
                + " AND ca.idclaseAlumno=" + idClase
                + " AND ca.claseProfesor_idclaseProfesor = cp.idclaseProfesor "
                + " AND cp.idclaseProfesor=tp.claseProfesor_idclaseProfesor "
                + " AND tp.idtemaProfesor=sp.temaProfesor_idtemaProfesor "
                + " AND sp.idsubtemaProfesor=ep.subtemaProfesor_idsubtemaProfesor "
                + " AND ep.idejercicioProfesor=ea.ejercicioProfesor_idejercicioProfesor "
                + " AND ea.idejercicioAlumno= ee.ejercicioAlumno_idejercicioAlumno "
                + " AND ee.estado LIKE 'OK' "
                + " AND ep.tiempoTotal >= ea.tiempoTotal_a "
                + " AND ep.intentosTotal >= ea.intentosTotal_a; ";

        try {
            rs = conn.query(query);
            while (rs.next()) {
                inscritos = new AlumnoGraficaAvancesBeans();
                inscritos.setTotalPass(rs.getString(1));
                alumnosInscritos.add(inscritos);
            }
        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
        }
        return alumnosInscritos;
    }

    public List<AlumnoGraficaAvancesBeans> SubtemasTotal(int idSubtema) {
        ResultSet rs = null;
        String query = "";
        List<AlumnoGraficaAvancesBeans> alumnosInscritos = new ArrayList<AlumnoGraficaAvancesBeans>();
        AlumnoGraficaAvancesBeans inscritos = null;

        MysqlConnect conn = MysqlConnect.getDbCon();
        query = " SELECT count(*) "
                + " FROM   ejercicioprofesor ep, subtemaprofesor sp "
                + " WHERE ep.subtemaProfesor_idsubtemaProfesor= " + idSubtema
                + " AND sp.idsubtemaProfesor= ep.subtemaProfesor_idsubtemaProfesor ";

        try {
            rs = conn.query(query);
            while (rs.next()) {
                inscritos = new AlumnoGraficaAvancesBeans();
                inscritos.setTotalPass(rs.getString(1));
                alumnosInscritos.add(inscritos);
            }
        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
        }
        return alumnosInscritos;
    }

    public List<AlumnoGraficaAvancesBeans> SubtemasTotalPass(int idSubtema, int idUsuario) {
        ResultSet rs = null;
        String query = "";
        List<AlumnoGraficaAvancesBeans> alumnosInscritos = new ArrayList<AlumnoGraficaAvancesBeans>();
        AlumnoGraficaAvancesBeans inscritos = null;

        MysqlConnect conn = MysqlConnect.getDbCon();
        query = "   SELECT count(*) "
                + " FROM   ejercicioprofesor ep, subtemaprofesor sp, ejercicioalumno ea, ejercicioestado ee "
                + " WHERE ep.subtemaProfesor_idsubtemaProfesor=" + idSubtema
                + " AND ea.usuarios_idusuarios =" + idUsuario
                + " AND sp.idsubtemaProfesor= ep.subtemaProfesor_idsubtemaProfesor "
                + " AND ep.idejercicioProfesor = ea.ejercicioProfesor_idejercicioProfesor "
                + " AND ea.idejercicioAlumno = ee.ejercicioAlumno_idejercicioAlumno "
                + " AND ee.estado LIKE 'OK';";

        try {
            rs = conn.query(query);
            while (rs.next()) {
                inscritos = new AlumnoGraficaAvancesBeans();
                inscritos.setTotalPass(rs.getString(1));
                alumnosInscritos.add(inscritos);
            }
        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
        }
        return alumnosInscritos;
    }
}
