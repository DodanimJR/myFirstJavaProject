package net.javajunior.hibernate.model;





import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import net.javajunior.hibernate.dao.TransactionsDao;



@Entity
@Table(name="Accounts")

public class Accounts {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	@Column(name="noCuenta")
    private Integer noCuenta;
	@Column(name="Titular")
    private String Titular;
	@Column(name="Balance")
    private Integer Balance;
	@Column(name="Activa")
    private Boolean Activa;
	@Column(name="Moneda")
    private String Moneda;
	@Column(name="BalanceMaximo",nullable=true)
	private int BalanceMaximo;
	@Column(name="SeguroActivo",nullable=true)
	private Boolean SeguroActivo;
	@Column(name="TipoDeCuenta")
	private String tipoDeCuenta;

	public String getTipoDeCuenta() {
		return tipoDeCuenta;
	}

	public void setTipoDeCuenta(String tipoDeCuenta) {
		this.tipoDeCuenta = tipoDeCuenta;
	}
	private int nTransaccion = 0;
	
	
	
    
	public Accounts(Integer noCuenta, String titular, Integer balance, Boolean activa, String moneda,String tipodecuenta) {
		super();
		this.noCuenta = noCuenta;
		Titular = titular;
		Balance = balance;
		Activa = activa;
		Moneda = moneda;
		tipoDeCuenta= tipodecuenta;
	}

	public Accounts(Integer noCuenta, String titular, Integer balance, Boolean activa, String moneda,String tipodecuenta, int balanceMaximo,
			Boolean seguroActivo) {
		super();
		this.noCuenta = noCuenta;
		Titular = titular;
		Balance = balance;
		Activa = activa;
		Moneda = moneda;
		BalanceMaximo = balanceMaximo;
		SeguroActivo = seguroActivo;
		tipoDeCuenta=tipodecuenta;
	}


	
	public Accounts() {
		super();
	}
	
	
    
 
    
	public Accounts(Integer nCuenta, String titular2, Integer balance2, Boolean activa2, String moneda2) {
		// TODO Auto-generated constructor stub
	}

	public int getnTransaccion() {
		return nTransaccion;
	}

	public void setnTransaccion(int nTransaccion) {
		this.nTransaccion = nTransaccion;
	}

	public int getBalanceMaximo() {
		return BalanceMaximo;
	}

	public void setBalanceMaximo(int balanceMaximo) {
		BalanceMaximo = balanceMaximo;
	}

	public Boolean getSeguroActivo() {
		return SeguroActivo;
	}

	public void setSeguroActivo(Boolean seguroActivo) {
		SeguroActivo = seguroActivo;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getNoCuenta() {
		return noCuenta;
	}
	public void setNoCuenta(Integer noCuenta) {
		this.noCuenta = noCuenta;
	}
	public String getTitular() {
		return Titular;
	}
	public void setTitular(String titular) {
		Titular = titular;
	}
	public Integer getBalance() {
		return Balance;
	}
	public void setBalance(Integer balance) {
		Balance = balance;
	}
	public Boolean getActiva() {
		return Activa;
	}
	public void setActiva(Boolean activa) {
		Activa = activa;
	}
	public String getMoneda() {
		return Moneda;
	}
	public void setMoneda(String moneda) {
		Moneda = moneda;
	}
    public void MostrarEstadodeCuenta(){
        System.out.println("Numero de cuenta : "+ getNoCuenta());
        System.out.println("Nombre del titular : "+ getTitular());
        System.out.println("Balance actual de la cuenta : "+ getBalance());
        System.out.println("Moneda de la cuenta : "+ getMoneda());
        String estadocuenta="";
        if(getActiva()!=false){estadocuenta="Activa";}else{estadocuenta="No activa";}
        System.out.println("La cuenta esta : " + estadocuenta);
    }
    public void depositarDinero(int deposito){
    	TransactionsDao transactionsDao= new TransactionsDao();
        setnTransaccion(getnTransaccion()+1);
        int nbalance = getBalance() + deposito;
        setBalance(nbalance);
        System.out.println("Deposito realizado con exito");
        System.out.println("Su nuevo balance es : " + getBalance());
        Transactions transacciondepo= new Transactions(deposito,getNoCuenta(),"deposito");
        transactionsDao.saveTransaction(transacciondepo);




    }
    public void retirarDinero(int retiro){
    	TransactionsDao transactionsDao= new TransactionsDao();
        if(getBalance()-retiro<0){System.out.println("No se puede realizar esta transaccion :C");}else{
            setnTransaccion(getnTransaccion()+1);

            int nbalance = getBalance() - retiro;
            setBalance(nbalance);
            System.out.println("Retiro realizado con exito");
            System.out.println("Su nuevo balance es : " + getBalance());
            Transactions transaccionretiro= new Transactions(retiro,getNoCuenta(),"retiro");
            transactionsDao.saveTransaction(transaccionretiro);


        }
    }
    public void enviarTransferencia(Integer nCuentadestino, Integer Monto, String Detalle){
    	
        if(getBalance()-Monto<0){System.out.println("No se puede realizar esta transaccion :C");}else{
            setnTransaccion(getnTransaccion()+1);
            TransactionsDao transactionsDao= new TransactionsDao();

            int nbalance = getBalance() - Monto;
            setBalance(nbalance);
            System.out.println("Transferencia completada exitosamente");
            System.out.println("Su nuevo balance es : " + getBalance());
            Transactions transaccionenvtrans= new Transactions(Monto,getNoCuenta(),"Envio de transferencia",Detalle,nCuentadestino);
            transactionsDao.saveTransaction(transaccionenvtrans);



        }
    }
    public void recibirTransferencia(Integer nCuentaRemitente, Integer Monto, String Detalle){
        setnTransaccion(getnTransaccion()+1);
        TransactionsDao transactionsDao= new TransactionsDao();

        int nbalance = getBalance() + Monto;
        setBalance(nbalance);
        System.out.println("Se recibio dinero");
        System.out.println("Su nuevo balance es : " + getBalance());
        Transactions transaccionrectrans= new Transactions(Monto,getNoCuenta(),"Recibo de transferencia",Detalle,nCuentaRemitente);
        transactionsDao.saveTransaction(transaccionrectrans);


    }
    public void verHistorialTransacciones(){
    	TransactionsDao transactionsDao= new TransactionsDao();
    	List<Transactions>lista=transactionsDao.getAllTransactions();
        if(lista==null){
            System.out.println("No hay transacciones realizadas");
        }else{
            System.out.println("---------------------------");
            System.out.println("Historial de transacciones de la cuenta:  "+ getNoCuenta());
            System.out.println("A nombre de: "+getTitular());
            

            System.out.println("|*****************************************************************|");
            System.out.println("|-------------------------------------------------------------|");
            System.out.println("|Id|------|detalle|------|monto|------|tipoTransaccion|-------|");
       
         for(int x = 0;x<lista.size();x++) {
				
			if(lista.get(x).getCuentaRealizadora()==getNoCuenta()) {
				System.out.println("|-------------------------------------------------------------|");
				System.out.println("|"+lista.get(x).getId()+"------"+lista.get(x).getDetalle()+"------"+lista.get(x).getMonto()+"------"+lista.get(x).getTipodeTransaction()+"------|");
				  
			}
				         		
 
       	  
           }
         System.out.println("|*****************************************************************|");
         
         
//            for(int x=0;x<this.Transaccion.size();x++){System.out.println(this.Transaccion.get(x));}
        }
    	//List<Transactions>lista=transactionsDao.getAllTransactions();
		
		//for(int x=0;x<lista.size();x++) {
			
		//	System.out.println(lista.get(x).getId());
		//	System.out.println(lista.get(x).getCuentaRealizadora());
		//	System.out.println(lista.get(x).getDetalle());
		//	System.out.println(lista.get(x).getMonto());
			
		//}
//    	System.out.println("Working on this teacher");
//       
    }

    
    
	
    
    
    
    
    
    
}

