package es.iestetuan.dam2.dao.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import es.iestetuan.dam2.configProperties.GestorConfiguracion;
import es.iestetuan.dam2.dao.IAlumno;
import es.iestetuan.dam2.vo.Alumno;

public class AlumnoJDBC implements IAlumno{

	String url="jdbc:mariadb://localhost:3306/instituto";
		String driver= "org.mariadb.jdbc.Driver";
		String user="root";
		String pass= "";
	
	
	private Connection conectar() {
		
		Connection conexion = null;
		
		try {
//			Class.forName(GestorConfiguracion.gestionPropiedades("driver"));
//			Class.forName(null)
//			conexion = DriverManager.getConnection(GestorConfiguracion.gestionPropiedades("url")
//					,GestorConfiguracion.gestionPropiedades("user"),GestorConfiguracion.gestionPropiedades("pass"));
//			
			Class.forName(driver);
			conexion = DriverManager.getConnection(url, user, pass);
			
			if (conexion!=null) {
				System.out.println("conectada");
			}else {
				System.out.println("fallo conexion");
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conexion;
		
	}

	@Override
	public Alumno getAlumno(int nia) throws IOException {
		
		Alumno alumno = new Alumno();
		
		Connection conexion = conectar();
		
		try {
			PreparedStatement pstm = conexion.prepareStatement("select * from t_alumno where i_alumno=?");
			pstm.setInt(1, nia);
			
			
			ResultSet result = pstm.executeQuery();
			
			
			if (result.first()) {
				alumno.setId_alumno(nia);
				alumno.setNombre(result.getString(2));
				alumno.setApellido1(result.getString(3));
				alumno.setApellido2(result.getString(4));
				alumno.setNie(result.getString(5));
				alumno.setEmail(result.getString(6));
				alumno.setTelefono(result.getInt(7));
				
			}else {
				System.out.println("nie incorrecto");
			}
			
			
			
			
			conexion.close();
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return alumno;
	}

	@Override
	public List<Alumno> getAlumnos() {

		List <Alumno> lista = new ArrayList<Alumno>();
		
		
		Connection conexion = conectar();
		
		try {
			PreparedStatement pstm = conexion.prepareStatement("select * from t_alumno");
			
			ResultSet result = pstm.executeQuery();
			
			while(result.next()) {
				
				Alumno alumno = new Alumno();
				
				alumno.setId_alumno(result.getInt(1));
				alumno.setNombre(result.getString(2));
				alumno.setApellido1(result.getString(3));
				alumno.setApellido2(result.getString(4));
				alumno.setNie(result.getString(5));
				alumno.setEmail(result.getString(6));
				alumno.setTelefono(result.getInt(7));
				
				
				lista.add(alumno);
			}
			
			
			conexion.close();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
		return lista;
	}

	@Override
	public void guardarUsuario(List<Alumno> lista) {
		
		Connection conexion = conectar();
		
		
		try {
			
			PreparedStatement pstm2;
			
			for (Alumno al : lista) {
				
				
				PreparedStatement pstm = conexion.prepareStatement("select * from t_alumno where id_alumno = ?");
				pstm.setInt(1,al.getId_alumno());
				
				
				ResultSet result = pstm.executeQuery();
				
				
				if (result.first()) {
					
				}else {
					pstm2 = conexion.prepareStatement("insert into t_alumno values (?,?,?,?,?,?,?)");
					pstm2.setInt(1,al.getId_alumno());
					pstm2.setString(2,al.getNombre());
					pstm2.setString(3,al.getApellido1());
					pstm2.setString(4, al.getApellido2());
					pstm2.setString(5, al.getNie());
					pstm2.setString(6, al.getEmail());
					pstm2.setInt(7,al.getTelefono());
					
					
					int x = pstm2.executeUpdate();
					
					if (x>0)
						System.out.println("insercion completada");
					else
						System.out.println("fallo");
				}
				
			
			}		
			
			
			conexion.close();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

	@Override
	public void altaAlumno(Alumno alumno) {
	
		Connection conexion = conectar();
		
		try {
			PreparedStatement pstm = conexion.prepareStatement("insert into t_alumno values(?,?,?,?,?,?,?)");
			pstm.setInt(1, alumno.getId_alumno());
			pstm.setString(2,alumno.getNombre());
			pstm.setString(3,alumno.getApellido1());
			pstm.setString(4,alumno.getApellido2());
			pstm.setString(5,alumno.getNie());
			pstm.setString(6,alumno.getEmail());
			pstm.setInt(7,alumno.getTelefono());
			
			
			int x = pstm.executeUpdate();
			
			if (x>0)
				System.out.println("inserción completada");
			else
				System.out.println("fallo en la inserción");
			
			
			conexion.close();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

	@Override
	public void borrarAlumno(int nia) {
		
		Connection conexion = conectar();
		
		
		try {
			PreparedStatement pstm = conexion.prepareStatement("delete from t_alumno where id_alumno = ?");
			
			pstm.setInt(1, nia);
			
			
			int x = pstm.executeUpdate();
			
			if (x>0)
				System.out.println("borrado completado");
			else
				System.out.println("fallo en el borrado");
			
			
			conexion.close();
			
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void modificarAlumno(Alumno alumno) {

		Connection conexion = conectar();
		
		try {
			PreparedStatement pstm = conexion.prepareStatement("update t_alumno set nombre = ?,apellido1 = ?, apellido2 = ?,"
																+ "nie = ?, email = ?, telefono = ?  where id_alumno = ?");
			
			pstm.setString(1,alumno.getNombre());
			pstm.setString(2, alumno.getApellido1());
			pstm.setString(3, alumno.getApellido2());
			pstm.setString(4, alumno.getNie());
			pstm.setString(5, alumno.getEmail());
			pstm.setInt(6, alumno.getTelefono());
			pstm.setInt(7,alumno.getId_alumno());
			
			
			int x = pstm.executeUpdate();
			
			if(x>0)
				System.out.println("modificación correcta");
			else
				System.out.println("modificación incorrecta");
			
			
			
			conexion.close();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
