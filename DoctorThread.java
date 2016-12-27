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
		        Server.sendMessageToShow();           //����Ϣ��������
		        Server.sendMessageToPresident();    //���͸�����Ϣ��Ժ��
		        break;
			}
			analysis(info);  //��������
			}
		} catch (IOException e) {
			         doctorExit();
			         Server.sendMessageToShow();           //����Ϣ��������
			         Server.sendMessageToPresident();      //���͸�����Ϣ��Ժ��
			         e.printStackTrace(); 
		}
		doctorExit();
        Server.sendMessageToShow();           //����Ϣ��������
        Server.sendMessageToPresident();      //���͸�����Ϣ��Ժ��
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
	         Server.sendMessageToShow();           //����Ϣ��������
	         Server.sendMessageToPresident();      //���͸�����Ϣ��Ժ��
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
				System.out.println("������Ϣ���շѣ�"+message);
			}
			
		} catch (IOException e) {
			//���շ�Ա����ʧ��

			e.printStackTrace();
		}
		
		try {
			if(Server.store!=null){
				PrintWriter pw= new PrintWriter(Server.store.getOutputStream());
				pw.println(message);
				pw.flush();
				System.out.println("������Ϣ��ҩʦ��"+message);
			}
			
		} catch (IOException e) {
			    //��ҩʦ����ʧ��
			e.printStackTrace();
		}
		
		for(Server.DoctorInfo di:Server.onlineDoctor){
			if(di.account.equals(myAccount)){
				di.waitCount--;
				di.patientQueue.remove(patientInfo[3]);
				System.out.println("��������˺ţ�"+myAccount+" �ȴ�������"+di.waitCount);
			}
		}
		
		Server.sendMessageToShow(); //����Ϣ��������
		Server.sendMessageToPresident();      //���͸�����Ϣ��Ժ��
	    //�Ѳ��˴�ҽ���������Ƴ�
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
			System.out.println("���͸�ҽ����ҩƷ��Ϣ"+message);
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
