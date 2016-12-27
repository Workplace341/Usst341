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
		
		sendAllInfoToPresident(); //发送信息给院长
		
		
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
			          //与客户端连接失败
			e.printStackTrace(); 
			//break;  
		}
	}
	private void analysis(String info) {
		// TODO Auto-generated method stub	
	}

	
	public static void sendDoctorInfoToPresident(){
		String message="doctor-";
		message+=SQLOperate.sendDoctorInfoToPresident();
		pw.println(message);
		System.out.println("发送医生信息给院长："+message);
		pw.flush();
	}
	
	static public void sendMedicineInfoToPresident(){
		String message="medicine-";
		message+=SQLOperate.sendMedicineInfoToAdmin();
		pw.println(message);
		System.out.println("发送药品信息给院长："+message);
		pw.flush();
	}
	
	static public void sendDepartmentInfoToPresident(){
		
		int internalCount=0;
		int surgeryCount=0;
		int paediatricsCount=0;
		for(Server.DoctorInfo d:Server.onlineDoctor){
			if(d.department.equals("internal")){
				internalCount+=d.waitCount;
			}
			else if(d.department.equals("surgery")){
				surgeryCount+=d.waitCount;
			}
			else if(d.department.equals("paediatrics")){
				paediatricsCount+=d.waitCount;
			}
		}
		
		String str[]=SQLOperate.sendDepartmentInfoToPresident().split("=");
			
		String message1="internal-"+internalCount+",";
		message1+=str[0];
		pw.println(message1);
		System.out.println("发送内科信息给院长："+message1);
		pw.flush();
		String message2="surgery-"+surgeryCount+",";
		message2+=str[1];
		pw.println(message2);
		System.out.println("发送外科信息给院长："+message2);
		pw.flush();
		String message3="paediatrics-"+paediatricsCount+",";
		message3+=str[2];
		pw.println(message3);
		System.out.println("发送儿科信息给院长："+message3);
		pw.flush();
	}
	
	static public void sendAllInfoToPresident(){
		sendMedicineInfoToPresident();
		sendDoctorInfoToPresident();	
		sendDepartmentInfoToPresident();
	}
	
}
