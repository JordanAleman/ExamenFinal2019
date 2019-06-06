package ejecutor;

import java.util.ArrayList;
import java.util.HashMap;

import control.ManipulacionDatos;
import modelo.Cuenta;
import modelo.Movimiento;
import modelo.Vehiculo;

public class Main {
	public static void main(String[] args) {

		
		// Este comando es para insertar los datos en la base de datos. Una vez insertado se comenta para que no de problemas
		new ManipulacionDatos().insertarVehiculosPorFichero("ficheros/vehiculos.csv","&&");

		HashMap <String, ArrayList<Vehiculo>> vehiculosPorNif = new ManipulacionDatos().mapaVehiculos();
		new ManipulacionDatos().muestraListaVehiculosPorPropietarios(vehiculosPorNif);
		
		HashMap <Integer, Cuenta> mapaCuentas = new ManipulacionDatos().mapaCuentas();
		HashMap <Cuenta, ArrayList<Movimiento>> cuentasConMovs = new ManipulacionDatos().mapaMovimientos(mapaCuentas);
		new ManipulacionDatos().muestraMovimientos(mapaCuentas, cuentasConMovs);
	}
	
}
