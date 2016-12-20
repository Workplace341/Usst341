package client;

import java.io.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.*;
import java.nio.channels.NetworkChannel;
import java.util.ArrayList;

public class ClientChargeThread extends Thread {
	
	class patientInfo{
		String id;
		String name;
		String sex;
		String age;		
	}
	class ChargePatientInfo{
		ChargePatientInfo(int price,String name,String sex,String age,String id){
			this.price=price;
			this.name=name;
			this.sex=sex;
			this.age=age;
			this.id=id;
		}
		int price;
		String id;
		String name;
		String sex;
		String age;		
	}
	
	private static String waitString="名字\t性别\t年龄\n";
	private Socket server;
	static private PrintWriter pw;
	static private BufferedReader br;
	static public ArrayList<ChargePatientInfo> myChargePatientInfo=new ArrayList<ChargePatientInfo>();
	
	ClientChargeThread(Socket server){
		this.server=server;
		try {
			br=new BufferedReader(new InputStreamReader(server.getInputStream()));
			pw=new PrintWriter(server.getOutputStream());
		} catch (IOException e) {
			            //与服务器连接失败
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		String message;
		try {
			while(true){
				message=br.readLine();
				analysis(message);  //分析数据
				}
								
		} catch (IOException e) {
			           //连接失败
			e.printStackTrace();
		}
	}
	//message=chooseDoctor,name(doctor),department,id
	
     private void analysis(String message){
    	 String string[]=message.split(",");
    	 if(string[0].equals("noDoctor")){
    		 Charge.notice.setText("没有合适的医生");
    	 }
    	 else if(string[0].equals("chooseDoctor")){
    		 Charge.notice.setText("请去"+string[2]+"找"+string[1]+"医生");
    	 }
    	 else if((string[0].equals("true"))||(string[0].equals("false"))){
    		 addChargePatientInfo(message);
    	 }
    	 
    	 
    	// addInfo()
  
     }

	

    //message=false,20,小明,男,12,14#安神补脑片,2-name,3-...
	private void addChargePatientInfo(String message) {
		String s[]=message.split("#");
		String property[]=s[0].split(",");
		if(myChargePatientInfo.size()==0){
			Charge.cname.setText(property[2]);
			Charge.csex.setText(property[3]);
			Charge.cage.setText(property[4]);		
		} 
		ChargePatientInfo p=new ChargePatientInfo(Integer.parseInt(property[1]), property[2], property[3], property[4], property[5]);
		myChargePatientInfo.add(p);
		waitString+=(property[2]+"\t"+property[3]+"\t"+property[4]+"\n");
		Charge.wait.setText(waitString);
		Charge.pay.setText("需要支付"+property[1]+"元");
	}
	static public void Send(String department,String name,String sex,String age){
		pw.println(department+","+name+","+sex+","+age);
		pw.flush();
		System.out.println("发送给服务器："+department+","+name+","+sex+","+age);
	}
	static public void addInfo(){
		
	}

}
