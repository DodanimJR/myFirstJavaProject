package net.javajunior.hibernate.model;

public class SavingAccount extends Accounts{

    private Integer Intereses;
    public SavingAccount(Integer NCuenta,String titular,Integer balance,Boolean activa,String moneda,Integer intereses){
        super(NCuenta,titular,balance,activa,moneda);
        Intereses = intereses;

    }
	public Integer getIntereses() {
		return Intereses;
	}
	public void setIntereses(Integer intereses) {
		Intereses = intereses;
	}
	   @Override public void retirarDinero(int retiro){
	        System.out.println("No se pueden realizar retiros a esta cuenta :)");

	    }


  
}

