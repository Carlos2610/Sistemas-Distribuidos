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
        int option = -1;

        try{
            while(option!=2){
                System.out.println("[1] Operacion lógica (suma,resta,mult,div)");
                System.out.println("[2] Salir");
                

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

                resultIsOk = doOperation(sockCli);

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

    public boolean doOperation(Socket server_Sock){
        boolean isCorrect = false;
        String operation = "";
        int leftOp, rightOp, result;
        String output;
        

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


            output = leftOp + operation + rightOp + " = " + result;
            escribeSocket(server_Sock, output);

            output = "";

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
    
    /*
	* Lee datos del socket. Supone que se le pasa un buffer con hueco 
	*	suficiente para los datos. Devuelve el numero de bytes leidos o
	* 0 si se cierra fichero o -1 si hay error.
	*/
	public String leeSocket (Socket p_sk, String p_Datos)
	{
		try
		{
			InputStream aux = p_sk.getInputStream();
			DataInputStream flujo = new DataInputStream( aux );
			p_Datos = flujo.readUTF();
		}
		catch (Exception e)
		{
			System.out.println("Error: " + e.toString());
		}
      return p_Datos;
	}

	/*
	* Escribe dato en el socket cliente. Devuelve numero de bytes escritos,
	* o -1 si hay error.
	*/
	public void escribeSocket (Socket p_sk, String p_Datos)
	{
		try
		{
			OutputStream aux = p_sk.getOutputStream();
			DataOutputStream flujo= new DataOutputStream( aux );
			flujo.writeUTF(p_Datos);      
		}
		catch (Exception e)
		{
			System.out.println("Error: " + e.toString());
		}
		return;
	}
	
    


    

}
