package ejecutor;

import java.util.ArrayList;
import java.util.HashMap;

import control.ManipulacionDatos;
import modelo.Vehiculo;

public class Main {
	public static void main(String[] args) {

		
//		new ManipulacionDatos().insertarVehiculosPorFichero("ficheros/vehiculos.csv","&&");

		HashMap <String, ArrayList<Vehiculo>> vehiculosPorNif = new ManipulacionDatos().mapaVehiculos();
		new ManipulacionDatos().muestraListaVehiculosPorPropietarios(vehiculosPorNif);
	}
	
}
