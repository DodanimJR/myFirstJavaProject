package net.javajunior.hibernate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Transactions")

public class Transactions {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	@Column(name="monto")
	private int monto;
	@Column(name="cuentaRealizadora")
	private int cuentaRealizadora;
	@Column(name="tipodeTransaction")
	private String tipodeTransaction;
	@Column(name="detalle",nullable=true)
	private String detalle;
	@Column(name="noCuentaDestino",nullable=true)
	private int noCuentaDestino;
	
	

	@SuppressWarnings("null")
	public Transactions(int monto, int cuentaRealizadora, String tipodeTransaction) {
		super();
		this.monto = monto;
		this.cuentaRealizadora = cuentaRealizadora;
		this.tipodeTransaction = tipodeTransaction;
	}

	public Transactions() {
		super();
	}

	public Transactions(int monto, int cuentaRealizadora, String tipodeTransaction, String detalle,
			int noCuentaDestino) {
		super();
		this.monto = monto;
		this.cuentaRealizadora = cuentaRealizadora;
		this.tipodeTransaction = tipodeTransaction;
		this.detalle = detalle;
		this.noCuentaDestino = noCuentaDestino;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCuentaRealizadora() {
		return cuentaRealizadora;
	}

	public void setCuentaRealizadora(int cuentaRealizadora) {
		this.cuentaRealizadora = cuentaRealizadora;
	}

	public int getMonto() {
		return monto;
	}

	public void setMonto(int monto) {
		this.monto = monto;
	}



	public String getTipodeTransaction() {
		return tipodeTransaction;
	}

	public void setTipodeTransaction(String tipodeTransaction) {
		this.tipodeTransaction = tipodeTransaction;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public int getNoCuentaDestino() {
		return noCuentaDestino;
	}

	public void setNoCuentaDestino(int noCuentaDestino) {
		this.noCuentaDestino = noCuentaDestino;
	}
	
	
	
	

}
