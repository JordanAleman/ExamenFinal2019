package control;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import modelo.Cuenta;
import modelo.Movimiento;
import modelo.Vehiculo;

public class ManipulacionDatos {

	private Connection con = null;

	public ManipulacionDatos() {
		BaseDatos conexion = new BaseDatos("localhost", "banco", "Jordan", "Jordan");
		con = conexion.getConnection();
	}
	
// Ejercicio 1 -----------------------------------------------------------------------------------------------------------------------------------------	
	public void insertarVehiculosPorFichero(String rutaFichero, String delimitador) {
		try {
			// Al usar "InputStreamReader" también tenemos la opción de leer un fichero igual que "FileReader". Lo bueno de este que usamos ahora
			// es que podemos pasar por parámetro por medio de "FileInputStream" una ruta de fichero y su codificación.
			// Al ponerle codificación "utf-8" se puede conseguir leer tildes y 'ñ', entre otros caracteres.
			BufferedReader fichero = new BufferedReader(new InputStreamReader(new FileInputStream(rutaFichero), "utf-8"));
			String registro;
			PreparedStatement stmt = null;
			int contador = 0;
			
			try {
				while((registro = fichero.readLine()) != null){
					// Romper la cadena registro
					String[] campos = registro.split(delimitador);
					
					stmt = con.prepareStatement("insert into vehiculo (codigo, matricula, fecha, estado, precio, nif) values (?,?,?,?,?,?)");

					stmt.setInt(1, Integer.parseInt(campos[0]));		
					stmt.setString(2, campos[1]);
					stmt.setString(3, campos[2]);
					stmt.setString(4, campos[3]);
					stmt.setFloat(5, Float.parseFloat( campos[4]));
					stmt.setString(6, campos[5]);


					stmt.executeUpdate();
					
					contador++;
					
					if (contador % 2 == 0) {
						System.out.println("Insertando...");

					}	
					
				}
				con.close();
				System.out.println("Tododos los elementos de vehículos han sido insertados correctamente");

			} catch (SQLException ex) {
				// En el caso de que se produzca algún error al ejecutar la sentencia SQL, nos aparecerá por pantalla de esta misma consola el siguiente mensaje de error.
				System.out.println("Error al insertar un dato en la base de datos");

			} catch (NullPointerException ex) {
				System.out.println("Fuera de rango");
			}

			fichero.close();		
			
		} catch (FileNotFoundException e) {
			System.out.println("Fichero no encontrado.");
		} catch (IOException e) {
			System.out.println("IO Excepcion");
		}	
			
	}
	
	public HashMap <String, ArrayList<Vehiculo>> mapaVehiculos (){
		HashMap <String, ArrayList<Vehiculo>> vehiculosPorNif = new HashMap <String, ArrayList<Vehiculo>>();
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			
			stmt = con.prepareStatement("select * from vehiculos;");
			rs = stmt.executeQuery();

			while (rs.next()) { // devuelve una linea de la consulta, es decir, una fila de la tabla
				Vehiculo vehiculo = new Vehiculo(rs.getInt("codigo"), rs.getString("matricula"), rs.getString("fecha"), rs.getString("estado").charAt(0),
						rs.getFloat("precio"), rs.getString("nif"));
	
				if (!vehiculosPorNif.containsKey(vehiculo.getNifPropietario())) {
					ArrayList<Vehiculo> listaVehiculos = new ArrayList<Vehiculo>();
					listaVehiculos.add(vehiculo);
					vehiculosPorNif.put(vehiculo.getNifPropietario(), listaVehiculos);
				} else {
					vehiculosPorNif.get(vehiculo.getNifPropietario()).add(vehiculo);
				}
			}

			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("Te quedas con las ganas");
		} catch (NullPointerException e) {
			System.out.println("Va a ser que no");
		}
		
		return vehiculosPorNif;
		
	}
	
	public void muestraListaVehiculosPorPropietarios(HashMap <String, ArrayList<Vehiculo>> mapaVehiculos) {
		float acumuladorSubTotal = 0.0f;
		float acumuladorTotal = 0.0f;
		int contador = 1;
		
		Set<String> clavesMapa = mapaVehiculos.keySet();
		
		System.out.println("Listado vehículos por cada propietario");
		for (String claves : clavesMapa) {
			System.out.println("\nPropietario " + contador);
			contador++;
			for (int i = 0; i < mapaVehiculos.get(claves).size(); i++) {
				System.out.println("Nif: [" + mapaVehiculos.get(claves).get(i).getNifPropietario() + "] \tMatrícula: [" 
			+ mapaVehiculos.get(claves).get(i).getMatricula() + "] \tPrecio: [" + mapaVehiculos.get(claves).get(i).getPrecio() + "€]");
				acumuladorSubTotal += mapaVehiculos.get(claves).get(i).getPrecio();
				
				if (i == mapaVehiculos.get(claves).size() - 1) {
					System.out.println("Subtotal de [" + mapaVehiculos.get(claves).get(i).getNifPropietario() + "] --> \t" + acumuladorSubTotal + "€");
					acumuladorTotal += acumuladorSubTotal;
				}
			}
		}
		
		System.out.println("\nTotal -->\t" + acumuladorTotal + "€");	
	}
	
// ---------------------------------------------------------------------------------------------------------------------------------------------------------------	
	
// Ejercicio 2 ---------------------------------------------------------------------------------------------------------------------------------------------------
	
	public HashMap <Integer, Cuenta> mapaCuentas (){
		HashMap <Integer, Cuenta> mapaCuentas = new HashMap <Integer, Cuenta>();
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			
			stmt = con.prepareStatement("select * from cuentas;");
			rs = stmt.executeQuery();

			while (rs.next()) { // devuelve una linea de la consulta, es decir, una fila de la tabla
				Cuenta cuenta = new Cuenta(rs.getInt("id"), rs.getString("descripcion"), rs.getFloat("saldo"));
	
				mapaCuentas.put(cuenta.getId(), cuenta);
			}

			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("Te quedas con las ganas");
		} catch (NullPointerException e) {
			System.out.println("Va a ser que no");
		}
		
		return mapaCuentas;
		
	}
	
	public HashMap <Cuenta, ArrayList<Movimiento>> mapaMovimientos (HashMap <Integer, Cuenta> mapaCuentas){
		HashMap <Cuenta, ArrayList<Movimiento>> cuentasConMovs = new HashMap <Cuenta, ArrayList<Movimiento>>();
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {

			
			stmt = con.prepareStatement("select * from movs;");

			rs = stmt.executeQuery();
			while (rs.next()) { // devuelve una linea de la consulta, es decir, una fila de la tabla
				Movimiento movimiento = new Movimiento(rs.getInt("id"), rs.getInt("idCta"), rs.getString("fecha"), 
						rs.getString("descripcion"), rs.getFloat("importe"));
	
				// Recorreremos nuestro mapa de cuentas para comprobar si ya está una cuenta incluida en nuestro no mapa de movimientos
				Set<Integer> clavesMapa = mapaCuentas.keySet();
				
				for (Integer claves: clavesMapa) {
					// Si la id del cuenta que hay en el movimiento es la misma id que de la cuenta del mapaCuenta que se está evaluando, entrará entonces en el if
					if (movimiento.getIdCta() == mapaCuentas.get(claves).getId()) {
						
						// Si el mapa de cuentasConMovs no contiene como clave la cuenta que estamos evaluando, lo añade como clave y como valor una nueva lista de movimientos
						if (!cuentasConMovs.containsKey(mapaCuentas.get(claves))) {
							ArrayList<Movimiento> listaMovimiento = new ArrayList<Movimiento>();
							listaMovimiento.add(movimiento);
							cuentasConMovs.put(mapaCuentas.get(claves), listaMovimiento);
						} else {
							cuentasConMovs.get(mapaCuentas.get(claves)).add(movimiento);
						}
						
					}
				}
				
			}

			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("Te quedas con las ganas");
		} catch (NullPointerException e) {
			System.out.println("Va a ser que no");
		}
		
		return cuentasConMovs;
		
	}
	
	
	
}
