package finalServer;

import java.io.*;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class AdminThread extends Thread {
	Socket client;
	BufferedReader br;
	PrintWriter pw;
	AdminThread(Socket s){
		this.client=s;
		try {
			br = new BufferedReader(new InputStreamReader(client.getInputStream()));
			pw= new PrintWriter(client.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
	
	public void run(){
		String info;
		try {
			while(true){
			info=br.readLine();
			analysis(info);  //��������
			}
		} catch (IOException e) {
			          //��ͻ�������ʧ��
			e.printStackTrace(); 
			//break;  
		}
	}
	private void analysis(String info) {
		
	}

}