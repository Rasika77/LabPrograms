package Lab02;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientSock {
	public static void main(String[] args) throws IOException {
        
        if (args.length != 2) {
            System.err.println("Usage: java EchoClient <host name> <port number>");
            System.exit(1);
        }
 
        String hostName = args[0];
        int portNumber = Integer.parseInt(args[1]);
 
        try {
            Socket echos = new Socket(hostName, portNumber);
            PrintWriter pwout = new PrintWriter(echos.getOutputStream(), true);
            BufferedReader brin = new BufferedReader(new InputStreamReader(echos.getInputStream()));
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        
            String serverIn;
            while ((serverIn = stdIn.readLine()) != null) {
                pwout.println(serverIn);
                
                String serverRes = brin.readLine();
                System.out.println("echo: " + serverRes);
                if(serverRes.equalsIgnoreCase("Client connection is closed")){
                	System.out.println("Out of client loop");                	
                	break;
                }
                echos.close();
            }
            System.out.println("Client process ended.");
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                hostName);
            System.exit(1);
        } 
    }
}
