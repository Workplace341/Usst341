package client;

import java.io.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.*;
import java.nio.channels.NetworkChannel;
import java.util.ArrayList;

import javax.annotation.PreDestroy;

import client.ClientStoreThread.StorePatientInfo;

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
	class PredictPatientInfo{
		public PredictPatientInfo(String n,String s,String a,String d) {
			name=n;
			sex=s;
			age=a;
			department=d;
		}
		String name;
		String sex;
		String age;
		String department;
	}
	
	public static String waitString="����\t�Ա�\t����\t���˺���\n";
	public static String predictWaitString="����\t�Ա�\t����\tԤԼ����\n";
	private Socket server;
	static private PrintWriter pw;
	static private BufferedReader br;
	static public ArrayList<ChargePatientInfo> myChargePatientInfo=new ArrayList<ChargePatientInfo>();
	static public ArrayList<PredictPatientInfo> myPredictPatinetInfo=new ArrayList<PredictPatientInfo>();
	
	ClientChargeThread(Socket server){
		this.server=server;
		try {
			br=new BufferedReader(new InputStreamReader(server.getInputStream()));
			pw=new PrintWriter(server.getOutputStream());
		} catch (IOException e) {
			            //�����������ʧ��
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		String message;
		try {
			while(true){
				message=br.readLine();
				analysis(message);  //��������
				}
								
		} catch (IOException e) {
			           //����ʧ��
			e.printStackTrace();
		}
	}
	//message=chooseDoctor,name(doctor),department,id
	
     private void analysis(String message){
    	 String string[]=message.split(",");
    	 if(string[0].equals("noDoctor")){
    		 Charge.notice.setText("û�к��ʵ�ҽ��");
    	 }
    	 else if(string[0].equals("chooseDoctor")){
    		 String noti="��ȥ"+string[2]+"��"+string[1]+"ҽ��";
    		 Charge.notice.setText(noti);
    		 Charge.pnotice.setText(noti);
    	 }
    	 else if((string[0].equals("true"))||(string[0].equals("false"))){
    		 addChargePatientInfo(message);
    	 }
    	 else if(string[0].equals("predict")){
    		 addPredictPatientInfo(message);
    	 }
    	 
    	// addInfo()
  
     }

	
    //message=predict,name,sex,age,depart
    private void addPredictPatientInfo(String message) {
		String property[]=message.split(",");
		if(myPredictPatinetInfo.size()==0){
			Charge.pname.setText(property[1]);
			Charge.psex.setText(property[2]);
			Charge.page.setText(property[3]);
			Charge.pdepart.setText(property[4]);
		}
		PredictPatientInfo p=new PredictPatientInfo(property[1],property[2] , property[3], property[4]);
		myPredictPatinetInfo.add(p);
		predictWaitString+=(property[1]+"\t"+property[2]+"\t"+property[3]+"\t"+property[4]+"\n");
		Charge.pwait.setText(predictWaitString);
		
	}
	//info=false,20,С��,��,12,id#������Ƭ,2-name,3-...
	private void addChargePatientInfo(String message) {
		String s[]=message.split("#");
		String property[]=s[0].split(",");
		if(myChargePatientInfo.size()==0){
			Charge.cname.setText(property[2]);
			Charge.csex.setText(property[3]);
			Charge.cage.setText(property[4]);
			Charge.ID.setText(property[5]);
			Charge.pay.setText("��Ҫ֧��"+property[1]+"Ԫ");
		} 
		ChargePatientInfo p=new ChargePatientInfo(Integer.parseInt(property[1]), property[2], property[3], property[4], property[5]);
		myChargePatientInfo.add(p);
		waitString+=(property[2]+"\t"+property[3]+"\t"+property[4]+"\t"+property[5]+"\n");
		Charge.wait.setText(waitString);
		
	}
	
	static public void Send(String department,String name,String sex,String age){
		pw.println(department+","+name+","+sex+","+age);
		pw.flush();
		System.out.println("���͸���������"+department+","+name+","+sex+","+age);
	}
	
	//info=true,20,С��,��,12,id#������Ƭ,2-name,3-...
	static public void sendPayInfo(){
		String s="true,"+myChargePatientInfo.get(0).price+","+myChargePatientInfo.get(0).name+","+myChargePatientInfo.get(0).sex+","
                +myChargePatientInfo.get(0).age+","+myChargePatientInfo.get(0).id+"#";
		pw.println(s);
		pw.flush();
		System.out.println("�շѶ˷��͸�����������Ϣ��"+s);
	}
	
	static public void addInfo(){
		
	}
	
	static public void sendPredictInfo(){
		PredictPatientInfo p=myPredictPatinetInfo.get(0);
		Send(p.department, p.name, p.sex, p.age);
		
		myPredictPatinetInfo.remove(0);
	}
	
	static public void updatePredictPatienInfo(){
		predictWaitString="����\t�Ա�\t����\tԤԼ����\n";
		for(PredictPatientInfo pm:myPredictPatinetInfo){
			predictWaitString+=(pm.name+"\t"+pm.sex+"\t"+pm.age+"\t"+pm.department+"\n");
		}
		Charge.pwait.setText(predictWaitString);
		Charge.pnotice.setText("�Һ�֪ͨ");
	}
	
	static public void updatePaitenInfo(){
		waitString="����\t�Ա�\t����\t���˺���\n";
		for(ChargePatientInfo mp:myChargePatientInfo){
			waitString+=(mp.name+"\t"+mp.sex+"\t"+mp.age+"\t"+mp.id+"\n");		
		}
		Charge.wait.setText(waitString);
		//Store.currentString="";
		//sendMedicineInfo="";
		//Doctor.content.setText("");
	}
	
	static public void nextFunction(){
		if(myChargePatientInfo.size()==0){
			return;
		}
		ChargePatientInfo p=myChargePatientInfo.get(0);
		myChargePatientInfo.remove(0);
		myChargePatientInfo.add(p);
		updatePaitenInfo();
		
		p=myChargePatientInfo.get(0);
		Charge.cname.setText(p.name);
		Charge.csex.setText(p.sex);
		Charge.cage.setText(p.age);
		Charge.ID.setText(p.id);
		Charge.pay.setText("��Ҫ֧��"+p.price+"Ԫ");
	}
	
	static public void nextPredictFunction(){
		if(myPredictPatinetInfo.size()==0){
			return;
		}
		PredictPatientInfo p=myPredictPatinetInfo.get(0);
		myPredictPatinetInfo.remove(0);
		myPredictPatinetInfo.add(p);
		updatePredictPatienInfo();
		
		p=myPredictPatinetInfo.get(0);
		Charge.pname.setText(p.name);
		Charge.psex.setText(p.sex);
		Charge.page.setText(p.age);
		Charge.pdepart.setText(p.department);
	}
	
	static public void cancelPredictFunction(){
		if(myPredictPatinetInfo.size()==0){
			return;
		}
		myPredictPatinetInfo.remove(0);
		updatePredictPatienInfo();
		
		if(myPredictPatinetInfo.size()!=0){
			PredictPatientInfo p=myPredictPatinetInfo.get(0);
			Charge.pname.setText(p.name);
			Charge.psex.setText(p.sex);
			Charge.page.setText(p.age);
			Charge.pdepart.setText(p.department);
		}
		else{
			Charge.pname.setText("");
			Charge.psex.setText("");
			Charge.page.setText("");
			Charge.pdepart.setText("");
		}
		
	}

}
