package net.javajunior.hibernate;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import net.javajunior.hibernate.dao.AccountsDao;
import net.javajunior.hibernate.model.Accounts;






public class App {
	
    public static void MainMenu(){
        System.out.println("-------------------------------------------------------------");
        System.out.println("Bienvenido al sistema bancario");
        System.out.println("Indique que transaccion desea realizar");
        System.out.println("1. Depositar");
        System.out.println("2. Retirar");
        System.out.println("3. Enviar Transferencia");
        System.out.println("4. Salir");
    }
    public static Accounts getCuentaValida(ArrayList<Accounts> cuentas,Integer Ncuenta){
        Accounts result = null;
        for(int x=0;x<cuentas.size();x++){
            if(cuentas.get(x).getNoCuenta()==Ncuenta){

                result = cuentas.get(x);
            }

        }
        return result;
    }
	
	public static void main(String[] args) {
		
		AccountsDao cuentasDao= new AccountsDao();
		Scanner sc = new Scanner(System.in);

		
        try {
        	// Instanciar todas las cuentas de banco para subirlas a la base de datos por el error raro de sessionfactory*
        	ArrayList<Accounts> bank = new ArrayList<Accounts>();
			Accounts CuentaC1 = new Accounts (1, "JUAN", 500, true, "Dolares","Cuenta Corriente", 1000, true);
			cuentasDao.saveAccount(CuentaC1);

			Accounts  CuentaC2 = new Accounts (2, "Pablo", 500, true, "Dolares","Cuenta Corriente", 1000, true);
			cuentasDao.saveAccount(CuentaC2);

			Accounts  CuentaC3 = new Accounts (3, "Roberto", 500, true, "Dolares","Cuenta Corriente", 1000, true);
			cuentasDao.saveAccount(CuentaC3);

			Accounts  CuentaA1 = new Accounts (4, "Pepe", 2500, true, "Dolares","Cuenta Ahorros");
			cuentasDao.saveAccount(CuentaA1);

			Accounts  CuentaA2 = new Accounts (5, "Tilin", 2500, true, "Dolares","Cuenta Ahorros");
			cuentasDao.saveAccount(CuentaA2);

			Accounts  CuentaA3 = new Accounts (6, "Toledo", 2500, true, "Dolares","Cuenta Ahorros");
			cuentasDao.saveAccount(CuentaA3);

			
			
		// Crear consulta la base de datos por las cuentas existentes
			List<Accounts>lista=cuentasDao.getAllAccounts();
		// extraer las cuentas existentes y guardarlas en un arreglo	
			for(int x=0;x<lista.size();x++) {

				bank.add(lista.get(x));
			}
			
			//transacciones obligatorias de las cuentas
			 	CuentaC1.depositarDinero(25);
	            CuentaC2.depositarDinero(25);
	            CuentaC3.depositarDinero(25);
	            CuentaC1.retirarDinero(50);
	            CuentaC2.retirarDinero(50);
	            CuentaC3.retirarDinero(50);
	            CuentaA2.depositarDinero(5000);
	            CuentaA3.depositarDinero(5000);

	            CuentaC1.enviarTransferencia(2,20,"pago-servicios profesionales");
	            CuentaC2.recibirTransferencia(1,20,"pago-servicios profesionales");

	            CuentaC2.enviarTransferencia(3,20,"pago-salida a comer");
	            CuentaC3.recibirTransferencia(2,20,"pago-pago-salida a comer");

	            CuentaC3.enviarTransferencia(4,20,"pago mudanza");
	            CuentaA1.recibirTransferencia(3,20,"pago mudanza");

	            CuentaA1.enviarTransferencia(4,30,"pago corte de cabello");
	            CuentaA2.recibirTransferencia(5,30,"pago corte de cabello");

	            CuentaC1.enviarTransferencia(5,30,"pago apuesta");
	            CuentaC2.recibirTransferencia(6,30,"pago apuesta");

	            CuentaC1.enviarTransferencia(6,20,"pago - compra de cable");
	            CuentaC2.recibirTransferencia(1,20,"pago - compra de cable");
	            
	            for(int x = 0; x<bank.size();x++) {
	            	bank.get(x).verHistorialTransacciones();
	            }
	            
	            
	            
	            
	    //codigo del primer examen arreglado para funcionar con el actual        
	          while(true){
	            MainMenu();
	            String mmchoice = sc.nextLine();
	            Boolean exitflag = false;

	            switch(mmchoice){
	                case "4":
	                    exitflag = true;
	                    System.out.println("Exiting");
	                    break;
	                case "1":
	                    System.out.println("Cuentas disponibles:  ");
	                    for(int x=0;x<bank.size();x++){
	                        System.out.println(x+"---"+bank.get(x).getNoCuenta()+"---"+bank.get(x).getTitular().toString()+"- $"+bank.get(x).getBalance());
	                    }
	                    try{
	                        System.out.println("Ahora inidique cual es su cuenta");
	                        String tcuentaindex = sc.nextLine();
	                        switch(tcuentaindex){case "000":String a=null;Integer trashv=Integer.valueOf(a);trashv=trashv++;break;}
	                        Integer cuentaindex=Integer.valueOf(tcuentaindex);
	                        Accounts taccount=getCuentaValida(bank, bank.get(cuentaindex).getNoCuenta());
	                        if(taccount!=null){
	                            System.out.println("Bienvenido "+ bank.get(cuentaindex).getTitular());
	                            System.out.println("Indique el monto que desea depositar");
	                            String tcantdeposito=sc.nextLine();
                             switch(tcantdeposito){case "000":String a=null;Integer trashv=Integer.valueOf(a);trashv=trashv++;break;}
	                            Integer cantdeposito=Integer.valueOf(tcantdeposito);
	                            System.out.println("Indique el detalle del deposito");
	                            bank.get(cuentaindex).depositarDinero(cantdeposito);
	                        }

	                        break;

	                    }catch(java.lang.NumberFormatException er){
	                        System.out.println("Regresando al menu...");
	                        break;
	                    }catch(java.lang.IndexOutOfBoundsException e){
	                        System.out.println("Indice no valido, intente de nuevo mas tarde");
	                        break;
	                    }



	                case "2":
	                    System.out.println("Cuentas disponibles:  ");
	                    for(int x=0;x<bank.size();x++){
	                        System.out.println(x+"---"+bank.get(x).getNoCuenta()+"---"+bank.get(x).getTitular().toString()+"- $"+bank.get(x).getBalance());
	                    }
	                    try{
	                        System.out.println("Ahora inidique cual es su cuenta");
	                        String tcuentaindex = sc.nextLine();
	                        switch(tcuentaindex){case "000":String a=null;Integer trashv=Integer.valueOf(a);trashv=trashv++;break;}
	                        Integer cuentaindex=Integer.valueOf(tcuentaindex);
	                        Accounts taccount=getCuentaValida(bank, bank.get(cuentaindex).getNoCuenta());
	                        if(taccount!=null){
                            System.out.println("Bienvenido "+ bank.get(cuentaindex).getTitular());
	                            System.out.println("Indique el monto que desea retirar");
	                            String tcantretir=sc.nextLine();
	                            switch(tcantretir){case "000":String a=null;Integer trashv=Integer.valueOf(a);trashv=trashv++;break;}
                            Integer cantretir=Integer.valueOf(tcantretir);
	                            bank.get(cuentaindex).retirarDinero(cantretir);
	                        }

	                        break;
	
	                    }catch(java.lang.NumberFormatException er){
	                        System.out.println("Regresando al menu...");
	                        break;
	                    }catch(java.lang.IndexOutOfBoundsException e){
	                        System.out.println("Indice no valido, intente de nuevo mas tarde");
	                        break;
	                    }
	                case "3":
	
	                    System.out.println("Cuentas disponibles:  ");
	                    Boolean localexitflag = false;
	                    for(int x=0;x<bank.size();x++){
	                        System.out.println(x+"---"+bank.get(x).getNoCuenta()+"---"+bank.get(x).getTitular().toString()+"- $"+bank.get(x).getBalance());
	                    }
	
	                    try{
	                        if(localexitflag==true){break;}
	                        System.out.println("Ahora inidique cual es su cuenta");
	                        String tcuentaindex = sc.nextLine();
	                        switch(tcuentaindex){case "000":String a=null;Integer trashv=Integer.valueOf(a);trashv=trashv++;break;}
	                        Integer cuentaindex=Integer.valueOf(tcuentaindex);
	                        Accounts taccount=getCuentaValida(bank, bank.get(cuentaindex).getNoCuenta());
	                        if(taccount!=null){
	                            System.out.println("Bienvenido "+ bank.get(cuentaindex).getTitular());
	                            System.out.println("A cual cuenta desea realizar la transferencia?");
	                            ArrayList<Accounts> bankwithoutcuenta = new ArrayList<Accounts>();
	                            for(int x=0;x<bank.size();x++){
	                                if(x==cuentaindex){continue;}else{bankwithoutcuenta.add(bank.get(x));}
	                            }
	                            for(int x=0;x<bankwithoutcuenta.size();x++){
	                                System.out.println(x+"---"+bankwithoutcuenta.get(x).getNoCuenta()+"---"+bankwithoutcuenta.get(x).getTitular().toString()+"- $"+bankwithoutcuenta.get(x).getBalance());
	                            }
	                            String tenvchoice=sc.nextLine();
	                            switch(tenvchoice){case "000":String a=null;Integer trashv=Integer.valueOf(a);trashv=trashv++;break;}
	                            Integer envchoice=Integer.valueOf(tenvchoice);
	                            Accounts receiveraccount=getCuentaValida(bankwithoutcuenta, bankwithoutcuenta.get(envchoice).getNoCuenta());
	                            if(receiveraccount!=null){
	                                if(localexitflag==true){break;}
	                                System.out.println("Indique el monto que desea transferir a "+bankwithoutcuenta.get(envchoice).getTitular());
	                                String tcanttrans=sc.nextLine();
	                                switch(tcanttrans){case "000":String a=null;Integer trashv=Integer.valueOf(a);trashv=trashv++;break;}
	                                Integer canttrans=Integer.valueOf(tcanttrans);
	                                System.out.println("Indique el detalle de la transferencia");
	                                String detail = sc.nextLine();
	                                switch(detail){case "000":String a=null;Integer trashv=Integer.valueOf(a);trashv=trashv++;break;}
	                                bank.get(cuentaindex).enviarTransferencia(envchoice,canttrans, detail);
	                                if(bank.get(cuentaindex).getBalance()-canttrans>=0){
	                                    bankwithoutcuenta.get(envchoice).recibirTransferencia(bank.get(cuentaindex).getNoCuenta(), canttrans, detail);
	                                }
	                            }

	                        }
	                        break;

	                    }catch(java.lang.NumberFormatException er){
	                        System.out.println("Regresando al menu...");
	                        break;
	                    }catch(java.lang.IndexOutOfBoundsException e){
	                        System.out.println("Indice no valido, intente de nuevo mas tarde");
	                        break;
	                    }


	                case "prueba":
	                    for(int x=0;x<bank.size();x++){bank.get(x).verHistorialTransacciones();}
	            }
	            if(exitflag==true){break;}

	        }
	         
	         sc.close();
	            
	            
	            
	            
	            
	            
	            


		} catch (java.lang.IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		
		
		

		
		
		
		
		
		
		
		
	}
	
	
	
	
}
