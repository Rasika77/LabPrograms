package Lab02;
//package Lab02;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.net.Socket;
public class RunnableWorker implements Runnable {
	protected Socket clntSock = null;
    protected String txtFrmSrvr   = null;

    public RunnableWorker(Socket clntSocket, String txtFrmSrvr) {
        this.clntSock = clntSocket;
        this.txtFrmSrvr   = txtFrmSrvr;
        
    }
    public void run() {
        try {
            InputStream inputstrm  = clntSock.getInputStream();
            OutputStream outputstrm = clntSock.getOutputStream();
            long timetaken = System.currentTimeMillis();
            outputstrm.write(("OK\n\nRunnableWorker: " + this.txtFrmSrvr + " - " +timetaken +"").getBytes());
            System.out.println("OK\n\nRunnableWorker: " + this.txtFrmSrvr + " - " +Thread.currentThread() +timetaken +"");
            outputstrm.close();
            inputstrm.close();
            System.out.println("Your request has processed in time : " + timetaken);
        } catch (IOException e) {           
            e.printStackTrace();
        }
    }
}
