package finalServer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.*;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Set;

public class DoctorThread extends Thread {
	
	String myAccount;
	Socket client;
	BufferedReader br;

	PrintWriter pw;
	static HashMap<String,Server.MedicineInfo> medicineData=new HashMap<String,Server.MedicineInfo>();
	
	DoctorThread(String account,Socket s){
		this.client=s;	
		this.myAccount=account;
		sendInitMedicineInfo();
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
			System.out.println("xinxi"+info);
			if(info==null){
				doctorExit();
				System.out.println("Mac");
		        Server.sendMessageToShow();           //发信息给滚动屏
		        Server.sendMessageToPresident();    //发送更新信息给院长
		        break;
			}
			analysis(info);  //分析数据
			}
		} catch (IOException e) {
			         doctorExit();
			         Server.sendMessageToShow();           //发信息给滚动屏
			         Server.sendMessageToPresident();      //发送更新信息给院长
			         e.printStackTrace(); 
		}
		doctorExit();
        Server.sendMessageToShow();           //发信息给滚动屏
        Server.sendMessageToPresident();      //发送更新信息给院长
	}
	      

		//(doctorName)
	//info=name(patient),sex,age,id#medicine,count-medicine,count....
	private void analysis(String info) {
		int price=0;
		String string[];
		String patientInfo[] = null;
		String medicineInfo[] = null;
		try{
			string=info.split("#");
			patientInfo=string[0].split(",");
			medicineInfo=string[1].split("-");
		}catch (Exception e) {	
			 doctorExit();
	         Server.sendMessageToShow();           //发信息给滚动屏
	         Server.sendMessageToPresident();      //发送更新信息给院长
	         return;
		}
		
//		SQLOperate.doctorReadMedicineData();
		for(String s:medicineInfo){
			String str[]=s.split(",");
			price+=medicineData.get(str[0]).price*Integer.parseInt(str[1]);
			
		}

		//message=boolean,price,name(patient),sex,age,id#medicine,count-medicine,count....
		String i=price+"";
		String message="false,"+i+","+info;
		try {
			if(Server.charge!=null){
				PrintWriter pw= new PrintWriter(Server.charge.getOutputStream());
				//bw = new BufferedWriter(new OutputStreamWriter(Server.charge.getOutputStream()));
				pw.println(message);
				pw.flush();
				System.out.println("发送信息给收费："+message);
			}
			
		} catch (IOException e) {
			//与收费员连接失败

			e.printStackTrace();
		}
		
		try {
			if(Server.store!=null){
				PrintWriter pw= new PrintWriter(Server.store.getOutputStream());
				pw.println(message);
				pw.flush();
				System.out.println("发送信息给药师："+message);
			}
			
		} catch (IOException e) {
			    //与药师连接失败
			e.printStackTrace();
		}
		
		for(Server.DoctorInfo di:Server.onlineDoctor){
			if(di.account.equals(myAccount)){
				di.waitCount--;
				di.patientQueue.remove(patientInfo[3]);
				System.out.println("减了这个账号："+myAccount+" 等待人数："+di.waitCount);
			}
		}
		
		Server.sendMessageToShow(); //发信息给滚动屏
		Server.sendMessageToPresident();      //发送更新信息给院长
	    //把病人从医生队列里移除
	}

	
	
	//message=medicine,name-price#name-price....
	public void sendInitMedicineInfo(){
		SQLOperate.doctorReadMedicineData();
		String message="medicine,";
		try {
			pw=new PrintWriter(client.getOutputStream());
			Set<String> set = medicineData.keySet();   
			for (String s:set) { 
				message+=(s+"-"+medicineData.get(s).price+"#");	 
			}  
			System.out.println("发送给医生的药品信息"+message);
			pw.println(message);  
			pw.flush();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	 private void doctorExit() {
		 for(Server.DoctorInfo d:Server.onlineDoctor){
			 if(d.account.equals(myAccount)){
					Server.onlineDoctor.remove(d);
					break;
			 }			
		 }
	 }
			
}
