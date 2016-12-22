package client;

import java.io.BufferedReader;
import java.io.*;
import java.io.PrintWriter;
import java.net.Socket;
import java.security.PrivilegedActionException;
import java.util.ArrayList;

import javax.print.Doc;
import javax.swing.JLabel;





public class ClientStoreThread extends Thread {
	
	class StorePatientInfo{
		StorePatientInfo(boolean i,int price,String name,String sex,String age,String id){
			this.price=price;
			this.name=name;
			this.sex=sex;
			this.age=age;
			this.id=id;
			this.isPay=i;
		}
		boolean isPay;
		int price;
		String id;
		String name;
		String sex;
		String age;	
		public ArrayList<Medicine> medicineQueue=new ArrayList<Medicine>();
	}
	class Medicine{
		Medicine(String n,int c){
			medicineName=n;
			count=c;
		}
		String medicineName;
		int count;
	}
	

	static public String currentId;
	private static String waitString="名字\t性别\t年龄\t病人号码\n";
    static public  ArrayList<StorePatientInfo> myWaitPatient=new ArrayList<StorePatientInfo>();
	private Socket server;
	static public PrintWriter pw;
    private BufferedReader br;
	ClientStoreThread(Socket server){
		this.server=server;	    
		
		try {
			br=new BufferedReader(new InputStreamReader(server.getInputStream()));
			pw=new PrintWriter(server.getOutputStream());
		} catch (IOException e) {
			            //与服务器连接失败
			e.printStackTrace();
		}
		Store.wait.setText(waitString);
	}
	
	@Override
	public void run() {
		String message;
		try {
			while(true){
				message=br.readLine();
				System.out.println("仓库从服务器收到的信息："+message);
				analysis(message);  //分析数据
				}
								
		} catch (IOException e) {
			           //连接失败
			e.printStackTrace();
		}
	}

	//message=true|false,price,name,sex,age,id#medicine,count-medicine,count-....
	private void analysis(String message) {
		String string[]=message.split(",");
		if(string[0].equals("false")){
			analysisFalse(message);
		}
		else if(string[0].equals("true")){
			analysisiTrue(message);
		}
	}

	private void analysisiTrue(String message) {
		String s[]=message.split("#");
		String property[]=s[0].split(",");
		for(StorePatientInfo sp:myWaitPatient){
			if(sp.id.equals(property[5])){
				sp.isPay=true;
				if(myWaitPatient.indexOf(sp)==0){
					Store.mark.setText("此病人已付款");
				}
			}
		}
	}
	//message=true|false,price,name,sex,age,id#medicine,count-medicine,count-....
	private void analysisFalse(String message) {
		String s[]=message.split("#"); //s[0]=true|false,price,name,sex,age,id s[1]=medicine,count-medicine,count-....
		String property[]=s[0].split(",");
		
		StorePatientInfo p=new StorePatientInfo(false, Integer.parseInt(property[1]), property[2], property[3], property[4], property[5]);
		String medicineInfo[]=s[1].split("-");
		for(String ss:medicineInfo){
			String divide[]=ss.split(",");
			Medicine m=new Medicine(divide[0], Integer.parseInt(divide[1]));
			p.medicineQueue.add(m);	
		}
		myWaitPatient.add(p);
		
		waitString+=(property[2]+"\t"+property[3]+"\t"+property[4]+"\t"+property[5]+"\n");
		Store.wait.setText(waitString);
		
		if(myWaitPatient.size()==1){
			currentId=property[5];
			Store.sname.setText(property[2]);
			Store.ssex.setText(property[3]);
			Store.sage.setText(property[4]);
			Store.ID.setText(property[5]);
			showCurrentMedicineInfo();
		    Store.mark.setText("尚未付款");
		}
	}

	static public void showCurrentMedicineInfo() {
		String currentMedicineString="药品名字\t数量\n";
		if(myWaitPatient.size()!=0){
			for(Medicine m:myWaitPatient.get(0).medicineQueue){
			currentMedicineString+=(m.medicineName+"\t"+m.count+"\n");
		    }
		}		
		Store.content.setText(currentMedicineString);
	}
   
	static public void updatePaitenInfo(){
		waitString="名字\t性别\t年龄\t病人号码\n";
		for(StorePatientInfo mp:myWaitPatient){
			waitString+=(mp.name+"\t"+mp.sex+"\t"+mp.age+"\t"+mp.id+"\n");		
		}
		Store.wait.setText(waitString);
		//Store.currentString="";
		//sendMedicineInfo="";
		//Doctor.content.setText("");
	}
	
	static public void sendInfo(){
		StorePatientInfo p=myWaitPatient.get(0);
		String str="true,"+p.price+","+p.name+","+p.sex+","+p.age+","+p.id+"#";
		for(Medicine m:p.medicineQueue){
			str+=m.medicineName+","+m.count+"-";
		}
		pw.println(str);
		pw.flush();
		myWaitPatient.remove(0);
		
	}
	
	static public void nextFunctioin(){
		if(myWaitPatient.size()==0){
			return;
		}
		StorePatientInfo p=myWaitPatient.get(0);
		myWaitPatient.remove(0);
		myWaitPatient.add(p);
		updatePaitenInfo();
		showCurrentMedicineInfo();
		
		p=myWaitPatient.get(0);
		Store.sname.setText(p.name);
		Store.ssex.setText(p.sex);
		Store.sage.setText(p.age);
		Store.ID.setText(p.id);
		Store.mark.setText(p.isPay?"此病人已付款":"尚未付款");
		
	}
}
