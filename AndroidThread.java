package finalServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class AndroidThread extends Thread {
	Socket client;
	BufferedReader br;
	PrintWriter pw;
	AndroidThread(Socket s){
		this.client=s;
		try {
			br = new BufferedReader(new InputStreamReader(client.getInputStream(),"UTF-8"));
			pw= new PrintWriter(client.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//System.out.println(info);
			
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
		String str[]=info.split(",");
		if(str[0].equals("predict")){
			System.out.println(info);
			pw.println("OK");
			pw.flush();
			
			try {
				PrintWriter cpw= new PrintWriter(Server.charge.getOutputStream());
				cpw.println(info);
				cpw.flush();
				System.out.println("�����������շѶ�ԤԼ��Ϣ��"+info);
				
				
			} catch (IOException e) {
				                        //���շѶ�����ʧ��
				e.printStackTrace();
			}
			
		}		
			
	}

}
