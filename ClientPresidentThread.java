package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientPresidentThread extends Thread {
	public static class paediatricsDetail{
		String number;
		String totalNumber;
		String money;
	}
	public static class surgeryDetail{
		String number;
		String totalNumber;
		String money;
	}
	public static class internalDetail{
		String number;
		String totalNumber;
		String money;
	}
	public static class medicineDetail{
		String name;
		String count;
		String price;
	}
	public static class doctorDetail{
		String name;
		String department;
		String count;
		String income;
	}
	private Socket server;
	private PrintWriter pw;
    private BufferedReader br;
	ClientPresidentThread(Socket server){
		this.server=server;	 
		try {
			//System.out.println("shoudaooooo"); 
			br=new BufferedReader(new InputStreamReader(server.getInputStream()));
			pw=new PrintWriter(server.getOutputStream());
		} catch (IOException e) {
			            //与服务器连接失败
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		//System.out.println("admin从服务器收到的信息：");
		String message;
		try {
			while(true){
				message=br.readLine();
				System.out.println("admin从服务器收到的信息："+message);
				analysis(message);  //分析数据
				}
								
		} catch (IOException e) {
			           //连接失败
			e.printStackTrace();
		}
	}
	
	void analysis(String message)
	{
		String[] kind=message.split("-");
		if(kind[0].equals("paediatrics"))
		{
			String[] paediatrics=kind[1].split(",");
			paediatrics[0]="";
		}
		else if(kind[0].equals("surgery"))
		{
			
		}
		else if(kind[0].equals("internal"))
		{
			
		}
		else if(kind[0].equals("medicine"))
		{
			
		}
		else if(kind[0].equals("doctor"))
		{
			
		}
	}

}
