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
                System.out.println("[1] Operacion lógica");
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
    
    
    


    

}
