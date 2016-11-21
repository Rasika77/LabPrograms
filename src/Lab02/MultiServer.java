package Lab02;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiServer implements Runnable{
	protected int          serverPortVal   = 8080;
    protected ServerSocket serverSocketVal = null;
    protected boolean      hasStopped    = false;
    protected Thread       movingThread= null;

    public MultiServer(int port){
        this.serverPortVal = port;
    }

    public void run(){
        synchronized(this){
            this.movingThread = Thread.currentThread();
            System.out.println("Thread running" +this.movingThread);
        }
        opnSvrSocket();
        while(! hasStopped()){
            Socket clntSock = null;
            try {
            	
            		 
                clntSock = this.serverSocketVal.accept();
                new Thread(
                        new RunnableWorker(clntSock, "This is a multithreaded Server")
                    ).start();
            	
            } catch (IOException e) {
                if(hasStopped()) {
                	
                    System.out.println("Server has Stopped.check--inside catch block") ;
                    e.printStackTrace();
                    System.out.println("exception");
                    return;
                }
                throw new RuntimeException(
                    "Client cannot be connected - Error", e);
            }
           
        }
        System.out.println("Server has Stopped.") ;
    }
    private synchronized boolean hasStopped() {
        return this.hasStopped;
    }
    public synchronized void stop(){
        this.hasStopped = true;
        try {
            this.serverSocketVal.close();
        } catch (IOException e) {
            throw new RuntimeException("Server can not be closed -", e);
        }
    }
    private void opnSvrSocket() {
        try {
            this.serverSocketVal = new ServerSocket(this.serverPortVal);
        } catch (IOException e) {
            throw new RuntimeException("Not able to open the port 8080", e);
        }
    }
}
