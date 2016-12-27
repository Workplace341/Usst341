package finalServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class PresidentThread extends Thread {
	
	Socket client;
	
	BufferedReader br;
	static PrintWriter pw;
	public PresidentThread(Socket client) {
		this.client=client;
		try {
			br = new BufferedReader(new InputStreamReader(client.getInputStream()));
			pw= new PrintWriter(client.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
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
		// TODO Auto-generated method stub
		
	}

	
	public void sendMessageToPresident(){
		
	}
}
