package finalServer;

import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.*;

public class Server {

	static public class DoctorInfo{
		String account;
		String name;
		String department;
		int waitCount;
		ArrayList<String> patientQueue=new ArrayList<String>();	
	}
	
	public static class MedicineInfo{
		MedicineInfo(String name,int count,int price){
			this.name=name;
			this.count=count;
			this.price=price;
		}
		String name;
		int count;
		int price;
	}
	
	
	
	
	
	ServerSocket server;
	static Socket charge;
	static Socket store;
	static Socket client;
	static Socket president;
	static Socket admin;
	
	
	static int internalCount;//内科排队人数
	static int surgeryCount; //外科排队人数
	static int paediatricsCount; //外科排队人数
	static public HashMap<String,Socket> doctorSocket =new HashMap<String,Socket>();
	static public ArrayList<DoctorInfo> onlineDoctor=new ArrayList<DoctorInfo>();
    static public ArrayList<ShowThread> myShow=new ArrayList<ShowThread>();
    static public ArrayList<DoctorThread> myDoctor=new ArrayList<DoctorThread>();
	
	private String command;
	static public ResultSet rs=null;
	static public Statement st = null;
	static public Connection con;
	
	
	

	
	
	Server(){     
		//构造函数
		//加载数据库驱动程序
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			                   //驱动失败
			e.printStackTrace();
		}	
		//建立数据库连接
		String str="jdbc:sqlserver://localhost:1433;databaseName=medical";
		try {
			con = DriverManager.getConnection(str,"sa","199511");
			
		} catch (SQLException e) {
			                              //连接失败
			e.printStackTrace();
		}
		if(con!=null){
			System.out.println("数据库连接成功");
		}
		new SQLOperate(con);
		
	
		
		
		
		
		
		
		try {  
		    server=new ServerSocket(12000); //打开端口
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}	
	
	public void openServer(){
		
		while(true){  //死循环，一直等待socket连接
			try {
				client=server.accept();  
				System.out.println("已连接上");
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						addSoket(Server.client);				
					}
				}).start();
					
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
		

	private void addSoket(Socket c) {
		try {
			Socket client=c;
			//info=类型，账号，密码，
			BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
	    	while(true){
				String info=br.readLine();
				if(analysis(info,client)){//分析登录的信息
					break;
				}  
			}
			System.out.println("登陆成功，线程退出");
			
		} catch (IOException e) {
			        //登录不成功，连接失败
			e.printStackTrace();
		}
		
	}
	
	public boolean analysis(String info, Socket client){
		
		String[] string=info.split(",");
		if(string[0].equals("predict")){
			new AndroidThread(client).start();
			return true;
		}
		else if(string[0].equals("show")){
			
			ShowThread s=new ShowThread(client);
			s.start();
			myShow.add(s);
			return true;
		}
		String account=string[1];
		String password=string[2];
		PrintWriter pw;
		if(!SQLOperate.login(string[0],account,password)){// 登录，如果账号密码正确，则进入下一步开始线程
			
			
			try {
				pw = new PrintWriter(client.getOutputStream());
				pw.println("NO");
		    	pw.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return false;
		}
		
		try {
			pw = new PrintWriter(client.getOutputStream());
			String sign="OK,";	
			if(string[0].equals("doctor")){
				doctorSocket.put(string[1], client); 
				SQLOperate.addOnlineDoctorInfo(string[1]);                                     //update在线医生信息
				Server.sendMessageToShow();     //发信息给滚动屏
				//System.out.println("收到的账号名字"+string[1]);
				sign+=findDoctorName(string[1]);
			//	System.out.println("发送的名字"+sign);
			}
			pw.println(sign);
	    	pw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(string[0].equals("charge")){
			charge=client;
			System.out.println("开启线程");
			new ChargeThread(client).start();
			
		}		
		else if(string[0].equals("doctor")){
//			doctorSocket.put(string[1], client); 
//			 SQLOperate.addOnlineDoctorInfo(string[1]);                                     //update在线医生信息
//			 ShowThread.sendMessageToShow();
			DoctorThread d=new DoctorThread(string[1],client);
					d.start();  //开启医生线程
					myDoctor.add(d);
		}
		else if(string[0].equals("store")){
			store=client;
			new StoreThread(client).start();
		}
		else if(string[0].equals("president")){
			president=client;
			new PresidentThread(client).start();
		}
		else if(string[0].equals("admin")){
			admin=client;
			new AdminThread(client).start();
		}
		return true;
	}
	
	private String findDoctorName(String doctorAccount){
		//System.out.println("查找的账号"+doctorAccount);
		for(DoctorInfo d:onlineDoctor){
			if(d.account.equals(doctorAccount)){
				return (d.name+","+d.department);
			}
			
		}
		
		
		return null;	
	}
	
	
	static public void sendMessageToShow(){
		for(ShowThread s:myShow){
			s.sendMessageToShow();
		}
	}
	
	static public void sendMessageToPresident(){
		if(president!=null){
			PresidentThread.sendAllInfoToPresident();
		}
	}
	
	static public void sendMedicineInfoToDoctor(){
		for(DoctorThread d:myDoctor){
			d.sendInitMedicineInfo();
		}
	}
	
	static public void sendMessageToAdmin(){		
		if(admin!=null){
			AdminThread.sendAllInfoToAdmin();  //发送更新信息给管理员
		}
	}
	
	public static void main(String[] args) {
		Server server=new Server();
		server.openServer();  //打开服务器
	}
}

