package es.iestetuan.dam2.app;

import java.util.List;

import es.iestetuan.dam2.dao.jdbc.AlumnoJDBC;
import es.iestetuan.dam2.dao.xml.AlumnoXml;
import es.iestetuan.dam2.vo.Alumno;

public class Aplicativo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		AlumnoXml a1 = new AlumnoXml();
		AlumnoJDBC a2 = new AlumnoJDBC();
		
		
		
		
		
	/*	
	List<Alumno>lista = a1.getAlumnos();
	
	for (Alumno al : lista) {
		System.out.println(al);
	}
		
		//primera carga en la base de datos desde el fichero TXT.
		a2.guardarUsuario(lista);
		
		//creacion del xml. 
		a1.guardarUsuario(lista);
		
		
		a1.altaAlumno(new Alumno(999,"alberto","sanchez","","","",0));
		a1.borrarAlumno(999);
		
		
		
		*/
		
		//operaciones en la base de datos.
		
		/*
			List<Alumno>lista  = a2.getAlumnos();
		
			for (Alumno al : lista) {
			System.out.println(al);
			}
		
			a2.guardarUsuario(lista);
				
			a2.altaAlumno(new Alumno(999,"alberto","sanchez","","","",0));
				
			a2.modificarAlumno(new Alumno(999,"alberto","sanchez",null,null,null,0));
				
			a2.borrarAlumno(999);
		*/
	}

}
