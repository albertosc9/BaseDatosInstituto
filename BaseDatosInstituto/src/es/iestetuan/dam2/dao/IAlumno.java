package es.iestetuan.dam2.dao;

import java.io.IOException;
import java.util.List;

import es.iestetuan.dam2.vo.Alumno;



public interface IAlumno {

	public Alumno getAlumno(int nia) throws IOException;
	public List<Alumno> getAlumnos();
	public void guardarUsuario(List<Alumno>lista);
	public void altaAlumno(Alumno alumno);
	public void borrarAlumno(int nia);
	public void modificarAlumno(Alumno alumno);
}
