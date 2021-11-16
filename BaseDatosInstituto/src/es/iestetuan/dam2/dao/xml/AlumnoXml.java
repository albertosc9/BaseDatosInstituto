package es.iestetuan.dam2.dao.xml;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import es.iestetuan.dam2.dao.IAlumno;
import es.iestetuan.dam2.vo.Alumno;

public class AlumnoXml implements IAlumno{

	@Override
	public Alumno getAlumno(int nia) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Alumno> getAlumnos() {
		
		List<Alumno>lista = new ArrayList<Alumno>();
		
		Properties prop = new Properties();
		
		
		try {
			FileInputStream InputStream = new FileInputStream("recursos/conf.properties");
			
			prop.load(InputStream);
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		
		//	File txt = new File(GestorConfiguracion.gestionPropiedades("txt"));
	
//		boolean primera = true;
//		String linea;
//		FileReader fr;
//		try {
//			File txt =  new File(prop.getProperty("txt"));
//			
//			fr = new FileReader(txt,Charset.forName("UTF-8"));
//			
//			BufferedReader br = new BufferedReader(fr);
//			
//			while ((linea=br.readLine())!=null) {
//				if (primera) {
//					
//					primera = false;
//					
//				}else {
//					
//					Alumno alumno = new Alumno();
//					
//					String [] partes = linea.split(",");
//					
//					int id = Integer.parseInt(partes[0]);
//					
//					
//					alumno.setId_alumno(id);
//					alumno.setNombre(partes[1]);
//					alumno.setApellido1(partes[2]);
//					alumno.setApellido2(partes[3]);
//					
//					lista.add(alumno);
//					
//					
//					
//				}
//			}
//			
//			
//			
//			
//			
//			
//			
//			
//			
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
		
		
		try {
			
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
		
			Document doc = db.parse(prop.getProperty("xml"));
			
			
			NodeList nodos = doc.getElementsByTagName("alumno");
			
			for (int i=0;i<nodos.getLength();i++) {
				Node alumno = nodos.item(i);
				Element elemento = (Element) alumno;
			
				Alumno al = new Alumno();
				
				int id_alumno = Integer.parseInt(elemento.getAttribute("nia"));
				
				al.setId_alumno(id_alumno);
				al.setNombre(elemento.getElementsByTagName("nombre").item(0).getTextContent());
				al.setApellido1(elemento.getElementsByTagName("apellido1").item(0).getTextContent());
				al.setApellido2(elemento.getElementsByTagName("apellido2").item(0).getTextContent());
				al.setNie(elemento.getElementsByTagName("nie").item(0).getTextContent());
				al.setEmail(elemento.getElementsByTagName("email").item(0).getTextContent());
				al.setTelefono(Integer.parseInt(elemento.getElementsByTagName("telefono").item(0).getTextContent()));
				
				lista.add(al);
				
				
			}
			
			
			
			
			
			
			
			
			
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	}
		
		
		
		
		
		return lista;
	}

	@Override
	public void guardarUsuario(List<Alumno> lista) {
		
		
//		AlumnoJDBC alo = new AlumnoJDBC();
//		
//		alo.guardarUsuario(lista);
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		try {
		
			db = dbf.newDocumentBuilder();
			Document doc = db.newDocument();
			
			DOMImplementation dom = db.getDOMImplementation();
			doc = dom.createDocument(null, "xml", null);
			
			
			
			Element alumnos = doc.createElement("alumnos");
			doc.getDocumentElement().appendChild(alumnos);
			
			
			
			
			
			for (Alumno al : lista) {
				Element raiz,rama;
			
				String id = Integer.toString(al.getId_alumno());
				
				raiz = doc.createElement("alumno");
				alumnos.appendChild(raiz);
				raiz.setAttribute("nia",id);
				
				
				rama = doc.createElement("nie");
				rama.appendChild(doc.createTextNode(al.getNie()));
				raiz.appendChild(rama);

				
				rama = doc.createElement("nombre");
				rama.appendChild(doc.createTextNode(al.getNombre()));
				raiz.appendChild(rama);
				
				
				rama = doc.createElement("apellido1");
				rama.appendChild(doc.createTextNode(al.getApellido1()));
				raiz.appendChild(rama);
				
				
				rama = doc.createElement("apellido2");
				rama.appendChild(doc.createTextNode(al.getApellido2()));
				raiz.appendChild(rama);
				
				rama = doc.createElement("email");
				rama.appendChild(doc.createTextNode(al.getEmail()));
				raiz.appendChild(rama);
				
				
				String telefono =Integer.toString(al.getTelefono());
				
				rama = doc.createElement("telefono");
				rama.appendChild(doc.createTextNode(telefono));
				raiz.appendChild(rama);
				
				
				
				
				
				
			}
			
			
			TransformerFactory tf = TransformerFactory.newInstance();
			
			Transformer transformer = tf.newTransformer();
			
			DOMSource source = new DOMSource(doc);
			
			StreamResult result = new StreamResult(new File("recursos/alumnos-dam2.xml"));
			
			transformer.transform(source, result);
			
			
		
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		
	}

	@Override
	public void altaAlumno(Alumno alumno) {
	
		
		AlumnoXml a1 = new AlumnoXml();
		List<Alumno> lista = getAlumnos();
		
	
		
		lista.add(alumno);
		
		a1.guardarUsuario(lista);
		
	}

	@Override
	public void borrarAlumno(int nia) {
		
	
		
		List<Alumno>lista = getAlumnos();
		
		
		
		for (Alumno al : lista) {
			
			if (al.getId_alumno()==nia) {
				lista.remove(al);
				break;
				
			}
		}
		
		guardarUsuario(lista);
		
		
		
		
	}

	@Override
	public void modificarAlumno(Alumno alumno) {
		
		List<Alumno>lista = getAlumnos();
		
		for (Alumno al : lista) {
			
			if (al.getId_alumno()==alumno.getId_alumno()) {
				
				al.setApellido1(alumno.getApellido1());
				al.setApellido2(alumno.getApellido2());
				al.setEmail(alumno.getEmail());
				al.setNie(alumno.getNie());
				al.setNombre(alumno.getNombre());
				al.setTelefono(alumno.getTelefono());

				break;
				
			}
			
		}
		
		guardarUsuario(lista);
		
	}

}
