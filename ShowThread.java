package finalServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ShowThread extends Thread {
	
	Socket client;
	
	BufferedReader br;
	 PrintWriter pw;
	public ShowThread(Socket client) {
		this.client=client;
		try {
			br = new BufferedReader(new InputStreamReader(client.getInputStream()));
			pw= new PrintWriter(client.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sendMessageToShow();
	}
	@Override
	public void run() {
		String info;
		try {
			while(true){
			info=br.readLine();
			analysis(info);  //分析数据
			}
		} catch (IOException e) {
			
			Server.myShow.remove(this);
			e.printStackTrace();
		}
	}
	private void analysis(String info) {
		// TODO Auto-generated method stub
		
	}

	
	 public void sendMessageToShow(){
		String surgery="surgery-";
		String internal="internal-";
		String paediatrics="paediatrics-";
		for(Server.DoctorInfo d:Server.onlineDoctor){
			if(d.department.equals("surgery")){
				surgery+=(d.name+",");
				surgery+=(d.waitCount+"=");
			}
			else if(d.department.equals("internal")){
				internal+=(d.name+",");
				internal+=(d.waitCount+"=");
			}
			else if(d.department.equals("paediatrics")){
				paediatrics+=(d.name+",");
				paediatrics+=(d.waitCount+"=");
			}
		}
		
		pw.println(surgery+"#"+internal+"#"+paediatrics);
		System.out.println("发送信息给滚动大屏："+surgery+"#"+internal+"#"+paediatrics);
		pw.flush();
	}
}