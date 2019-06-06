package modelo;

public class Vehiculo {
	private int codigo;
	private String nifPropietario;
	private String matricula;
	private char estado;
	private float precio;
	private String fechaMatricula;
	
	public Vehiculo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Vehiculo(int codigo, String nifPropietario, String matricula, char estado, float precio,
			String fechaMatricula) {
		super();
		this.codigo = codigo;
		this.nifPropietario = nifPropietario;
		this.matricula = matricula;
		this.estado = estado;
		this.precio = precio;
		this.fechaMatricula = fechaMatricula;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNifPropietario() {
		return nifPropietario;
	}

	public void setNifPropietario(String nifPropietario) {
		this.nifPropietario = nifPropietario;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
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

	public String getFechaMatricula() {
		return fechaMatricula;
	}

	public void setFechaMatricula(String fechaMatricula) {
		this.fechaMatricula = fechaMatricula;
	}
	
	
	
}