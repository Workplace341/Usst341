package finalServer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class StoreThread extends Thread {
	Socket client;
	BufferedReader br;

	PrintWriter pw;
	StoreThread(Socket s){
		this.client=s;
		try {
			br = new BufferedReader(new InputStreamReader(client.getInputStream()));
			
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
			analysis(info);  //分析数据
			}
		} catch (IOException e) {
			          //与客户端连接失败
			e.printStackTrace(); 
			//break;  
		}	}
	
	//info=boolean,id#...+...
	private void analysis(String info) {
		String str[]=info.split(",");
		if(str[0].equals("true")){
			ChargeThread.allPatient.remove(str[1]);
		}
	
	}

}
