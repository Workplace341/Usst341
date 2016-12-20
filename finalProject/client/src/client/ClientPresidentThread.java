package client;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientPresidentThread extends Thread {
	private Socket server;
	private PrintWriter pw;
    private BufferedReader br;
	ClientPresidentThread(Socket server){
		this.server=server;	    
	}

}
