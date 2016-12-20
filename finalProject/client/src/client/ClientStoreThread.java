package client;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientStoreThread extends Thread {
	private Socket server;
	private PrintWriter pw;
    private BufferedReader br;
	ClientStoreThread(Socket server){
		this.server=server;	    
	}

}
