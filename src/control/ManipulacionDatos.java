package control;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
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
			BufferedReader fichero = new BufferedReader(new FileReader(rutaFichero));
			String registro;
			PreparedStatement stmt = null;
			int contador = 0;
			
			try {
				while((registro = fichero.readLine()) != null){
					// Romper la cadena registro
					String[] campos = registro.split(delimitador);
					
					stmt = con.prepareStatement("insert into vehiculos (codigo, matricula, fecha, estado, precio, nif) values (?,?,?,?,?,?)");

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
	
	public void muestraMovimientos(HashMap <Integer, Cuenta> mapaCuentas, HashMap <Cuenta, ArrayList<Movimiento>> mapaMovimientos) {
		float acumulador;
		
		Set<Integer> clavesMapaCuenta = mapaCuentas.keySet();
		Set<Cuenta> clavesMapaMovs = mapaMovimientos.keySet();
		System.out.println("\nListado de movimientos de cuentas:");
		
		// En este primer for recorreremos el mapa de cuentas para obtener la información sobre su descripión y saldo inicial.
		for (Integer clavesCuenta : clavesMapaCuenta) {
			System.out.println("-------------------------------------------------------------");
			System.out.println("\nCuenta: [" + mapaCuentas.get(clavesCuenta).getCuenta() + "] \nSaldo Inicial: [" + mapaCuentas.get(clavesCuenta).getSaldo() + "]");
			
			
			for (Cuenta clavesMovs : clavesMapaMovs) {
				
				// Recorremos el mapa de movimientos y evaluaremos los movimientos de la cuenta que estamos evaluando actualmente
				if (clavesCuenta == clavesMovs.getId()) {
					System.out.println("Movimientos:");
					acumulador = mapaCuentas.get(clavesCuenta).getSaldo();
					for (int i = 0; i < mapaMovimientos.get(clavesMovs).size(); i++) {
						System.out.println("\t\t" + mapaMovimientos.get(clavesMovs).get(i).getImporte());
						acumulador += mapaMovimientos.get(clavesMovs).get(i).getImporte();
						
					}
					System.out.println("Saldo final: \t[" + acumulador + "€]");
				}
				
			}
				
				
		}

	
	}
	
}
