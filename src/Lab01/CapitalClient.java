package Lab01;
import java.net.*;
import java.io.*;
public class CapitalClient {
	 public static void main(String[] args) {

		    int port = 8000;
		    InputStreamReader is = new InputStreamReader(System.in);
		    BufferedReader b1 = new BufferedReader(is);
		    String input=null;  
		try{
		    System.out.println("Enter the string you want to see the caps of:");
		    input = b1.readLine();
		}
		catch(Exception e1){
			
			e1.printStackTrace();
		}
		    for (int i = 0; i < args.length; i++) {
		      try {
		         URL url = new URL(args[i]);
		         if (url.getPort() != -1) port = url.getPort();
		         if (!(url.getProtocol().equalsIgnoreCase("http"))) {
		          System.err.println("Sorry is that http?");
		          continue;
		         }
		         Socket s = new Socket(url.getHost(), port);
		         OutputStream os = s.getOutputStream();
		         PrintWriter pw = new PrintWriter(os, false);
		         pw.print("GET " + url.getFile()+"/?message="+input + " HTTP/1.0\r\n"  );
		         pw.print("Accept: text/plain, text/html, text/*\r\n");
		         pw.print("\r\n");
		         pw.flush();
		         InputStream in = s.getInputStream();
		         InputStreamReader isr = new InputStreamReader(in);
		         BufferedReader br = new BufferedReader(isr);
		         int r;
		         while ((r = br.read()) != -1) {
		           System.out.print((char) r);
		           
		         }
		         s.close();
		        
		      }
		      
		 catch (MalformedURLException ex) {
		        System.err.println(args[i] + "URL is not valid");
		      }
		      catch (IOException ex) {
		        System.err.println(ex);
		      }

		    }

		  }
}
