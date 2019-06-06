package modelo;

public class Vehiculo {
	private int codigo;
	private String matricula;
	private String fechaMatricula;
	private char estado;
	private float precio;
	private String nifPropietario;

	
	public Vehiculo() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Vehiculo(int codigo, String matricula, String fechaMatricula, char estado, float precio,
			String nifPropietario) {
		super();
		this.codigo = codigo;
		this.matricula = matricula;
		this.fechaMatricula = fechaMatricula;
		this.estado = estado;
		this.precio = precio;
		this.nifPropietario = nifPropietario;
	}


	public int getCodigo() {
		return codigo;
	}


	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}


	public String getMatricula() {
		return matricula;
	}


	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}


	public String getFechaMatricula() {
		return fechaMatricula;
	}


	public void setFechaMatricula(String fechaMatricula) {
		this.fechaMatricula = fechaMatricula;
	}


	public char getEstado() {
		return estado;
	}


	public void setEstado(char estado) {
		this.estado = estado;
	}


	public float getPrecio() {
		return precio;
	}


	public void setPrecio(float precio) {
		this.precio = precio;
	}


	public String getNifPropietario() {
		return nifPropietario;
	}


	public void setNifPropietario(String nifPropietario) {
		this.nifPropietario = nifPropietario;
	}
	
}

	