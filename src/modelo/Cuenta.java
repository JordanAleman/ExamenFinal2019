package modelo;

public class Cuenta {
	private int id;
	private String cuenta;
	private float saldo;
	public Cuenta(int id, String cuenta, float saldo) {
		super();
		this.id = id;
		this.cuenta = cuenta;
		this.saldo = saldo;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCuenta() {
		return cuenta;
	}
	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}
	public float getSaldo() {
		return saldo;
	}
	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}
	
	
}
