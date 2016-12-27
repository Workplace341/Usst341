package com.example.androidclient;

import java.io.*;
import java.io.PrintWriter;
import java.net.*;
import java.net.UnknownHostException;

public class ClientAndroid extends Thread {

	final String IP="192.168.1.107";//"10.20.179.1";//"10.40.140.14";//"192.168.1.107";
	final int PORT=12000;
	static public PrintWriter pw;
	private BufferedReader br;
	public ClientAndroid() {
		
	}
	@Override
	public void run() {
        try {
			
			Socket socket=new Socket(IP,PORT);
		    pw= new PrintWriter(socket.getOutputStream());
		    br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		    pw.println("predict,predict");
		    pw.flush();
		    MainActivity.connect=true;
		    
		} catch (UnknownHostException e) {
			//MainActivity.notice.setText("���ӷ�����ʧ��");
			e.printStackTrace();
		} catch (IOException e) {
		//	MainActivity.notice.setText("���ӷ�����ʧ��");
			e.printStackTrace();
		}
		
       
        
		String message;
		try {
			while(true){
				message=br.readLine();
				//System.out.println("�ֿ�ӷ������յ�����Ϣ��"+message);
				analysis(message);  //��������
				}
								
		} catch (IOException e) {
			           //����ʧ��
			e.printStackTrace();
		}
	}
	
	private void analysis(String message) {
		new Thread(){
			@Override
			public void run() {
//				MainActivity.notice.setText("sad");
			}
			
		}.start();
		if(message.equals("OK")){
			
			
		}
		else{
			//MainActivity.notice.setText("�˲�������û��ҽ����ԤԼʧ��");
		}
		
	}
	
	
	static public void sendInfo(String info){
		pw.println("predict,"+info);
		pw.flush();
	}
}
