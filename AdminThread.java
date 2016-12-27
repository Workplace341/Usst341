package finalServer;

import java.io.*;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.locks.AbstractQueuedLongSynchronizer;

public class AdminThread extends Thread {
	Socket client;
	BufferedReader br;
	static public PrintWriter pw;
	AdminThread(Socket s){
		this.client=s;
		try {
			br = new BufferedReader(new InputStreamReader(client.getInputStream()));
			pw = new PrintWriter(client.getOutputStream());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		sendAllInfoToAdmin();  //���͸�����Ϣ������Ա
	
	}
	
	public void run(){
		String info;
		try {
			while(true){
			info=br.readLine();
			System.out.println("�������ӹ���Ա�յ�����Ϣ:"+info);
			analysis(info);  //��������
			}
		} catch (IOException e) {
			          //��ͻ�������ʧ��
			e.printStackTrace(); 
			//break;  
		}
	}
	//info=operate-name,newName,account,price
	private void analysis(String info) {
		if(info.equals("")){
			return ;
		}
		if(info==null){
			return;
		}
			
		String string[]=info.split(",");
		switch(string[0]){
		case "changeMedicineInfo":SQLOperate.changeMedicineInfo(string[1], string[2], string[3],string[4]);/*Server.sendMedicineInfoToDoctor();*/break;
		case "addMedicineInfo":SQLOperate.addMedicineInfo(string[1], string[2], string[3]);/*Server.sendMedicineInfoToDoctor();*/break;
		case "deleteMedicineInfo":SQLOperate.deleteMedicineInfo(string[1]);/*Server.sendMedicineInfoToDoctor();*/break;
		case "changeDoctorInfo":SQLOperate.changeDoctorInfo(string[1], string[2], string[3], string[4], string[5], string[6]);break;
		case "addDoctorInfo":SQLOperate.addDoctorInfo(string[1], string[2], string[3], string[4], string[5], string[6]);break;
		case "deleteDoctorInfo":SQLOperate.deleteDoctorInfo(string[1]);break;
	    }
	
	}
	
	static void sendDoctorInfo(){
		String message="account-";
		message+=SQLOperate.sendDoctorInfoToAdmin();
		pw.println(message);
		System.out.println("����ҽ����Ϣ������Ա��"+message);
		pw.flush();
	}
	
	static void sendMedicneInfo(){
		String message="medicine-";
		message+=SQLOperate.sendMedicineInfoToAdmin();
		pw.println(message);
		System.out.println("����ҩƷ��Ϣ������Ա��"+message);
		pw.flush();
	}
	
	static void sendAllInfoToAdmin(){
		sendMedicneInfo();//�ڹ��캯���﷢ҩƷ��Ϣ������Ա�ͻ���
		sendDoctorInfo();// ҽ����Ϣ
	}
}
