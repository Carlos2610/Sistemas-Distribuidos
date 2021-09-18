import java.io.*;
import java.net.*;

public class miCliente {

    

    public static void main(String[] args){
        miCliente myClient = new miCliente();
        
        
        if(args.length < 2){
            System.out.println ("Debe indicar la direccion del servidor y el puerto");
			System.out.println ("$./Cliente nombre_servidor puerto_servidor");
			System.exit(-1);
        }else{
            int i = 0;
            String host = args[0];
            String port = args[1];

            while(i==0){
                myClient.menu(host,port);
            }
        }
    }

    public void menu(String host, String port){
        int option = 0;

        try{
            while(option!=0 && option!=3){
                System.out.println("[1] Operacion lógica (suma,resta,mult,div)");
                System.out.println("[2] Cuentacaracteres");
                System.out.println("[3] Salir");

                InputStreamReader reader = new InputStreamReader(System.in);
                BufferedReader br = new BufferedReader(reader);

                option = Integer.parseInt(br.readLine());
            }

            if(option == 1){
                //pedir operacion
            }else if(option == 2){
                //contarcaracteres
            }else{
                System.exit(0);
            }

        }catch(Exception e){
            System.out.println("ERROR: "+ e.getMessage());
        }
        
    }

    public void pedirOperacionLógica(String str_host, String str_port){

        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        boolean pedirOp = true;
        boolean resultIsOk = false;

        try{

            Socket sockCli = new Socket(str_host, Integer.parseInt(str_port));

            while(pedirOp){

                resultIsOk = doOperation();

                if(resultIsOk){
                    System.out.println("Operacion correcta");
                }else{
                    System.out.println("Operacion incorrecta");

                }
                

            }

        }catch(Exception e){

            System.out.println("ERROR: "+ e.getMessage());
        }

    }

    public boolean doOperation(){
        boolean isCorrect = false;
        String operation = "";
        int leftOp, rightOp, result;
        

        InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader (isr);

        try{
            System.out.println("Introduce primer operando");
            leftOp = Integer.parseInt(br.readLine());

            System.out.println("Introduce operador");
            operation = br.readLine();

            System.out.println("Introduce segundo operando");
            rightOp = Integer.parseInt(br.readLine());

            System.out.println("Introduce resultado");
            result = Integer.parseInt(br.readLine());




            if(operation.compareTo("+")==0){
                if((leftOp + rightOp) == result ){
                    isCorrect = true;
                }
            }else if(operation.compareTo("*")==0){
                if((leftOp * rightOp) == result ){
                    isCorrect = true;
                }
            }else if(operation.compareTo("/")==0){
                if((leftOp / rightOp) == result ){
                    isCorrect = true;
                }    
            }else if(operation.compareTo("-")==0){
                if((leftOp + rightOp) == result ){
                    isCorrect = true;
                }
            }else{
                System.out.println("Entrada incorrecta");
            }    
        }catch(Exception e){
            System.out.println("ERROR: "+e.getMessage());
        }
        
        return isCorrect;
    }
    
    
    


    

}
