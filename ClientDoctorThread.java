package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import javax.print.Doc;

public class ClientDoctorThread extends Thread {
	class patientInfo{
		patientInfo(String name,String sex,String age,String id){
			this.name=name;
			this.sex=sex;
			this.age=age;
			this.id=id;
		}
		String id;
		String name;
		String sex;
		String age;		
	}
	class medicineInfo{
		medicineInfo(String name,int price){
			this.name=name;
			//this.quickName=quickName;
			this.price=price;
		}
		String name;
		//String quickName;
		int price;
	}
	
	//info=name(patient),sex,age,id#medicine,count-medicine,count....
	static public String sendMedicineInfo="";
	private Socket server;
	private static PrintWriter pw;
    private BufferedReader br;
    private static String waitString="名字\t性别\t年龄\t病人号码\n";
    static public  ArrayList<patientInfo> myWaitPatient=new ArrayList<patientInfo>();
    static public HashMap<String,medicineInfo> myMedicineInfo=new HashMap<String,medicineInfo>();
    
	ClientDoctorThread(Socket server){
		this.server=server;	 
		try {
			br=new BufferedReader(new InputStreamReader(server.getInputStream()));
			pw=new PrintWriter(server.getOutputStream());
		} catch (IOException e) {
			            //与服务器连接失败
			e.printStackTrace();
		}
		Doctor.wait.setText(waitString);
	}
	@Override
	public void run() {
		String message;
		try {
			while(true){
				message=br.readLine();
				analysis(message);  //分析数据
				System.out.println("从服务器收到病人的信息："+message);   
			}
								
		} catch (IOException e) {
			           //连接失败
			e.printStackTrace();
		}
	}
	//message=name(patient),sex,age//,id
	private void analysis(String message) {
		//first(message);
		String string[]=message.split(",");
		if(string[0].equals("medicine")){  //如果是第一次收到信息，先初始化客户端药品信息
			initMedicineInfo(message);
			return ;
		}
		if(myWaitPatient.size()==0){
			Doctor.name.setText(string[0]);
			Doctor.sex.setText(string[1]);
			Doctor.age.setText(string[2]);	
			Doctor.ID.setText(string[3]);
		} 
		patientInfo p=new patientInfo(string[0],string[1],string[2],string[3]);
		myWaitPatient.add(p);
		waitString+=(string[0]+"\t"+string[1]+"\t"+string[2]+"\t"+string[3]+"\n");
		Doctor.wait.setText(waitString);
		
	}
	
	//message=medicine,name+price#name+price....
	private void initMedicineInfo(String message) {
		String string[]=message.split(",");
		String nextString[]=string[1].split("#"); //nextString[0]=name+count
		
		//myMedicineInfo.clear();
		myMedicineInfo=new HashMap<String,medicineInfo>();
		for(String str:nextString){
			System.out.println(str);
			//if(str==null)continue;
			String divide[]=str.split("-");
			medicineInfo mi=new medicineInfo(divide[0], Integer.parseInt(divide[1]));
			myMedicineInfo.put(divide[0],mi);
			
		}
	}
		

    static public void sendMedicineInfo(String str){
    	pw.println(str);
		pw.flush();
		System.out.println("发送给服务器："+str);
    }
		
	static public void updatePaitenInfo(){
		waitString="名字\t性别\t年龄\t病人号码\n";
		for(patientInfo mp:myWaitPatient){
			waitString+=(mp.name+"\t"+mp.sex+"\t"+mp.age+"\t"+mp.id+"\n");		
		}
		Doctor.wait.setText(waitString);
		Doctor.currentString="";
		sendMedicineInfo="";
		Doctor.content.setText("");
	}
	
	static public void nextFunctioin(){
		if(myWaitPatient.size()==0){
			return;
		}
		patientInfo p=myWaitPatient.get(0);
		myWaitPatient.remove(0);
		myWaitPatient.add(p);
		updatePaitenInfo();
		
		p=myWaitPatient.get(0);
		Doctor.name.setText(p.name);
		Doctor.sex.setText(p.sex);
		Doctor.age.setText(p.age);
		Doctor.ID.setText(p.id);
	}

}
