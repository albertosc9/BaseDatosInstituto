package es.iestetuan.dam2.vo;

public class Alumno {

	private int id_alumno;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String nie;
	private String email;
	private int telefono;
	
	
	
	public Alumno() {
	}
	
	
	public Alumno(int id_alumno, String nombre, String apellido1, String apellido2, String nie, String email,
			int telefono) {
		this.id_alumno = id_alumno;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.nie = nie;
		this.email = email;
		this.telefono = telefono;
	}


	

	public int getId_alumno() {
		return id_alumno;
	}


	public void setId_alumno(int id_alumno) {
		this.id_alumno = id_alumno;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellido1() {
		return apellido1;
	}


	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}


	public String getApellido2() {
		return apellido2;
	}


	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}


	public String getNie() {
		return nie;
	}


	public void setNie(String nie) {
		this.nie = nie;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public int getTelefono() {
		return telefono;
	}


	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}


	@Override
	public String toString() {
		return "Alumno [id_alumno=" + id_alumno + ", nombre=" + nombre + ", apellido1=" + apellido1 + ", apellido2="
				+ apellido2 + ", nie=" + nie + ", email=" + email + ", telefono=" + telefono + "]";
	}


	
	
	
	
	
}
