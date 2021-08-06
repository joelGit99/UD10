import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;


import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion_sqlApp {
	static Connection conexion;
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexion = DriverManager.getConnection("jdbc:mysql://192.168.1.250:3306?useTimezone=true&serverTimezone=UTC","remote","Bootcamp2980.");
			System.out.println("Servidor conectado");
		} catch(SQLException | ClassNotFoundException ex) {
			System.out.print("No se ha podido conectar con la base de datos");
			System.out.print(ex);
		}
		
		// 1
		createDB("Tienda_informatica");
		createTable("Tienda_informatica","CREATE TABLE FABRICANTES ( id int auto_increment primary key, nombre nvarchar(100) );");
		createTable("Tienda_informatica", "CREATE TABLE ARTICULOS ( id int auto_increment primary key, nombre nvarchar(100), precio int, fabricante int references FABRICANTES (id) on delete cascade );");
		insertData("Tienda_informatica","INSERT INTO FABRICANTES (nombre) VALUES('HP')");
		insertData("Tienda_informatica", "INSERT INTO ARTICULOS (nombre, precio, fabricante) VALUES('PC', 1000, 1)");
		insertData("Tienda_informatica","INSERT INTO FABRICANTES (nombre) VALUES('Samsung')");
		insertData("Tienda_informatica", "INSERT INTO ARTICULOS (nombre, precio, fabricante) VALUES('Mando', 50, 2)");
		getValues("Tienda_informatica","FABRICANTES");
		getValues("Tienda_informatica","ARTICULOS");
		deleteRecord("Tienda_informatica","DELETE FROM ARTICULOS WHERE nombre = 'PC';");
		
		// 2
		createDB("Empleados");
		createTable("Empleados","CREATE TABLE DEPARTAMENTOS ( id int auto_increment primary key, nombre nvarchar(100), presupuesto int);");
		createTable("Empleados", "CREATE TABLE EMPLEADOS ( DNI varchar(8) primary key, nombre nvarchar(100), apellidos nvarchar(255), departamento int references DEPARTAMENTOS (id) on delete cascade );");
		insertData("Empleados","INSERT INTO DEPARTAMENTOS (nombre, presupuesto) VALUES('Base de datos', 30000)");
		insertData("Empleados", "INSERT INTO EMPLEADOS VALUES('39931225', 'Joel', 'Fernández', 1)");
		getValues("Empleados", "DEPARTAMENTOS");
		getValues("Empleados", "EMPLEADOS");
		deleteRecord("Empleados", "DELETE FROM EMPLEADOS;");
		
		// 3
		createDB("Los_Almacenes");
		createTable("Los_Almacenes","CREATE TABLE ALMACENES ( id int auto_increment primary key, lugar nvarchar(100), capacidad int);");
		createTable("Los_Almacenes", "CREATE TABLE CAJAS ( num_referencia char(5) primary key, contenido nvarchar(100), valor int, almacen int references ALMACENES (id) on delete cascade );");
		insertData("Los_Almacenes","INSERT INTO ALMACENES (lugar, capacidad) VALUES('Barcelona', 50)");
		insertData("Los_Almacenes", "INSERT INTO CAJAS VALUES('47842', 'Lápices', 20, 1)");
		getValues("Los_Almacenes", "ALMACENES");
		getValues("Los_Almacenes", "CAJAS");
		deleteRecord("Los_Almacenes", "DELETE FROM CAJAS;");
		
		// 4
		createDB("Cine");
		createTable("Cine","CREATE TABLE PELICULAS ( id int auto_increment primary key, nombre nvarchar(100), calificacion_edad int);");
		createTable("Cine", "CREATE TABLE SALAS ( id int auto_increment primary key, nombre nvarchar(100), pelicula int references PELICULAS (id) on delete cascade );");
		insertData("Cine","INSERT INTO PELICULAS (nombre, calificacion_edad) VALUES('Harry Potter', 7)");
		insertData("Cine","INSERT INTO PELICULAS (nombre, pelicula) VALUES('Fantasia', 1)");
		getValues("Cine", "PELICULAS");
		getValues("Cine", "SALAS");
		deleteRecord("Cine", "DELETE FROM PELICULAS;");
		
		// 5
		createDB("Los_directores");
		createTable("Los_directores","CREATE TABLE DESPACHOS ( numero int primary key, capacidad int);");
		createTable("Los_directores", "CREATE TABLE DIRECTORES ( DNI varchar(8) primary key, nomApels nvarchar(255), DNIJefe varchar(8) references DIRECTORES (DNI) on delete cascade, despacho int references DESPACHOS (numero) on delete cascade );");
		insertData("Los_directores","INSERT INTO DESPACHOS VALUES(8, 30)");
		insertData("Los_directores","INSERT INTO DIRECTORES VALUES('39931225', 'Joel', '45762458, 8')");
		getValues("Los_directores", "DESPACHOS");
		getValues("Los_directores", "DIRECTORES");
		deleteRecord("Los_directores", "DELETE FROM DESPACHOS;");
		
		// 6
		createDB("Piezas_proveedores");
		createTable("Piezas_proveedores","CREATE TABLE PIEZAS ( id int auto_increment primary key, nombre nvarchar(100) );");
		createTable("Piezas_proveedores","CREATE TABLE PROVEEDORES ( id int auto_increment primary key, nombre nvarchar(100) );");
		createTable("Piezas_proveedores","CREATE TABLE SUMINISTRA ( id_pieza int references PIEZAS (id) on delete cascade, id_proveedor int references PROVEEDORES (id), precio int, PRIMARY KEY(id_pieza, id_proveedor) );");
		insertData("Piezas_proveedores","INSERT INTO PIEZAS (nombre) VALUES('Tornillo')");
		insertData("Piezas_proveedores","INSERT INTO PROVEEDORES (nombre) VALUES('TSB')");
		insertData("Piezas_proveedores","INSERT INTO SUMINISTRA VALUES(1, 1, 5)");
		getValues("Piezas_proveedores", "PIEZAS");
		getValues("Piezas_proveedores", "PROVEEDORES");
		getValues("Piezas_proveedores", "SUMINISTRA");
		deleteRecord("Piezas_proveedores", "DELETE FROM SUMINISTRA;");
		
		// 7
		createDB("Los_cientificos");
		createTable("Los_cientificos","CREATE TABLE CIENTIFICOS ( DNI varchar(8) primary key, nombre nvarchar(255) );");
		createTable("Los_cientificos","CREATE TABLE PROYECTO ( id char(4) primary key, nombre nvarchar(255), horas int );");
		createTable("Los_cientificos","CREATE TABLE ASIGNADO_A ( cientifico varchar(8) references CIENTIFICOS (DNI) on delete cascade, proyecto char(4) references PROYECTO (id), PRIMARY KEY(cientifico, proyecto) );");
		insertData("Los_cientificos","INSERT INTO CIENTIFICOS VALUES('39931225', 'Joel')");
		insertData("Los_cientificos","INSERT INTO PROYECTO VALUES('6245', 'Proyecto biologia', 30)");
		insertData("Los_cientificos","INSERT INTO ASIGNADO_A VALUES('39931225', '6245')");
		getValues("Los_cientificos", "CIENTIFICOS");
		getValues("Los_cientificos", "PROYECTO");
		getValues("Los_cientificos", "ASIGNADO_A");
		deleteRecord("Los_cientificos", "DELETE FROM ASIGNADO_A;");
		
		// 8
		createDB("Grandes_almacenes");
		createTable("Grandes_almacenes","CREATE TABLE PRODUCTOS ( id int auto_increment primary key, nombre nvarchar(100), precio int );");
		createTable("Grandes_almacenes","CREATE TABLE CAJEROS ( id int auto_increment primary key, nomApels nvarchar(255) );");
		createTable("Grandes_almacenes","CREATE TABLE MAQUINAS_REGISTRADORAS ( id int auto_increment primary key, piso int );");
		createTable("Grandes_almacenes","CREATE TABLE VENTA ( producto int references PRODUCTOS (id) on delete cascade, cajero int references CAJEROS (id) on delete cascade, maquina int references MAQUINAS_REGISTRADORAS (id) on delete cascade, PRIMARY KEY(producto, cajero, maquina) );");
		insertData("Grandes_almacenes","INSERT INTO PRODUCTOS (nombre, precio) VALUES('Ratón', 50)");
		insertData("Grandes_almacenes","INSERT INTO CAJEROS (nomApels) VALUES('Cajero 1')");
		insertData("Grandes_almacenes","INSERT INTO MAQUINAS_REGISTRADORAS (piso) VALUES(3)");
		insertData("Grandes_almacenes","INSERT INTO VENTA VALUES(1, 1, 1)");
		getValues("Grandes_almacenes", "PRODUCTOS");
		getValues("Grandes_almacenes", "CAJEROS");
		getValues("Grandes_almacenes", "MAQUINAS_REGISTRADORAS");
		getValues("Grandes_almacenes", "VENTA");
		deleteRecord("Grandes_almacenes", "DELETE FROM PRODUCTOS;");
		
		// 9
		createDB("Los_investigadores");
		createTable("Los_investigadores","CREATE TABLE FACULTAD ( id int auto_increment primary key, nombre nvarchar(100) );");
		createTable("Los_investigadores","CREATE TABLE INVESTIGADORES ( DNI varchar(8) primary key, nomApels nvarchar(255), facultad int references FACULTAD (id) );");
		createTable("Los_investigadores","CREATE TABLE EQUIPOS ( num_serie char(4) primary key, nombre nvarchar(100), facultad int references FACULTAD (id) );");
		createTable("Los_investigadores","CREATE TABLE RESERVA ( DNI_inv varchar(8) references INVESTIGADORES (DNI) on delete cascade,  num_serie char(4) references EQUIPOS (num_serie) on delete cascade, comienzo datetime, fin datetime, PRIMARY KEY(DNI_inv, num_serie) );");
		insertData("Los_investigadores","INSERT INTO FACULTAD (nombre) VALUES('Medicina')");
		insertData("Los_investigadores","INSERT INTO INVESTIGADORES VALUES('39931225', 'Joel', 1)");
		insertData("Los_investigadores","INSERT INTO EQUIPOS VALUES('5577', 'Equipo estudio 1', 1)");
		insertData("Los_investigadores","INSERT INTO RESERVA VALUES('39931225','5577', '2021-06-15', '2021-08-15')");
		getValues("Los_investigadores", "FACULTAD");
		getValues("Los_investigadores", "INVESTIGADORES");
		getValues("Los_investigadores", "EQUIPOS");
		getValues("Los_investigadores", "RESERVA");
		deleteRecord("Los_investigadores", "DELETE FROM INVESTIGADORES;");
		
		
		
		

	}
	/**
	 * Método para cerrar la conexión
	 */
	public static void closeConnection() {
		try {
			conexion.close();
			JOptionPane.showMessageDialog(null, "Se ha cerrado la conexión");
		} catch(SQLException ex) {
			System.out.println(ex.getMessage());
			System.out.println("Error cerrando la conexión");
		}
	}
	
	/**
	 * Método para crear la base de datos
	 */
	public static void createDB(String name) {
		try {
			String Query = "CREATE DATABASE " + name + ";";
			Statement st = conexion.createStatement();
			st.execute(Query);
			System.out.println("Se ha creado la base de datos " + name);
		} catch(SQLException ex) {
			System.out.println(ex.getMessage());
			System.out.println("Error creando la base de datos");
		}
	}
	
	/**
	 * Método para crear una tabla
	 * @param db
	 * @param comando
	 */
	public static void createTable(String db, String comando) {
		try {
			String Querydb = "USE " + db + ";";
			Statement stdb = conexion.createStatement();
			stdb.executeUpdate(Querydb);
			
			String Query = comando;
			Statement st = conexion.createStatement();
			st.executeUpdate(Query);
			System.out.println("Tabla creada con éxito!");
			closeConnection();
		} catch(SQLException ex) {
			System.out.println(ex.getMessage());
			System.out.println("Error creando la tabla");
		}
	}
	
	/**
	 * Método para insertar registros
	 * @param db
	 * @param comando
	 */
	public static void insertData(String db, String comando) {
		try {
			String Querydb = "USE " + db + ";";
			Statement stdb = conexion.createStatement();
			stdb.executeUpdate(Querydb);
			
			String Query = comando;
			Statement st = conexion.createStatement();
			st.executeUpdate(Query);
			System.out.println("Datos insertados con éxito!");
			closeConnection();
		} catch(SQLException ex) {
			System.out.println(ex.getMessage());
			System.out.println("Error creando la tabla");
		}
	}
	
	/**
	 * Método para mostrar registros
	 * @param db
	 * @param tabla
	 */
	public static void getValues(String db, String tabla) {
		try {
			String Querydb = "USE " + db + ";";
			Statement stdb = conexion.createStatement();
			stdb.executeUpdate(Querydb);
			
			String Query = "SELECT * FROM " + tabla;
			Statement st = conexion.createStatement();
			java.sql.ResultSet resultSet;
			resultSet = st.executeQuery(Query);
			while(resultSet.next()) {
				System.out.println(resultSet.next());
			}
			closeConnection();
		} catch(SQLException ex) {
			System.out.println(ex.getMessage());
			System.out.println("Error en la adquisición de datos");
		}
	}
	
	/**
	 * Método para eliminar datos
	 * @param db
	 * @param comando
	 */
	public static void deleteRecord(String db, String comando) {
		try {
			String Querydb = "USE " + db + ";";
			Statement stdb = conexion.createStatement();
			stdb.executeUpdate(Querydb);
			
			String Query = comando;
			Statement st = conexion.createStatement();
			st.executeUpdate(Query);
			System.out.println("Datos eliminados con éxito!");
			closeConnection();
		} catch(SQLException ex) {
			System.out.println(ex.getMessage());
			System.out.println("Error eliminando los datos");
		}
	}

}
