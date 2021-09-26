import java.io.*;
import java.net.*;

public class miServidor {

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
			p_Datos = new String();
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

	public Boolean doOperation(String str, int checkResult){
		String[] elements = str.split(" ");
		Boolean isCorrect = false;

		

		return isCorrect;
	}


    public static void main(String[] args){
        String str = "";
        String port = "";
        Boolean result = false;
        int check = 0; 
        try {

            miServidor server = new miServidor();

            if(args.length < 1){

                System.out.println("Debe indicar el puerto de escucha del servidor");
				System.out.println("$./Servidor puerto_servidor");
				System.exit (1);

            }

            port = args[0];
            ServerSocket serverSocket = new ServerSocket(Integer.parseInt(port));
            System.out.println("Listening port " + port);

            for(;;){
                Socket clientSocket = serverSocket.accept();
                System.out.println("Waiting for requests");

				while(check != -1){
					str = server.leeSocket(clientSocket, str);
					result = server.doOperation(str, check);
					server.escribeSocket(clientSocket, str);

				}

                
            }

        } catch(Exception e){

        }
    }
    
}
