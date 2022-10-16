/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplicacion_consulta;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.sql.*;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

class Marco_Aplicacion extends JFrame{
	
	public Marco_Aplicacion(){
		setTitle ("Consulta BBDD");
		setBounds(500,300,400,400);
		setLayout(new BorderLayout());
		JPanel menus=new JPanel();
		menus.setLayout(new FlowLayout());
		secciones=new JComboBox();
		secciones.setEditable(false);
		secciones.addItem("Todos");
		paises=new JComboBox();
		paises.setEditable(false);
		paises.addItem("Todos");
		resultado= new JTextArea(4,50);
		resultado.setEditable(false);
		add(resultado);
		menus.add(secciones);
		menus.add(paises);
		add(menus, BorderLayout.NORTH);
		add(resultado, BorderLayout.CENTER);
		JButton botonConsulta=new JButton("Consulta");

		botonConsulta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ejecutaConsulta();
			}
		});
		add(botonConsulta, BorderLayout.SOUTH);



		//-----CONEXIÓN CON BBDD-----------------------------

		try{
			// 1. Crear conexión
			miConexion = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/prueba","root","root");

			System.out.println("CONECTADO A BASE DE DATOS.");

			// 2. Crear objeto Statement
			Statement sentencia = miConexion.createStatement();
			String consulta = " SELECT DISTINCTROW SECCIÓN FROM PRODUCTOS";

			// 3. Ejecutar SQL
			//-----CARGA JCOMBOX SECCIONES
			ResultSet rs = sentencia.executeQuery(consulta);

			while (rs.next()){
				secciones.addItem(rs.getString(1));
			}
			rs.close();

			//-----CARGA JCOMBOX PAISES
			consulta = " SELECT DISTINCTROW PAÍSDEORIGEN FROM PRODUCTOS";
			// 3. Ejecutar SQL
			rs = sentencia.executeQuery(consulta);

			while (rs.next()){
				paises.addItem(rs.getString(1));
			}
			rs.close();

		}catch(Exception e){
			System.out.println("NO CONECTA!!!");
			System.err.println(e);
		}
	}

	private void ejecutaConsulta(){
		ResultSet rs = null;
		try{
			resultado.setText("");
			String seccion = (String) secciones.getSelectedItem();
			String pais = (String) paises.getSelectedItem();

			if (!seccion.equals("Todos") && pais.equals("Todos")){
				enviaConsultaSeccion = miConexion.prepareStatement(consultaSeccion);
				enviaConsultaSeccion.setString(1,seccion);
				rs = enviaConsultaSeccion.executeQuery();

			}else if(seccion.equals("Todos") && !pais.equals("Todos")){
				enviaConsultaPais = miConexion.prepareStatement(consultaPais);
				enviaConsultaPais.setString(1,pais);
				rs = enviaConsultaPais.executeQuery();

			}else if (!seccion.equals("Todos") && !pais.equals("Todos")){
				enviaConsultaTodos = miConexion.prepareStatement(consultaTodos);
				enviaConsultaTodos.setString(1,pais);
				enviaConsultaTodos.setString(2,seccion);
				rs = enviaConsultaTodos.executeQuery();
			}else {
				enviaConsultaGeneral = miConexion.prepareStatement(consultaGeneral);
				rs = enviaConsultaGeneral.executeQuery();
			}

			while (rs.next()){
				resultado.append(rs.getString(1));
				resultado.append(", ");
				resultado.append(rs.getString(2));
				resultado.append(", ");
				resultado.append(rs.getString(3));
				resultado.append(", ");
				resultado.append(rs.getString(4));
				resultado.append("\n");
			}
		}catch (Exception e){
			System.err.println(e);
		}
	}


	private Connection miConexion;

	private PreparedStatement enviaConsultaSeccion;
	private PreparedStatement enviaConsultaPais;
	private PreparedStatement enviaConsultaTodos;
	private PreparedStatement enviaConsultaGeneral;

	private final String consultaSeccion = "SELECT NOMBREARTÍCULO, SECCIÓN, PRECIO, PAÍSDEORIGEN FROM PRODUCTOS WHERE SECCIÓN = ?";
	private final String consultaPais = "SELECT NOMBREARTÍCULO, SECCIÓN, PRECIO, PAÍSDEORIGEN FROM PRODUCTOS WHERE PAÍSDEORIGEN = ?";
	private final String consultaTodos = "SELECT NOMBREARTÍCULO, SECCIÓN, PRECIO, PAÍSDEORIGEN FROM PRODUCTOS WHERE PAÍSDEORIGEN = ? AND SECCIÓN = ?";
	private final String consultaGeneral = "SELECT NOMBREARTÍCULO, SECCIÓN, PRECIO, PAÍSDEORIGEN FROM PRODUCTOS";


	private JComboBox secciones;
	private JComboBox paises;
	private JTextArea resultado;
}

