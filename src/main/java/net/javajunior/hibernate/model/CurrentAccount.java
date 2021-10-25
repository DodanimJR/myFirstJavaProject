package net.javajunior.hibernate.model;

import net.javajunior.hibernate.dao.TransactionsDao;

public class CurrentAccount extends Accounts{
    private Integer BalanceMaximo;
    private Boolean tieneSeguro;

    public Boolean getTieneSeguro() {
        return tieneSeguro;
    }
    public int getBalanceMaximo() {
        return BalanceMaximo;
    }

    public CurrentAccount(Integer NCuenta, String titular, Integer balance, Boolean activa, String moneda, Integer balanceMaximo, Boolean Tieneseguro){
        super(NCuenta,titular,balance,activa,moneda);
        BalanceMaximo  = balanceMaximo;
        tieneSeguro = Tieneseguro;


    }
    
    @Override public void depositarDinero(int deposito){
        if(deposito+getBalance()>BalanceMaximo){
            System.out.println("No es posible realizar esta transaccion");
        }else{
        	TransactionsDao transactionsDao= new TransactionsDao();
            setnTransaccion(getnTransaccion()+1);
            int nbalance = getBalance() + deposito;
            setBalance(nbalance);
            System.out.println("Deposito realizado con exito");
            System.out.println("Su nuevo balance es : " + getBalance());
            Transactions transacciondepo= new Transactions(deposito,getNoCuenta(),"deposito");
            transactionsDao.saveTransaction(transacciondepo);
            




        }
    }
    @Override public void recibirTransferencia(Integer nCuentaRemitente, Integer Monto, String Detalle){
        if(Monto+getBalance()>BalanceMaximo){
            System.out.println("No es posible realizar esta transaccion");
        }else{
        	TransactionsDao transactionsDao= new TransactionsDao();
            setnTransaccion(getnTransaccion()+1);
            int nbalance = getBalance() + Monto;
            setBalance(nbalance);
            System.out.println("Se recibio dinero");
            System.out.println("Su nuevo balance es : " + getBalance());
            Transactions transaccionrectrans= new Transactions(Monto,getNoCuenta(),"Recibo de transferencia",Detalle,nCuentaRemitente);
            transactionsDao.saveTransaction(transaccionrectrans);


        }
    }
}
