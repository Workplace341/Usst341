package client;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientAdminThread extends Thread {
	private Socket server;
	private PrintWriter pw;
    private BufferedReader br;
	ClientAdminThread(Socket server){
		this.server=server;	    
	}

}
