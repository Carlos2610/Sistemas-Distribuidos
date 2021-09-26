import java.lang.Exception;
import java.net.Socket;
import java.io.*;


public class miHiloServidor extends Thread{

    private Socket clientSocket;

    public miHiloServidor(Socket client){
        this.clientSocket = client;
    }

    public String leeSocket (Socket sock, String input)
	{
		try
		{
			InputStream aux = sock.getInputStream();
			DataInputStream flujo = new DataInputStream( aux );
			input = new String();
			input = flujo.readUTF();
		}
		catch (Exception e)
		{
			System.out.println("Error: " + e.toString());
		}

      return input;
	}

	/*
	* Escribe dato en el socket cliente. Devuelve numero de bytes escritos,
	* o -1 si hay error.
	*/
	public void escribeSocket (Socket sock, String output)
	{
		try
		{
			OutputStream aux = sock.getOutputStream();
			DataOutputStream flujo= new DataOutputStream( aux );
			flujo.writeUTF(output);      
		}
		catch (Exception e)
		{
			System.out.println("Error: " + e.toString());
		}
		return;
	}

    public Boolean doOperation(String str, int checkOk){
        String[] inputData = str.split(" ");
        Boolean result = false;

        System.out.println("Server: Operation "+ inputData[1]);
        

        if(inputData.length != 1){
            int operation = 0;
            System.out.println("Server: Left Operand = " + inputData[0]);
            System.out.println("Server: Right Operand = " + inputData[2]);

            if(inputData[1].compareTo("+")==0){
                operation = Integer.parseInt(inputData[0]) + Integer.parseInt(inputData[2]);
            }else if(inputData[1].compareTo("-")==0){
                operation = Integer.parseInt(inputData[0]) - Integer.parseInt(inputData[2]);
            }else if(inputData[1].compareTo("*")==0){
                operation = Integer.parseInt(inputData[0]) * Integer.parseInt(inputData[2]);
            }else if(inputData[1].compareTo("/")==0){
                operation = Integer.parseInt(inputData[0]) / Integer.parseInt(inputData[2]);
            }else{
                System.out.println("INCORRECT OPERATION INPUT");
            }

            System.out.println("Server: Expected result = " + operation);
            System.out.println("Server: Input result = " + inputData[3]);
            System.out.println("--------------------------------------");

            if(inputData[1].compareTo("+")==0){
                if((Integer.parseInt(inputData[0]) + Integer.parseInt(inputData[2])) == Integer.parseInt(inputData[3]) ){
                    result = true;
                }
            }else if(inputData[1].compareTo("*")==0){
                if(((Integer.parseInt(inputData[0]) * Integer.parseInt(inputData[2])) == Integer.parseInt(inputData[3]))){
                    result = true;
                }
            }else if(inputData[1].compareTo("/")==0){
                if(((Integer.parseInt(inputData[0]) / Integer.parseInt(inputData[2])) == Integer.parseInt(inputData[3]))){
                    result = true;
                }    
            }else if(inputData[1].compareTo("-")==0){
                if(((Integer.parseInt(inputData[0]) - Integer.parseInt(inputData[2])) == Integer.parseInt(inputData[3]))){
                    result = true;
                }
            }else{
                checkOk = -1;
            }
        }

        return result;
    }

    public void run(){
        Boolean resultado= false;
        int checkResult = 0;
		String str="";
		
        try {
			while (checkResult != -1)
			{
				str = this.leeSocket (clientSocket, str);
				/*
				* Se escribe en pantalla la informacion que se ha recibido del
				* cliente
				*/
				resultado = this.doOperation(str,checkResult);
				if(resultado){
                    str+= "(Server check: CORRECT RESULT)";
                }else{
                    str+="(Server check: CORRECT RESULT)";
                }
				this.escribeSocket (clientSocket, str);						
			}
			clientSocket.close();
			//System.exit(0); No se debe poner esta sentencia, porque en ese caso el primer cliente que cierra rompe el socket 
			//				  y desconecta a todos				
        }
        catch (Exception e) {
          System.out.println("Error: " + e.toString());
        }
      }
}
    

